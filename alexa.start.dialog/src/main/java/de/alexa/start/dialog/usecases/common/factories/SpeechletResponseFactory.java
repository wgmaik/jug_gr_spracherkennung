package de.alexa.start.dialog.usecases.common.factories;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class SpeechletResponseFactory {
	public SpeechletResponse createNoActionResponse() {
		SpeechletResponse speechletResponse = new SpeechletResponse();
		speechletResponse.setShouldEndSession(true);
		return speechletResponse;
	}
	
	public SpeechletResponse createPlainTellResponse(String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle(speechText);
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    public SpeechletResponse createPlainAskResponse(String speechText) {
		PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
		outputSpeech.setText(speechText);

		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(outputSpeech);

		return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
	}

	public SpeechletResponse createSsmlAskResponse(String ssmlSpeechText) {
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech(); //Speech Synthesis Markup Language
		ssmlOutputSpeech.setSsml(ssmlSpeechText);

		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(ssmlOutputSpeech);

		return SpeechletResponse.newAskResponse(ssmlOutputSpeech, reprompt);
	}
}
