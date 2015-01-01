package com.sibisoft.northstar.tdd;

public interface Expression {
	
	Money reduce(Bank bank, String to);
}
