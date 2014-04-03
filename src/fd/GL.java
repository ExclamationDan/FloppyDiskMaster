package fd;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class GL 
{

	public static int createVBOID() 
	{
		return GL15.glGenBuffers(); //Which can only supply you with a single id.
	}
	
	public static void vertexBufferData(int id, FloatBuffer buffer) 
	{
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id); //Bind buffer (also specifies type of buffer)
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); //Send up the data and specify usage hint.
	}
	
	public static void indexBufferData(int id, IntBuffer buffer) 
	{
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	public static void render(int vertexBufferID, int colourBufferID, int numberIndices) 
	{
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colourBufferID);
		GL11.glColorPointer(4, GL11.GL_FLOAT, 0, 0);
	
		//If you are not using IBOs:
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, numberIndices);
	}
		 
}
