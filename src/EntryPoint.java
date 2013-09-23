

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class EntryPoint {

	/*
	public static void main(String[] args) 
	{	
		EntryPoint Render = new EntryPoint();
		Render.start();

	}
	*/
	
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
		
		CSprite Sprite = new CSprite("res/sprites/player/player_idle.tga");
		
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		while (!Display.isCloseRequested()) 
		{		
			GL11.glMatrixMode(GL11.GL_PROJECTION);		
			GL11.glLoadIdentity();
			//GL11.glOrtho(0, 800, 0, 600, 1, -1);	
			GL11.glOrtho(0,800,600,0,1,-1);

			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
			
			
			
			Sprite.Draw();
			Sprite.SetSize(100, 100);
			
			Display.update();
		}
		
		Display.destroy();

}
}


 
