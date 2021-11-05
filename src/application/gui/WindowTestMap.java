package application.gui;

import java.util.HashMap;

import application.sprite.Moves;
import application.sprite.Player;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WindowTestMap extends WindowBase{
	private HashMap<KeyCode,Boolean> keys = new HashMap<KeyCode,Boolean>();
	private Player player = null;
	
	public WindowTestMap(Stage stage, Scene mainWindowScene) {
		super(stage, mainWindowScene);
		this.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
		this.setOnKeyPressed(event -> { keys.put(event.getCode(), true);});
		this.setOnKeyReleased(event -> { keys.put(event.getCode(), false);});
		createScene();
	}

	@Override
	protected void CreateSufficentPlayer(int x, int y) {
		player = new Player((int)(x*TILE_SIZE), (int)(y*TILE_SIZE) , TILE_SIZE, TILE_SIZE, Color.web("#06d6a0"), this);
		movables.add(player);
		gameRoot.getChildren().add(player);
		sprites.add(player.getHitBox());
		player.translateXProperty().addListener((observabel,oldValue,newValue)->{
				
				int offset = newValue.intValue();
				if( offset > WINDOW_WIDTH/2 && offset < (levelWidth - WINDOW_WIDTH/2)) {
					gameRoot.setLayoutX(- (offset - WINDOW_WIDTH/2));
				}
			});
	}

	@Override
	protected void initUi() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 0, 0, 0));
		hbox.prefWidthProperty().setValue(WINDOW_WIDTH);
		hbox.setSpacing(0);
		Button btnMainMenu = new Button("Menu");
		btnMainMenu.setMinWidth(150);
		Button btnStart = new Button("Start");
		btnStart.setMinWidth(WINDOW_WIDTH - 300);
		Button btnReset = new Button("Reset");
		btnReset.setMinWidth(150);
		btnReset.setDisable(true);
		
		btnMainMenu.setOnAction(e -> {stage.setScene(mainWindowScene); appRoot = new Pane();});
		btnStart.setOnAction(e -> {startTimer(); btnStart.setDisable(true);btnReset.setDisable(false);});
		btnReset.setOnAction(e -> {reset();btnReset.setDisable(true);btnStart.setDisable(false);});
		
		hbox.getChildren().addAll(btnMainMenu, btnStart, btnReset);
		uiRoot.getChildren().addAll(hbox);
		
	}

	@Override
	protected void update() {
		if((isPressed(KeyCode.A) || isPressed(KeyCode.UP) )&& player.getTranslateX() > 5) {
			player.moveX(-5);
		}
		if(isPressed(KeyCode.D) && player.getTranslateX() < (levelWidth-TILE_SIZE)) {
			player.moveX(5);
		}
		
		if(keys.containsKey(KeyCode.W) ? keys.get(KeyCode.W) : false) {
			player.jump();
		}
		for(Moves m: movables) {
			m.update();
		}
		player.playerView();
	}
	

		
	
	private boolean isPressed(KeyCode code) {
		return keys.getOrDefault(code, false);
	}

}
