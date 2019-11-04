/**
 * 
 */
package com.sci.services.account.pulsar;

import org.apache.pulsar.client.api.PulsarClientException;

import com.sci.services.model.UserMsg;

/**
 * @author mn259
 *
 */
public interface EmailConsumerListener {
	void messageFetched(UserMsg msg) throws PulsarClientException;
}
