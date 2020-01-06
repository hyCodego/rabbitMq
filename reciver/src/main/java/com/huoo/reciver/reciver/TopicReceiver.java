package com.huoo.reciver.reciver;

import com.alibaba.fastjson.JSONObject;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Component
@Slf4j
public class TopicReceiver {

		@RabbitListener(containerFactory="myFactory",bindings = @QueueBinding(
				value = @Queue(value = "q_msg", durable = "true"),
				exchange = @Exchange(value = "e_msg", type = ExchangeTypes.TOPIC,delayed = "true"),
				key = "k_msg"),admin = "myRabbitAdmin")
		@RabbitHandler
		public void receive(String msg) {
				log.error("延迟消息接受时间： {} <--> 接受消息内容: {}", LocalDateTime.now(),JSONObject.toJSONString(msg));
		}

}
