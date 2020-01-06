package com.huoo.reciver.reciver;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Component
@Slf4j
public class FanouttReceiver{

		@RabbitListener(containerFactory="myFactory",bindings = @QueueBinding(
				value = @Queue(value = "qf1_msg", durable = "true"),
				exchange = @Exchange(value = "ef_msg", type = ExchangeTypes.FANOUT,delayed = "true"),
				key = "kf_msg"),admin = "myRabbitAdmin")
		@RabbitHandler
		public void receive1(String msg) {
				log.error("[receive1 ]延迟消息接受时间： {} <--> 接受消息内容: {}", LocalDateTime.now(), JSONObject.toJSONString(msg));
		}

		@RabbitListener(containerFactory="myFactory",bindings = @QueueBinding(
				value = @Queue(value = "qf2_msg", durable = "true"),
				exchange = @Exchange(value = "ef_msg", type = ExchangeTypes.FANOUT,delayed = "true"),
				key = "kf_msg"),admin = "myRabbitAdmin")
		@RabbitHandler
		public void receive2(String msg) {
				log.error("[receive2 ]延迟消息接受时间： {} <--> 接受消息内容: {}", LocalDateTime.now(), JSONObject.toJSONString(msg));
		}

}
