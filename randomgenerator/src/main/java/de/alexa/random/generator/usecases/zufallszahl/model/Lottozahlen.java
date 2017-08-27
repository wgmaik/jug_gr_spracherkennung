package de.alexa.random.generator.usecases.zufallszahl.model;

import com.google.common.collect.Lists;

import java.util.List;

public class Lottozahlen {
	private List<Integer> numbers = Lists.newArrayList();
	private Integer superzahl;

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public Integer getSuperzahl() {
		return superzahl;
	}

	public void setSuperzahl(Integer superzahl) {
		this.superzahl = superzahl;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(numbers.get(0));

		for(int i = 1; i < numbers.size(); i++){
			stringBuilder.append("<break time=\"1s\" />").append(numbers.get(i));
		}

		stringBuilder.append("<break time=\"1s\" />Superzahl: ").append(superzahl);

		return stringBuilder.toString();
	}
}
