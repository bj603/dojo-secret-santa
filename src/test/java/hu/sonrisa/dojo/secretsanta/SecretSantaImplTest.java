/*
 *   Copyright (c) 2018 Sonrisa Informatikai Kft. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of
 *  Sonrisa Informatikai Kft. ("Confidential Information").
 *  You shall not disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Sonrisa.
 * 
 *  SONRISA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 *  THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *  TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 *  PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SONRISA SHALL NOT BE LIABLE FOR
 *  ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 *  DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package hu.sonrisa.dojo.secretsanta;

import hu.sonrisa.dojo.secretsanta.solution.SecretSantaImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Test cases for {@link SecretSantaImpl} class.
 *
 * @author joe
 */
public class SecretSantaImplTest {
	
	private SecretSanta secretSanta;
	
	@Before
	public void setup(){
		secretSanta = new SecretSantaImpl();
	}

	@Test
	public void testEmptyInput() {
		// ----- arrange
		final String input = "";
		
		// ----- act
		final List<Assignement> output = secretSanta.randomlyAssign(input);
		
		// ----- assert
		printResults(input, output);
		Assert.assertNull("Result sould be null.", output);
	}
	
	
	@Test
	public void testNullInput() {
		// ----- arrange
		final String input = null;
		
		// ----- act
		final List<Assignement> output = secretSanta.randomlyAssign(input);
		
		// ----- assert
		printResults(input, output);
		Assert.assertNull("Result sould be null.", output);
	}
	
	@Test
	public void testOneLineInput() {
		// ----- arrange
		final String input = "Joe Jonathan";
		
		// ----- act
		final List<Assignement> output = secretSanta.randomlyAssign(input);
		
		// ----- assert
		printResults(input, output);
		AssignementValidator.assertAssignements(2, output);
	}
	
	@Test
	public void testNormalInput() {
		// ----- arrange
		final String input = 
				"Andre\n"
				+ "Brian Bruno\n"
				+ "Cecile\n"
				+ "Debbie Debora Donald\n"
				+ "Eleonora Elen Edgar Earl\n"
				+ "Ferdinand\n"
				+ "Gabrielle";
		
		// ----- act
		final List<Assignement> output = secretSanta.randomlyAssign(input);
		
		// ----- assert
		printResults(input, output);	
		AssignementValidator.assertAssignements(13, output);
	}
	
	private void printResults(final String input, final List<Assignement> output) {
		System.out.println("input: \n" + input);
		System.out.println("\noutput: \n" + assignementsToString(output));
		System.out.println("******************************");
	}
	
	private String assignementsToString(List<Assignement> assignements){
		return assignements == null ? null : 
				assignements.stream()
				.map(Assignement::toString)
				.collect(Collectors.joining("\n"));
	}
}
