package application.sprite;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Obstacle extends Sprite {

	public Obstacle(int xCordinate,int yCordinate, int width,int height, Color color) {
		super(xCordinate, yCordinate, width, height, color);
	}
	@Override
	public void intersect(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intersectWithDeadly(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void intersectWithPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

}
