/* CRITTERS <Critter2.java>
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

/* CRITTERS <MyCritter2.java>
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

 *MyCritter2 runs in a random direction at each time step. 
 *On the board, it will show as a "2". It
 *do not reproduce. They will always fight when encountered.  
 */
package assignment5;

public class Critter2 extends Critter{
	
	public int dir;
	// Tiger constructor
	public Critter2() {
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public String toString() {
		return "2";
	}
	
	
	// Runs in direction 7 and uses amt of energy in run_energy_cost
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		String out=this.look(0,true); 
	//	System.out.println("looked and saw: "+ out);
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.STAR;
	}

	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.YELLOW; }
}
