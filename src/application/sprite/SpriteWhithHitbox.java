package application.sprite;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class SpriteWhithHitbox extends Sprite {
	protected HitBox hitBox;
	
	public HitBox getHitBox() {
		return hitBox;
	}

	public SpriteWhithHitbox(int xCordinate, int yCordinate, int width, int height, Color color) {
		super(xCordinate, yCordinate, width, height, color);
	}

	
	
	@Override
	public abstract void intersect(Sprite sprite);

}
