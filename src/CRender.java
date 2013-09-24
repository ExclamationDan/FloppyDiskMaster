import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class CRender extends CEngineModule
{
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

}
