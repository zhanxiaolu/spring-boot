package zxl.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import zxl.springboot.batch.domain.Person;

/**
 * Created by xiao on 2018/10/6.
 */
@Slf4j
@Component("PersonProcessor")
public class PersonProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        person.setId(System.currentTimeMillis());
        log.info("PersonProcessor:{}", person.toString());
        return person;
    }
}
