package net.kozibrodka.tmt.TURBO_MODEL_2000_DEV;

import net.minecraft.client.render.Tessellator;

public class ModelBox {
    private TmtVertex[] vertexPositions;
    private TexturedQuad[] quadList;
    public final float posX1;
    public final float posY1;
    public final float posZ1;
    public final float posX2;
    public final float posY2;
    public final float posZ2;
    public String field_40673_g;

    public ModelBox(ModelRenderer modelRenderer, int var2, int var3, float var4, float var5, float var6, int var7, int var8, int var9, float var10) {
        this.posX1 = var4;
        this.posY1 = var5;
        this.posZ1 = var6;
        this.posX2 = var4 + (float)var7;
        this.posY2 = var5 + (float)var8;
        this.posZ2 = var6 + (float)var9;
        this.vertexPositions = new TmtVertex[8];
        this.quadList = new TexturedQuad[6];
        float var11 = var4 + (float)var7;
        float var12 = var5 + (float)var8;
        float var13 = var6 + (float)var9;
        var4 -= var10;
        var5 -= var10;
        var6 -= var10;
        var11 += var10;
        var12 += var10;
        var13 += var10;
        if(modelRenderer.mirror) {
            float var14 = var11;
            var11 = var4;
            var4 = var14;
        }

        TmtVertex var23 = new TmtVertex(var4, var5, var6, 0.0F, 0.0F);
        TmtVertex var15 = new TmtVertex(var11, var5, var6, 0.0F, 8.0F);
        TmtVertex var16 = new TmtVertex(var11, var12, var6, 8.0F, 8.0F);
        TmtVertex var17 = new TmtVertex(var4, var12, var6, 8.0F, 0.0F);
        TmtVertex var18 = new TmtVertex(var4, var5, var13, 0.0F, 0.0F);
        TmtVertex var19 = new TmtVertex(var11, var5, var13, 0.0F, 8.0F);
        TmtVertex var20 = new TmtVertex(var11, var12, var13, 8.0F, 8.0F);
        TmtVertex var21 = new TmtVertex(var4, var12, var13, 8.0F, 0.0F);
        this.vertexPositions[0] = var23;
        this.vertexPositions[1] = var15;
        this.vertexPositions[2] = var16;
        this.vertexPositions[3] = var17;
        this.vertexPositions[4] = var18;
        this.vertexPositions[5] = var19;
        this.vertexPositions[6] = var20;
        this.vertexPositions[7] = var21;
        this.quadList[0] = new TexturedQuad(new TmtVertex[]{var19, var15, var16, var20}, var2 + var9 + var7, var3 + var9, var2 + var9 + var7 + var9, var3 + var9 + var8, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[1] = new TexturedQuad(new TmtVertex[]{var23, var18, var21, var17}, var2, var3 + var9, var2 + var9, var3 + var9 + var8, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[2] = new TexturedQuad(new TmtVertex[]{var19, var18, var23, var15}, var2 + var9, var3, var2 + var9 + var7, var3 + var9, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[3] = new TexturedQuad(new TmtVertex[]{var16, var17, var21, var20}, var2 + var9 + var7, var3 + var9, var2 + var9 + var7 + var7, var3, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[4] = new TexturedQuad(new TmtVertex[]{var15, var23, var17, var16}, var2 + var9, var3 + var9, var2 + var9 + var7, var3 + var9 + var8, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[5] = new TexturedQuad(new TmtVertex[]{var18, var19, var20, var21}, var2 + var9 + var7 + var9, var3 + var9, var2 + var9 + var7 + var9 + var7, var3 + var9 + var8, modelRenderer.textureWidth, modelRenderer.textureHeight);


//        this.quadList[0] = new TexturedQuadAlt(new QuadPoint[]{var19, var15, var16, var20}, var2 + var9 + var7, var3 + var9, var2 + var9 + var7 + var9, var3 + var9 + var8, var1.textureWidth, var1.textureHeight);
//        this.quadList[1] = new TexturedQuadAlt(new QuadPoint[]{var23, var18, var21, var17}, var2, var3 + var9, var2 + var9, var3 + var9 + var8, var1.textureWidth, var1.textureHeight);
//        this.quadList[2] = new TexturedQuadAlt(new QuadPoint[]{var19, var18, var23, var15}, var2 + var9, var3, var2 + var9 + var7, var3 + var9, var1.textureWidth, var1.textureHeight);
//        this.quadList[3] = new TexturedQuadAlt(new QuadPoint[]{var16, var17, var21, var20}, var2 + var9 + var7, var3 + var9, var2 + var9 + var7 + var7, var3, var1.textureWidth, var1.textureHeight);
//        this.quadList[4] = new TexturedQuadAlt(new QuadPoint[]{var15, var23, var17, var16}, var2 + var9, var3 + var9, var2 + var9 + var7, var3 + var9 + var8, var1.textureWidth, var1.textureHeight);
//        this.quadList[5] = new TexturedQuadAlt(new QuadPoint[]{var18, var19, var20, var21}, var2 + var9 + var7 + var9, var3 + var9, var2 + var9 + var7 + var9 + var7, var3 + var9 + var8, var1.textureWidth, var1.textureHeight);
        if(modelRenderer.mirror) {
            for(int var22 = 0; var22 < this.quadList.length; ++var22) {
                this.quadList[var22].flipFace();
            }
        }

    }

    public void render(Tessellator var1, float var2) {
        for(int var3 = 0; var3 < this.quadList.length; ++var3) {
            this.quadList[var3].draw(var1, var2);
        }

    }

    public ModelBox func_40671_a(String var1) {
        this.field_40673_g = var1;
        return this;
    }
}
