/**
 * 
 */
package com.sci.services.account.pulsar;

import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author mn259
 *
 */
public interface EmailConsumerListener {
	void messageFetched(String msg) throws PulsarClientException;
}
