package de.alexa.random.generator.usecases.zufallszahl;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.random.generator.guice.modules.PropertiesModule;
import de.alexa.random.generator.usecases.common.IIntentHandler;
import de.alexa.random.generator.usecases.common.factories.SpeechletResponseFactory;
import de.alexa.random.generator.usecases.zufallszahl.calculator.RandomNumberCalculator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@Named
public class LottozahlenIntentHandler implements IIntentHandler {

	private RandomNumberCalculator randomNumberCalculator;
	private SpeechletResponseFactory speechletResponseFactory;
	private ResourceBundle messageProperties;

	@Inject
	public LottozahlenIntentHandler(RandomNumberCalculator randomNumberCalculator,
									SpeechletResponseFactory speechletResponseFactory,
									@Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties) {
		this.randomNumberCalculator = randomNumberCalculator;
		this.speechletResponseFactory = speechletResponseFactory;
		this.messageProperties = messageProperties;
	}

	@Override
	public SpeechletResponse handleIntent(IntentRequest request, Session session) {
		String responseText = String.format(messageProperties.getString("alexa.response.lottozahlen.tell"), randomNumberCalculator.calculateLottozahlen().toString());
		return speechletResponseFactory.createSsmlTellResponse(responseText);
	}
}
