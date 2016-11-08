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
	static GridPane grid = new GridPane();
	
	
	@Override 
	public void start(Stage primaryStage) {
		try {
			grid.setGridLinesVisible(true);
			primaryStage.setTitle("Welcome to Critter World");
			Scene scene = new Scene(grid, 500, 500); 
			
			// critNum and makeCrit combine to give user control to create critters
			TextField critType = new TextField();
			critType.setText("Enter Type");
			TextField critNum = new TextField();
			critNum.setText("Enter Number");
			Button makeCrit = new Button();
	        makeCrit.setText(" Make  Critters ");
	        makeCrit.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Made " + critNum.getText() + " " + critType.getText()+ " Critters"); //call makeCritter
	            }
	        });
	        
	        // show btn will display crit world
	        Button show = new Button();
	        show.setText(" Show   Critters");
	        show.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Show Critters"); //actually display world
	            }
	        });
	        
	     // Step btn will call doTimeStep
	        Button step = new Button();
	        step.setText("Update Critters");
	        step.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Critters will be updated"); //actually call doTimeStep
	            }
	        });
			
			
	        grid.add(makeCrit, 1, 1);
	        grid.add(critType, 2, 1);
	        grid.add(critNum, 3, 1);
	        grid.add(show, 1, 2);
	        grid.add(step, 1, 3);
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
