package de.alexa.random.generator.usecases;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletException;
import de.alexa.random.generator.usecases.common.IIntentHandler;
import de.alexa.random.generator.usecases.common.NoActionIntentHandler;
import de.alexa.random.generator.usecases.help.HelpIntentHandler;
import de.alexa.random.generator.usecases.zufallszahl.LottozahlenIntentHandler;
import de.alexa.random.generator.usecases.zufallszahl.WuerfelnIntentHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class IntentStrategyHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntentStrategyHandler.class);

	private final NoActionIntentHandler noActionIntentHandler;
	private final HelpIntentHandler helpIntentHandler;
	private LottozahlenIntentHandler lottozahlenIntentHandler;
	private WuerfelnIntentHandler wuerfelnIntentHandler;

	@Inject
	public IntentStrategyHandler(NoActionIntentHandler noActionIntentHandler,
								 HelpIntentHandler helpIntentHandler,
								 LottozahlenIntentHandler lottozahlenIntentHandler,
								 WuerfelnIntentHandler wuerfelnIntentHandler) {
		this.noActionIntentHandler = noActionIntentHandler;
		this.helpIntentHandler = helpIntentHandler;
		this.lottozahlenIntentHandler = lottozahlenIntentHandler;
		this.wuerfelnIntentHandler = wuerfelnIntentHandler;
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
			case "Lottozahlen":
				return lottozahlenIntentHandler;
			case "Wuerfeln":
				return wuerfelnIntentHandler;
			default:
				String message = String.format("Invalid Intent: %s", intentName);
				LOGGER.error(message);
				return noActionIntentHandler;
		}
	}
}
