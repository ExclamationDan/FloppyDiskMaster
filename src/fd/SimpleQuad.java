package fd;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class SimpleQuad 
{
	private int vaoId = 0;
	private int vboId = 0;
	private int vboiId = 0;
	private int indicesCount = 0;
	
	// Vertices, the order is not important.
	float[] vertices = {
	-0.5f, 0.5f, 0f,    // Left top         ID: 0
	-0.5f, -0.5f, 0f,   // Left bottom      ID: 1
	0.5f, -0.5f, 0f,    // Right bottom     ID: 2
	0.5f, 0.5f, 0f      // Right left       ID: 3
	};
	
	static byte[] indices = {
			// Left bottom triangle
			0, 1, 2,
			// Right top triangle
			2, 3, 0
			};
	
	
	public static float[] GetQuad(float Width, float Height)
	{
		return new float[] 
		{
			0.0f,Height,0f,
			0.0f,0.0f,0.0f,
			Width,0.0f,0.0f,
			Width,Height,0.0f,
		};
				
		
	}
	
	public SimpleQuad(float Width, float Height)
	{
		
		
		// Sending data to OpenGL requires the usage of (flipped) byte buffers
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		verticesBuffer.put(GetQuad(Width,Height));
		verticesBuffer.flip();
		 
		
		indicesCount = indices.length;
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
		indicesBuffer.put(indices);
		indicesBuffer.flip();


		// Create a new Vertex Array Object in memory and select it (bind)

		// A VAO can have up to 16 attributes (VBO's) assigned to it by default
		vaoId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoId);
		 

		// Create a new Vertex Buffer Object in memory and select it (bind)
		// A VBO is a collection of Vectors which in this case resemble the location of each vertex.

		vboId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
		// Put the VBO in the attributes list at index 0
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		// Deselect (bind to 0) the VBO
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

		 

		// Deselect (bind to 0) the VAO
		GL30.glBindVertexArray(0);

		// Create a new VBO for the indices and select it (bind)
		vboiId = GL15.glGenBuffers();

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		// Deselect (bind to 0) the VBO
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
	}
	
	public void Draw()
	{
		// Bind to the VAO that has all the information about the vertices
		GL30.glBindVertexArray(vaoId);
		GL20.glEnableVertexAttribArray(0);

		// Bind to the index VBO that has all the information about the order of the vertices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
		 
		// Draw the vertices
		GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);
		 
		// Put everything back to default (deselect)
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	void Destroy()
	{
		
	}
	
	
}
