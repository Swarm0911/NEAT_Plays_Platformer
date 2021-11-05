package application.gui;

import java.util.ArrayList;

import application.map.Map;
import application.sprite.Monster;
import application.sprite.Moves;
import application.sprite.Obstacle;
import application.sprite.Player;
import application.sprite.Spike;
import application.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public abstract class WindowBase extends Scene{
	Scene mainWindowScene;
	Stage stage;
	
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	protected ArrayList<Moves> movables = new ArrayList<Moves>();
	
	
	protected static Pane appRoot = new Pane();
	protected Pane gameRoot = new Pane();
	protected Pane uiRoot = new Pane();
	protected AnimationTimer timer = null;

	protected int levelWidth = 0;
	protected static int WINDOW_WIDTH = 940;
	protected static int WINDOW_HEIGH = 720;
	protected int TILE_SIZE = 60;
	
	
	protected abstract void CreateSufficentPlayer(int x, int y);
	
	protected abstract void initUi();
	
	protected abstract void update();
	
	public WindowBase(Stage stage, Scene mainWindowScene){
		super(appRoot, WINDOW_WIDTH, WINDOW_HEIGH);
		this.stage = stage;
		this.mainWindowScene = mainWindowScene;
		
	}
	
	public ArrayList<Sprite> getSprites(){
		return sprites;
	}
	
	
	protected  void createScene() {
		String[] map = new Map().getMap();
		levelWidth = map[0].length() * TILE_SIZE;

		initUi();
		Rectangle backGround = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGH, Color.AZURE);

		int playerPositionX = 0;
		int playerPositionY = 0;
		for(int i = 0; i < map.length; i++) {
			String line = map[i];
			for(int j = 0; j < line.length(); j++) {
				switch(line.charAt(j)) {
				case '0':
					break;
				case '8':
					Obstacle platformUp = new Obstacle((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, Color.web("#8D99AE"));
					gameRoot.getChildren().add(platformUp);
					sprites.add(platformUp);
					break;
				case 's':
					Spike spike = new Spike((int)(j*TILE_SIZE), (int)(i*TILE_SIZE) , TILE_SIZE, TILE_SIZE, Color.web("#EF233C"));
					gameRoot.getChildren().add(spike);
					sprites.add(spike.getHitBox());
					break;
				case 'm':
					Monster monster = new Monster((int)(j*TILE_SIZE), (int)(i*TILE_SIZE), TILE_SIZE, TILE_SIZE, Color.web("#EF233C"), this);
					gameRoot.getChildren().add(monster);
					sprites.add(monster.getHitBox());
					movables.add(monster);
					break;
				case 'p':
					playerPositionX = j;
					playerPositionY = i;
					break;
				}
			}
		}
		
		CreateSufficentPlayer(playerPositionX,playerPositionY);
		appRoot.getChildren().addAll(backGround,gameRoot,uiRoot);	
		
	}

	
	protected void startTimer() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				update();
			}
		};
		timer.start();
	}
	
	protected void reset() {
		timer.stop();
		timer = null;
		gameRoot.setLayoutX(0);
		for(Moves m : movables) {
			m.reset();
		}
	}
	

}
