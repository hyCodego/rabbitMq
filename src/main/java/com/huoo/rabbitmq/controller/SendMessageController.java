package com.huoo.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Slf4j
@RestController
public class SendMessageController {
		/**
		 * 使用RabbitTemplate,这提供了接收/发送等等方法
		 */
		@Resource
		RabbitTemplate rabbitTemplate;

		@GetMapping("/sendDirectMessage/{msg}")
		public String sendDirectMessage(@PathVariable String msg) {

						String info=JSONObject.toJSONString("我是延迟消息");
						rabbitTemplate.convertAndSend("ef_msg", "kf_msg", info, message -> {
								message.getMessageProperties().setDelay(60000);
								log.info("延迟消息发送时间---》{}",LocalDateTime.now());
								return message;
						});
				return "ok";
		}

}
