package com.github.commons.configuration.gae;

import java.util.Iterator;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;

import com.google.appengine.api.datastore.DatastoreService;

/**
 * Represents a {@link Configuration} that stores configuration parameters using <i>Google App Engine</i> <a href="https://developers.google.com/appengine/docs/java/datastore/" >Datastore API</a>.
 */
public class ConfigurationGAE extends AbstractConfiguration {

	private final DatastoreService datastoreService;
	
	public ConfigurationGAE(DatastoreService datastoreService) {
		super();
		this.datastoreService = datastoreService;
	}

	@Override
	public boolean containsKey(String key) {
		// TODO implement
		return false;
	}

	@Override
	public Iterator<String> getKeys() {
		// TODO implement
		return null;
	}

	@Override
	public Object getProperty(String key) {
		// TODO implement
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO implement
		return false;
	}

	@Override
	protected void addPropertyDirect(String key, Object value) {
		// TODO implement
	}

}
