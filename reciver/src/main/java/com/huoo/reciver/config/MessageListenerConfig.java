package com.huoo.reciver.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CompositeConnectionListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyun
 * @date 2020/1/3
 */
@Configuration
public class MessageListenerConfig {


		@Bean(name = "myFactory")
		public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
				SimpleRabbitListenerContainerFactoryConfigurer configurer,
				ConnectionFactory connectionFactory) {
				SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
				factory.setConcurrentConsumers(3);
				factory.setMaxConcurrentConsumers(4);
				factory.setPrefetchCount(5);
				connectionFactory.addConnectionListener(new CompositeConnectionListener());
				configurer.configure(factory,connectionFactory);
				return factory;
		}

		@Bean(name = "myRabbitAdmin")
		public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
				return new RabbitAdmin(connectionFactory);
		}

}
