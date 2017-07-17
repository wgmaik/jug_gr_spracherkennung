package de.alexa.start.dialog.usecases.begruessung;

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
public class BegruessungYesIntentHandler implements IIntentHandler {

    private final SpeechletResponseFactory speechletResponseFactory;
    private final ResourceBundle messageProperties;

    @Inject
    public BegruessungYesIntentHandler(SpeechletResponseFactory speechletResponseFactory,
                                       @Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties) {
        this.speechletResponseFactory = speechletResponseFactory;
        this.messageProperties = messageProperties;
    }

    @Override
    public SpeechletResponse handleIntent(IntentRequest request, Session session) {
        String city = (String) session.getAttribute(BegruessungSessionConstants.SESSION_KEY_CITY);
        String personCount = (String) session.getAttribute(BegruessungSessionConstants.SESSION_KEY_PERSON_COUNT);

        if(city != null && personCount != null){
            return speechletResponseFactory.createPlainTellResponse(messageProperties.getString("alexa.response.begruessung.yes.intent.answer"));
        }else{
            return speechletResponseFactory.createNoActionResponse();
        }
    }
}
