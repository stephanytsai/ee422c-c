/* CRITTERS <Help.java>
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import assignment4.Critter;

public class Help {
	/**
	 * checks if a word is in a String[]
	 * @param find
	 * @param group
	 * @return
	 */
	public static boolean isin(String find, String[] group){
		for (String s:group){
			if (find.equals(s)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * checks if a is a critter type 
	 * @param a
	 * @return
	 */ //TODO no hard coding
/*	public static boolean isType(String a){
		String[] types={"Turtle", "Critter", "Lion", "Tiger",
				"Bear", "Algae", "Craig"};
		if(isin(a,types)){
			return true;
		}
		return false; 
	}*/
	
	 /**
     * is the input an integer?
     * @param a
     * @return 
     */
	public static boolean isInt(String a){
		int i=0;
		try{
			i=Integer.parseInt(a);
			return true;
		}catch(NumberFormatException e){
			//System.out.println("contine!");
			return false;
		}
	}
	

    /**
     * 1. words in input array exist in valid word list?
     * 		-yes: look at other words
     * 		-no: is it an integer?
     * 			-yes: look at other words
     * 			-no: caught invalid word
     * @param array
     * @return true: all words 
     */
	public static boolean validInput (String[] array){ //deleted static from both //TODO no hard coding critter types
		String[] valid={"make", "show", "step", "seed", "stats","quit"};
		boolean gotvalid=false;
		int num;
		Critter critterInstance = null;
		Class critterType = null; 
		String holder="";
		
		for (String word:array){
			gotvalid=false;
			if (isin(word,valid)){
				gotvalid=true;
			}else if (isInt(word)){
				gotvalid=true;
			}else{
				try{
					holder=holder.concat("assignment5."+word);
					critterType = Class.forName(holder);
				}catch (ClassNotFoundException e) {
				//	gotvalid=false;
					return false;
				}
				gotvalid=true;
			}
			if(gotvalid==false){
				return false;
			}			
		}
		
		return true;
	}
	
	/**
	 * checks for invalid commands
	 * implements quit, make, show, step, seed, stats
	 * @param a
	 * @param user
	 * @throws InvalidCritterException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	public static boolean implementInput(String[] a, String user) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvalidCritterException{
		if (a[0].equals("quit") && a.length==1){
			return true;
		}else if (a[0].equals("show") && a.length==1){
			Critter.displayWorld();
			return false;
		//	System.out.println("displaying world");//DEBUG
		}else if (a[0].equals("step")){
			if(a.length==1){
				Critter.worldTimeStep(); //not tested yet
				//System.out.println("WorldTimeStep once");//DEBUG
				return false;
			}else if (a.length==2 && isInt(a[1])){
				int num=Integer.parseInt(a[1]);
				if (num<=0){
					System.out.println("invalid input: "+ user);
					return false;
				}
				for (int i=0; i<num; i++){
					Critter.worldTimeStep(); //not tested yet
				}
				//System.out.println("WorldTimeStep "+ a[1]);//DEBUG
				return false;
			}else{
				System.out.println("error processing: "+ user);
				return false;
			}
		}else if (a[0].equals("seed") && a.length==2){
			if (isInt(a[1])){
				Critter.setSeed(Integer.parseInt(a[1])); //test? how?
				System.out.println("Critter.setSeed()"); //DEBUG
				return false;
			}
		}else if(a[0].equals("make") && (a.length==2 || a.length==3)){
			int numMake=1;
			if(a.length==3 && isInt(a[2])){
				numMake=Integer.parseInt(a[2]);
			}
			if(numMake<=0){
				System.out.println("invalid command: "+ user);
				return false;
			}

			Critter critterInstance = null;
			Class critterType = null; 
			String holder="";
			try { 
				holder=holder.concat("assignment5."+a[1]);
				critterType = Class.forName(holder);
			} catch (ClassNotFoundException e) {
				System.out.println("error processing: "+user);
				return false;
			}
			
			for(int i=0; i<numMake; i++){
				Critter.makeCritter(a[1]);
			}
			return false;
		}else if (a[0].equals("stats") && a.length==2){
			String holder="";
			Class critterType;
			try { 
				holder=holder.concat("assignment5."+ a[1]);
				critterType = Class.forName(holder);
			} catch (ClassNotFoundException e) {
				System.out.println("error processing: "+user);
				return false;
			}
			List<Critter> instances=Critter.getInstances(holder);
			Class<?>[] types={List.class};
			Method method_object=null;
			try {
				method_object = critterType.getMethod("runStats", types);
			} catch (NoSuchMethodException e) {
			} catch (SecurityException e) {
			}
			System.out.println("method: "+ method_object.toString());
			try {
				method_object.invoke(null, instances);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();

			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			return false;
		}else{
			System.out.println("error processing: "+ user);
			return false;
		}
		return false;
	}
}
