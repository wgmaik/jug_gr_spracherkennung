package de.alexa.random.generator.usecases.zufallszahl;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.random.generator.guice.modules.PropertiesModule;
import de.alexa.random.generator.usecases.common.IIntentHandler;
import de.alexa.random.generator.usecases.zufallszahl.calculator.RandomNumberCalculator;
import de.alexa.random.generator.usecases.common.factories.SpeechletResponseFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@Named
public class ZufallszahlBetweenIntentHandler implements IIntentHandler {

	private SpeechletResponseFactory speechletResponseFactory;
	private ResourceBundle messageProperties;
	private RandomNumberCalculator randomNumberCalculator;

	@Inject
	public ZufallszahlBetweenIntentHandler(SpeechletResponseFactory speechletResponseFactory,
										   @Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties,
										   RandomNumberCalculator randomNumberCalculator) {
		this.speechletResponseFactory = speechletResponseFactory;
		this.messageProperties = messageProperties;
		this.randomNumberCalculator = randomNumberCalculator;
	}

	@Override
	public SpeechletResponse handleIntent(IntentRequest request, Session session) {
		Slot startNumber = request.getIntent().getSlot("startNumber");
		Slot endNumber = request.getIntent().getSlot("endNumber");

		int startInt = Integer.getInteger(startNumber.getValue());
		int endInt = Integer.getInteger(endNumber.getValue());

		try{
			int random = randomNumberCalculator.calculateIntBetweenTwoNumbers(startInt, endInt);

			return speechletResponseFactory.createPlainTellResponse(String.format(messageProperties.getString("alexa.response.zufallzahl.between.tell"), String.valueOf(random)));
		}catch (IllegalArgumentException e){
			return speechletResponseFactory.createPlainTellResponse(messageProperties.getString("alexa.response.zufallzahl.between.start.greater.end.error"));
		}

	}
}
