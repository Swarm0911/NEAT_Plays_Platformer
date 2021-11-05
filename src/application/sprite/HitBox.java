package application.sprite;

import javafx.scene.paint.Color;

public class HitBox extends Sprite {
	Sprite container;
	public HitBox(int xCordinate, int yCordinate, int width, int height, Color color, Sprite container) {
		super(xCordinate, yCordinate, width, height, color);
		this.container = container;
	}

	@Override
	public void intersect(Sprite sprite) {
		container.intersect(sprite);

	}

	@Override
	public void intersectWithDeadly(Sprite sprite) {
		container.intersectWithDeadly(sprite);
		
	}

	@Override
	public void intersectWithPlayer(Player player) {
		container.intersectWithPlayer(player);
		
	}

}
