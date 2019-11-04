/**
 * 
 */
package com.sci.services.account.pulsar;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sci.services.model.UserMsg;

/**
 * @author mn259
 *
 */
public class EmailConsumer {

	private static final Logger mLogger = LoggerFactory.getLogger(EmailConsumer.class);
	private static final String SERVICE_URL = "pulsar://34.206.196.97:6650";
	private static final String TOPIC_NAME = "user-topic";

	public void consumeUserMessage() throws PulsarClientException {

		EmailConsumer consumer = new EmailConsumer(SERVICE_URL, TOPIC_NAME);
		consumer.addListener(user -> {
			mLogger.info("Consumed message details :" + user.toString());
		//	new EmailService().sendMail(user);
			// consumer.close();
		});
		consumer.run();
	}

	public EmailConsumer() {
	}

	private String mServiceUrl;
	private String mTopicName;
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
			mLogger.info("Consumer acknowledge: " + msg);
			consumer.acknowledge(msg);
			mLogger.info("after Consumer acknowledge: " + msg);
			//String content = new String(msg.getData());
			UserMsg user = (UserMsg) SerializationUtils.deserialize(msg.getData());
			/*ByteArrayInputStream bis = new ByteArrayInputStream(msg.getData());
			ObjectInput in = new ObjectInputStream(bis);
			UserMsg user =(UserMsg) in.readObject();*/
			mLogger.info("Consumer UserMsg: " + user);
			mListener.messageFetched(user);
			mLogger.info("Consumer: " + user);
		} catch (IOException e) {
			mLogger.info("Consumer error: " + e.getMessage());
		}
	}
}