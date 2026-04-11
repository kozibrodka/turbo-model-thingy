package net.kozibrodka.tmt.TURBO_MODEL_173;
import java.util.ArrayList;
import net.minecraft.client.model.Vertex;
import net.minecraft.util.math.Vec3d;


public class PositionTransformVertex extends Vertex
{

    public PositionTransformVertex(float f, float f1, float f2, float f3, float f4)
    {
        this(Vec3d.create(f, f1, f2), f3, f4);
    }

    public PositionTransformVertex(Vertex positiontexturevertex, float f, float f1)
    {
        super(positiontexturevertex, f, f1);
        transformGroups = new ArrayList();
        if(positiontexturevertex instanceof PositionTransformVertex)
        {
            neutralVector = ((PositionTransformVertex)positiontexturevertex).neutralVector;
        } else
        {
            neutralVector = Vec3d.create(positiontexturevertex.pos.x, positiontexturevertex.pos.y, positiontexturevertex.pos.z);
        }
    }

    public PositionTransformVertex(Vertex positiontexturevertex)
    {
        this(positiontexturevertex, positiontexturevertex.u, positiontexturevertex.v);
    }

    public PositionTransformVertex(Vec3d vec3d, float f, float f1)
    {
        super(vec3d, f, f1);
        transformGroups = new ArrayList();
        neutralVector = Vec3d.create(vec3d.x, vec3d.y, vec3d.z);
    }

    public void setTransformation()
    {
        if(transformGroups.size() == 0)
        {
            pos.x = neutralVector.x;
            pos.y = neutralVector.y;
            pos.z = neutralVector.z;
            return;
        }
        double d = 0.0D;
        for(int i = 0; i < transformGroups.size(); i++)
        {
            d += ((TransformGroup)transformGroups.get(i)).getWeight();
        }

        pos.x = 0.0D;
        pos.y = 0.0D;
        pos.z = 0.0D;
        for(int j = 0; j < transformGroups.size(); j++)
        {
            TransformGroup transformgroup = (TransformGroup)transformGroups.get(j);
            double d1 = transformgroup.getWeight() / d;
            Vec3d vec3d = transformgroup.doTransformation(this);
            pos.x += d1 * vec3d.x;
            pos.y += d1 * vec3d.y;
            pos.z += d1 * vec3d.z;
        }

    }

    public void addGroup(TransformGroupBone transformgroupbone)
    {
        transformGroups.add(transformgroupbone);
    }

    public void removeGroup(TransformGroupBone transformgroupbone)
    {
        transformGroups.remove(transformgroupbone);
    }

    public Vec3d neutralVector;
    public ArrayList transformGroups;
}