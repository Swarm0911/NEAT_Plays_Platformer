package application.sprite;

import java.util.ArrayList;

import application.sprite.Sprite;
import application.gui.WindowBase;
import application.gui.WindowTestMap;
import application.sprite.HitBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player extends SpriteWhithHitbox implements Moves{

	private WindowBase gameWindow;
	private double score;
	private Boolean jump = false;
	private Boolean canMove = true;
	private int verticalMovement = 10;
	private int startingPositionX;
	private int startingPositionY;
	
	public Player(int xCordinate, int yCordinate, int width, int height, Color color, WindowBase gameWindow) {
		super(xCordinate, yCordinate + 1, width, height, color);
		this.hitBox = new HitBox(xCordinate + 2, yCordinate + 10, width - 4 , height - 10, color, this);
		this.gameWindow = gameWindow;
		startingPositionX = xCordinate;
		startingPositionY = yCordinate;
		
		
	}

	@Override
	public void intersect(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void intersectWithDeadly(Sprite sprite) {
		this.die();
		
	}

	@Override
	public void intersectWithPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveX(int value) {
		ArrayList<Sprite> sprites = gameWindow.getSprites();
		boolean moveRight = value > 0;
		if(canMove) {
			for(int i = 0; i <= Math.abs(value); i++) {
				for(Sprite s: sprites) {
					if(hitBox.getBoundsInParent().intersects(s.getBoundsInParent())) {
						if(moveRight) {
							if(hitBox.getTranslateX() + hitBox.getWidth() == s.getTranslateX()) {
								s.intersectWithPlayer(this);
								hitBox.setTranslateX(hitBox.getTranslateX() - 2);
								this.setTranslateX(this.getTranslateX() - 2);
								return;
							}
						}
						else {
							if(hitBox.getTranslateX()  == s.getTranslateX() + s.getSpriteWidth()) {
								s.intersectWithPlayer(this);
								hitBox.setTranslateX(hitBox.getTranslateX() + 2);
								this.setTranslateX(this.getTranslateX() + 2);
								return;
							}
						}
					}
				}
				hitBox.setTranslateX(hitBox.getTranslateX() - (value > 0 ? -1 : 1));
				this.setTranslateX(this.getTranslateX() - (value > 0 ? -1 : 1));
				if(this.getTranslateX() < 0) {
					hitBox.setTranslateX(hitBox.getTranslateX() + 1);
					this.setTranslateX(this.getTranslateX() + 1);
				}
			}		
			this.score = this.getTranslateX();
		}
	}

	@Override
	public void moveY(int value) {
		if(canMove) {
			ArrayList<Sprite> sprites = gameWindow.getSprites();
			boolean moveDown = value > 0;
			for(int i = 0; i <= Math.abs(value); i++) {
				for(Sprite s: sprites)
					if(hitBox.getBoundsInParent().intersects(s.getBoundsInParent()))
						if(moveDown) {
							if(hitBox.getTranslateY() + hitBox.getHeight()  == s.getTranslateY() ) {
								s.intersectWithPlayer(this);
								hitBox.setTranslateY(hitBox.getTranslateY() - 2);
								this.setTranslateY(this.getTranslateY() - 2);
								jump = true;
								return;
							}
						}
						else {
							if(hitBox.getTranslateY()   == s.getTranslateY() + s.getSpriteHeight()) {
								s.intersectWithPlayer(this);
								verticalMovement += 5;
								return;
							}
						}
				hitBox.setTranslateY(hitBox.getTranslateY() + (value > 0 ? 1 : -1));
				this.setTranslateY(this.getTranslateY() + (value > 0 ? 1 : -1));
				
			}
		}
	}
	
	@Override
	public void update() {
		if(verticalMovement < 10) {
			verticalMovement += 1;
		}
		this.moveY(verticalMovement);
		
	}
	
	public double getScore() {
		return score;
	}
	
	public void jump() {
		if(jump == true)
			verticalMovement = -20;
		jump = false;
	}
	
	public void die() {
		canMove = false;
		this.setFill(Color.web("#d90429"));
	}
	
	public void reset() {
		this.setTranslateX(startingPositionX);
		this.setTranslateY(startingPositionY + 1);
		hitBox.setTranslateX(this.startingPositionX +2);
		hitBox.setTranslateY(this.startingPositionY +10);
		canMove = true;
		this.setFill(Color.web("#06d6a0"));
	}
	
	private int thereIsSomething(double xCoordinate, double yCoordinate) {
		ArrayList<Sprite> sprites = gameWindow.getSprites();
		for(Sprite s: sprites) {
			if(s.getTranslateX() < xCoordinate && xCoordinate < (s.getTranslateX() + s.getWidth())) {
				if(s.getTranslateY() < yCoordinate && yCoordinate < (s.getTranslateY() + s.getHeight()))
					return 1;
			}
		}
		return 0;
	}
	
	public double[] playerView() {
		double[] directions = new double[8];
		directions[0] = thereIsSomething(this.getTranslateX() - 50, this.getTranslateY() - 50 );
		directions[1] = thereIsSomething(this.getTranslateX() + this.getWidth() / 2, this.getTranslateY() - 50 );
		directions[2] = thereIsSomething(this.getTranslateX() + this.getWidth() + 50, this.getTranslateY() - 50 );
		directions[3] = thereIsSomething(this.getTranslateX() + this.getWidth() + 50, this.getTranslateY() + this.getHeight() / 2 );
		directions[4] = thereIsSomething(this.getTranslateX() + this.getWidth() + 50, this.getTranslateY() + this.getHeight() + 50 );
		directions[5] = thereIsSomething(this.getTranslateX() + this.getWidth() / 2, this.getTranslateY() + this.getHeight() + 50 );
		directions[6] = thereIsSomething(this.getTranslateX() -  50, this.getTranslateY() + this.getHeight() + 50 );
		directions[7] = thereIsSomething(this.getTranslateX() - 50, this.getTranslateY() + this.getHeight() / 2 );
		return directions;
	}
	
	
	
	

}
