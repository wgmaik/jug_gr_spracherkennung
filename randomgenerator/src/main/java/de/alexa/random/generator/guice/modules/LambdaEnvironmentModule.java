package de.alexa.random.generator.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.alexa.random.generator.guice.provider.DynamicSystemEnvironmentProvider;

public class LambdaEnvironmentModule extends AbstractModule {
	public static final String NIIIO_HTTPBASIC_USERNAME = "niiioHttpbasicUsername";
	public static final String NIIIO_HTTPBASIC_PASSWORD = "niiioHttpbasicPassword";
	public static final String NIIIO_CONTEXT = "niiioContextUrl";
	
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named(NIIIO_HTTPBASIC_USERNAME)).toProvider(new DynamicSystemEnvironmentProvider(NIIIO_HTTPBASIC_USERNAME, true));
		bind(String.class).annotatedWith(Names.named(NIIIO_HTTPBASIC_PASSWORD)).toProvider(new DynamicSystemEnvironmentProvider(NIIIO_HTTPBASIC_PASSWORD, true));
		bind(String.class).annotatedWith(Names.named(NIIIO_CONTEXT)).toProvider(new DynamicSystemEnvironmentProvider(NIIIO_CONTEXT, false));
	}
}
