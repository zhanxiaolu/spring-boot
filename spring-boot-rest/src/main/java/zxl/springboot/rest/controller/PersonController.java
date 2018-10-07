package zxl.springboot.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import zxl.springboot.rest.domain.Person;

import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@RestController
public class PersonController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/person/{id}",method = RequestMethod.POST)
    public String getPersonId(@PathVariable("id") Long id) {
        log.info("request person/{}",id);
        return id+"";

    }
    @RequestMapping(value = "/person/new",method = RequestMethod.POST)
    public Person createPerson(@RequestBody Person person) {
        return person;
    }
    @RequestMapping( value = "/person",produces={"text/html;charset=UTF-8"},method = RequestMethod.POST)
    public String modifyPerson(@RequestBody String body) {
        log.info(body);
        return body;
    }

}
