package fd;
import org.lwjgl.opengl.GL11;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.Util;
import org.newdawn.slick.util.ResourceLoader;


public class Shader 
{
	
	
	int m_ShaderProgram;
	int m_ShaderVert;
	int m_ShaderFrag;
	
	
    public void LoadShader(String ShaderVertex, String ShaderFragment)
    {

        /*
        * create the shader program. If OK, create vertex
        * and fragment shaders
        */
    	m_ShaderProgram=ARBShaderObjects.glCreateProgramObjectARB();

        if(m_ShaderProgram!=0)
        {
        	m_ShaderVert=createVertShader(ShaderVertex);
        	m_ShaderFrag=createFragShader(ShaderFragment);
        }

        /*
        * if the vertex and fragment shaders setup sucessfully,
        * attach them to the shader program, link the sahder program
        * (into the GL context I suppose), and validate
        */
        if(m_ShaderVert !=0 && m_ShaderFrag !=0){
            ARBShaderObjects.glAttachObjectARB(m_ShaderProgram, m_ShaderVert);
            ARBShaderObjects.glAttachObjectARB(m_ShaderProgram, m_ShaderFrag);
            ARBShaderObjects.glLinkProgramARB(m_ShaderProgram);
            ARBShaderObjects.glValidateProgramARB(m_ShaderProgram);

        }
    }

    /*
    * If the shader was setup succesfully, we use the shader. Otherwise
    * we run normal drawing code.
    */
    public void UseShader()
    {

        ARBShaderObjects.glUseProgramObjectARB(m_ShaderProgram);
        
        //release the shader
        //ARBShaderObjects.glUseProgramObjectARB(0);

    }

    /*
    * With the exception of syntax, setting up vertex and fragment shaders
    * is the same.
    * @param the name and path to the vertex shader
    */
    private int createVertShader(String filename)
    {

    	m_ShaderVert= GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        if(m_ShaderVert==0){return 0;}
        String vertexCode="";
        String line;
       
        try
        {
        	System.out.println(filename);  	
            BufferedReader reader=new BufferedReader(new FileReader(filename));
      
            while((line=reader.readLine())!=null)
            {
                vertexCode+=line + "\n";
            }
        }
        catch(Exception e)
        {
            System.out.println("Fail reading vertex shading code");
            return 0;
        }
        /*
        * associate the vertex code String with the created vertex shader
        * and compile
        */
        GL20.glBindAttribLocation(m_ShaderProgram, 0, "in_Position");
        GL20.glBindAttribLocation(m_ShaderProgram, 1, "in_TextureCoord");
        GL20.glShaderSource(m_ShaderVert,vertexCode);
        GL20.glCompileShader(m_ShaderVert);

        //if there was a problem compiling, reset vertShader to zero
        if(!printLogInfo(m_ShaderVert))
        {
        	m_ShaderVert=0;
        }
        //if zero we won't be using the shader
        return m_ShaderVert;
    }

    //same as per the vertex shader except for method syntax
    private int createFragShader(String filename){

    	m_ShaderFrag=GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        if(m_ShaderFrag==0){return 0;}
            String fragCode="";
            String line;
        try
        {
            BufferedReader reader=new BufferedReader(new FileReader(filename));
            while((line=reader.readLine())!=null)
            {
                fragCode+=line + "\n";
            }
        }
        catch(Exception e)
        {
            System.out.println("Fail reading fragment shading code");
            return 0;
        }
        GL20.glShaderSource(m_ShaderFrag, fragCode);
        GL20.glCompileShader(m_ShaderFrag);
        if(!printLogInfo(m_ShaderFrag))
        {
        	m_ShaderFrag=0;
        }

        return m_ShaderFrag;
    }

    private static boolean printLogInfo(int obj)
    {
        IntBuffer iVal = BufferUtils.createIntBuffer(1);
        ARBShaderObjects.glGetObjectParameterARB(obj,
        ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB, iVal);

        int length = iVal.get();
        if (length > 1) 
        {
            // We have some info we need to output.
            ByteBuffer infoLog = BufferUtils.createByteBuffer(length);
            iVal.flip();
            ARBShaderObjects.glGetInfoLogARB(obj, iVal, infoLog);
            byte[] infoBytes = new byte[length];
            infoLog.get(infoBytes);
            String out = new String(infoBytes);
            System.out.println("Info log:\n"+out);
        }
        else return true;
        return false;
    }

	

}
