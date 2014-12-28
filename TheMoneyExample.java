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
		Dollar five = new Dollar(5);
		Dollar product = five.times(2);
		assertEquals(10, product.amount);
		product= five.times(3);
		assertEquals(15, product.amount);
		testEquality();
	}
	
	public void testEquality() {
		assertTrue(new Dollar(5).equals(new Dollar(5)));
		assertFalse(new Dollar(5).equals(new Dollar(6)));
	}
	
	class Dollar {
		
		int amount;
		
		Dollar(int amount) {
			this.amount = amount;
		}
		
		Dollar times(int multiplier) {
			return new Dollar (amount * multiplier);
		}
		
		public boolean equals(Object object) {
			Dollar dollar = (Dollar) object;
			return amount == dollar.amount;
		}
		
	}
}
