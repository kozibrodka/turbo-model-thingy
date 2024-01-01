package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.maths.Vec3f;

@Environment(EnvType.CLIENT)
public class TexturedQuad {
    public PositionTextureVertex[] quadPoint;
    public int field_2519;
    private boolean field_2520;

    public TexturedQuad(PositionTextureVertex[] args) {
        this.field_2519 = 0;
        this.field_2520 = false;
        this.quadPoint = args;
        this.field_2519 = args.length;
    }

    public TexturedQuad(PositionTextureVertex[] var1, int var2, int var3, int var4, int var5, float var6, float var7) {
        this(var1);
        float var8 = 0.0F / var6;
        float var9 = 0.0F / var7;
        var1[0] = var1[0].func_78240_a((float)var4 / var6 - var8, (float)var3 / var7 + var9);
        var1[1] = var1[1].func_78240_a((float)var2 / var6 + var8, (float)var3 / var7 + var9);
        var1[2] = var1[2].func_78240_a((float)var2 / var6 + var8, (float)var5 / var7 - var9);
        var1[3] = var1[3].func_78240_a((float)var4 / var6 - var8, (float)var5 / var7 - var9);
    }

    public void method_1925() {
        PositionTextureVertex[] var1 = new PositionTextureVertex[this.quadPoint.length];

        for(int var2 = 0; var2 < this.quadPoint.length; ++var2) {
            var1[var2] = this.quadPoint[this.quadPoint.length - var2 - 1];
        }

        this.quadPoint = var1;
    }

    public void method_1926(Tessellator arg, float f) {
        Vec3f var3 = this.quadPoint[1].pointVector.method_1307(this.quadPoint[0].pointVector);
        Vec3f var4 = this.quadPoint[1].pointVector.method_1307(this.quadPoint[2].pointVector);
        Vec3f var5 = var4.method_1309(var3).method_1296();
        arg.start();
        if (this.field_2520) {
            arg.setNormal(-((float)var5.x), -((float)var5.y), -((float)var5.z));
        } else {
            arg.setNormal((float)var5.x, (float)var5.y, (float)var5.z);
        }

        for(int var6 = 0; var6 < 4; ++var6) {
            PositionTextureVertex var7 = this.quadPoint[var6];
            arg.vertex((double)((float)var7.pointVector.x * f), (double)((float)var7.pointVector.y * f), (double)((float)var7.pointVector.z * f), (double)var7.field_1147, (double)var7.field_1148);
        }

        arg.draw();
    }
}
