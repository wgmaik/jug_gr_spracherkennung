package de.alexa.random.generator.guice;

import com.amazonaws.services.lambda.runtime.log4j.LambdaAppender;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.alexa.random.generator.guice.modules.InterceptorModule;
import de.alexa.random.generator.guice.modules.LambdaEnvironmentModule;
import de.alexa.random.generator.guice.modules.PropertiesModule;
import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInitializer.class);
	
	private static final String LOGGER_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} <%X{AWSRequestId}> %-5p %c{1}:%L - %m%n";
	
	public static Injector initialize() {
		configureLogger();
		
		LOGGER.info("Creating application injector.");
		return Guice.createInjector(
				new InterceptorModule(),
				new LambdaEnvironmentModule(),
				new PropertiesModule());
	}
	
	private static void configureLogger() {
		LambdaAppender appender = new LambdaAppender();
		appender.setLayout(new PatternLayout(LOGGER_PATTERN));
		org.apache.log4j.Logger.getRootLogger().addAppender(appender);
	}
}
