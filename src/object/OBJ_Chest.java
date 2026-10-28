package object;

import java.io.IOException;
import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
	GamePanel gp;
	public OBJ_Chest(GamePanel gp) {
		 name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/16bp-chest-1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
