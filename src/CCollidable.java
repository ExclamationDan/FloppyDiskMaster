
public class CCollidable extends CQuad
{	
	enum Collision_Flag {NONE,GUI,WORLD,ENTITY,ENTITY_ONLY,ALL};
	enum Collision_Type {SPHERE,BOX};
	
	Collision_Flag m_Collision_Flag;
	Collision_Type m_Collision_Type;
	
	void SetCollisionFlag(Collision_Flag Flag)
	{
		m_Collision_Flag = Flag;
	}
	
	void SetCollisionType(Collision_Type Type)
	{
		m_Collision_Type = Type;
	}
	
	CCollidable()
	{
		m_Collision_Flag = Collision_Flag.NONE;
		m_Collision_Type = Collision_Type.BOX;	
	}
	
}
