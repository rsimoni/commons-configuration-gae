package com.github.commons.configuration.gae;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;

public class ConfigurationGAETest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	private DatastoreService datastoreService = context.mock(DatastoreService.class);
	private ConfigurationGAE configuration = new ConfigurationGAE(datastoreService);
	
	@Test public void xxx() {
		Object value = configuration.getProperty("mykey");
	}

}
