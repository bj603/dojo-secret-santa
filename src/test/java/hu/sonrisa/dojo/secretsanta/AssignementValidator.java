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

import java.util.List;
import org.junit.Assert;

/**
 *
 * @author joe
 */
public class AssignementValidator {
	
	public static final void assertAssignements(int expectedNumOfAssignements, List<Assignement> actAassignements){
		// verify the number of assignements
		Assert.assertEquals("Number of assignements not equals with the expected value.", 
					expectedNumOfAssignements, actAassignements.size());
		
		// verify they are not from same family
		actAassignements.forEach(
				assignement -> {
					char firstCharOfPerson = assignement.getPerson().charAt(0);
					char firstCharOfAssignement = assignement.getAssignedPerson() != null ? assignement.getAssignedPerson().charAt(0) : '_';
					Assert.assertNotEquals("Family memers should not be assigned to each other: " + assignement, firstCharOfAssignement, firstCharOfPerson);
				}
		);
	}
	
}
