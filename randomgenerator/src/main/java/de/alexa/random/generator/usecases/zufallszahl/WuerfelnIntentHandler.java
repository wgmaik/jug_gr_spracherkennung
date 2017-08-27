package de.alexa.random.generator.usecases.zufallszahl;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.random.generator.guice.modules.PropertiesModule;
import de.alexa.random.generator.usecases.common.IIntentHandler;
import de.alexa.random.generator.usecases.common.factories.SpeechletResponseFactory;
import de.alexa.random.generator.usecases.zufallszahl.calculator.RandomNumberCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@Named
public class WuerfelnIntentHandler implements IIntentHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(WuerfelnIntentHandler.class);

	private RandomNumberCalculator randomNumberCalculator;
	private SpeechletResponseFactory speechletResponseFactory;
	private ResourceBundle messageProperties;

	@Inject
	public WuerfelnIntentHandler(RandomNumberCalculator randomNumberCalculator,
								 SpeechletResponseFactory speechletResponseFactory,
								 @Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties) {
		this.randomNumberCalculator = randomNumberCalculator;
		this.speechletResponseFactory = speechletResponseFactory;
		this.messageProperties = messageProperties;
	}

	@Override
	public SpeechletResponse handleIntent(IntentRequest request, Session session) {
		Slot seitenSlot = request.getIntent().getSlot("seiten");

		int seiten = 6;
		if (seitenSlot.getValue() != null && !seitenSlot.getValue().isEmpty()) {
			try {
				LOGGER.debug("Seiten Slot value: " + seitenSlot.getValue());
				seiten = Integer.valueOf(seitenSlot.getValue());
			} catch (Exception e) {
				return speechletResponseFactory.createPlainAskResponse(messageProperties.getString("alexa.response.wuerfeln.seiten.missed"));
			}
		}

		try {
			int number = randomNumberCalculator.calculateIntBetweenTwoNumbers(1, seiten);
			return speechletResponseFactory.createPlainTellResponse(String.format(messageProperties.getString("alexa.response.wuerfeln.tell"), number));
		} catch (IllegalArgumentException e) {
			return speechletResponseFactory.createPlainTellResponse(messageProperties.getString("alexa.response.wuerfeln.seiten.lower.one"));
		}
	}
}
