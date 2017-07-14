package de.alexa.start.dialog.guice.interceptors;

import com.amazon.speech.speechlet.SpeechletException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLoggingInterceptor implements MethodInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggingInterceptor.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			return invocation.proceed();
		} catch (SpeechletException e) {
			throw e;
		} catch (Throwable e) {
			String message = String.format("Unhandled error occured for invocation '%s'.", invocation.getMethod());
			LOGGER.error(message, e);
			throw e;
		}
	}
}
