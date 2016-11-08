package assignment5;

import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
	static GridPane gpWorld = new GridPane();
	
	
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
			Scene scene = new Scene(border, 800, 800); 
			
			// critNum and makeCrit combine to give user control to create critters
			TextField critType = new TextField();
			critType.setText("Enter Type");
			TextField critNum = new TextField();
			critNum.setText("Enter Number");
			
			//makes critters of user given type and number
			Button makeCrit = new Button();
	        makeCrit.setText(" Make  Critters ");
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
	        clear.setText(" Clear  Critters");
	        clear.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critters will be cleared"); //actually call doTimeStep
	                gpWorld.getChildren().clear();
	                CritterWorld.critterCollection.clear();
	            }
	        });
	        
	        Button night = new Button();
	        night.setText(" Night   Time ");
	        night.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	            	gpWorld.setStyle("-fx-background-color: black;");
	            }
	        });
	        
	        Button day = new Button();
	        day.setText(" Day   Time ");
	        day.setOnAction(new EventHandler<ActionEvent>() {
		       	 
	            @Override
	            public void handle(ActionEvent event) {
	            	gpWorld.setStyle("-fx-background-color: palegreen;");
	            }
	        });
	        
	        // show btn will display crit world
	        Button show = new Button();
	        show.setText(" Show   Critters");
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
	        Button step = new Button();
	        step.setText("Update Critters");
	        step.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critters will be updated"); //actually call doTimeStep
	                try {
						Critter.worldTimeStep();
					} catch (InstantiationException | ClassNotFoundException | IllegalAccessException
							| InvalidCritterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
			
			
	        gpMenu.add(makeCrit, 1, 1);
	        gpMenu.add(critType, 2, 1);
	        gpMenu.add(critNum, 3, 1);
	        gpMenu.add(day, 4, 1);
	        gpMenu.add(step, 1, 2);
	        gpMenu.add(show, 2, 2);
	        gpMenu.add(clear, 3, 2);
	        gpMenu.add(night, 4, 2);
			primaryStage.setScene(scene);
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
