package application.sprite;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import data_structure.LinkedList;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile extends Rectangle{
	LinkedList type;
	
	public MapTile(int xCordinate,int yCordinate,int width, int height, LinkedList ll) {
	
		this.type = ll;
		
		this.setFill(this.type.getColor());
		this.setTranslateX(xCordinate);
		this.setTranslateY(yCordinate);
		this.setHeight(height);
		this.setWidth(width);
		this.setStrokeWidth(1);
		this.setStroke(Color.web("#2B2D42"));
		this.setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY)
				this.type = type.next();
			else
				this.type = type.previous();
			this.setFill(this.type.getColor());
			});
		
	}	
	
	public char getType() {
		return type.getType();
	}
}
