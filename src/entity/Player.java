package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
//	public int hasKey = 0;
//	public int hasFinKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		//COORDINATES TO CENTER PLAYER CHARACTER IN SCREEN
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//HITBOX DIMENSIONS
		solidArea = new Rectangle(8, 20, 32, 32);
		solidArea.x = 8;
		solidArea.y = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = 32 * gp.tileSize;
		worldY = 48 * gp.tileSize;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		
		up1 = setup("Back_Left_Step");
		up2 = setup("Back_Right_Step");
		down1 = setup("Front_Left_Step");
		down2 = setup("Front_Right_Step");
		left1 = setup("Left_Side_Step");
		left2 = setup("Left_Side");
		right1 = setup("Right_Side_Step");
		right2 = setup("Right_Side");
		
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			
		}
		return image;
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.checker.checkTile(this);
			int objIndex = gp.checker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					if((worldY - speed) != 0) {
						worldY -= speed;
					}
					break;
				case "down":
					if(worldY + speed <= gp.worldHeight - (solidArea.height * 2)) {
						worldY += speed;
					}
					break;
				case "left":
					if(worldX + speed != 0) {
						worldX -= speed;
					}
					break;
				case "right":
					if(worldX + speed <= gp.worldWidth - (solidArea.height * 2)) {
						worldX += speed;
					}
					break;
				}
			}
			
			//ANIMATION CLOCK
			spriteCounter++;
			if(spriteCounter > 12) {
				gp.playSE(6);
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}	
		}
	}
	
	public void pickUpObject(int index) {
		
		if(index != 999) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
	}
}
