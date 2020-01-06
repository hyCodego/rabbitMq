package com.huoo.reciver.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyun
 * @date 2020/1/6
 */
@Configuration
public class DelayConfig {



		public static final String NORMAL_QUEUE_NAME = "normal.queue";
		public static final String NORMAL_EXCHANGE_NAME = "normal.exchange";
		public static final String NORMAL_ROUTING_KEY = "normal.routing.key";

		public static final String TTL_QUEUE_NAME = "ttl.queue";
		public static final String TTL_EXCHANGE_NAME = "ttl.exchange";
		public static final String TTL_ROUTING_KEY = "ttl.routing.key";

		@Bean
		public Queue queue(){
				return new Queue(NORMAL_QUEUE_NAME);
		}

		@Bean
		public DirectExchange directExchange(){
				return new DirectExchange(NORMAL_EXCHANGE_NAME);
		}

		@Bean
		public Binding binding()
		{
				return BindingBuilder.
						bind(queue()).
						to(directExchange())
						.with(NORMAL_ROUTING_KEY);
		}


		@Bean
		public Queue ttlQueue(){
				return QueueBuilder.durable(TTL_QUEUE_NAME)
						.withArgument("x-dead-letter-exchange", NORMAL_EXCHANGE_NAME)// 到期后转发的交换机
						.withArgument("x-dead-letter-routing-key", NORMAL_ROUTING_KEY)// 到期后转发的交换机对应的路由key
						.build();
		}

		@Bean
		public DirectExchange ttlDirectExchange(){
				return new DirectExchange(TTL_EXCHANGE_NAME);
		}

		@Bean
		public Binding ttlBinding()
		{
				return BindingBuilder.
						bind(ttlQueue()).
						to(ttlDirectExchange())
						.with(TTL_ROUTING_KEY);
		}


}
