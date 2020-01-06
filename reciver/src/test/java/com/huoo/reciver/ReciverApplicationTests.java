package com.huoo.reciver;

import com.huoo.reciver.reciver.SendMessageController;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReciverApplication.class)
class ReciverApplicationTests {

		@Resource
		SendMessageController sendMessageController;
		@Test
		void contextLoads() throws InterruptedException {
				sendMessageController.sendDirectMessage("");
				new CountDownLatch(1).await();
		}

}
