package zxl.springboot.dao.jdbc;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zxl.springboot.dao.domain.Person;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xiao on 2018/10/10.
 */
@Service
public class PersonServiceJdbc {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void insertPersons(List<Person> persons) {
        String sql = "insert into person(id,name,sex,age) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Person person = persons.get(i);
                preparedStatement.setLong(1, person.getId());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getSex());
                preparedStatement.setInt(4, person.getAge());
            }

            @Override
            public int getBatchSize() {
                return persons.size();
            }
        });
    }

}
