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

/**
 * Model class to encapsulate a gift assignement.
 *
 * @author joe
 */
public class Assignement {
	
	private final String person;
	private final String assignedPerson;

	public Assignement(String person, String assignedPerson) {
		this.person = person;
		this.assignedPerson = assignedPerson;
	}

	public String getPerson() {
		return person;
	}

	public String getAssignedPerson() {
		return assignedPerson;
	}

	@Override
	public String toString() {
		return person + " -> " + assignedPerson;
	}
	
}
