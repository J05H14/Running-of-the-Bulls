package application;

import gui.MazeGUIPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	MazeGUIPane root = new MazeGUIPane();
	Scene scene = new Scene(root, 500, 520);
	int row = 0;
	int col = 1;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			scene.getStylesheets().add("application/application.css");
			primaryStage.setTitle("Bull Run");
			primaryStage.setScene(scene);
			primaryStage.show();
			root.startGame(25, 25, 0.3, 4, 1);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
