package fd;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;
import org.newdawn.slick.opengl.Texture;





public class Sprite extends Quad
{
	MetaTexture m_Texture;
	
	Sprite(String TexturePath)
	{
		SetTexture(TexturePath);
	}
	
	void SetTexture(String Path)
	{
		m_Texture = Engine.Resource.LoadTexture(Path);			
		SetSize(m_Texture.m_Height,m_Texture.m_Width);
	}
	
	void Draw()
	{	
		System.out.println("DrawSprite!");
		

	}


}
