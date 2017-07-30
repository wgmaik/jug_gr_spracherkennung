package de.alexa.random.generator.guice.modules;

import com.amazon.speech.speechlet.Speechlet;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import de.alexa.random.generator.guice.interceptors.ExceptionLoggingInterceptor;

public class InterceptorModule extends AbstractModule {
	@Override
	protected void configure() {
		ExceptionLoggingInterceptor exceptionLoggingInterceptor = new ExceptionLoggingInterceptor();
		requestInjection(exceptionLoggingInterceptor);
		bindInterceptor(Matchers.subclassesOf(Speechlet.class), Matchers.any(), exceptionLoggingInterceptor);
	}
}
