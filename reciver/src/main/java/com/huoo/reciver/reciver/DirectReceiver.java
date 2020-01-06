package com.huoo.reciver.reciver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Component
@RabbitListener(queues = "normal.queue")
public class DirectReceiver  {

		@RabbitHandler
		public void handle(String msg){
				System.out.println("receiver:" + msg + "_" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		}

}
