package com.sun.boot.activemq;

import com.sun.boot.activemq.produce.Queue_Produce;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = BootMqProduceApplication.class)
@WebAppConfiguration
class BootMqProduceApplicationTests {

    @Resource
    private Queue_Produce queue_produce;

    @Test
    void testSend() {
        queue_produce.produceMsg();
    }

}
