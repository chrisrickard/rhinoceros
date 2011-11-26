package rhinoceros;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WebServerHandler extends AbstractHandler
{
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("image/png");
		response.setStatus(200);
		baseRequest.setHandled(true);

		BufferedImage image = Screenshot.getThumbnail();
		ImageIO.write(image, "png", response.getOutputStream());
	}
}