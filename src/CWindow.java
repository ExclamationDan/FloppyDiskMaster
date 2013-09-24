import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class CWindow extends CEngineModule
{
	boolean m_IsOpen;
	int m_Height;
	int m_Width;
	
	static CSprite Sprite;
	
	
	public void InitOpenGL(int Height, int Width)
	{
		m_Height = Height;
		m_Width = Width;
		m_IsOpen = true;
		try 
		{
			Display.setDisplayMode(new DisplayMode(m_Height,m_Width));
			Display.create();
		} 
		
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Engine.Resource.LoadShader("default", "res/shaders/default.vertex.txt","res/shaders/default.fragment.txt");
		Engine.Resource.UseShader("default");
		
		Sprite = new CSprite("res/sprites/player/player_idle.tga");
		//Sprite.SetSize(100, 100);
	}
	public void WindowLoop()
	{	
		m_IsOpen = !Display.isCloseRequested();


		GL11.glMatrixMode(GL11.GL_PROJECTION);		
		GL11.glLoadIdentity();
		//GL11.glOrtho(0, 800, 0, 600, 1, -1);	
		GL11.glOrtho(0,m_Height,m_Width,0,1,-1);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
		GL11.glClearColor(0.0f, 0.5f, 1.0f, 1.0f);
			
			
		Sprite.Draw();

			
		Display.update();
	
		if (!m_IsOpen)
		{
			Display.destroy();
		}
		
	}
	
	public void Think()
	{
		WindowLoop();
	}
	
	

}
