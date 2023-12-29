package net.kozibrodka.tmt.TURBO_MODEL_125;

import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;

public class TransformGroupBone extends TransformGroup {

    public TransformGroupBone(Bone bone, double wght) {
        this.baseVector = bone.getPosition();
        this.baseAngles = bone.getAbsoluteAngle();
        this.attachedBone = bone;
        this.weight = wght;
    }

    public Angle3D getBaseAngles() {
        return this.baseAngles.copy();
    }

    public Angle3D getTransformAngle() {
        Angle3D returnAngle = this.attachedBone.getAbsoluteAngle().copy();
        returnAngle.angleX -= this.baseAngles.angleX;
        returnAngle.angleY -= this.baseAngles.angleY;
        returnAngle.angleZ -= this.baseAngles.angleZ;
        return returnAngle;
    }

    public Vec3f getBaseVector() {
        return Vec3f.method_1293(this.baseVector.x, this.baseVector.y, this.baseVector.z);
    }

    public Vec3f getTransformVector() {
        return this.baseVector.method_1307(this.attachedBone.getPosition());
    }

    public Vec3f getCurrentVector() {
        return this.attachedBone.getPosition();
    }

    public double getWeight() {
        return this.weight;
    }

    public void attachBone(Bone bone) {
        this.baseVector = bone.getPosition();
        this.baseAngles = bone.getAbsoluteAngle();
        this.attachedBone = bone;
    }

    public Vec3f doTransformation(PositionTransformVertex vertex) {
        Vec3f vector = Vec3f.method_1293(vertex.neutralVector.x, vertex.neutralVector.y, vertex.neutralVector.z);
        vector = this.getBaseVector().method_1307(vector);
        Angle3D angle = this.getTransformAngle();
        this.setVectorRotations(vector, angle.angleX, angle.angleY, angle.angleZ);
        return vector;
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

    protected Angle3D baseAngles;
    protected Vec3f baseVector;
    protected Bone attachedBone;
    protected double weight;
}
