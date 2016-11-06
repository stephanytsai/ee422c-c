/* CRITTERS <MyCritter4.java>
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

/*

 *MyCritter3 walks in the 1 direction each time step.
 *They do not reproduce. They will always fight 
 *when encountered. They will show on the board as a "3".  
 */
package assignment5;

public class Critter3 extends Critter {
	@Override
	public String toString() {
		return "3"; 
	}
	
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
	
		this.walk(1); //energy level reduced in walk function
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
