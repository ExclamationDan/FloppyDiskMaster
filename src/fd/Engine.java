package fd;



public class Engine 
{
	static Resource	Resource = new Resource();
	static Render	Render = new Render();
	
	static void Engine_Start()
	{
		Render.InitOpenGL(800,600);
		Mainloop();
	}
	
	static void Mainloop()
	{
		while(Render.CanRender())
		{
			Resource.Think();
			Render.Think();
		}
		
		
	}

	public static void main(String[] args) 
	{	
		Engine_Start();
	}
}
