package net.kozibrodka.tmt.TURBO_MODEL_264;

import net.minecraft.client.render.Tessellator;

public class TexturedQuad {
    public TmtVertex[] vertexPositions;
    public int nVertices;
    private boolean invertNormal;

    public TexturedQuad(TmtVertex[] par1ArrayOfTmtVertex)
    {
        this.nVertices = 0;
        this.invertNormal = false;
        this.vertexPositions = par1ArrayOfTmtVertex;
        this.nVertices = par1ArrayOfTmtVertex.length;
    }

    public TexturedQuad(TmtVertex[] par1ArrayOfTmtVertex, int par2, int par3, int par4, int par5, float par6, float par7)
    {
        this(par1ArrayOfTmtVertex);
        float var8 = 0.0F / par6;
        float var9 = 0.0F / par7;
        par1ArrayOfTmtVertex[0] = par1ArrayOfTmtVertex[0].remap((float)par4 / par6 - var8, (float)par3 / par7 + var9);
        par1ArrayOfTmtVertex[1] = par1ArrayOfTmtVertex[1].remap((float)par2 / par6 + var8, (float)par3 / par7 + var9);
        par1ArrayOfTmtVertex[2] = par1ArrayOfTmtVertex[2].remap((float)par2 / par6 + var8, (float)par5 / par7 - var9);
        par1ArrayOfTmtVertex[3] = par1ArrayOfTmtVertex[3].remap((float)par4 / par6 - var8, (float)par5 / par7 - var9);
    }

    public void flipFace()
    {
        TmtVertex[] var1 = new TmtVertex[this.vertexPositions.length];

        for (int var2 = 0; var2 < this.vertexPositions.length; ++var2)
        {
            var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
        }

        this.vertexPositions = var1;
    }

    public void draw(Tessellator par1Tessellator, float par2)
    {
        Vec3d var3 = this.vertexPositions[1].pos.relativize(this.vertexPositions[0].pos);
        Vec3d var4 = this.vertexPositions[1].pos.relativize(this.vertexPositions[2].pos);
        Vec3d var5 = var4.crossProduct(var3).normalize();
        par1Tessellator.startQuads();

        if (this.invertNormal)
        {
            par1Tessellator.normal(-((float)var5.x), -((float)var5.y), -((float)var5.z));
        }
        else
        {
            par1Tessellator.normal((float)var5.x, (float)var5.y, (float)var5.z);
        }

        for (int var6 = 0; var6 < 4; ++var6)
        {
            TmtVertex var7 = this.vertexPositions[var6];
            par1Tessellator.vertex((float)var7.pos.x * par2, (float)var7.pos.y * par2, (float)var7.pos.z * par2, var7.u, var7.v);
        }

        par1Tessellator.draw();
    }
}
