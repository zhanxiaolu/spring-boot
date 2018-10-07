package zxl.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;
import zxl.springboot.batch.domain.Person;

/**
 * Created by xiao on 2018/10/7.
 */
@Slf4j
@Component
public class PersonStepListener {
    @OnSkipInRead
    public void onSkipInRead(Throwable throwable) {
        log.error("personstep onSkipInRead:{}", throwable.getMessage());
    }

    @OnSkipInProcess
    public void onSkipInProcess(Person person, Throwable throwable) {
        log.error("personstep onSkipInProcess:{}", person.getName());
    }

    @OnSkipInWrite
    public void onSkipInWrite(Person person, Throwable throwable) {
        log.error("personstep onSkipInWrite:{}", person.getName());
    }

}
