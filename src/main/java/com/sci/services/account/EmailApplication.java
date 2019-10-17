package com.sci.services.account;

import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.sci.pulsar.service.EmailConsumer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient

public class EmailApplication implements CommandLineRunner{
	private static final String SERVICE_URL = "pulsar://34.206.196.97:6650";
	private static final String TOPIC_NAME = "user-topic";
	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}
	@Override
	public void run(String... args){
		try {
			new EmailConsumer(SERVICE_URL,TOPIC_NAME).consumeUserMessage();
		} catch (PulsarClientException e) {
			e.printStackTrace();
		}
	}

}
