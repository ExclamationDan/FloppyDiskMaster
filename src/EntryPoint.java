

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;

public class EntryPoint {

	public static void main(String[] args) 
	{	
		EntryPoint Render = new EntryPoint();
		Render.start();
	}
	
	
	public void start() 
	{

		try 
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} 
		
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
 

		while (!Display.isCloseRequested()) 
		{		
			GL11.glMatrixMode(GL11.GL_PROJECTION);		
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);	
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
			GL11.glColor3f(0.9f,0.9f,0.3f);

			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(100,100);
			GL11.glVertex2f(400,100);
			GL11.glVertex2f(400,400);
			GL11.glVertex2f(100,400);
			GL11.glEnd();
			
			Display.update();
		}
		
		Display.destroy();

}
}


 
