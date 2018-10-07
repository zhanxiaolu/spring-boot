package zxl.springboot.batch.domain;

import org.springframework.stereotype.Component;

/**
 * Created by xiao on 2018/10/6.
 */
@Component
public class Person {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private long id;

    private String name;

    private String sex;

    private int age;


}
