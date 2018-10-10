package zxl.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import zxl.springboot.batch.domain.Person;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xiao on 2018/10/7.
 */
@Slf4j
@Component("PersonWriter")
class PersonWriter implements ItemWriter<Person> {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(List<? extends Person> list) throws Exception {
        log.info("write persons : {} {}", list.size());
        String sql = "insert into person(id,name,sex,age) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Person person = list.get(i);
                preparedStatement.setLong(1, person.getId());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getSex());
                preparedStatement.setInt(4, person.getAge());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }
}
