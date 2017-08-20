package de.alexa.random.generator.usecases.zufallszahl.calculator;

import de.alexa.random.generator.usecases.zufallszahl.model.Lottozahlen;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RandomNumberCalculatorTest {
	private RandomNumberCalculator randomNumberCalculator;

	@Before
	public void setUp() throws Exception {
		randomNumberCalculator = new RandomNumberCalculator();
	}

	@Test
	public void calculateIntBetweenTwoNumbers() throws Exception {
		int random = randomNumberCalculator.calculateIntBetweenTwoNumbers(2, 5);

		assertThat(random >= 2, equalTo(true));
		assertThat(random <= 5, equalTo(true));
	}

	@Test
	public void calculateLottozahlen() throws Exception {
		Lottozahlen lottozahlen = randomNumberCalculator.calculateLottozahlen();

		for(Integer number : lottozahlen.getNumbers()){
			assertThat(number >= 1, equalTo(true));
			assertThat(number <= 49, equalTo(true));
		}

		assertThat(lottozahlen.getSuperzahl() >= 0, equalTo(true));
		assertThat(lottozahlen.getSuperzahl() <= 9, equalTo(true));

		String string = String.valueOf(lottozahlen.getNumbers().get(0)) +
				"," +
				lottozahlen.getNumbers().get(1) +
				"," +
				lottozahlen.getNumbers().get(2) +
				"," +
				lottozahlen.getNumbers().get(3) +
				"," +
				lottozahlen.getNumbers().get(4) +
				"," +
				lottozahlen.getNumbers().get(5) +
				" Superzahl: " +
				lottozahlen.getSuperzahl();

		assertThat(lottozahlen.toString(), equalTo(string));
	}
}