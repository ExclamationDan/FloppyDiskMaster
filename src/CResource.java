import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.GL20;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import java.util.Vector;


public class CResource extends CEngineModule
{
	class TexturePair
	{
		String  m_FilePath;
		Texture m_Texture;
	}

	class ShaderPair
	{
		
		String 	m_Name;
		CShader	m_Shader;
	}
	
	Vector<TexturePair> m_TextureList = new Vector<TexturePair>();
	Vector<ShaderPair> m_ShaderList = new Vector<ShaderPair>();
	

	
	void UseShader(String AliasName)
	{
		for(int i = 0;i < m_ShaderList.size();i++)
		{
			ShaderPair SP = m_ShaderList.get(i);
			if (SP.m_Name == AliasName)
			{
				//System.out.println("Returned texture successfully: "+TP.m_Texture.getTextureID());
				SP.m_Shader.UseShader();
				Engine.Render.m_CurrentShader = SP.m_Shader.m_ShaderProgram;
				return;
			}
		}
		
		System.out.println("UseShader(): Failed to fine AliasName. Did you call LoadShader()?");
		
	}
	
	int LoadShader(String Name, String VertexFilePath,String FragmentFilePath)
	{
		for(int i = 0;i < m_ShaderList.size();i++)
		{
			ShaderPair SP = m_ShaderList.get(i);
			if (SP.m_Name == Name)
			{
				//System.out.println("Returned texture successfully: "+TP.m_Texture.getTextureID());
				//SP.m_Shader.UseShader();
				return SP.m_Shader.m_ShaderProgram;
			}
		}
		
		ShaderPair SP = new ShaderPair();
		SP.m_Name = Name;
		SP.m_Shader = new CShader();
		SP.m_Shader.LoadShader(VertexFilePath,FragmentFilePath);
		m_ShaderList.add(SP);
		
		//SP.m_Shader.UseShader();
		return SP.m_Shader.m_ShaderProgram;
	}
	
	CMetaTexture LoadTexture(String Path) 
	{
		
		for(int i = 0;i < m_TextureList.size();i++)
		{
			TexturePair TP = m_TextureList.get(i);
			if (TP.m_FilePath == Path)
			{
				//System.out.println("Returned texture successfully: "+TP.m_Texture.getTextureID());
				return new CMetaTexture(TP.m_Texture);
			}
		}
		
		try 
		{
			
			TexturePair TP = new TexturePair();
			TP.m_FilePath = Path;
			TP.m_Texture = TextureLoader.getTexture("TGA",ResourceLoader.getResourceAsStream(Path));
			m_TextureList.add(TP);
			System.out.println("Loaded texture successfully: "+TP.m_Texture.getTextureID());
			return new CMetaTexture(TP.m_Texture);
			
		} 
		catch (IOException e) 
		{
			System.out.println("Texture failed to load!");
			e.printStackTrace();
			return null;
		}

	}
	
	



}

