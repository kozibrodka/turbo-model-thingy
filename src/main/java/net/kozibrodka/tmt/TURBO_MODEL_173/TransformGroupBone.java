package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class TransformGroupBone extends TransformGroup
{

    public TransformGroupBone(Bone bone, double d)
    {
        baseVector = bone.getPosition();
        baseAngles = bone.getAbsoluteAngle();
        attachedBone = bone;
        weight = d;
    }

    public Angle3D getBaseAngles()
    {
        return baseAngles.copy();
    }

    public Angle3D getTransformAngle()
    {
        Angle3D angle3d = attachedBone.getAbsoluteAngle().copy();
        angle3d.angleX -= baseAngles.angleX;
        angle3d.angleY -= baseAngles.angleY;
        angle3d.angleZ -= baseAngles.angleZ;
        return angle3d;
    }

    public Vec3d getBaseVector()
    {
        return Vec3d.create(baseVector.x, baseVector.y, baseVector.z);
    }

    public Vec3d getTransformVector()
    {
        return baseVector.relativize(attachedBone.getPosition());
    }

    public Vec3d getCurrentVector()
    {
        return attachedBone.getPosition();
    }

    public double getWeight()
    {
        return weight;
    }

    public void attachBone(Bone bone)
    {
        baseVector = bone.getPosition();
        baseAngles = bone.getAbsoluteAngle();
        attachedBone = bone;
    }

    public Vec3d doTransformation(PositionTransformVertex positiontransformvertex)
    {
        Vec3d vec3d = Vec3d.create(positiontransformvertex.neutralVector.x, positiontransformvertex.neutralVector.y, positiontransformvertex.neutralVector.z);
        vec3d = getBaseVector().relativize(vec3d);
        Angle3D angle3d = getTransformAngle();
        setVectorRotations(vec3d, angle3d.angleX, angle3d.angleY, angle3d.angleZ);
        return vec3d;
    }

    protected void setVectorRotations(Vec3d vec3d, float f, float f1, float f2)
    {
        float f3 = f;
        float f4 = f1;
        float f5 = f2;
        float f6 = MathHelper.cos(f3);
        float f7 = MathHelper.sin(f3);
        float f8 = MathHelper.cos(f4);
        float f9 = MathHelper.sin(f4);
        float f10 = MathHelper.cos(f5);
        float f11 = MathHelper.sin(f5);
        double d = vec3d.x;
        double d1 = vec3d.y;
        double d2 = vec3d.z;
        double d3 = (double)f6 * d1 - (double)f7 * d2;
        double d4 = (double)f6 * d2 + (double)f7 * d1;
        double d5 = (double)f8 * d4 - (double)f9 * d;
        double d6 = (double)f8 * d + (double)f9 * d4;
        double d7 = (double)f10 * d6 - (double)f11 * d3;
        double d8 = (double)f10 * d3 + (double)f11 * d6;
        d = d7;
        d1 = d8;
        d2 = d5;
        vec3d.x = d;
        vec3d.y = d1;
        vec3d.z = d2;
    }

    protected Angle3D baseAngles;
    protected Vec3d baseVector;
    protected Bone attachedBone;
    protected double weight;
}
