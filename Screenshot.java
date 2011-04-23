package rhinoceros;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class Screenshot
{
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static Rectangle screenRectangle = new Rectangle(screenSize);
	private static Robot robot;

	
	public static BufferedImage getScreenshot() {
		try {
			BufferedImage image = Screenshot.getRobot().createScreenCapture(screenRectangle);
			return image;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error taking Screenshot", 0);
		}return null;
	}
	
	private static Robot getRobot() throws Exception {
		if(Screenshot.robot == null) {
			Screenshot.robot	= new Robot();
		}
		
		return Screenshot.robot;
	}
	
}