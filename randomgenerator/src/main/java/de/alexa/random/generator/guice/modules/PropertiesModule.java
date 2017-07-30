package de.alexa.random.generator.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.util.ResourceBundle;

public class PropertiesModule extends AbstractModule {
	public static final String MESSAGES_BUNDLE_NAME = "resources.messages";
	
	public static final String MESSAGE_PROPERTIES = "MESSAGE_PROPERTIES";

	@Override
	protected void configure() {
		bind(ResourceBundle.class).annotatedWith(Names.named(MESSAGE_PROPERTIES)).toInstance(ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME));
	}
}
