/**
 * 
 */
package com.sci.pulsar.service;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.email.services.util.EmailUtil;

/**
 * @author mn259
 *
 */
public class EmailConsumer {
	
	private static final Logger mLogger = LoggerFactory.getLogger(EmailConsumer.class);
	private static final String SERVICE_URL = "pulsar://34.206.196.97:6650";
	private static final String TOPIC_NAME = "user-topic";

	public static void consumeUserMessage() throws PulsarClientException {

		EmailConsumer consumer = new EmailConsumer(SERVICE_URL, TOPIC_NAME);
		consumer.addListener(msg -> {
				mLogger.info(msg.toString());
				new EmailUtil().prepareEmail(msg);
				consumer.close();
		});
		consumer.run();
	}

	private final String mServiceUrl;
	private final String mTopicName;
	private Consumer<byte[]> mConsumer;
	private EmailConsumerListener mListener;

	public EmailConsumer(String serviceUrl, String topicName) {
		mServiceUrl = serviceUrl;
		mTopicName = topicName;
	}

	public void addListener(EmailConsumerListener listener) {
		mListener = listener;
	}

	void run() throws PulsarClientException {
		assert mListener == null;
		mLogger.info("Instantiating consumer...");
		mConsumer = initClient().newConsumer().topic(mTopicName).subscriptionType(SubscriptionType.Shared)
				.subscriptionName(mTopicName).messageListener(this::readMessage).subscribe();
	}

	void close() throws PulsarClientException {
		mConsumer.close();
		mLogger.info("Consuner is closed");
	}

	private PulsarClient initClient() throws PulsarClientException {
		return PulsarClient.builder().serviceUrl(mServiceUrl).build();
	}

	@SuppressWarnings("rawtypes")
	private void readMessage(Consumer<byte[]> consumer, Message msg) {
		try {
			consumer.acknowledge(msg);
			String content = new String(msg.getData());
			mListener.messageFetched(content);
			mLogger.info("Consumer: " + content);
		} catch (PulsarClientException e) {
			mLogger.info("Consumer error: " + e.getMessage());
		}
	}
}