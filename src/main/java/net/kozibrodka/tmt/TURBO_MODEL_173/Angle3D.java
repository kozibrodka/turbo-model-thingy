package net.kozibrodka.tmt.TURBO_MODEL_173;

public class Angle3D
{

    public Angle3D(float f, float f1, float f2)
    {
        angleX = f;
        angleY = f1;
        angleZ = f2;
    }

    public void addAngles(float f, float f1, float f2)
    {
        angleX += f;
        angleY += f1;
        angleZ += f2;
    }

    public void addAngles(Angle3D angle3d)
    {
        angleX += angle3d.angleX;
        angleY += angle3d.angleY;
        angleZ += angle3d.angleZ;
    }

    public void multiplyAngles(float f, float f1, float f2)
    {
        angleX *= f;
        angleY *= f1;
        angleZ *= f2;
    }

    public void multiplyAngles(Angle3D angle3d)
    {
        angleX *= angle3d.angleX;
        angleY *= angle3d.angleY;
        angleZ *= angle3d.angleZ;
    }

    public static Angle3D getCenter(Angle3D angle3d, Angle3D angle3d1)
    {
        Angle3D angle3d2 = new Angle3D(0.0F, 0.0F, 0.0F);
        angle3d2.addAngles(angle3d);
        angle3d2.addAngles(angle3d1);
        angle3d2.multiplyAngles(0.5F, 0.5F, 0.5F);
        return angle3d2;
    }

    public Angle3D copy()
    {
        return new Angle3D(angleX, angleY, angleZ);
    }

    public float angleX;
    public float angleY;
    public float angleZ;
}
