package com.huoo.reciver.reciver;

import com.alibaba.fastjson.JSONObject;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

				for (int i=1;i<3;i++){
						String info=JSONObject.toJSONString("我是延迟消息"+(i));
						if (i==1){
								rabbitTemplate.convertAndSend("ttl.exchange", "ttl.routing.key", info, message -> {
										message.getMessageProperties().setExpiration(String.valueOf(30*1000));
										log.info("延迟消息发送时间---》{}",LocalDateTime.now());
										return message;
								});
						}else {
								rabbitTemplate.convertAndSend("ttl.exchange", "ttl.routing.key", info, message -> {
										message.getMessageProperties().setExpiration(String.valueOf(20*1000));
										log.info("延迟消息发送时间---》{}",LocalDateTime.now());
										return message;
								});
						}

				}

				return "ok";
		}

}
