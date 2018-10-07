package zxl.springboot.rest.domain;

/**
 * Created by xiao on 2018/8/27.
 */
public class Person {
    private String id;

    public Person(){};
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
