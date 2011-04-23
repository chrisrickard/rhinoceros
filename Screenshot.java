package rhinoceros;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class Screenshot
{
  public static BufferedImage getScreenshot()
  {
    try
    {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Rectangle screenRectangle = new Rectangle(screenSize);
      Robot robot = new Robot();
      BufferedImage image = robot.createScreenCapture(screenRectangle);
      return image;
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error taking Screenshot", 0);
    }return null;
  }
}