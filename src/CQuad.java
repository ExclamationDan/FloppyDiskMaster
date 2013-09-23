import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;

public class CQuad 
{
	CVec2 m_Vertices[] = new CVec2[4];
	int 	m_Height = 100;
	int 	m_Width = 100;
	CVec2 	m_Pos;
	
	// 0=Pos, 1=Texcoord 
	// This holds a "pointer" to the actual data on the GPU.
	int m_VBO = 0;
	
	// This holds a shader state "pointer" describing how to draw the data on the GPU.
	int m_VAO = 0;

	float m_Matrix[] = {1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1};
	
	CQuad()
	{
		SetSize(5,5);
	}
	
	CQuad(int Height, int Width)
	{
		SetSize(Height,Width);	
	}
	
	void SetSize(int Height,int Width)
	{
		// Only update if things change.
		if (Height == m_Height && Width == m_Width) {return;}
		
		m_Height = Height;
		m_Width = Width;
		
		// Note, OpenGL draws vertices in a counter clockwise motion.
		//Left Down
		m_Vertices[0] = new CVec2(0,0,0,0);
		
		//Right Down
		m_Vertices[1] = new CVec2(m_Width,0,1,0);
		
		//Right Up
		m_Vertices[2] = new CVec2(m_Width,m_Height,1,1);
		
		//Left Up
		m_Vertices[3] = new CVec2(0,m_Height,0,1);	
		
		
		GLUpdateDrawBuffers(m_Vertices);
	}
	
	void SetPos(float X, float Y)
	{
		m_Pos.x = X;
		m_Pos.y = Y;
		
		m_Matrix[12] = m_Pos.x;
		m_Matrix[13] = m_Pos.y;
	}
	
	FloatBuffer Array_ToFB(CVec2[] A)
	{
		float[] Data = new float[A.length*4];
		int Index = 0;
		for (int i = 0; i < A.length; i++)
		{
			float[] ParseArray = A[i].ToArray();
			for (int E = 0; E < ParseArray.length; E++)
			{	
				Data[Index] = ParseArray[E];
				Index++;
				
			}
		}
		
		ByteBuffer BB = ByteBuffer.allocateDirect( (A.length * 4) * 4);
		BB.order(ByteOrder.nativeOrder());
		FloatBuffer FB = BB.asFloatBuffer();
		//FB.position(0);
		FB.put(Data);
		FB.position(0);
		FB.flip();
		
		return FB;
	}
	
	void GLUpdateDrawBuffers(CVec2[] Vertex_TexData)
	{
		System.out.println("GLUpdateDrawBuffers(): Called");
		GL15.glDeleteBuffers(m_VBO);
		
		
		//Create VAO buffer.
		m_VAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(m_VAO);
		
		
		// Create VBO buffer, Upload Vert Data
		m_VBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, m_VBO);
		GL15.glBufferData(m_VBO, Array_ToFB(m_Vertices), GL15.GL_STATIC_DRAW);
		
		// Pos
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT,false, 16, 0);
		// Texcoord (Why does a zero offset make this work, shouldn't it be 8 bytes?)
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT,false, 16, 0);
		
		//Finish and save VAO state.
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
	}
	
	
	
}
