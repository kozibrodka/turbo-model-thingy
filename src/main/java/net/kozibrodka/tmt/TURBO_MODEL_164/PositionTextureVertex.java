package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.util.maths.Vec3f;

public class PositionTextureVertex extends QuadPoint {
    public float texturePositionW;

    public PositionTextureVertex(float par1, float par2, float par3, float par4, float par5) {
        this(par1, par2, par3, par4, par5, 1.0F);
    }

    public PositionTextureVertex(float var1, float var2, float var3, float var4, float var5, float par6) {
        this(Vec3f.method_1293((double)var1, (double)var2, (double)var3), var4, var5); //TODO?
    }

    public PositionTextureVertex func_78240_a(float par1, float par2) {
        return new PositionTextureVertex(this, par1, par2, 1.0F);
    }

    public PositionTextureVertex setTexturePosition(float var1, float var2, float q) {
        return new PositionTextureVertex(this, var1, var2, q);
    }

    public PositionTextureVertex(PositionTextureVertex par1PositionTextureVertex, float par2, float par3) {
        this(par1PositionTextureVertex, par2, par3, 1.0F);
    }

    public PositionTextureVertex(PositionTextureVertex par1PositionTextureVertex, float par2, float par3, float q) {
        super(par1PositionTextureVertex, par2, par3);
        this.texturePositionW = 1.0F;
        this.texturePositionW = q;
    }

    public PositionTextureVertex(Vec3f par1Vec3, float par2, float par3) {
        this(par1Vec3, par2, par3, 1.0F);
    }

    public PositionTextureVertex(Vec3f par1Vec3, float par2, float par3, float par4) {
        super(par1Vec3, par2, par3);
        this.texturePositionW = 1.0F;
        this.texturePositionW = par4;
    }
}
