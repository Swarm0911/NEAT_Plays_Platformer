package application.sprite;

import application.sprite.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Sprite extends Rectangle{
	
	private int spriteHeight;
	private int spriteWidth;
	
	public Sprite(int xCordinate,int yCordinate,int width, int height, Color color) {
		this.setTranslateX(xCordinate);
		this.setTranslateY(yCordinate);
		this.setHeight(height);
		this.setWidth(width);
		this.setFill(color);
		this.setStrokeWidth(1);
		this.setStroke(Color.web("#2B2D42"));
		
		spriteHeight = height;
		spriteWidth = width;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
	}
	
	public int getSpriteWidth() {
		return spriteWidth;
	}
	
	public abstract void intersect(Sprite sprite);
	
	public abstract void intersectWithDeadly(Sprite sprite);
	
	public abstract void intersectWithPlayer(Player player);

	
}
