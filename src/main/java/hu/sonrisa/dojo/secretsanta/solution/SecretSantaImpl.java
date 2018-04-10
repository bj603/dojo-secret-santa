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
package hu.sonrisa.dojo.secretsanta.solution;

import hu.sonrisa.dojo.secretsanta.Assignement;
import hu.sonrisa.dojo.secretsanta.SecretSanta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A possible soultion for the Secret Santa dojo exercise.
 *
 * @author joe
 */
public class SecretSantaImpl implements SecretSanta {
	
	private List<String> everyone;
	private List<Family> families;

	@Override
	public List<Assignement> randomlyAssign(String participants) {
		if (participants == null || "".equals(participants)){
			return new ArrayList<>();
		}
		
		families = parseToFamilies(participants);
		everyone = flatToEveryone(families);
		
		final List<Assignement> result = new ArrayList<>();
		
		everyone.forEach(
				someOne -> result.add(new Assignement(someOne, getRandomAssignment(someOne, result)))
		);
		
		return result;
	}
	
	private List<String> flatToEveryone(List<Family> families){
		final List<String> everyOne = new ArrayList<>();
		families.forEach(
				family -> everyOne.addAll(family.getMembers())
		);
		return everyOne;
	}
	
	/**
	 * Parses the given participants into a list of families.
	 * 
	 * Every participant in the same line will be a family.
	 * 
	 * @param participants
	 * @return 
	 */
	private List<Family> parseToFamilies(String participants){
		final List<Family> families = new ArrayList<Family>();
		
		final String[] lines = participants.split("\n");
		
		Arrays.stream(lines).forEach(
				line -> families.add(Family.parseFromMembers(line))
		);
		
		return families;
	}
	
	/**
	 * Provides a random assignement for the given person.
	 * 
	 * It ensures that the person and the assignement are not in the same family,
	 * and everyone is assigned only once.
	 * 
	 * It returns null if there is no suitable assignment for the given person.
	 * 
	 * @param person
	 * @param currentAssignements
	 * @return 
	 */
	private String getRandomAssignment(String person,  List<Assignement> currentAssignements){
		String potentialCandidate = null;
		
		// copies the list of everyone into a disposable candidate list
		List<String> candidates = new ArrayList(everyone);
		
		// removes the himself/herself as porential candidate
		candidates.remove(person);
		
		do{
			potentialCandidate = getRandomElement(candidates);
			
			// if she/he is from a different family and not assigned yet, she/he is a good assignment
			if(differentFamily(person, potentialCandidate) 
					|| noAssignedYet(potentialCandidate, currentAssignements)){
				
				return potentialCandidate;
			}
			
			// if she/he is not a good assignement, she can be removed from the list of candidates
			candidates.remove(potentialCandidate);
		}while(!candidates.isEmpty());
		
		return potentialCandidate;
		
	}
	
	/**
	 * Returns whether the given person has been already assigned to someone.
	 * 
	 * @param person
	 * @param currentAssignements
	 * @return 
	 */
	private boolean noAssignedYet(String person, List<Assignement> currentAssignements){
		return currentAssignements
				.stream()
				.noneMatch(ass -> ass.getAssignedPerson() != null && ass.getAssignedPerson().equals(person));
	}
	
	/**
	 * Returns whether the two given person belongs to the same family or not.
	 * 
	 * @param someone
	 * @param other
	 * @return 
	 */
	private boolean differentFamily(String someone, String other){
		return families
				.stream()
				.noneMatch(family -> 
							family.contains(someone) 
						 && family.contains(other));
	}
	
	/**
	 * Returns with a rendom element from the given list.
	 * 
	 * @param list
	 * @return 
	 */
	private String getRandomElement(List<String> list){
		return list.get(getRandomNumberBetweenZeroAndMaximum(list.size()));
	}
	
	/**
	 * Gives you a random number between 0 (inclusive) and the given maximum (exclusive).
	 * 
	 * @param max
	 * @return 
	 */
	private int getRandomNumberBetweenZeroAndMaximum(int max){
		Random r = new Random();
		int low = 0;
		int high = max;
		return r.nextInt(high-low) + low;
	}
	
	/**
	 * Inner class that encapsulates the member of a family.
	 * 
	 */
	private static class Family{
		
		private List<String> members;

		private Family(List<String> members) {
			this.members = members;
		}

		public static Family parseFromMembers(String members) {
			// splites the member string into members array
			final String[] splitted = members.split(" ");
			
			// trims every element in members array and converts them into a list
			final List<String> trimmedList = Arrays.asList(splitted)
					.stream()
					.map(String::trim)
					.collect(Collectors.toList());
			
			return new Family(trimmedList);
		}
		
		public void add(String member){
			members.add(member);
		}
		
		public boolean contains(String someone){
			return members.contains(someone);
		}
		
		public List<String> getMembers(){
			return members;
		}

		@Override
		public String toString() {
			return "Family{" + "members=" + members + '}';
		}
	}
}
