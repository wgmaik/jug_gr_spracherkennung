package de.alexa.start.dialog.usecases;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletException;
import de.alexa.start.dialog.usecases.begruessung.BegruessungIntentHandler;
import de.alexa.start.dialog.usecases.common.IIntentHandler;
import de.alexa.start.dialog.usecases.common.NoActionIntentHandler;
import de.alexa.start.dialog.usecases.help.HelpIntentHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class IntentStrategyHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntentStrategyHandler.class);
	
	private final NoActionIntentHandler noActionIntentHandler;
	private final HelpIntentHandler helpIntentHandler;
	private final BegruessungIntentHandler begruessungIntentHandler;

	@Inject
	public IntentStrategyHandler(NoActionIntentHandler noActionIntentHandler,
								 HelpIntentHandler helpIntentHandler,
								 BegruessungIntentHandler begruessungIntentHandler) {
		this.noActionIntentHandler = noActionIntentHandler;
		this.helpIntentHandler = helpIntentHandler;
		this.begruessungIntentHandler = begruessungIntentHandler;
	}
	
	public IIntentHandler createAndLogHandler(IntentRequest request) throws SpeechletException {
		String intentName = StringUtils.defaultString(request.getIntent().getName());

		LOGGER.info("Got request for intent with name '{}'.", intentName);
		IIntentHandler handler = createHandler(intentName);
		LOGGER.info("Using intent handler of type '{}' to handle request.", handler.getClass().getName());
		
		return handler;
	}
	
	private IIntentHandler createHandler(String intentName) throws SpeechletException {
		switch (intentName) {
			case "AMAZON.HelpIntent":
				return helpIntentHandler;
			case "Begruessung":
				return begruessungIntentHandler;
			default:
				String message = String.format("Invalid Intent: %s", intentName);
				LOGGER.error(message);
				return noActionIntentHandler;
		}
	}
}
