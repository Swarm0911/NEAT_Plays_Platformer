package application.gui;
	
import application.map.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class WindowMenu extends Application {
	Stage window = null;
	Scene mainScene = null;
	WindowTestMap testMapWindow = null;
	
	private int WINDOW_WIDTH = 940;
	private int WINDOW_HEIGH = 720;
	private int BUTTON_WIDTH = 800;
	private int VBOX_SPACING = 25;
	
	private Scene mainSceneBuilder() {
		Map m = new Map();
		VBox vb = new VBox();
		vb.prefWidthProperty().setValue(WINDOW_WIDTH);
		vb.prefHeightProperty().setValue(WINDOW_HEIGH);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(VBOX_SPACING);
		
		Button btnTestMap = new Button("Test Map");
		btnTestMap.setMinWidth(BUTTON_WIDTH);
		btnTestMap.setOnAction(e -> window.setScene(new WindowTestMap(window,mainScene)));
		
		Button btnTrainAI = new Button("Evolve AI");
		btnTrainAI.setMinWidth(BUTTON_WIDTH);
		btnTrainAI.setOnAction(e -> window.setScene(new WindowEvolveAI(window,mainScene)));
		
		Button btnTestAI = new Button("Test AI");
		btnTestAI.setMinWidth(BUTTON_WIDTH);
		btnTestAI.setDisable(true);
		
		Button btnMapEditor = new Button("Map Editor");
		btnMapEditor.setMinWidth(BUTTON_WIDTH);
		btnMapEditor.setOnAction(e -> window.setScene(new WindowMapEditor(window,mainScene)));
		
		vb.getChildren().addAll(btnTestMap,btnTrainAI,btnTestAI,btnMapEditor);
		Scene scene = new Scene(vb,WINDOW_WIDTH,WINDOW_HEIGH);
		return scene;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			mainScene = mainSceneBuilder();
			mainScene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			window.setScene(mainScene);
			window.setTitle("Neat plays platformer");
			window.setResizable(false);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
