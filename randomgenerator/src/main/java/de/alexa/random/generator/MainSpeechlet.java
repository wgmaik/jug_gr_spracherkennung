package de.alexa.random.generator;

import com.amazon.speech.speechlet.*;
import de.alexa.random.generator.guice.modules.PropertiesModule;
import de.alexa.random.generator.usecases.IntentStrategyHandler;
import de.alexa.random.generator.usecases.common.IIntentHandler;
import de.alexa.random.generator.usecases.common.factories.SpeechletResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

public class MainSpeechlet implements Speechlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainSpeechlet.class);
	
	private final IntentStrategyHandler intentStrategyHandler;
	private final SpeechletResponseFactory speechletResponseFactory;
	private final ResourceBundle messageProperties;

	@Inject
	public MainSpeechlet(IntentStrategyHandler intentStrategyHandler,
						 SpeechletResponseFactory speechletResponseFactory,
						 @Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties) {
		this.intentStrategyHandler = intentStrategyHandler;
		this.speechletResponseFactory = speechletResponseFactory;
		this.messageProperties = messageProperties;
	}
	
	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		LOGGER.info("Session started: {}", session.getSessionId());
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return speechletResponseFactory.createPlainAskResponse(messageProperties.getString("alexa.response.welcome"));
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		IIntentHandler handler = intentStrategyHandler.createAndLogHandler(request);
		return handler.handleIntent(request, session);
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
		LOGGER.info("Session ended: {}", session.getSessionId());
	}
}
