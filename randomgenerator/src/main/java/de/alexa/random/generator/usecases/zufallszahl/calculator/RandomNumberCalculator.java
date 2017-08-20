package de.alexa.random.generator.usecases.zufallszahl.calculator;

import de.alexa.random.generator.usecases.zufallszahl.model.Lottozahlen;

import javax.inject.Named;
import java.util.Random;

@Named
public class RandomNumberCalculator {

	public int calculateIntBetweenTwoNumbers(int start, int end) throws IllegalArgumentException{
		if(end < start){
			throw new IllegalArgumentException("start is greater then end");
		}

		Random random = new Random();

		return random.nextInt(end - start) + start;
	}

	public Lottozahlen calculateLottozahlen(){
		Lottozahlen lottozahlen = new Lottozahlen();

		for(int i = 0; i < 6; i++){
			lottozahlen.getNumbers().add(calculateIntBetweenTwoNumbers(1, 49));
		}

		lottozahlen.setSuperzahl(calculateIntBetweenTwoNumbers(0, 9));


		return lottozahlen;
	}
}
