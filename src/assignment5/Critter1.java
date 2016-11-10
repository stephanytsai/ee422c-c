/* CRITTERS <sCritter1.java>
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
 * MyCritter1 runs in the 5 direction each time step if its 
 * energy is less than or equal to 200. Otherwise, it will
 * reproduce and the offspring will be in the space to the 
 * right of it parent. MyCritter4 will be shown by a "1". MyCritter4 
 * will always fight if encountered.
 */
package assignment5;

public class Critter1 extends Critter {
	
	@Override
	public String toString() {
		return "1";
	}
	
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		if (this.getEnergy()>200){
			Critter1 offspring=new Critter1();
			this.reproduce(offspring, 0);
		}else{
			this.run(5);
		}
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		this.look(1,true); 
		return true;
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.DIAMOND;
	}
	
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BLUE; }
	


}
