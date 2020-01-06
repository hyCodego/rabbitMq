package com.huoo.rabbitmq;

import com.huoo.rabbitmq.controller.SendMessageController;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RabbitmqApplicationTests {

		@Resource
		SendMessageController sendMessageController;

		@Test
		void contextLoads() {
				sendMessageController.sendDirectMessage("ddd");
		}

}
