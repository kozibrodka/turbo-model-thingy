package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.minecraft.client.model.Cuboid;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bone {

    public Bone(float x, float y, float z, float l) {
        this.neutralAngles = new Angle3D(x, y, z);
        this.relativeAngles = new Angle3D(0.0F, 0.0F, 0.0F);
        this.absoluteAngles = new Angle3D(0.0F, 0.0F, 0.0F);
        this.positionVector = Vec3f.method_1293(0.0D, 0.0D, 0.0D);
        this.length = l;
        this.childNodes = new ArrayList();
        this.models = new ArrayList();
        this.modelBaseRot = new HashMap();
        this.parentNode = null;
        this.offsetX = 0.0F;
        this.offsetY = 0.0F;
        this.offsetZ = 0.0F;
        this.positionVector = Vec3f.method_1293(0.0D, 0.0D, 0.0D);
    }

    public Bone(float xOrig, float yOrig, float zOrig, float xRot, float yRot, float zRot, float l) {
        this(xRot, yRot, zRot, l);
        this.positionVector = this.setOffset(xOrig, yOrig, zOrig);
    }

    public Bone(float x, float y, float z, float l, Bone parent) {
        this(x, y, z, l);
        this.attachBone(parent);
    }

    public void detachBone() {
        this.parentNode.childNodes.remove(this);
        this.parentNode = null;
    }

    public void attachBone(Bone parent) {
        if(this.parentNode != null) {
            this.detachBone();
        }

        this.parentNode = parent;
        parent.addChildBone(this);
        this.offsetX = parent.offsetX;
        this.offsetY = parent.offsetY;
        this.offsetZ = parent.offsetZ;
        this.resetOffset();
    }

    public Vec3f setOffset(float x, float y, float z) {
        if(this.parentNode != null) {
            Vec3f vector = this.parentNode.setOffset(x, y, z);
            this.offsetX = (float)vector.x;
            this.offsetY = (float)vector.y;
            this.offsetZ = (float)vector.z;
            return vector;
        } else {
            this.offsetX = x;
            this.offsetY = y;
            this.offsetZ = z;
            this.resetOffset(true);
            return Vec3f.method_1293((double)x, (double)y, (double)z);
        }
    }

    public void resetOffset() {
        this.resetOffset(false);
    }

    public void resetOffset(boolean doRecursive) {
        if(this.parentNode != null) {
            this.positionVector = Vec3f.method_1293(0.0D, 0.0D, (double)this.parentNode.length);
            this.parentNode.setVectorRotations(this.positionVector);
            this.positionVector.x += this.parentNode.positionVector.x;
            this.positionVector.y += this.parentNode.positionVector.y;
            this.positionVector.z += this.parentNode.positionVector.z;
        }

        if(doRecursive && !this.childNodes.isEmpty()) {
            for(int index = 0; index < this.childNodes.size(); ++index) {
                ((Bone)this.childNodes.get(index)).resetOffset(doRecursive);
            }
        }

    }

    public void setNeutralRotation(float x, float y, float z) {
        this.neutralAngles.angleX = x;
        this.neutralAngles.angleY = y;
        this.neutralAngles.angleZ = z;
    }

    public Bone getRootParent() {
        return this.parentNode == null ? this : this.parentNode.getRootParent();
    }

    public void addModel(Cuboid model) {
        this.addModel(model, false);
    }

    public void addModel(Cuboid model, boolean inherit) {
        this.addModel(model, 0.0F, 0.0F, 0.0F, inherit);
    }

    public void addModel(Cuboid model, boolean inherit, boolean isUpright) {
        this.addModel(model, 0.0F, 0.0F, 0.0F, inherit, isUpright);
    }

    public void addModel(Cuboid model, float x, float y, float z) {
        this.addModel(model, x, y, z, false);
    }

    public void addModel(Cuboid model, float x, float y, float z, boolean inherit) {
        this.addModel(model, x, y, z, inherit, false);
    }

    public void addModel(Cuboid model, float x, float y, float z, boolean inherit, boolean isUpright) {
        if(inherit) {
            x += this.neutralAngles.angleX + (isUpright ? (float)Math.PI / 2F : 0.0F);
            y += this.neutralAngles.angleY;
            z += this.neutralAngles.angleZ;
        }

        this.models.add(model);
        this.modelBaseRot.put(model, new Angle3D(x, y, z));
    }

    public void removeModel(Cuboid model) {
        this.models.remove(model);
        this.modelBaseRot.remove(model);
    }

    public Angle3D getAbsoluteAngle() {
        return new Angle3D(this.absoluteAngles.angleX, this.absoluteAngles.angleY, this.absoluteAngles.angleZ);
    }

    public Vec3f getPosition() {
        return Vec3f.method_1293(this.positionVector.x, this.positionVector.y, this.positionVector.z);
    }

    protected void addChildBone(Bone bone) {
        this.childNodes.add(bone);
    }

    public void prepareDraw() {
        if(this.parentNode != null) {
            this.parentNode.prepareDraw();
        } else {
            this.setAbsoluteRotations();
            this.setVectors();
        }

    }

    public void setRotations(float x, float y, float z) {
        this.relativeAngles.angleX = x;
        this.relativeAngles.angleY = y;
        this.relativeAngles.angleZ = z;
    }

    protected void setAbsoluteRotations() {
        this.absoluteAngles.angleX = this.relativeAngles.angleX;
        this.absoluteAngles.angleY = this.relativeAngles.angleY;
        this.absoluteAngles.angleZ = this.relativeAngles.angleZ;

        for(int i = 0; i < this.childNodes.size(); ++i) {
            ((Bone)this.childNodes.get(i)).setAbsoluteRotations(this.absoluteAngles.angleX, this.absoluteAngles.angleY, this.absoluteAngles.angleZ);
        }

    }

    protected void setAbsoluteRotations(float x, float y, float z) {
        this.absoluteAngles.angleX = this.relativeAngles.angleX + x;
        this.absoluteAngles.angleY = this.relativeAngles.angleY + y;
        this.absoluteAngles.angleZ = this.relativeAngles.angleZ + z;

        for(int i = 0; i < this.childNodes.size(); ++i) {
            ((Bone)this.childNodes.get(i)).setAbsoluteRotations(this.absoluteAngles.angleX, this.absoluteAngles.angleY, this.absoluteAngles.angleZ);
        }

    }

    protected void setVectorRotations(Vec3f vector) {
        float x = this.neutralAngles.angleX + this.absoluteAngles.angleX;
        float y = this.neutralAngles.angleY + this.absoluteAngles.angleY;
        float z = this.neutralAngles.angleZ + this.absoluteAngles.angleZ;
        this.setVectorRotations(vector, x, y, z);
    }

    protected void setVectorRotations(Vec3f vector, float xRot, float yRot, float zRot) {
        float xC = MathHelper.cos(xRot);
        float xS = MathHelper.sin(xRot);
        float yC = MathHelper.cos(yRot);
        float yS = MathHelper.sin(yRot);
        float zC = MathHelper.cos(zRot);
        float zS = MathHelper.sin(zRot);
        double xVec = vector.x;
        double yVec = vector.y;
        double zVec = vector.z;
        double xy = (double)xC * yVec - (double)xS * zVec;
        double xz = (double)xC * zVec + (double)xS * yVec;
        double yz = (double)yC * xz - (double)yS * xVec;
        double yx = (double)yC * xVec + (double)yS * xz;
        double zx = (double)zC * yx - (double)zS * xy;
        double zy = (double)zC * xy + (double)zS * yx;
        vector.x = zx;
        vector.y = zy;
        vector.z = yz;
    }

    protected void addVector(Vec3f destVec, Vec3f srcVec) {
        destVec.x += srcVec.x;
        destVec.y += srcVec.y;
        destVec.z += srcVec.z;
    }

    protected void setVectors() {
        Vec3f tempVec = Vec3f.method_1293(0.0D, 0.0D, (double)this.length);
        this.positionVector = Vec3f.method_1293((double)this.offsetX, (double)this.offsetY, (double)this.offsetZ);
        this.addVector(tempVec, this.positionVector);
        this.setVectorRotations(tempVec);

        for(int i = 0; i < this.childNodes.size(); ++i) {
            ((Bone)this.childNodes.get(i)).setVectors(tempVec);
        }

    }

    protected void setVectors(Vec3f vector) {
        this.positionVector = vector;
        Vec3f tempVec = Vec3f.method_1293(0.0D, 0.0D, (double)this.length);
        this.setVectorRotations(tempVec);
        this.addVector(tempVec, vector);

        for(int i = 0; i < this.childNodes.size(); ++i) {
            ((Bone)this.childNodes.get(i)).setVectors(tempVec);
        }

    }

    public void setAnglesToModels() {
        int i;
        for(i = 0; i < this.models.size(); ++i) {
            Cuboid currentModel = (Cuboid)this.models.get(i);
            Angle3D baseAngles = (Angle3D)this.modelBaseRot.get(currentModel);
            currentModel.pitch = baseAngles.angleX + this.absoluteAngles.angleX;
            currentModel.yaw = baseAngles.angleY + this.absoluteAngles.angleY;
            currentModel.roll = baseAngles.angleZ + this.absoluteAngles.angleZ;
            currentModel.rotationPointX = (float)this.positionVector.x;
            currentModel.rotationPointY = (float)this.positionVector.y;
            currentModel.rotationPointZ = (float)this.positionVector.z;
        }

        for(i = 0; i < this.childNodes.size(); ++i) {
            ((Bone)this.childNodes.get(i)).setAnglesToModels();
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
