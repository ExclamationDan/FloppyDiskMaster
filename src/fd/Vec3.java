package fd;
import org.lwjgl.opengl.GL11;


public class Vec3
{
	float x,y,z;
	
	public Vec3(float X, float Y, float Z)
	{
		x = X;
		y = Y;
		z = Z;
	}
	
	public Vec3(Vec3 Vec)
	{
		x = Vec.x;
		y = Vec.y;
		z = Vec.z;
	}
	
	public double Length()
	{
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
	}
	
	public double Magnitude()
	{
		return Length();
	}
	
	//I'm pretty sure this is incorrect.
	public double Dot(Vec3 Vec)
	{
		double mag = Magnitude()*Vec.Magnitude();
		
		return Math.cos(mag)*mag;
		
	}
	
	
}
