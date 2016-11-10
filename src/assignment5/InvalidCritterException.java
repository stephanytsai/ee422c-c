/* CRITTERS <InvalidCritterException.java>
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Stephany Tsai>
 * <st26536>
 * <16445>
 * <Rajan Makanji>
 * <rm45378>
 * <16445>
 * Slip days used: <0>
 * Fall 2016
 */

package assignment5;

public class InvalidCritterException extends Exception {
	String offending_class;
	
	public InvalidCritterException(String critter_class_name) {
		offending_class = critter_class_name;
	}
	
	public String toString() {
		return "Invalid Critter Class: " + offending_class;
	}

}
