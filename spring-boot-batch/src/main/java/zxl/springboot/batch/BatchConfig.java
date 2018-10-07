package zxl.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import zxl.springboot.batch.domain.Person;

import javax.annotation.Resource;

/**
 * EnableBatchProcessing自动创建以下bean
 * JobRepositorybean 名称 "jobRepository"
 * JobLauncher bean名称"jobLauncher"
 * JobRegistry bean名称"jobRegistry"
 * PlatformTransactionManager bean名称 "transactionManager"
 * jobExplorer bean名称 "jobExplorer"
 * JobBuilderFactory bean名称"jobBuilders"
 * StepBuilderFactory bean名称"stepBuilders"
 * Created by xiao on 2018/10/6.
 */

@Slf4j
@Configuration
@EnableBatchProcessing(modular = true)
public class BatchConfig {
    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    @Resource
    private PersonStepListener personStepListener;
    @Resource
    private PersonProcessor personProcessor;
    @Resource
    private PersonWriter personWriter;

    /**
     * 自动注册job
     * @param jobRegistry
     * @return
     */
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }
    @Bean
    public Job job() {
        return jobBuilderFactory.get("personJob")
                .incrementer(new RunIdIncrementer())
                .start(personStep()).build();
    }
    @Bean
    public ItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>(); //1
        reader.setResource(new FileSystemResource("spring-boot-batch/data/person.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "sex", "age"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public Step personStep() {
        return stepBuilderFactory.get("personStep")
                .<Person, Person>chunk(1)
                .reader(reader())
                .processor(personProcessor)
                .writer(personWriter)
                .faultTolerant()
                .skipLimit(1)
                .skip(Exception.class)

                .listener(personStepListener)
                .build();
    }
}
