package net.kozibrodka.tmt.TURBO_MODEL_173;
import net.minecraft.client.render.QuadPoint;
import net.minecraft.util.maths.Vec3f;

import java.util.ArrayList;


public class PositionTransformVertex extends QuadPoint
{

    public PositionTransformVertex(float f, float f1, float f2, float f3, float f4)
    {
        this(Vec3f.method_1293(f, f1, f2), f3, f4);
    }

    public PositionTransformVertex(QuadPoint positiontexturevertex, float f, float f1)
    {
        super(positiontexturevertex, f, f1);
        transformGroups = new ArrayList();
        if(positiontexturevertex instanceof PositionTransformVertex)
        {
            neutralVector = ((PositionTransformVertex)positiontexturevertex).neutralVector;
        } else
        {
            neutralVector = Vec3f.method_1293(positiontexturevertex.pointVector.x, positiontexturevertex.pointVector.y, positiontexturevertex.pointVector.z);
        }
    }

    public PositionTransformVertex(QuadPoint positiontexturevertex)
    {
        this(positiontexturevertex, positiontexturevertex.field_1147, positiontexturevertex.field_1148);
    }

    public PositionTransformVertex(Vec3f vec3d, float f, float f1)
    {
        super(vec3d, f, f1);
        transformGroups = new ArrayList();
        neutralVector = Vec3f.method_1293(vec3d.x, vec3d.y, vec3d.z);
    }

    public void setTransformation()
    {
        if(transformGroups.size() == 0)
        {
            pointVector.x = neutralVector.x;
            pointVector.y = neutralVector.y;
            pointVector.z = neutralVector.z;
            return;
        }
        double d = 0.0D;
        for(int i = 0; i < transformGroups.size(); i++)
        {
            d += ((TransformGroup)transformGroups.get(i)).getWeight();
        }

        pointVector.x = 0.0D;
        pointVector.y = 0.0D;
        pointVector.z = 0.0D;
        for(int j = 0; j < transformGroups.size(); j++)
        {
            TransformGroup transformgroup = (TransformGroup)transformGroups.get(j);
            double d1 = transformgroup.getWeight() / d;
            Vec3f vec3d = transformgroup.doTransformation(this);
            pointVector.x += d1 * vec3d.x;
            pointVector.y += d1 * vec3d.y;
            pointVector.z += d1 * vec3d.z;
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

    public Vec3f neutralVector;
    public ArrayList transformGroups;
}