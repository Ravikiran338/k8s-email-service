/**
 * 
 */
package com.sci.pulsar.service;

import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author mn259
 *
 */
public interface EmailConsumerListener {
	void messageFetched(String msg) throws PulsarClientException;
}
