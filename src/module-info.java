module NeatPlaysPlatformer {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens application.gui to javafx.graphics, javafx.fxml;
}
