import org.lwjgl.opengl.GL11;



public class Engine 
{
	static CResource 	Resource = new CResource();
	static CWindow 		Window = new CWindow();
	static CRender      Render = new CRender();
	
	static void Engine_Start()
	{
		
		Window.InitOpenGL(800,600);
		Mainloop();
	}
	
	static void Mainloop()
	{
		while(Window.m_IsOpen)
		{
			Resource.Think();
			Window.Think();
			Render.Think();
		}
		
		
	}

	public static void main(String[] args) 
	{	
		Engine_Start();

	}
}
