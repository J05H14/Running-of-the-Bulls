package gui;

import runningFX.*;


import javax.swing.JOptionPane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MazeGUIPane extends BorderPane{
	private Label[][] labels;
	StreetMap sM;
	GridPane gp = new GridPane();
	private Label fool;
	int fRow = 0, fCol = 1;
	private int headStart;


	public void startGame(int row, int col, double percentOfWalls, int headStart, int numBulls){
		sM = new StreetMap(row, col, percentOfWalls);
		this.headStart = headStart;
		
		setUpLabels(numBulls);

		Label title = new Label("Map");
		HBox ttl = new HBox();
		ttl.getStyleClass().add("title");

		ttl.getChildren().add(title);

		Button b = new Button("Run");

		b.setMinWidth(20);
		HBox button = new HBox();
		button.getChildren().add(b);

		b.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<Event>(){

			@Override
			public void handle(Event event) {
				sM = new StreetMap(25, 25, .3);
				sM.setFoolLocation(0, 1);
				fRow = 0;
				fCol = 1;
				setUpLabels(numBulls);
			}

		});
		
		Label win = new Label("You Win!");
		
		if(labels[sM.getRows() - 1][sM.getCols() - 2].getStyleClass().contains("fool")){
			button.getChildren().add(win);
		}
				
		setBottom(button);
		setTop(ttl);
		setCenter(gp);
	}

	private void setUpLabels(int numBulls) {

		labels = new Label[sM.getRows()][sM.getCols()];


		fool = labels[0][1];

		for (int row = 0; row < labels.length; row++){
			for (int col = 0; col < labels[row].length; col++) {
				Label l = null;
				if(row == 0 && col == 1){
					l = new Label("S");
				}
				else if(row == labels.length - 1 && col == labels[row].length - 2){
					l = new Label("E");
				}
				else{
					l = new Label();
				}

				setUpLabel(l, row, col);

				labels[row][col] = l;
				gp.add(l, col, row);
			}
		}

		setUpFool(numBulls);

	}

	private void setUpLabel(Label l, int row, int col) {
		l.setPrefHeight(20);
		l.setPrefWidth(20);

		l.getStyleClass().remove("startEnd");
		l.getStyleClass().remove("wall");
		l.getStyleClass().remove("free");

		fool = labels[row][col];

		if(sM.blockValue(row, col) == 'W'){
			l.getStyleClass().add("wall");
		}
		else if(sM.blockValue(row, col) == 'S'){
			l.getStyleClass().add("startEnd");
		}
		else if(sM.blockValue(row, col) == 'E'){
			l.getStyleClass().add("startEnd");
		}
		else{
			l.getStyleClass().add("free");
		}


		l.setOnMouseClicked(new EventHandler<InputEvent>(){

			@Override
			public void handle(InputEvent event) {
				if(!(row == 0 || col == 0 || row == sM.getRows() - 1 || col == sM.getCols() - 1)){
					if(l.getStyleClass().contains("free")){
						l.getStyleClass().remove("free");
						l.getStyleClass().add("wall");
					}
					else if(l.getStyleClass().contains("wall")){
						l.getStyleClass().remove("wall");
						l.getStyleClass().add("free");
					}
				}

			}

		});

	}

	public void setUpFool(int numBulls){
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
			int moveCount = 0;
			@Override
			public void handle(KeyEvent event) {
			//	JOptionPane.showMessageDialog(null, row);
				if(event.getCode() == KeyCode.DOWN){
					if(!(labels[fRow + 1][fCol].getStyleClass().contains("wall"))){
						fool = labels[fRow + 1][fCol];
						labels[fRow][fCol].getStyleClass().clear();
						fool.getStyleClass().clear();
						sM.setFoolLocation(fRow + 1, fCol);
						fool.getStyleClass().add("fool");
						if(sM.blockValue(fRow, fCol) == 'S'){
							labels[fRow][fCol].getStyleClass().add("startEnd");
						}
						else{
							labels[fRow][fCol].getStyleClass().add("free");
						}
						fRow++;
						moveCount++;
					}
				}
				if(event.getCode() == KeyCode.RIGHT){
					if(!(labels[fRow][fCol + 1].getStyleClass().contains("wall"))){
						fool = labels[fRow][fCol + 1];
						labels[fRow][fCol].getStyleClass().clear();
						fool.getStyleClass().clear();
						sM.setFoolLocation(fRow, fCol + 1);
						fool.getStyleClass().add("fool");
						labels[fRow][fCol].getStyleClass().add("free");
						fCol++;
						moveCount++;
					}
				}
				if(event.getCode() == KeyCode.UP){
					if(!(labels[fRow - 1][fCol].getStyleClass().contains("wall"))){
						fool = labels[fRow - 1][fCol];
						labels[fRow][fCol].getStyleClass().clear();
						fool.getStyleClass().clear();
						sM.setFoolLocation(fRow - 1, fCol);
						fool.getStyleClass().add("fool");
						if(sM.blockValue(fRow, fCol) == 'E'){
							labels[fRow][fCol].getStyleClass().add("startEnd");
						}
						else{
							labels[fRow][fCol].getStyleClass().add("free");
						}
						fRow--;
						moveCount++;
					}
				}
				if(event.getCode() == KeyCode.LEFT){
					if(!(labels[fRow][fCol - 1].getStyleClass().contains("wall"))){
						fool = labels[fRow][fCol - 1];
						labels[fRow][fCol].getStyleClass().clear();
						fool.getStyleClass().clear();
						sM.setFoolLocation(fRow, fCol - 1);
						fool.getStyleClass().add("fool");
						labels[fRow][fCol].getStyleClass().add("free");
						fCol--;
						moveCount++;
					}
				}
				if(moveCount > headStart){
					for(int i = 0; i < numBulls; i++){
						Bulls bull = new Bulls(sM, 2);
					}
				}
			}
		};
		setOnKeyPressed(keyEvent);
	}

	public Label[][] getLabels() {
		return labels;
	}

	public void setLabels(Label[][] labels) {
		this.labels = labels;
	}

	public Label getFool() {
		return fool;
	}

	public void setFool(Label fool) {
		this.fool = fool;
	}

	public int getHeadStart() {
		return headStart;
	}

	public void setHeadStart(int headStart) {
		this.headStart = headStart;
	}

}
