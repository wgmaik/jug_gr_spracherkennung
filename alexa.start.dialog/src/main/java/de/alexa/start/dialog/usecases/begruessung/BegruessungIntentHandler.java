package de.alexa.start.dialog.usecases.begruessung;


import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import de.alexa.start.dialog.guice.modules.PropertiesModule;
import de.alexa.start.dialog.usecases.common.IIntentHandler;
import de.alexa.start.dialog.usecases.common.factories.SpeechletResponseFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Named
public class BegruessungIntentHandler implements IIntentHandler{
    private final SpeechletResponseFactory speechletResponseFactory;
    private final ResourceBundle messageProperties;

    @Inject
    public BegruessungIntentHandler(SpeechletResponseFactory speechletResponseFactory,
                                    @Named(PropertiesModule.MESSAGE_PROPERTIES) ResourceBundle messageProperties) {
        this.speechletResponseFactory = speechletResponseFactory;
        this.messageProperties = messageProperties;
    }

    @Override
    public SpeechletResponse handleIntent(IntentRequest request, Session session) {
        String city = (String) session.getAttribute(BegruessungSessionConstants.SESSION_KEY_CITY);
        String personCount = (String) session.getAttribute(BegruessungSessionConstants.SESSION_KEY_PERSON_COUNT);

        if(city == null){
            Slot citySlot = request.getIntent().getSlot(BegruessungSessionConstants.SESSION_KEY_CITY);

            if(citySlot.getValue() == null || citySlot.getValue().equals("null")){
                return speechletResponseFactory.createPlainTellResponse(messageProperties.getString("alexa.response.begruessung.no.city"));
            }else{
                session.setAttribute(BegruessungSessionConstants.SESSION_KEY_CITY, citySlot.getValue());
                city = citySlot.getValue();
            }
        }

        if(personCount == null){
            Slot personCountSlot = request.getIntent().getSlot(BegruessungSessionConstants.SESSION_KEY_PERSON_COUNT);

            if(personCountSlot.getValue() == null || personCountSlot.getValue().equals("null")){
                String text = messageProperties.getString("alexa.response.begruessung.how.much.person");
                return speechletResponseFactory.createPlainAskResponse(String.format(text, city));
            }else{
                session.setAttribute(BegruessungSessionConstants.SESSION_KEY_PERSON_COUNT, personCountSlot.getValue());
                personCount = personCountSlot.getValue();
            }
        }

        String text = messageProperties.getString("alexa.response.begruessung.complete");
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return speechletResponseFactory.createSsmlAskResponse(String.format(text, personCount, date, city));
    }

}
