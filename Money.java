package com.sibisoft.northstar.tdd;

public class Money implements Expression {
	
	protected int amount;	
	protected String currency;
	
	Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	static Money dollar(int amount) {
		return new Dollar(amount, "USD");
	}
	
	static Money franc(int amount) {
		return new Franc(amount, "CHF");
	}
	
	@Override
	public Money reduce(Bank bank, String to) {
		//int rate = (currency.equals("CHF") && to.equals("USD"))?2:1;
		//return new Money(amount/rate, to);
		int rate = bank.rate(currency, to);
		return new Money(amount/rate, to);
	}
	
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}
	
	public Expression times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}
	
	public boolean equals(Object object) {
		Money money = (Money) object;
		return amount == money.amount
				&& currency().equals(money.currency());
	}
	
	String currency() {
		return currency;
	}
	
	public String toString() {
		return amount + " " + currency;
	}

}
