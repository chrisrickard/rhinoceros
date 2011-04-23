package rhinoceros;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientConnection
  implements Runnable
{
  private Socket socket = null;
  private BufferedReader bufferedReader = null;
  private DataOutputStream out = null;

  public ClientConnection(Socket socket)
  {
    this.socket = socket;
  }

  public void run()
  {
    try
    {
      this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      String line;
      while ((line = this.bufferedReader.readLine()) != null)
        System.out.println("recieved :: " + line);
    }
    catch (Exception e)
    {
    }
  }
}