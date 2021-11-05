package application.gui;

import java.io.FileWriter;
import java.io.IOException;

import application.map.Map;
import application.sprite.MapTile;
import application.sprite.Monster;
import application.sprite.Obstacle;
import application.sprite.Spike;
import data_structure.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WindowMapEditor extends Scene{
	private Scene mainWindowScene;
	private Stage stage;
	
	private static Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private AnimationTimer timer = null;
	

	private int levelWidth = 0;
	private int row, column;
	private static int WINDOW_WIDTH = 940;
	private static int WINDOW_HEIGH = 720;
	private int TILE_SIZE = 60;
	
	
	public WindowMapEditor(Stage stage, Scene mainWindowScene) {
		super(appRoot, WINDOW_WIDTH, WINDOW_HEIGH);
		this.stage = stage;
		this.mainWindowScene = mainWindowScene;
		this.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
		this.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.D) {
				gameRoot.setLayoutX(gameRoot.getLayoutX() - 10);
			}
			if(e.getCode() == KeyCode.A && gameRoot.getLayoutX() < 0) {
				gameRoot.setLayoutX(gameRoot.getLayoutX() + 10);
			}
		});
		createScene();
	}
	
	protected  void createScene() {
		LinkedList l0 = new LinkedList('0', Color.web("#EDF2F4") );
		LinkedList l1 = new LinkedList('8', Color.web("#8D99AE") );
		LinkedList l2 = new LinkedList('s', Color.web("#EF233C") );
		LinkedList l3 = new LinkedList('m', Color.web("#d90429") );
		LinkedList l4 = new LinkedList('p', Color.web("#06d6a0") );
		l0.add(l1);
		l0.add(l2);
		l0.add(l3);
		l0.add(l4);
		
		String[] map = new Map().getMap();
		levelWidth = map[0].length() * TILE_SIZE;
		
		row = map.length;
		column = map[0].length();
		initUi();
		Rectangle backGround = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGH, Color.AZURE);

		for(int i = 0; i < map.length; i++) {
			String line = map[i];
			for(int j = 0; j < line.length(); j++) {
				switch(line.charAt(j)) {
				case '0':
					MapTile tile0 = new MapTile((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, l0);
					gameRoot.getChildren().add(tile0);
					break;
				case '8':
					MapTile tile1 = new MapTile((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, l1);
					gameRoot.getChildren().add(tile1);
					break;
				case 's':
					MapTile tile2 = new MapTile((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, l2);
					gameRoot.getChildren().add(tile2);
					break;
				case 'm':
					MapTile tile3 = new MapTile((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, l3);
					gameRoot.getChildren().add(tile3);
					break;
				case 'p':
					MapTile tile4 = new MapTile((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, l4);
					gameRoot.getChildren().add(tile4);
					break;
				}
			}
		}
		appRoot.getChildren().addAll(backGround,gameRoot,uiRoot);	
	}
	
	protected void initUi() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 0, 0, 0));
		hbox.prefWidthProperty().setValue(WINDOW_WIDTH);
		hbox.setSpacing(0);
		Button btnMainMenu = new Button("Menu");
		btnMainMenu.setMinWidth(150);
		
		Button btnSave = new Button("Save");
		btnSave.setMinWidth(150);
		
		btnMainMenu.setOnAction(e -> {stage.setScene(mainWindowScene); appRoot = new Pane();});
		btnSave.setOnAction(e -> {this.save();});
		hbox.getChildren().addAll(btnMainMenu,btnSave);
		uiRoot.getChildren().addAll(hbox);
		
	}
	
	public void save() {
		String[] map  =  new String[row];
		StringBuilder sb = new StringBuilder();
		int countPlayer = 0;
		
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < column; c++) {
				MapTile temp = (MapTile) gameRoot.getChildren().get((r * column) + c);
				if(temp.getType() == 'p') {
					countPlayer++;
				}
				sb.append(temp.getType());
			}
			map[r] = sb.toString();
			sb = new StringBuilder();
		}
		
		if(countPlayer > 1)
			return;
	
		try {
			FileWriter fw = new FileWriter("map.txt");

			for(int r = 0; r < map.length; r++) {
				String line = map[r];
				for(int c = 0; c < line.length(); c++) {
					fw.write(line.charAt(c));
				}
				fw.write('\n');
				
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {			
			return;
		}
	}
	

}
