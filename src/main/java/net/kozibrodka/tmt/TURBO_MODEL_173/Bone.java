package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.client.model.Cuboid;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Bone
{

    public Bone(float f, float f1, float f2, float f3)
    {
        neutralAngles = new Angle3D(f, f1, f2);
        relativeAngles = new Angle3D(0.0F, 0.0F, 0.0F);
        absoluteAngles = new Angle3D(0.0F, 0.0F, 0.0F);
        positionVector = Vec3f.method_1293(0.0D, 0.0D, 0.0D);
        length = f3;
        childNodes = new ArrayList();
        models = new ArrayList();
        modelBaseRot = new HashMap();
        parentNode = null;
        offsetX = 0.0F;
        offsetY = 0.0F;
        offsetZ = 0.0F;
        positionVector = Vec3f.method_1293(0.0D, 0.0D, 0.0D);
    }

    public Bone(float f, float f1, float f2, float f3, float f4, float f5, float f6)
    {
        this(f3, f4, f5, f6);
        positionVector = setOffset(f, f1, f2);
    }

    public Bone(float f, float f1, float f2, float f3, Bone bone)
    {
        this(f, f1, f2, f3);
        attachBone(bone);
    }

    public void detachBone()
    {
        parentNode.childNodes.remove(this);
        parentNode = null;
    }

    public void attachBone(Bone bone)
    {
        if(parentNode != null)
        {
            detachBone();
        }
        parentNode = bone;
        bone.addChildBone(this);
        offsetX = bone.offsetX;
        offsetY = bone.offsetY;
        offsetZ = bone.offsetZ;
        resetOffset();
    }

    public Vec3f setOffset(float f, float f1, float f2)
    {
        if(parentNode != null)
        {
            Vec3f vec3d = parentNode.setOffset(f, f1, f2);
            offsetX = (float)vec3d.x;
            offsetY = (float)vec3d.y;
            offsetZ = (float)vec3d.z;
            return vec3d;
        } else
        {
            offsetX = f;
            offsetY = f1;
            offsetZ = f2;
            resetOffset(true);
            return Vec3f.method_1293(f, f1, f2);
        }
    }

    public void resetOffset()
    {
        resetOffset(false);
    }

    public void resetOffset(boolean flag)
    {
        if(parentNode != null)
        {
            positionVector = Vec3f.method_1293(0.0D, 0.0D, parentNode.length);
            parentNode.setVectorRotations(positionVector);
            positionVector.x += parentNode.positionVector.x;
            positionVector.y += parentNode.positionVector.y;
            positionVector.z += parentNode.positionVector.z;
        }
        if(flag && !childNodes.isEmpty())
        {
            for(int i = 0; i < childNodes.size(); i++)
            {
                ((Bone)childNodes.get(i)).resetOffset(flag);
            }

        }
    }

    public void setNeutralRotation(float f, float f1, float f2)
    {
        neutralAngles.angleX = f;
        neutralAngles.angleY = f1;
        neutralAngles.angleZ = f2;
    }

    public Bone getRootParent()
    {
        if(parentNode == null)
        {
            return this;
        } else
        {
            return parentNode.getRootParent();
        }
    }

    public void addModel(Cuboid modelrenderer)
    {
        addModel(modelrenderer, false);
    }

    public void addModel(Cuboid modelrenderer, boolean flag)
    {
        addModel(modelrenderer, 0.0F, 0.0F, 0.0F, flag);
    }

    public void addModel(Cuboid modelrenderer, boolean flag, boolean flag1)
    {
        addModel(modelrenderer, 0.0F, 0.0F, 0.0F, flag, flag1);
    }

    public void addModel(Cuboid modelrenderer, float f, float f1, float f2)
    {
        addModel(modelrenderer, f, f1, f2, false);
    }

    public void addModel(Cuboid modelrenderer, float f, float f1, float f2, boolean flag)
    {
        addModel(modelrenderer, f, f1, f2, flag, false);
    }

    public void addModel(Cuboid modelrenderer, float f, float f1, float f2, boolean flag, boolean flag1)
    {
        if(flag)
        {
            f += neutralAngles.angleX + (flag1 ? 1.570796F : 0.0F);
            f1 += neutralAngles.angleY;
            f2 += neutralAngles.angleZ;
        }
        models.add(modelrenderer);
        modelBaseRot.put(modelrenderer, new Angle3D(f, f1, f2));
    }

    public void removeModel(Cuboid modelrenderer)
    {
        models.remove(modelrenderer);
        modelBaseRot.remove(modelrenderer);
    }

    public Angle3D getAbsoluteAngle()
    {
        return new Angle3D(absoluteAngles.angleX, absoluteAngles.angleY, absoluteAngles.angleZ);
    }

    public Vec3f getPosition()
    {
        return Vec3f.method_1293(positionVector.x, positionVector.y, positionVector.z);
    }

    protected void addChildBone(Bone bone)
    {
        childNodes.add(bone);
    }

    public void prepareDraw()
    {
        if(parentNode != null)
        {
            parentNode.prepareDraw();
        } else
        {
            setAbsoluteRotations();
            setVectors();
        }
    }

    public void setRotations(float f, float f1, float f2)
    {
        relativeAngles.angleX = f;
        relativeAngles.angleY = f1;
        relativeAngles.angleZ = f2;
    }

    protected void setAbsoluteRotations()
    {
        absoluteAngles.angleX = relativeAngles.angleX;
        absoluteAngles.angleY = relativeAngles.angleY;
        absoluteAngles.angleZ = relativeAngles.angleZ;
        for(int i = 0; i < childNodes.size(); i++)
        {
            ((Bone)childNodes.get(i)).setAbsoluteRotations(absoluteAngles.angleX, absoluteAngles.angleY, absoluteAngles.angleZ);
        }

    }

    protected void setAbsoluteRotations(float f, float f1, float f2)
    {
        absoluteAngles.angleX = relativeAngles.angleX + f;
        absoluteAngles.angleY = relativeAngles.angleY + f1;
        absoluteAngles.angleZ = relativeAngles.angleZ + f2;
        for(int i = 0; i < childNodes.size(); i++)
        {
            ((Bone)childNodes.get(i)).setAbsoluteRotations(absoluteAngles.angleX, absoluteAngles.angleY, absoluteAngles.angleZ);
        }

    }

    protected void setVectorRotations(Vec3f vec3d)
    {
        float f = neutralAngles.angleX + absoluteAngles.angleX;
        float f1 = neutralAngles.angleY + absoluteAngles.angleY;
        float f2 = neutralAngles.angleZ + absoluteAngles.angleZ;
        setVectorRotations(vec3d, f, f1, f2);
    }

    protected void setVectorRotations(Vec3f vec3d, float f, float f1, float f2)
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

    protected void addVector(Vec3f vec3d, Vec3f vec3d1)
    {
        vec3d.x += vec3d1.x;
        vec3d.y += vec3d1.y;
        vec3d.z += vec3d1.z;
    }

    protected void setVectors()
    {
        Vec3f vec3d = Vec3f.method_1293(0.0D, 0.0D, length);
        positionVector = Vec3f.method_1293(offsetX, offsetY, offsetZ);
        addVector(vec3d, positionVector);
        setVectorRotations(vec3d);
        for(int i = 0; i < childNodes.size(); i++)
        {
            ((Bone)childNodes.get(i)).setVectors(vec3d);
        }

    }

    protected void setVectors(Vec3f vec3d)
    {
        positionVector = vec3d;
        Vec3f vec3d1 = Vec3f.method_1293(0.0D, 0.0D, length);
        setVectorRotations(vec3d1);
        addVector(vec3d1, vec3d);
        for(int i = 0; i < childNodes.size(); i++)
        {
            ((Bone)childNodes.get(i)).setVectors(vec3d1);
        }

    }

    public void setAnglesToModels()
    {
        for(int i = 0; i < models.size(); i++)
        {
            Cuboid modelrenderer = (Cuboid)models.get(i);
            Angle3D angle3d = (Angle3D)modelBaseRot.get(modelrenderer);
            modelrenderer.pitch = angle3d.angleX + absoluteAngles.angleX;
            modelrenderer.yaw = angle3d.angleY + absoluteAngles.angleY;
            modelrenderer.roll = angle3d.angleZ + absoluteAngles.angleZ;
            modelrenderer.rotationPointX = (float)positionVector.x;
            modelrenderer.rotationPointY = (float)positionVector.y;
            modelrenderer.rotationPointZ = (float)positionVector.z;
        }

        for(int j = 0; j < childNodes.size(); j++)
        {
            ((Bone)childNodes.get(j)).setAnglesToModels();
        }

    }

    protected Angle3D neutralAngles;
    public Angle3D relativeAngles;
    protected Angle3D absoluteAngles;
    private Vec3f positionVector;
    private float length;
    private Bone parentNode;
    protected ArrayList childNodes;
    private ArrayList models;
    private Map modelBaseRot;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
}
