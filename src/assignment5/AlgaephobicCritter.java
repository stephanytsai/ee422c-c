/* CRITTERS <AlgaephobicCritters.java>
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

//import project5.Critter.CritterShape;

public class AlgaephobicCritter extends Critter {

	@Override
	public String toString() { return "S"; }

	public AlgaephobicCritter() {
		Params.look_energy_cost = 0;
		Params.walk_energy_cost = 0;
		Params.run_energy_cost = 0;
		Params.refresh_algae_count = 0;
	}

	public boolean fight(String not_used) { return false; }

	@Override
	public void doTimeStep() {
		/* Move to somewhere without algae */
		for (int dir = 0; dir < 8; dir++) {
			if(this.look(dir, false) == null) {
				walk(dir);
				return;
			}
		}			
		for (int dir = 0; dir < 8; dir++) {
			if(this.look(dir, true) == null) {
				run(dir);
				return;
			}
		}
		return;
	}

	public static String runStats2(java.util.List<Critter> avoidingCritters) {
		if(avoidingCritters.size() != 0) {
			return "So far so good";
		}
		else {
			return "Algae suck";
		}
	}

	@Override
	public CritterShape viewShape() { return CritterShape.DIAMOND; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.SALMON; }

}
