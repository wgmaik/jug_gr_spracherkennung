package de.alexa.random.generator.usecases.common;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.random.generator.usecases.common.factories.SpeechletResponseFactory;

import javax.inject.Inject;

public class NoActionIntentHandler implements IIntentHandler {
	private final SpeechletResponseFactory speechletResponseFactory;

	@Inject
	public NoActionIntentHandler(SpeechletResponseFactory speechletResponseFactory) {
		this.speechletResponseFactory = speechletResponseFactory;
	}
	
	@Override
	public SpeechletResponse handleIntent(IntentRequest request, Session session) {
		return speechletResponseFactory.createNoActionResponse();
	}
}
