package net.kozibrodka.tmt.TURBO_MODEL_1337;


public class PositionTextureVertex extends TmtVertex {
    public float texturePositionW;

    public PositionTextureVertex(float var1, float var2, float var3, float var4, float var5)
    {
        this(var1, var2, var3, var4, var5, 1.0F);
    }

    public PositionTextureVertex(float var1, float var2, float var3, float var4, float var5, float var6)
    {
        this(Vec3d.createVectorHelper((double)var1, (double)var2, (double)var3), var4, var5);
    }

    public PositionTextureVertex setTexturePosition(float var1, float var2)
    {
        return new PositionTextureVertex(this, var1, var2, 1.0F);
    }

    public PositionTextureVertex setTexturePosition(float var1, float var2, float var3)
    {
        return new PositionTextureVertex(this, var1, var2, var3);
    }

    public PositionTextureVertex(PositionTextureVertex var1, float var2, float var3)
    {
        this(var1, var2, var3, 1.0F);
    }

    public PositionTextureVertex(PositionTextureVertex var1, float var2, float var3, float var4)
    {
        super(var1, var2, var3);
        this.texturePositionW = 1.0F;
        this.texturePositionW = var4;
    }

    public PositionTextureVertex(Vec3d var1, float var2, float var3)
    {
        this(var1, var2, var3, 1.0F);
    }

    public PositionTextureVertex(Vec3d var1, float var2, float var3, float var4)
    {
        super(var1, var2, var3);
        this.texturePositionW = 1.0F;
        this.texturePositionW = var4;
    }
}
