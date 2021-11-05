package application.sprite;

import java.util.ArrayList;

import application.sprite.Sprite;
import application.gui.WindowBase;
import application.gui.WindowTestMap;
import javafx.scene.paint.Color;

public class Monster extends SpriteWhithHitbox implements Moves{
	private WindowBase gameWindow;
	
	private int startingPositionX;
	private int startingPositionY;
	
	private int verticalMovement = 0;
	private int direction = -1;
	
	public Monster(int xCordinate, int yCordinate, int width, int height, Color color, WindowBase gameWindow) {
		super(xCordinate, yCordinate+2, width, height, color);
		this.hitBox = new HitBox(xCordinate + 5, yCordinate + 10, width - 10 , height - 10, color, this);
		this.gameWindow = gameWindow;
		startingPositionX = xCordinate;
		startingPositionY = yCordinate;
	}

	@Override
	public void moveX(int value) {
		ArrayList<Sprite> sprites = gameWindow.getSprites();
		boolean moveRight = value > 0;
		for(int i = 0; i <= Math.abs(value); i++) {
			for(Sprite s: sprites) {
				if(hitBox.getBoundsInParent().intersects(s.getBoundsInParent()))
					if(moveRight) {
						if(hitBox.getTranslateX() + hitBox.getWidth() == s.getTranslateX()) {
							s.intersectWithDeadly(this);
							direction *= -1;
							return;
						}
					}
					else {
						if(hitBox.getTranslateX()  == s.getTranslateX() + s.getSpriteWidth()) {
							s.intersectWithDeadly(this);
							direction *= -1;
							return;
						}
					}
			}
			hitBox.setTranslateX(hitBox.getTranslateX() - (value > 0 ? -1 : 1));
			this.setTranslateX(this.getTranslateX() - (value > 0 ? -1 : 1));
		}	
	}

	@Override
	public void moveY(int value) {
		ArrayList<Sprite> sprites = gameWindow.getSprites();
		boolean moveDown = value > 0;
		for(int i = 0; i <= Math.abs(value); i++) {
			for(Sprite s: sprites) {
				if(hitBox.getBoundsInParent().intersects(s.getBoundsInParent())) {
					if(moveDown) {
						if(hitBox.getTranslateY() + hitBox.getHeight()  == s.getTranslateY() ) {
							s.intersectWithDeadly(this);
							hitBox.setTranslateY(hitBox.getTranslateY() - 2);
							this.setTranslateY(this.getTranslateY() - 2);
							return;
						}
					}
				}
			}
			hitBox.setTranslateY(hitBox.getTranslateY() + 1);
			this.setTranslateY(this.getTranslateY() + 1);
		}
		
	}

	@Override
	public void reset() {
		this.setTranslateX(startingPositionX);
		this.setTranslateY(startingPositionY + 1);
		hitBox.setTranslateX(this.startingPositionX + 5);
		hitBox.setTranslateY(this.startingPositionY + 10);
		
	}

	@Override
	public void intersect(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		moveX(1 * direction);
		if(verticalMovement < 10) {
			verticalMovement += 1;
		}
		this.moveY(verticalMovement);
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
