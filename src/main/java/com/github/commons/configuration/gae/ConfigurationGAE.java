package com.github.commons.configuration.gae;

import java.util.Iterator;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Represents a {@link Configuration} that stores configuration parameters using <i>Google App Engine</i> <a href="https://developers.google.com/appengine/docs/java/datastore/" >Datastore API</a>.
 */
public class ConfigurationGAE extends AbstractConfiguration {

	@Override
	public boolean containsKey(String arg0) {
		// TODO implement
		return false;
	}

	@Override
	public Iterator<String> getKeys() {
		// TODO implement
		return null;
	}

	@Override
	public Object getProperty(String arg0) {
		// TODO implement
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO implement
		return false;
	}

	@Override
	protected void addPropertyDirect(String arg0, Object arg1) {
		// TODO implement
	}

}
