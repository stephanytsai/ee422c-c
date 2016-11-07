/*
 * MyCritter4 walks in a random direction each time step if its 
 * energy is less than or equal to 150. Otherwise, it will
 * reproduce and the offspring will be in the space to the 
 * right of it parent. MyCritter4 will be shown with an "4". MyCritter4 
 * will always fight if encountered.
 */
package assignment5;

public class Critter4 extends Critter {
	
	public int dir;
	
	// Lion constructor
	public Critter4() {
		dir = Critter.getRandomInt(8);
	}
	
	// Represent the Lion Critter subclass with the char "L"
	@Override
	public String toString() {
		return "4";
	}
	
	// Lion will walk in direction 4 and use amt of energy in walk_energy_cost
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		
		if (this.getEnergy()>150){
			Critter4 offspring=new Critter4();
			this.reproduce(offspring, 0);
		}else{
			this.walk(dir);
		}
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.SQUARE;
	}
	
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.ORANGE; }

}
