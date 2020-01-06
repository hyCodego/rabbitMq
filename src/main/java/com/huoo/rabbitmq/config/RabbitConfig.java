package com.huoo.rabbitmq.config;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Configuration
public class RabbitConfig {

		@Bean("myContainerFactory")
		public SimpleRabbitListenerContainerFactory myContainerFactory(
				SimpleRabbitListenerContainerFactoryConfigurer rabbitListenerContainerFactoryConfigurer,
				ConnectionFactory connectionFactory) {
				SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
				factory.setPrefetchCount(1);
				factory.setConcurrentConsumers(2);
				factory.setMaxConcurrentConsumers(3);
				rabbitListenerContainerFactoryConfigurer.configure(factory, connectionFactory);
				return factory;
		}



}
