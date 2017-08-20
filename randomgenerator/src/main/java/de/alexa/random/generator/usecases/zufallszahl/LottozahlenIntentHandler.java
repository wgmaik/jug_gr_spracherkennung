package de.alexa.random.generator.usecases.zufallszahl;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.random.generator.usecases.common.IIntentHandler;

import javax.inject.Named;

@Named
public class LottozahlenIntentHandler implements IIntentHandler {
	@Override
	public SpeechletResponse handleIntent(IntentRequest request, Session session) {
		return null;
	}
}
