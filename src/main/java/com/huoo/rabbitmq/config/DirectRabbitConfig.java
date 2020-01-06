package com.huoo.rabbitmq.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Configuration
public class DirectRabbitConfig {

		/**
		 *  创建队列
		 *  durable 是否持久化
		 * @return
		 */
		@Bean
		public Queue TestDirectQueue(){
				return new Queue("TestDirectQueue",true);
		}

		/**
		 * Direct交换机 起名：TestDirectExchange
		 */
		@Bean
		DirectExchange TestDirectExchange() {
				return new DirectExchange("TestDirectExchange");
		}

		/**
		 * 绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
		 */
		@Bean
		public Binding bindingDirect() {
				return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
		}

}
