package application.sprite;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Spike extends SpriteWhithHitbox{

	public Spike(int xCordinate, int yCordinate, int width, int height, Color color) {
		super(xCordinate, yCordinate, width, height, color);
		this.hitBox = new HitBox(xCordinate + 10, yCordinate + 20, width - 20, height - 20, Color.RED, this);
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
		player.die();
		
	}
	

}
