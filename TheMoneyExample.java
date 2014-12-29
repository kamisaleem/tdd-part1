package com.sibisoft.northstar.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Kamran
 *
 */
public class TheMoneyExample {

	@Test
	public void testMultiplication() {
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}	

	@Test
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		//assertTrue(Money.franc(5).equals(Money.franc(5)));
		//assertFalse(Money.franc(5).equals(Money.franc(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(5)));
	}
	
//	@Test
//	public void testFrancMultiplication() {
//		Money five = Money.franc(5);
//		assertEquals(Money.franc(10), five.times(2));
//		assertEquals(Money.franc(15), five.times(3));
//	}

	@Test
	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}
	
	@Test
	public void testDifferentClassEquality() {
		assertTrue(new Money(10,"CHF").equals(new Franc(10,"CHF")));
	}
	
	@Test
	public void testSimpleAdditon() {
		//Money sum = Money.dollar(5).plus(Money.dollar(5));
		//assertEquals(Money.dollar(10),sum);
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
	
	@Test
	public void testPlusReturnsSum() {
		Money five = Money.dollar(5);
		Expression results = five.plus(five);
		Sum sum = (Sum) results;
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}
	
	@Test
	public void testReduceSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), result);
	}
	
	@Test
	public void testReduceMoney() {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1), "USD");
		assertEquals(Money.dollar(1), result);
	}
}

class Sum implements Expression {
	
	Money augend;
	Money addend;
	
	Sum(Money augend, Money addend) {
		this.augend = augend;
		this.addend = addend;
	}
	
	public Money reduce(String to) {
		int amount = augend.amount + addend.amount;
		return new Money(amount, to);
	}
}

class Bank {
	Money reduce(Expression source, String to) {

		return source.reduce(to);
	}
}

interface Expression {
	
	Money reduce(String to);
	
}

class Money implements Expression {
	
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

	Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}
	
	public boolean equals(Object object) {
		Money money = (Money) object;
		return amount == money.amount
				&& currency().equals(money.currency());
	}
	
	Expression plus(Money addend) {
		return new Sum(this, addend);
	}
	
	public Money reduce(String to) {
		return this;
	}
	
	String currency() {
		return currency;
	}
	
	public String toString() {
		return amount + " " + currency;
	}
}


class Dollar extends Money {
	
	Dollar(int amount, String currency) {
		super(amount, currency);
	}
	
	static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	
	Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}
	
	String currency() {
		return currency;
	}
}

class Franc extends Money {
		
	Franc(int amount, String currency) {
		super(amount, currency);
	}
	
	static Money franc(int amount) {
		return new Money(amount, "CHF");
	}
		
	Money times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}
	
	String currency() {
		return currency;
	}
}
