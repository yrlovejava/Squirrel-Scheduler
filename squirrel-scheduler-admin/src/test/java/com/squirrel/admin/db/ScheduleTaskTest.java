package com.squirrel.admin.db;

import com.squirrel.admin.dao.entity.ScheduleTaskDO;
import com.squirrel.admin.dao.mapper.ScheduleTaskMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The class is used to test the schedule task.
 */
@SpringBootTest
public class ScheduleTaskTest {

    @Resource
    private ScheduleTaskMapper scheduleTaskMapper;

    @Test
    public void testSaveScheduleTask() {
        ScheduleTaskDO task = ScheduleTaskDO.builder()
                .name("Test Task")
                .cron("0 0 12 * * ?")
                .description("Test Description")
                .schedulerId(1)
                .emailList("test1@example.com,test2@example.com")
                .author("Test Author")
                .status(0)
                .build();
        scheduleTaskMapper.insert(task);
    }
}
