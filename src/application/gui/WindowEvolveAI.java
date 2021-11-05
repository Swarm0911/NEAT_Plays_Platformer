package application.gui;

import java.util.ArrayList;

import application.sprite.Moves;
import application.sprite.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import neat.main.Client;
import neat.main.Neat;

public class WindowEvolveAI extends WindowBase{
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Client> clients;
	
	private Neat neat;
	private int distance = 0;
	private int generation = 1;
	private int elapsedTime = 1;
	
	private int NUMBER_OF_CLIENT = 100;
	private int MAX_GENERATION = 200;
	private int LEARNING_TIME = 5 ;
	private int RAPID_EVOLUTION = 1;
	
	private Label labelGeneration;
	Rectangle timeElapsedVisualization;
	private int updateRectangle = (60 * LEARNING_TIME) / 300;
	public WindowEvolveAI(Stage stage, Scene mainWindowScene) {
		super(stage, mainWindowScene);
		this.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
		createScene();
		neat = new Neat(8 ,3 ,NUMBER_OF_CLIENT);
		clients = neat.getClients().getData();
	}

	@Override
	protected void CreateSufficentPlayer(int x, int y) {
		for(int i = 0; i < NUMBER_OF_CLIENT; i++) {
			Player player = new Player((int)(x*TILE_SIZE), (int)(y*TILE_SIZE) , TILE_SIZE, TILE_SIZE, Color.web("#06d6a0"), this);
			players.add(player);
			movables.add(player);
			gameRoot.getChildren().add(player);
			player.translateXProperty().addListener((observabel,oldValue,newValue)->{
							
							int offset = newValue.intValue();
							if( offset > WINDOW_WIDTH/2 && offset < (levelWidth - WINDOW_WIDTH/2)) {
							 if(offset > distance) {
								 distance = offset;
							 }
								
							}
						});
		}
	}

	@Override
	protected void initUi() {
		HBox hboxTop = new HBox();
		hboxTop.setPadding(new Insets(10, 0, 0, 0));
		hboxTop.prefWidthProperty().setValue(WINDOW_WIDTH);
		hboxTop.setSpacing(0);
		Button btnMainMenu = new Button("Menu");
		btnMainMenu.setMinWidth(150);
		Button btnStart = new Button("Start");
		btnStart.setMinWidth(WINDOW_WIDTH - 300);
		Button btnReset = new Button("Reset");
		btnReset.setMinWidth(150);
		btnReset.setDisable(true);
		
		btnMainMenu.setOnAction(e -> {stage.setScene(mainWindowScene); appRoot = new Pane();});
		btnStart.setOnAction(e -> {startTimer(); btnStart.setDisable(true);btnReset.setDisable(false);});
		btnReset.setOnAction(e -> {
			reset();
			btnReset.setDisable(true);
			btnStart.setDisable(false);
			neat = new Neat(8 ,3 ,NUMBER_OF_CLIENT);
			clients = neat.getClients().getData();
			generation = 1;
			labelGeneration.setText("Generation " + generation);
			timeElapsedVisualization.setWidth(1);
			elapsedTime = 1;
			distance = 0;
			
		});

		hboxTop.getChildren().addAll(btnMainMenu, btnStart, btnReset);
		
		HBox hboxBottom = new HBox();
		hboxBottom.prefWidthProperty().setValue(WINDOW_WIDTH);
		hboxBottom.setSpacing(0);
		hboxBottom.setAlignment(Pos.BASELINE_CENTER);
		labelGeneration = new Label("Generation " + generation);
		hboxBottom.getChildren().addAll(labelGeneration);
		
		VBox vbox = new VBox();
		vbox.setSpacing(25);
		vbox.getChildren().addAll(hboxTop,hboxBottom);
		
		timeElapsedVisualization = new Rectangle(0, 105, 1, 60);
		Rectangle background = new Rectangle(0, 105, 940, 60);
		background.setFill(Color.TRANSPARENT);
		background.setStroke(Color.web("#06d6a0"));
		background.setStrokeWidth(5);
		timeElapsedVisualization.setFill(Color.web("#06d6a0"));
		uiRoot.getChildren().addAll(timeElapsedVisualization,background, vbox);
		
	}

	@Override
	protected void update() {
		if(elapsedTime % (updateRectangle) == 0 ) {
			timeElapsedVisualization.setWidth(timeElapsedVisualization.getWidth() + 3.2);
		}
		if(distance > WINDOW_WIDTH/2)
			gameRoot.setLayoutX(- (distance - WINDOW_WIDTH/2));
		for(int i = 0; i < NUMBER_OF_CLIENT; i++) {
			double[] direction = players.get(i).playerView();
			int command = max(clients.get(i).calculate(direction));
			
			switch (command) {
				case 0:
					players.get(i).moveX(-5);
					break;
				case 1:
					players.get(i).moveX(5);
					break;
				case 2:
					players.get(i).jump();
					break;
			}
			clients.get(i).setScore(players.get(i).getScore());
		}
		for(Moves m: movables) {
			m.update();
		}
		
		if( ((60 * LEARNING_TIME) / elapsedTime) == 0 ) {
			for(int i = 0; i < players.size(); i++) {
				players.get(i).reset();
				}
			for(Moves m: movables) {
				m.reset();
			}
			if(generation < MAX_GENERATION) {
				for(int e = 0; e < RAPID_EVOLUTION; e++)
						neat.evolve();
				generation++;
				labelGeneration.setText("Generation " + generation);
			}
			elapsedTime = 1;
			distance = 0;
			gameRoot.setLayoutX(distance);
			timeElapsedVisualization.setWidth(0);
		}
		elapsedTime++;
		
	}
	
	public int max(double[] array) {
		double max = 0;
		int index  = -1;
		for(int i = 0; i < array.length; i++)
			if(array[i] > max) {
				max = array[i];
				index = i;
			}
		return index;
	}

}
