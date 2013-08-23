package com.github.commons.configuration.gae;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

/**
 * Represents a {@link Configuration} that stores configuration parameters using <i>Google App Engine</i> <a href="https://developers.google.com/appengine/docs/java/datastore/" >Datastore API</a>.
 */
public class ConfigurationGAE extends AbstractConfiguration {

	public static final String DEFAULT_ENTITY_KIND = "Configuration";
	public static final String DEFAULT_PROPERTY_VALUE = "value";
	
	private final DatastoreService datastoreService;
	private final String entityKind;
	private final String propertyValue;
	
	public ConfigurationGAE(DatastoreService datastoreService) {
		this(datastoreService, DEFAULT_ENTITY_KIND, DEFAULT_PROPERTY_VALUE);
	}

	public ConfigurationGAE(DatastoreService datastoreService, String entityKind, String propertyValue) {
		super();
		this.datastoreService = datastoreService;
		this.entityKind = entityKind;
		this.propertyValue = propertyValue;
	}

	@Override
	public boolean containsKey(String key) {
		Key datastoreKey = newKey(key);
		
		// TODO probably there is a more performant way...
		Iterable<Entity> iterable = datastoreService.prepare(new Query(entityKind).setKeysOnly()).asIterable();
		for (Entity entity : iterable) {
			if (entity.getKey().compareTo(datastoreKey) == 0)
				return true;
		}
		return false;
	}

	@Override
	public Iterator<String> getKeys() {
		Iterable<Entity> iterable = datastoreService.prepare(new Query(entityKind).setKeysOnly()).asIterable();
		List<String> list = new LinkedList<String>();
		for (Entity entity : iterable)
			list.add(entity.getKey().getName());
		return list.iterator();
	}

	@Override
	public Object getProperty(String key) {
		try {
			Entity entity = datastoreService.get(newKey(key));
			Object value = entity.getProperty(propertyValue);
			return value;
		} catch (EntityNotFoundException e) {
			if (isThrowExceptionOnMissing())
				throw new NoSuchElementException("key not found '" + key + "'");
			
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO change to increase performance
		return ! getKeys().hasNext();
	}

	@Override
	protected void addPropertyDirect(String key, Object value) {
		Entity entity = new Entity(newKey(key));
		entity.setProperty(propertyValue, value);
		datastoreService.put(entity);
	}

	protected Key newKey(String key) {
		return KeyFactory.createKey(entityKind, key);
	}

}
