/* CRITTERS <Critter.java>
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

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import assignment5.Algae;
import assignment5.Critter;
import assignment5.CritterWorld;
import assignment5.InvalidCritterException;
import assignment5.Params;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected String look(int direction, boolean steps) {
		int num_steps=0; 
		int x_hold=this.x_coord; 
		int y_hold=this.y_coord; 
		if (steps){
			num_steps=1;
		}else{
			num_steps=2;
		}
		
		if (direction==0){
			x_hold+=num_steps;
			x_hold= Math.floorMod(x_hold, Params.world_width);
		}else if(direction==1){
			x_hold+=num_steps;
			y_hold+=num_steps;
			y_hold= Math.floorMod(y_hold, Params.world_height);
			x_hold= Math.floorMod(x_hold, Params.world_width);
		}else if(direction==2){
			y_hold+=num_steps;
			y_hold= Math.floorMod(y_hold, Params.world_height);
		}else if(direction==3){
			x_hold-=num_steps;
			y_hold+=num_steps;
			x_hold= Math.floorMod(x_hold, Params.world_width);
			y_hold= Math.floorMod(y_hold, Params.world_height);
		}else if (direction==4){
			x_hold-=num_steps;
			x_hold= Math.floorMod(x_hold, Params.world_width);
		}else if(direction==5){
			x_hold-=num_steps;
			y_hold-=num_steps;
			x_hold= Math.floorMod(x_hold, Params.world_width);
			y_hold= Math.floorMod(y_hold, Params.world_height);
		}else if(direction==6){
			y_hold-=num_steps;
			y_hold= Math.floorMod(y_hold, Params.world_height);
		}else if(direction==7){
			x_hold+=num_steps;
			y_hold-=num_steps;
			x_hold= Math.floorMod(x_hold, Params.world_width);
			y_hold= Math.floorMod(y_hold, Params.world_height);
		}
		
		this.energy=this.energy-Params.look_energy_cost;
		Iterator critterIter= critterCollectionCopy.iterator(); 
		Critter current; 
		while(critterIter.hasNext()){
			current=(Critter) critterIter.next();
//			System.out.println("current= "+ current.x_coord+ " "+ current.y_coord+" = "+ current.toString()); //debug
			if(current.x_coord==x_hold && current.y_coord==y_hold){
				return current.toString();
			}
		}
		return "";
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	protected int getX() { return x_coord; }
	protected int getY() { return y_coord; }

	protected final void walk(int direction) {
		if (direction==0){
			this.x_coord++;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
		}else if(direction==1){
			this.x_coord++;
			this.y_coord++;
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
		}else if(direction==2){
			this.y_coord++;
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==3){
			this.x_coord--;
			this.y_coord++;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if (direction==4){
			this.x_coord--;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
		}else if(direction==5){
			this.x_coord--;
			this.y_coord--;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==6){
			this.y_coord--;
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==7){
			this.x_coord++;
			this.y_coord--;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}
		this.energy=this.getEnergy()-Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
		if (direction==0){
			this.x_coord+=2;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
		}else if(direction==1){
			this.x_coord+=2;
			this.y_coord+=2;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==2){
			this.y_coord+=2;
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==3){
			this.x_coord-=2;
			this.y_coord+=2;
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			
		}else if (direction==4){
			this.x_coord-=2;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
		}else if(direction==5){
			this.x_coord-=2;
			this.y_coord-=2;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==6){
			this.y_coord-=2;
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);

		}else if(direction==7){
			this.x_coord+=2;
			this.y_coord-=2;
			this.x_coord= Math.floorMod(this.x_coord, Params.world_width);
			this.y_coord= Math.floorMod(this.y_coord, Params.world_height);
 

		}
		this.energy= (this.getEnergy()-Params.run_energy_cost);

	}

	/**
	 * Places the new offspring in an adjacent coordinate location to the parent, according to the direction that is passed
	 * @param offspring
	 * @param direction
	 */
	@SuppressWarnings("unchecked")
	protected final void reproduce(Critter offspring, int direction) {
		this.energy=Math.floorDiv(this.getEnergy(), 2)+1;
		offspring.energy=(Math.floorDiv(this.getEnergy(), 2));
		offspring.walk(direction);
		Iterator I=babies.iterator();
		Critter current;
		while(I.hasNext()){
			current=(Critter) I.next();
		}
		babies.add(offspring); 
		
	}
	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * Check to see if a critter (this) is in the same position as another critter (c2)
	 * @param c2
	 * @return
	 */
	public boolean isSamePostion(Critter c2) {
		if ( (this.x_coord == c2.x_coord) && (this.y_coord == c2.y_coord)){
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Resolve encounter between two critters that occupy the same location
	 * @param c1
	 * @param c2
	 */
	private static void resolveEncounter(Critter c1, Critter c2) {
		//determine which critters want to attack and give them an attack power number
		boolean c1fight = c1.fight(c2.toString()); 
		boolean c2fight = c2.fight(c1.toString());
		int c1Attack = 0;
		int c2Attack = 0;
		
	
		if (c1.getEnergy()>0 && c1fight){
			c1Attack = Critter.getRandomInt(c1.getEnergy());
		}else if (c2.getEnergy()>0 && c2fight){
			c2Attack = Critter.getRandomInt(c2.getEnergy());
		}else if (c2.getEnergy()<=0){
			c2.energy=(0);
		}else if (c1.getEnergy()<=0){
			c1.energy=(0);
		}
		
		//use attack power number to determine which critter wins 
		//winner retains energy and is awarded 1/2 losers energy. Loser dies (Energy is set to <= 0).
		if(c1Attack >= c2Attack){
			int c1Energy = c1.getEnergy() + (c2.getEnergy()/2);
			c1.energy=(c1Energy);
			c2.energy=(0);
		}else if(c1Attack==0 && c2Attack==0){
			//Algae overlapping 
			c1.energy=(0);
		}else{
			int c2Energy = c2.getEnergy() + (c1.getEnergy()/2);
			c2.energy=(c2Energy);
			c1.energy=(0);
		}
	}
	/**
	 * subtracts rest_energy
	 * clears dead critters from critterworld
	 * returns updated list 
	 * 
	 * @return
	 */
	
	private static List<Critter> clearDead(){ 
		Iterator Ia=CritterWorld.critterCollection.iterator();
		Critter current;
		while(Ia.hasNext()){
			current=(Critter) Ia.next();
			int energy= current.getEnergy();
			energy=energy-Params.rest_energy_cost;
			current.energy=(energy);
			if (energy<=0){
				Ia.remove(); 
			}
		}
		return CritterWorld.critterCollection; 
	}
	
	
	/**
	 *invoke doTimeStep for each critter in critterworld
	 *encounters
	 *rest energy
	 *generate algae
	 *add babies to population
	 *clear dead
	 * @throws InvalidCritterException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	//make global critter collection here
	public static List<Critter> critterCollectionCopy = null;
	
	public static void worldTimeStep() throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvalidCritterException {
		critterCollectionCopy=new java.util.ArrayList<Critter>();
		System.out.println("crittercollectioncopy before world time step:--------");
		for (Critter c:CritterWorld.critterCollection){
			critterCollectionCopy.add(c);
			System.out.println(c.x_coord+" "+c.y_coord+" "+c.toString()); //debug
		}
		Iterator critterIter= CritterWorld.critterCollection.iterator(); 
		Critter current; 
		while(critterIter.hasNext()){
			current=(Critter) critterIter.next();
			current.doTimeStep();
			current.energy=(current.getEnergy()-Params.rest_energy_cost);
		}
		//if more than one critter in space, 
		//encounter
		int i;
		int j;
		int numCritters = CritterWorld.critterCollection.size();
		for (i=0; i<numCritters; i++){
			current = CritterWorld.critterCollection.get(i);
			for(j=i+1; j<numCritters; j++){
				Critter nextCritter = CritterWorld.critterCollection.get(j);
				
				//compare current to nextCritter in terms of coordinate location
				if(current.isSamePostion(nextCritter) && (current.getEnergy()*nextCritter.getEnergy() > 0)){
					Critter.resolveEncounter(current, nextCritter);
				}
			}
		}
		
		//subtract rest energy
		Iterator I= CritterWorld.critterCollection.iterator(); 
		Critter curr; 
		while(critterIter.hasNext()){
			curr=(Critter) I.next();
			curr.energy=(curr.getEnergy()-Params.rest_energy_cost);
		}
		
		//adding algae   
		for (int k=0; k<Params.refresh_algae_count; k++){
			Critter offspring=new Algae(); 
			offspring.energy=(Params.start_energy);
			offspring.x_coord=(getRandomInt(Params.world_width));  
			offspring.y_coord=(getRandomInt(Params.world_height));
			CritterWorld.critterCollection.add(offspring);
		}
		
		//add babies to population
		Iterator babiesI= babies.iterator(); 
		Critter currentB;
		while (babiesI.hasNext()){
			CritterWorld.critterCollection.add((Critter) babiesI.next());
		}
		babies.clear();
		Critter.clearDead(); //rest energy and clear dead
	}
	
	/**displayWorld()
	 * create a matrix of values height x width
	 * prints top border
	 * for each critter in critter world
	 * 		prints each position [row][col] of grid
	 * end
	 * prints bottom border
	 * print matrix
	 */
	@SuppressWarnings("restriction")
	public static void displayWorld(){
		Critter[][] critterArray = displayWorldOLD();
		double dimension = Math.min(250/Params.world_height, 500/Params.world_width);
		double polyDim = Math.min(250/Params.world_height, 500/Params.world_width);
		int row;
        int col;
        
        for(col=0;col<Params.world_width;col++){
        	for(row=0;row<Params.world_height;row++){
        		Rectangle blank = new Rectangle(col,row,dimension,dimension);
        		blank.setStroke(javafx.scene.paint.Color.WHITE);
            	blank.setFill(javafx.scene.paint.Color.SANDYBROWN);
            	Crit2Main.gpWorld.add(blank, col, row);
        		
        	}
        }
        
        /*
		Iterator I = CritterWorld.critterCollection.iterator();
        Critter current;
        int x;
        int y;
        Critter.CritterShape shape;
        */
        int x;
        int y;
        Critter.CritterShape shape;
        int i;
        int j;
        Critter current;
        for(i=0;i<Params.world_width;i++){
        	for(j=0;j<Params.world_height;j++){
        		
        		if(critterArray[i][j]==null){
        			Rectangle blank = new Rectangle(i,j,dimension,dimension);
            		blank.setStroke(javafx.scene.paint.Color.WHITE);
                	blank.setFill(javafx.scene.paint.Color.SANDYBROWN);
                	Crit2Main.gpWorld.add(blank, i, j);
        		}
        		
        		else{
        		current = critterArray[i][j];
        		x = current.getX();
            	y = current.getY();
            	shape = current.viewShape();
            	
            	switch (shape) {
                case CIRCLE:
                	Circle cir = new Circle(i,j,.9*dimension/2);
                	cir.setStroke(current.viewColor());
                	cir.setFill(current.viewColor());
    				Crit2Main.gpWorld.add(cir, i, j);
                    break;
                        
                case SQUARE:
                	Rectangle rec = new Rectangle(i,j,.9*dimension,.9*dimension);
                	rec.setStroke(current.viewColor());
                	rec.setFill(current.viewColor());
                	Crit2Main.gpWorld.add(rec, i, j);
                    break;
                             
                case TRIANGLE: 
                	Polygon tri = new Polygon();
                	tri.setStroke(current.viewColor());
                	tri.setFill(current.viewColor());
    		        tri.getPoints().addAll(new Double[]{
    		        	.1*polyDim, .1*polyDim,
    		            .9*polyDim, .1*polyDim,
    		            polyDim/2, .9*polyDim });
    		        Crit2Main.gpWorld.add(tri, i, j);
                    break;
                    
                case DIAMOND: 
                	Polygon diam = new Polygon();
                	diam.setStroke(current.viewColor());
                	diam.setFill(current.viewColor());
    			       	diam.getPoints().addAll(new Double[]{
    			           polyDim/2, .1*polyDim,
    			           .9*polyDim, polyDim/2,
    			           polyDim/2, .9*polyDim,
    			           .1*polyDim, polyDim/2  });
    			       	Crit2Main.gpWorld.add(diam, i, j);
                    break;
                    
                case STAR: 
                	Polygon star = new Polygon();
                	star.setStroke(current.viewColor());
                	star.setFill(current.viewColor());
    		        star.getPoints().addAll(new Double[]{
    		            polyDim/2, .1*polyDim,
    		            .6*polyDim, .4*polyDim,
    		            .9*polyDim, .4*polyDim,
    		            .7*polyDim, .6*polyDim,
    		            .9*polyDim, .9*polyDim,
    		            polyDim/2, .7*polyDim,
    		            .1*polyDim, .9*polyDim,
    		            .3*polyDim, .6*polyDim, 
    		            .1*polyDim, .4*polyDim,
    		            .4*polyDim, .4*polyDim  });
    		        Crit2Main.gpWorld.add(star, i, j);
                    break;
                           
            	} //end of switch
        		}
        	}
        }
       
	}
	
	
	
	
	
	public static Critter[][] displayWorldOLD() {
		Critter array[][]=new Critter[Params.world_width][Params.world_height];  //height is rows, width is cols
		// array=new  String[Params.world_width][Params.world_height]; 
		Iterator I= CritterWorld.critterCollection.iterator(); 
		Critter current; 
		String word;
		while(I.hasNext()){
			System.out.println("now");
			current=(Critter) I.next();
			int x=current.getEnergy();
		//	System.out.println("Energy: " + current.getClass()+ " "+ x);  //DEBUG
			if (x>0 && array[current.x_coord][current.y_coord]==null){
				array[current.x_coord][current.y_coord]=current;
			}
		}
		
		return array;

		/*
		//printing first border
		System.out.print("+"); //not println so won't have --- on a new line
		for (int i=0; i<Params.world_width; i++){
			System.out.print("-"); //TODO don't know if this has spaces
		}
		System.out.println("+"); //println moves cursor to new line after printing "+"
		
		//printing grid
		for (int i=0; i<Params.world_height; i++){
			System.out.print("|"); //side border
			for (int j=0; j<Params.world_width; j++){ //prints each row
				if(array[j][i]==null){
					System.out.print(" "); 
				}else{
					System.out.print(array[j][i]); 
				}
			}
			System.out.println("|");
		}
		
		//bottom border
		System.out.print("+"); 
		for (int i=0; i<Params.world_width; i++){
			System.out.print("-");
		}
		System.out.println("+"); 
		*/
				
	}
	

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 * @throws IllegalAccessException 
	 */

	public static void makeCritter(String critter_class_name) throws InvalidCritterException, InstantiationException, ClassNotFoundException, IllegalAccessException {
		Critter critterInstance = null;
		Class critterType; 
		String name="";
		try { 
			name=name.concat("assignment5."+critter_class_name);
			critterType = Class.forName(name); 
			critterInstance =  (Critter) critterType.newInstance(); 
		} catch (ClassNotFoundException e) {
			return;
			//throw new InvalidCritterException(e.toString());
		} catch (IllegalAccessException | InstantiationException e){
		//	throw new InvalidCritterException(e.toString());
			return;
		}
		
		(critterInstance).x_coord=(getRandomInt(Params.world_width));
		(critterInstance).y_coord=(getRandomInt(Params.world_height));
		(critterInstance).energy=(Params.start_energy);
		
		CritterWorld.critterCollection.add(critterInstance); //ADDING CRITTER
		
		//TODO if critter is reproduced, energy reset in reproduce fxn
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		Critter critterInstance = null;
		Class critterType; 

		try { 
			critterType = Class.forName(critter_class_name); 
		} catch (ClassNotFoundException e) {
		    throw new InvalidCritterException(e.toString());
		}
		for (Critter c:CritterWorld.critterCollection){
			if (c.getClass().equals(critterType)){ //add if same critter type
				result.add(c);
			}
		}
		
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static String runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		String ret = "";
		for (String s : critter_count.keySet()) {
			ret = (prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
		return "" + critters.size() + " critters as follows -- "+ ret;		
	}	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			population=CritterWorld.critterCollection; 
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		Iterator I= CritterWorld.critterCollection.iterator(); 
		Critter current;
		while (I.hasNext()){
			current=(Critter) I.next();
			I.remove();
		}
		
	}
	
	
	
}
