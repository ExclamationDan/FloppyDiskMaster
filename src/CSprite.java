import org.lwjgl.opengl.ARBVertexArrayObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;


public class CSprite extends CCollidable
{
	CMetaTexture m_Texture;
	
	CSprite(String TexturePath)
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
		//Engine.Render.UseShader("default");
		//int CurrentShader = Engine.Render.m_CurrentShader;
		//int Tex = GL20.glGetUniformLocation(CurrentShader,"in_Texture");
		
		//GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_Texture.m_TextureID);
		//GL20.glUniform1i(Tex, m_Texture.m_TextureID);
		
		//System.out.println("Draw!");
		
		ARBVertexArrayObject.glBindVertexArray(m_VAO);
		//System.out.println("VAO Num: "+m_VAO);
		//GL20.glEnableVertexAttribArray(0);
		//GL20.glEnableVertexAttribArray(1);
		 
		// Draw the vertices
		GL11.glDrawArrays(GL11.GL_QUADS, 0, 4);
		
		
		GL20.glDisableVertexAttribArray(0);
		//GL20.glDisableVertexAttribArray(1);
		ARBVertexArrayObject.glBindVertexArray(0);
		
		/*
		GL11.glBegin(GL11.GL_QUADS);	
		for (int V = 0; V < 4;V++)
		{
			//System.out.println(V);
			GL11.glTexCoord2f(m_Vertices[V].s,m_Vertices[V].t);
			GL11.glVertex2f(m_Vertices[V].x,m_Vertices[V].y);
		}
		*/
		GL11.glEnd();
		 
	}


}
