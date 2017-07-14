package de.alexa.start.dialog;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.google.common.collect.Sets;
import com.google.inject.Injector;
import de.alexa.start.dialog.guice.ApplicationInitializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class MainRequestStreamHandler extends SpeechletRequestStreamHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainRequestStreamHandler.class);
	
	private static final String ACCEPTED_APPLICATION_IDS = "acceptedApplicationIds";

	public MainRequestStreamHandler() {
		super(createMainSpeechlet(), calculateAcceptedApplicationIds());
	}

	private static MainSpeechlet createMainSpeechlet() {
		Injector injector = ApplicationInitializer.initialize();
		return injector.getInstance(MainSpeechlet.class);
	}
	
	private static Set<String> calculateAcceptedApplicationIds() {
		String acceptedApplicationIds = System.getenv(ACCEPTED_APPLICATION_IDS);
		if (StringUtils.isBlank(acceptedApplicationIds)) {
			LOGGER.error("Lambda configuration invalid. Please provide an environment key-value pair containing the '{}' key.", ACCEPTED_APPLICATION_IDS);
			return Sets.newHashSet();
		}
		
		return Sets.newHashSet(acceptedApplicationIds.split(","));
	}
}
