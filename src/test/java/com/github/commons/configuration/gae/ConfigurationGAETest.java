package com.github.commons.configuration.gae;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ConfigurationGAETest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
		setThreadingPolicy(new Synchroniser());
	}};
	private DatastoreService datastoreService = context.mock(DatastoreService.class);
	private ConfigurationGAE configuration = new ConfigurationGAE(datastoreService);
	
	@Test public void getProperty_uses_datastore_get_call_to_retrieve_an_entity_related_to_specified_configuration_parameter() throws EntityNotFoundException {
		final String configurationParameter = "mykey";
		final Key key = KeyFactory.createKey("Configuration", configurationParameter);
		final Entity anEntity = new Entity(key);
		
		context.checking(new Expectations() {{ 
			oneOf(datastoreService).get(with(any(Key.class)));
				will(returnValue(anEntity));
		}});
		
		Object value = configuration.getProperty(configurationParameter);
		Assert.assertNotNull(value);
	}

}
