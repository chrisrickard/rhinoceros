package rhinoceros;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Rhinoceros
{
  private SystemTray systemTray;
  private TrayIcon trayIcon;
  private WebServer server;

  public static void main(String[] args)
  {
    Rhinoceros main = new Rhinoceros();
  }

  public Rhinoceros()
  {
    initializeMenu();
    initializeServer();
  }

  private void initializeMenu()
  {
    if (SystemTray.isSupported())
    {
      this.systemTray = SystemTray.getSystemTray();
      Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/rhinoceros/resources/tray.gif"));

      PopupMenu popup = new PopupMenu();

      MenuItem screenshotItem = new MenuItem("Show local server...");
      screenshotItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { Rhinoceros.this.menuItem_openServer();
        }
      });
      popup.add(screenshotItem);

      MenuItem exitItem = new MenuItem("Exit");
      exitItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { Rhinoceros.this.menuItem_exit();
        }
      });
      popup.add(exitItem);

      this.trayIcon = new TrayIcon(image, "Rhinoceros", popup);
      this.trayIcon.setImageAutoSize(true);
      try
      {
        this.systemTray.add(this.trayIcon);
      }
      catch (AWTException e) {
        System.err.println("TrayIcon could not be added.");
      }

    }
    else
    {
      JOptionPane.showMessageDialog(null, "SystemTray not supported, how embarrassing", "Screenshot Error", 0);
      System.exit(0);
    }
  }

  private void initializeServer()
  {
    this.server = new WebServer();
    try
    {
      this.server.Listen();
    }
    catch (Exception e) {
      this.trayIcon.displayMessage("Server Error", e.getMessage(), TrayIcon.MessageType.ERROR);
    }
  }

  private void takeScreenshot()
  {
    BufferedImage image = Screenshot.getScreenshot();
    try
    {
      ImageIO.write(image, "png", new File("screenshot.png"));
      this.trayIcon.displayMessage("Success", "Screeshot taken: screenshot.png", TrayIcon.MessageType.INFO);
    }
    catch (Exception e) {
      this.trayIcon.displayMessage("Screenshot Error", "Screeshot could not be saved: " + e.getMessage(), TrayIcon.MessageType.ERROR);
    }
  }

  public void menuItem_openServer()
  {
    URI uri = null;
    try {
      uri = new URI("http://localhost:" + WebServer.port);
      Desktop.getDesktop().browse(uri);
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void menuItem_exit()
  {
    System.exit(0);
  }
}