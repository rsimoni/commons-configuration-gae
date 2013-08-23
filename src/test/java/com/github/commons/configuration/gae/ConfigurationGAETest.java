package com.github.commons.configuration.gae;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ConfigurationGAETest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before public void setup() {
		helper.setUp();
	}
	
	@After public void teardown() {
		helper.tearDown();
	}
	
	private ConfigurationGAE configuration = new ConfigurationGAE(DatastoreServiceFactory.getDatastoreService());
	
	@Test public void getString_returns_previously_inserted_string() {
		String aKey = "name";
		String aValue = "John";
		
		configuration.setProperty(aKey, aValue);
		Assert.assertEquals(aValue, configuration.getString(aKey));
	}

	@Test public void getInt_returns_previously_inserted_int() {
		String aKey = "age";
		int aValue = 37;
		
		configuration.setProperty(aKey, aValue);
		Assert.assertEquals(aValue, configuration.getInt(aKey));
	}

	@Test public void getString_returns_null_when_key_does_not_exist() {
		Assert.assertNull(configuration.getString("unexistant key"));
	}

	@Test(expected=NoSuchElementException.class)
	public void getString_throws_an_error_when_key_does_not_exist_and_configuration_is_set_to_thrown_errors_in_these_situations() {
		configuration.setThrowExceptionOnMissing(true);
		configuration.getString("unexistant key");
	}

	@Test public void containsKey_returns_true() {
		String aKey = "name";
		String aValue = "John";
		
		configuration.setProperty(aKey, aValue);
		Assert.assertTrue(configuration.containsKey(aKey));
	}

	@Test public void containsKey_returns_false() {
		String aKey = "name";
		
		Assert.assertFalse(configuration.containsKey(aKey));
	}

	@Test public void getKeys_returns_all_keys_previously_inserted() {
		String key1 = "name";
		String key2 = "lastname";
		
		configuration.setProperty(key1, "John");
		configuration.setProperty(key2, "Johnson");
		
		assertIteratorContainsKey(configuration.getKeys(), key1);
		assertIteratorContainsKey(configuration.getKeys(), key2);
	}

	@Test public void isEmpty_returns_true() {
		Assert.assertTrue(configuration.isEmpty());
	}
	
	@Test public void isEmpty_returns_false() {
		configuration.setProperty("name", "John");
		Assert.assertFalse(configuration.isEmpty());
	}
	
	private void assertIteratorContainsKey(Iterator<String> keys, String key) {
		while (keys.hasNext()) {
			String currKey = keys.next();
			if (currKey.equals(key))
				return;
		}
		Assert.fail("key not found '" + key + "'");
	}

}
