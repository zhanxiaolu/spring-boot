package zxl.springboot.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zxl.springboot.dao.Application;
import zxl.springboot.dao.domain.Person;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2018/10/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonServiceJdbcTest {
    @Resource
    private PersonServiceJdbc personServiceJdbc;

    @Test
    public void insertPersons() throws Exception {
        List<Person> personList = new ArrayList<Person>();
        Person person1 = new Person((long)1,"jame","m",20);
        Person person2 = new Person((long)2,"xiao","m",25);
        Person person3 = new Person((long)3,"burry","m",30);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personServiceJdbc.insertPersons(personList);
    }

}