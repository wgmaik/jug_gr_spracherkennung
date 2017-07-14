package de.alexa.start.dialog.guice.modules;

import com.amazon.speech.speechlet.Speechlet;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import de.alexa.start.dialog.guice.interceptors.ExceptionLoggingInterceptor;

public class InterceptorModule extends AbstractModule {
	@Override
	protected void configure() {
		ExceptionLoggingInterceptor exceptionLoggingInterceptor = new ExceptionLoggingInterceptor();
		requestInjection(exceptionLoggingInterceptor);
		bindInterceptor(Matchers.subclassesOf(Speechlet.class), Matchers.any(), exceptionLoggingInterceptor);
	}
}
