package zxl.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import zxl.springboot.batch.domain.Person;

import java.util.List;

/**
 * Created by xiao on 2018/10/7.
 */
@Slf4j
@Component("PersonWriter")
class PersonWriter implements ItemWriter<Person> {
    @Override
    public void write(List<? extends Person> list) throws Exception {
        for (Person person : list) {
            log.info("write person : {} {}", person.getId(), person.getName());
        }
    }
}
