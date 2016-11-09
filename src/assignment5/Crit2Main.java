package assignment5;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import java.lang.reflect.*;



@SuppressWarnings("restriction")
public class Crit2Main extends Application {
	public static BorderPane border = new BorderPane();
	public static GridPane gpMenu = new GridPane();
	public static GridPane gpMenu2 = new GridPane();
	public static GridPane gpWorld = new GridPane();
	public static GridPane gpStart = new GridPane();
	public static GridPane gpStats = new GridPane();
	
	public int turnCount=0;
	
	
	@Override 
	public void start(Stage primaryStage) {
		try {
			//gpMenu.setGridLinesVisible(true);
			//gpWorld.setGridLinesVisible(true);
			gpWorld.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
			//gpWorld.setStyle("-fx-background-color: palegreen, white; -fx-background-insets: 0, 200; -fx-border-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
			//gpWorld.setStyle("-fx-background-fill: black, white");
			//gpWorld.setStyle("-fx-background-insets: 0, 1 ");
			
			gpWorld.setAlignment(Pos.CENTER);
			//gpWorld.setHgap(2);
			//gpWorld.setVgap(2);
			primaryStage.setTitle("Welcome to Critter World");
			border.setLeft(gpMenu);
			border.setCenter(gpWorld);
			border.setRight(gpMenu2);
			border.setBottom(gpStats);
			Scene startScene = new Scene(gpStart, 1000, 650);
			Scene scene = new Scene(border, 1000, 650); 
			
			
			
			 Button start = new Button();
			 Button quit = new Button();
			 gpStart.setAlignment(Pos.CENTER);
			 gpStart.add(start,0,0);
			 gpStart.add(quit, 1,0);
			 gpStart.setStyle("-fx-background-color: coral; -fx-border-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
			 primaryStage.setScene(startScene);
			 primaryStage.show();
		        start.setText("LETS PLAY CRITTERS!");
		        start.setOnAction(new EventHandler<ActionEvent>() {
			       	 
		            @Override
		            public void handle(ActionEvent event) {
		                System.out.println("Critter Game Started!"); //actually start next scene
		                primaryStage.setScene(scene);
		    			primaryStage.show();
		             
		            }
		        });
		        quit.setText("I DONT WANT TO PLAY!");
		        quit.setOnAction(new EventHandler<ActionEvent>() {
			       	 
		            @Override
		            public void handle(ActionEvent event) {
		                System.out.println("Critter Game Ended!"); //actually quit
		    			primaryStage.close();
		             
		            }
		        });
		        
		     
			
			
			// critNum and makeCrit combine to give user control to create critters
			TextField critType = new TextField();
			critType.setText("Enter Type");
			TextField critNum = new TextField();
			critNum.setText("Enter Number");
			
			//makes critters of user given type and number
			Button makeCrit = new Button();
	        makeCrit.setText("Make Critters");
	        makeCrit.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Made " + critNum.getText() + " " + critType.getText()+ " Critters"); //call makeCritter
	                int i;
	                int num = Integer.parseInt(critNum.getText());
	                for(i=0;i<=num;i++){
	                	try {
							Critter.makeCritter(critType.getText());
						} catch (InstantiationException | ClassNotFoundException | IllegalAccessException
								| InvalidCritterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            }
	        });
	        
	        Button clear = new Button();
	        clear.setText("Start Over");
	        clear.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critters will be cleared"); //actually call doTimeStep
	                gpWorld.getChildren().clear();
	                CritterWorld.critterCollection.clear();
	            }
	        });
	        
	        Button night = new Button();
	        night.setText("Night Time");
	        night.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	            	gpWorld.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
	            }
	        });
	        
	        Button day = new Button();
	        day.setText("Day Time ");
	        day.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	            	gpWorld.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
	            }
	        });
	        
	        // show btn will display crit world
	        Button show = new Button();
	        show.setText("Show Critters");
	        show.setOnAction(new EventHandler<ActionEvent>() {
	       	
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Show Critters"); //actually display world
	                gpWorld.getChildren().clear(); //clears board before new locations are shown
	                
	                Critter.displayWorld(); //calls display world which adds shapes to gpWorld 
	                
	            }
	        });
	        
	        //setSeed button
	        Button setSeed = new Button();
	        TextField seed = new TextField();
	        seed.setText("Seed Value");
	        setSeed.setText("Seed");
	        setSeed.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Calling setSeed"); //actually call setseed
	                Critter.setSeed(Integer.parseInt(seed.getText()));
	            }
	        });
	        
	        //runStats
	        TextField statText = new TextField();
	        Button runStats = new Button();
	        runStats.setText("Critter Status");
	        TextField critStat = new TextField();
	        critStat.setText("Critter Type");
	        
	        runStats.setOnAction(new EventHandler<ActionEvent>() {
		      
	            @Override
	            public void handle(ActionEvent event) {
	            	gpStats.getChildren().clear();
	            	String statString;
	            	statString=Help.runStatsHelp(critStat.getText());
	            	statText.setText(statString);
	            	statText.setPrefWidth(statText.getText().length()*7);
	            	gpStats.add(statText, 0, 0);
	            }
	        });
	        
	       
	        
	        
	        
	        
	     // Step btn will call doTimeStep
	        TextField turns = new TextField();
	        turns.setText(Integer.toString(turnCount));
	        
	        TextField stepCount = new TextField();
			stepCount.setText("Enter # Turns");
	        
	        Button step = new Button();
	        step.setText("Update Critters");
	        step.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critters will be updated"); //actually call doTimeStep
	                int count=0;
	                int numSteps;
	                
	                try{
	    	        numSteps = Integer.parseInt(stepCount.getText());
	                }
	                catch (NumberFormatException e){
	                	numSteps = 1;
	                }
	    	        
	    	        for(count=0;count<numSteps;count++){
	    	        	try {
							Critter.worldTimeStep();
							turnCount++;
							turns.setText(Integer.toString(turnCount));
							
							//maybe add animation here?
						} catch (InstantiationException | ClassNotFoundException | IllegalAccessException
							| InvalidCritterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    	        }
	            }
	        });
	        
	        Button quitGame = new Button();
	        quitGame.setText(" QUIT ");
	        quitGame.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critter Game Ended!"); //actually quit
	    			primaryStage.close();
	             
	            }
	        });
			
	        /*
	        makeCrit.setMaxWidth(Double.MAX_VALUE);
	        step.setMaxWidth(Double.MAX_VALUE);
	        show.setMaxWidth(Double.MAX_VALUE);
	        clear.setMaxWidth(Double.MAX_VALUE);
	        day.setMaxWidth(Double.MAX_VALUE);
	        night.setMaxWidth(Double.MAX_VALUE);
	        quitGame.setMaxWidth(Double.MAX_VALUE);
	       	*/
	        
	        //first row
	        gpMenu.setStyle("-fx-background-color: sandybrown;");
	        gpMenu.setVgap(5);
	        gpMenu.setPadding(new Insets(5,5,5,5));
	        //gpMenu.setAlignment(Pos.CENTER);
	        gpMenu.add(makeCrit, 0, 0);
	        gpMenu.add(critType, 0, 1);
	        gpMenu.add(critNum, 0, 2);
	        gpMenu.add(step, 0, 5);
	        gpMenu.add(stepCount, 0, 6);
	        gpMenu.add(turns, 0, 7);
	        
	        
	        //second row
	        
	        //gpMenu2.setAlignment(Pos.CENTER);
	        gpMenu.add(setSeed, 0, 10);
	        gpMenu.add(seed, 0, 11);
	        gpMenu.add(show, 0, 14);
	        gpMenu.add(runStats, 0, 17);
	        gpMenu.add(critStat, 0, 20);
	        gpMenu.add(day, 0, 23);
	        gpMenu.add(night, 0, 26);
	        
	        gpMenu.add(clear, 0, 30);
	        gpMenu.add(quitGame, 0, 33);
			//primaryStage.setScene(scene);
			primaryStage.show();
		
	// Paints the icons. Painter.paint();
		} 
		catch(Exception e) { 
			e.printStackTrace(); 
		}
	} 
	
	public static void main(String[] args) {
	launch(args);
	}

}
