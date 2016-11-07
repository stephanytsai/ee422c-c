
package assignment5;

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
 
public class ViewComponent extends Application {
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		//grid.setPadding(new Insets(25,25,25,25));
		
		Scene scene = new Scene(grid, 500, 500);
		
        
		int x=0; //params.width
		int y=0; //params.height
		
		//perhaps iterate through CritterCollection and place each critter at its new location
		/* this should be in the Critter.displayWorld method
		 * while (I.hasnext){
		 * 		current=I.next
		 * 		make a shape depending on critter and position it at curr.xcoord, curr.ycoord
		 * 		grid.add(shape, y, x)
		 * 			
		 */
		
		

		for(x=0;x<20;x++){
			//create shapes
			for(y=0;y<20;y++){
				Circle cir = new Circle(x,y,5);
				Rectangle rec = new Rectangle(x,y,10,10);
				
				//triangle
				Polygon tri = new Polygon();
		        tri.getPoints().addAll(new Double[]{
		            0.0, 0.0,
		            10.0, 0.0,
		            5.0, 10.0 });
		        
		        //diamond
		        Polygon diam = new Polygon();
		        diam.getPoints().addAll(new Double[]{
		            5.0, 0.0,
		            10.0, 5.0,
		            5.0, 10.0,
		            0.0, 5.0  });
		        
				//Star
		        Polygon star = new Polygon();
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
		        
		        //conditions will actually by if(curr.getshape == SHAPE) -> add shape
				if(x<=3){
					grid.add(star, y, x);
				}
				if(x>3 && x<=7){
					grid.add(cir, y, x);
				}
				if(x>7 && x<=11){
					grid.add(diam, y, x);
				}
				if(x>11 && x<=15){
					grid.add(rec, y, x);
				}
				if(x>15){
					grid.add(tri, y, x);
				}
				
			}
			
			
			
		}
		

		//grid.setGridLinesVisible(true);
		
	
		primaryStage.setTitle("Critter World View");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}