package object;

import java.io.IOException;

import main.GamePanel;
import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
	GamePanel gp;
	public OBJ_Door(GamePanel gp) {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/16bp-door-1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
