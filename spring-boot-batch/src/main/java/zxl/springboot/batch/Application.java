package zxl.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Date;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:job.xml")
@ComponentScan("zxl.springboot")
public class Application {

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        JobRegistry jobRegistry = context.getBean(JobRegistry.class);
        Job job = null;
        try {
            job = jobRegistry.getJob("personJob");
//            job = jobRegistry.getJob("personJobxml");
        } catch (NoSuchJobException e) {
            log.error("no such job,", e);
            System.exit(1);
        }
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        JobExecution jobExecution = jobLauncher.run(job, (new JobParametersBuilder()).addDate("date", new Date()).toJobParameters());
        if (!jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
            log.error("{} Job execution failed.");
        }
        log.info(jobExecution.toString());
    }
}
