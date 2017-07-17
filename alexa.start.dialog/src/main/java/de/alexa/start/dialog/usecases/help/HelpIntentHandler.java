package de.alexa.start.dialog.usecases.help;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.start.dialog.guice.modules.PropertiesModule;
import de.alexa.start.dialog.usecases.common.IIntentHandler;
import de.alexa.start.dialog.usecases.common.factories.SpeechletResponseFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@Named
public class HelpIntentHandler implements IIntentHandler{

	private final SpeechletResponseFactory speechletResponseFactory;
	private final ResourceBundle messageProperties;

	@Inject
	public HelpIntentHandler(SpeechletResponseFactory speechletResponseFactory,
							 @Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties) {
		this.speechletResponseFactory = speechletResponseFactory;
		this.messageProperties = messageProperties;
	}

	@Override
	public SpeechletResponse handleIntent(IntentRequest request, Session session) {
		return speechletResponseFactory.createPlainAskResponse(messageProperties.getString("alexa.response.help"));
	}
}
