package object;

import java.io.IOException;
import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {
	GamePanel gp;
	public OBJ_Boots(GamePanel gp) {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/16bp-boot-1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
