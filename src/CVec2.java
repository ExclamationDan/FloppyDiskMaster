

public class CVec2 
{
	float x,y; 	//Position
	float s, t;	//Texture coordinates
	
	CVec2(float X, float Y, float S, float T)
	{
		x=X;
		y=Y;
		s = S;
		t = T;
	}
	
	CVec2(float X, float Y)
	{
		x=X;
		y=Y;
	}
	
	float[] ToArray()
	{
		float[] Elements = new float[4];
		Elements[0] = x;
		Elements[1] = y;
		Elements[2] = s;
		Elements[3] = t;
		
 		return Elements;
	}
}
