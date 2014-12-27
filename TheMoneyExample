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
		five.times(2);
		assertEquals(10, five.amount);
	}
	
	class Dollar {
		
		int amount;
		
		Dollar(int amount) {
			this.amount = amount;
		}
		
		void times(int multiplier) {
			amount *= multiplier;
		}
	}

}
