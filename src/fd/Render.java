package fd;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Render extends EngineModule
{
	
	
	int m_Width;
	int m_Height;
	
	SimpleQuad Q = null;
	
	Sprite S;
	
	int m_CurrentShader;
	String LastAlias = "";
	
	void UseShader(String AliasName)
	{
		if (LastAlias != AliasName)
		{
			Engine.Resource.UseShader(AliasName);
			LastAlias = AliasName;
		}
		
	}
	
	public boolean CanRender()
	{
		return !Display.isCloseRequested();
	}
	
	public void SetDimensions(int Width, int Height)
	{
		if (Width <= 0 )
			Width = 500;
		if (Height <= 0)
			Height = 500;
		
		m_Width = Width;
		m_Height = Height;
	}
	
	public void InitOpenGL(int Height, int Width)
	{
		SetDimensions(Height, Width);

		try 
		{
			PixelFormat pixelFormat = new PixelFormat();
			ContextAttribs contextAtrributes = new ContextAttribs(3, 2).withProfileCore(true).withForwardCompatible(true); 
			Display.setDisplayMode(new DisplayMode(m_Width, m_Height));
			Display.create(pixelFormat, contextAtrributes);
			GL11.glViewport(0, 0, m_Width, m_Height);
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(-1);
		}

		// Map the internal OpenGL coordinate system to the entire screen
		GL11.glViewport(0, 0, m_Width, m_Height);
		
		
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		
		
		//Engine.Resource.LoadShader("default", "res/shaders/default.vertex.txt","res/shaders/default.fragment.txt");
		//Engine.Resource.UseShader("default");
		
		
		Q = new SimpleQuad(0.1f,0.1f);
			
	}
	
	public void Think()
	{
		//GL11.glMatrixMode(GL11.GL_PROJECTION);		
		//GL11.glLoadIdentity();
		//GL11.glOrtho(0,m_Height,m_Width,0,1,-1);

		//GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
		GL11.glClearColor(0.0f, 0.5f, 1.0f, 1.0f);
		
		
		//Sprite S = new Sprite("res/sprites/player/player_idle.tga");
		//S.SetSize(100, 100);
		//S.Draw();
		
		if (!CanRender()) {Display.destroy();}
		
		
		Q.Draw();
		Display.update();
	}

}
