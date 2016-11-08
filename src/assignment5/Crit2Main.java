package assignment5;

import java.util.Iterator;

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



@SuppressWarnings("restriction")
public class Crit2Main extends Application {
	static BorderPane border = new BorderPane();
	static GridPane gpMenu = new GridPane();
	static GridPane gpMenu2 = new GridPane();
	static GridPane gpWorld = new GridPane();
	static GridPane gpStart = new GridPane();
	
	@Override 
	public void start(Stage primaryStage) {
		try {
			//gpMenu.setGridLinesVisible(true);
			//gpWorld.setGridLinesVisible(true);
			gpWorld.setStyle("-fx-background-color: palegreen; -fx-border-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
			//gpWorld.setStyle("-fx-background-color: palegreen, white; -fx-background-insets: 0, 200; -fx-border-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
			//gpWorld.setStyle("-fx-background-fill: black, white");
			//gpWorld.setStyle("-fx-background-insets: 0, 1 ");
			
			gpWorld.setAlignment(Pos.CENTER);
			//gpWorld.setHgap(2);
			//gpWorld.setVgap(2);
			primaryStage.setTitle("Welcome to Critter World");
			border.setTop(gpMenu);
			border.setCenter(gpWorld);
			border.setBottom(gpMenu2);
			Scene startScene = new Scene(gpStart, 800, 600);
			Scene scene = new Scene(border, 800, 600); 
			
			
			
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
	        clear.setText("Clear Critters");
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
	            	gpWorld.setStyle("-fx-background-color: black;");
	            }
	        });
	        
	        Button day = new Button();
	        day.setText("Day Time ");
	        day.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	            	gpWorld.setStyle("-fx-background-color: palegreen;");
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
	                
	                Iterator I = CritterWorld.critterCollection.iterator();
	                Critter current;
	                int x;
	                int y;
	                Critter.CritterShape shape;
	                while(I.hasNext()){
	                	current = (Critter) I.next();
	                	x = current.getX();
	                	y = current.getY();
	                	shape = current.viewShape();
	                	
	                	switch (shape) {
	                    case CIRCLE:
	                    	Circle cir = new Circle(x,y,5);
	                    	cir.setStroke(current.viewColor());
	                    	cir.setFill(current.viewColor());
	        				gpWorld.add(cir, x, y);
	                        break;
	                            
	                    case SQUARE:
	                    	Rectangle rec = new Rectangle(x,y,10,10);
	                    	rec.setStroke(current.viewColor());
	                    	rec.setFill(current.viewColor());
	        				gpWorld.add(rec, x, y);
	                        break;
	                                 
	                    case TRIANGLE: 
	                    	Polygon tri = new Polygon();
	                    	tri.setStroke(current.viewColor());
	                    	tri.setFill(current.viewColor());
	        		        tri.getPoints().addAll(new Double[]{
	        		            0.0, 0.0,
	        		            10.0, 0.0,
	        		            5.0, 10.0 });
	        				gpWorld.add(tri, x, y);
	                        break;
	                        
	                    case DIAMOND: 
	                    	Polygon diam = new Polygon();
	                    	diam.setStroke(current.viewColor());
	                    	diam.setFill(current.viewColor());
	     			       	diam.getPoints().addAll(new Double[]{
	     			           5.0, 0.0,
	     			           10.0, 5.0,
	     			           5.0, 10.0,
	     			           0.0, 5.0  });
	     			       	gpWorld.add(diam, x, y);
	                        break;
	                        
	                    case STAR: 
	                    	Polygon star = new Polygon();
	                    	star.setStroke(current.viewColor());
	                    	star.setFill(current.viewColor());
	        		        star.getPoints().addAll(new Double[]{
	        		            5.0, 0.0,
	        		            6.0, 4.0,
	        		            10.0, 5.0,
	        		            7.0, 6.0,
	        		            9.0, 9.0,
	        		            5.0, 8.0,
	        		            1.0, 9.0,
	        		            3.0, 6.0, 
	        		            0.0, 5.0,
	        		            4.0, 4.0  });
	        				gpWorld.add(star, x, y);
	                        break;
	                               
	                	}
	                	
	                	
	                }
	                
	                
	                
	                
	            }
	        });
	        
	     // Step btn will call doTimeStep
	        TextField stepCount = new TextField();
			stepCount.setText("Enter # Turns");
	        
	        Button step = new Button();
	        step.setText("Update Critters");
	        step.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critters will be updated"); //actually call doTimeStep
	                int count=0;
	    	        int numSteps = Integer.parseInt(stepCount.getText());
	    	        
	    	        for(count=0;count<numSteps;count++){
	    	        	try {
							Critter.worldTimeStep();
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
	        gpMenu.setStyle("-fx-background-color: indigo;");
	        gpMenu.setAlignment(Pos.TOP_CENTER);
	        gpMenu.add(makeCrit, 1, 1);
	        gpMenu.add(critType, 2, 1);
	        gpMenu.add(critNum, 3, 1);
	        gpMenu.add(step, 4, 1);
	        gpMenu.add(stepCount, 5, 1);
	        
	        
	        //second row
	        gpMenu2.setStyle("-fx-background-color: indigo;");
	        gpMenu2.setHgap(20);
	        gpMenu2.setAlignment(Pos.BOTTOM_CENTER);
	        gpMenu2.add(show, 1, 2);
	        gpMenu2.add(clear, 2, 2);
	        gpMenu2.add(day, 3, 2);
	        gpMenu2.add(night, 4, 2);
	        gpMenu2.add(quitGame, 5, 2);
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
