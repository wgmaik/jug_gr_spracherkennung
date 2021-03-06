package de.alexa.random.generator.usecases.common;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

public interface IIntentHandler {
	SpeechletResponse handleIntent(IntentRequest request, Session session);
}
