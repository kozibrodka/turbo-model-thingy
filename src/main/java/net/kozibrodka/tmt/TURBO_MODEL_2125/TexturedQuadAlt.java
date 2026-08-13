package net.kozibrodka.tmt.TURBO_MODEL_2125;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Vertex;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class TexturedQuadAlt {
    public Vertex[] quadPoint;
    public int field_2519;
    private boolean field_2520;

    public TexturedQuadAlt(Vertex[] args) {
        this.field_2519 = 0;
        this.field_2520 = false;
        this.quadPoint = args;
        this.field_2519 = args.length;
    }

    public TexturedQuadAlt(Vertex[] var1, int var2, int var3, int var4, int var5, float var6, float var7) {
        this(var1);
        float var8 = 0.0F / var6;
        float var9 = 0.0F / var7;
        var1[0] = var1[0].remap((float)var4 / var6 - var8, (float)var3 / var7 + var9);
        var1[1] = var1[1].remap((float)var2 / var6 + var8, (float)var3 / var7 + var9);
        var1[2] = var1[2].remap((float)var2 / var6 + var8, (float)var5 / var7 - var9);
        var1[3] = var1[3].remap((float)var4 / var6 - var8, (float)var5 / var7 - var9);
    }

    public void method_1925() {
        Vertex[] var1 = new Vertex[this.quadPoint.length];

        for(int var2 = 0; var2 < this.quadPoint.length; ++var2) {
            var1[var2] = this.quadPoint[this.quadPoint.length - var2 - 1];
        }

        this.quadPoint = var1;
    }

    public void method_1926(Tessellator arg, float f) {
        Vec3d var3 = this.quadPoint[1].pos.relativize(this.quadPoint[0].pos);
        Vec3d var4 = this.quadPoint[1].pos.relativize(this.quadPoint[2].pos);
        Vec3d var5 = var4.crossProduct(var3).normalize();
        arg.startQuads();
        if (this.field_2520) {
            arg.normal(-((float)var5.x), -((float)var5.y), -((float)var5.z));
        } else {
            arg.normal((float)var5.x, (float)var5.y, (float)var5.z);
        }

        for(int var6 = 0; var6 < 4; ++var6) {
            Vertex var7 = this.quadPoint[var6];
            arg.vertex((double)((float)var7.pos.x * f), (double)((float)var7.pos.y * f), (double)((float)var7.pos.z * f), (double)var7.u, (double)var7.v);
        }

        arg.draw();
    }
}
