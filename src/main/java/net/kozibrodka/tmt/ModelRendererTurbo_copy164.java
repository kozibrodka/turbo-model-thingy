//package net.kozibrodka.tmt;
//
//import net.kozibrodka.tmt.TURBO_MODEL_164.*;
//import net.minecraft.class_214;
//import net.minecraft.client.render.Tessellator;
//import net.minecraft.client.render.TexturedQuad;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.texture.TextureManager;
//import net.minecraft.util.maths.MathHelper;
//import org.lwjgl.opengl.GL11;
//
//import java.util.*;
//
//public class ModelRendererTurbo_copy164 extends ModelRenderer {
//
//    public ModelRendererTurbo_copy164(ModelBase modelbase, String s) {
//        super(modelbase, s);
//        this.flip = false;
//        this.compiled = false;
//        this.displayList = 0;
//        this.i = false;
//        this.j = true;
//        this.field_1402_i = false;
//        this.vertices = new PositionTextureVertex[0];
//        this.faces = new TexturedPolygon[0];
//        this.forcedRecompile = false;
//        this.transformGroup = new HashMap();
//        this.transformGroup.put("0", new TransformGroupBone(new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D));
//        this.textureGroup = new HashMap();
//        this.textureGroup.put("0", new TextureGroup());
//        this.currentTextureGroup = (TextureGroup)this.textureGroup.get("0");
//        this.boxName = s;
//        this.defaultTexture = "";
//        this.useLegacyCompiler = false;
//    }
//
//    public ModelRendererTurbo_copy164(ModelBase modelbase) {
//        this(modelbase, (String)null);
//    }
//
//    public ModelRendererTurbo_copy164(ModelBase modelbase, int textureX, int textureY) {
//        this(modelbase, textureX, textureY, 64, 32);
//    }
//
//    public ModelRendererTurbo_copy164(ModelBase modelbase, int textureX, int textureY, int textureU, int textureV) {
//        this(modelbase);
//        this.textureOffsetX = textureX;
//        this.textureOffsetY = textureY;
//        this.textureWidth = (float)textureU;
//        this.textureHeight = (float)textureV;
//    }
//
//    public void addPolygon(PositionTextureVertex[] verts) {
//        this.copyTo(verts, new TexturedPolygon[]{new TexturedPolygon(verts)});
//    }
//
//    public void addPolygon(PositionTextureVertex[] verts, int[][] uv) {
//        try {
//            for(int i = 0; i < verts.length; ++i) {
//                verts[i] = verts[i].func_78240_a((float)uv[i][0] / this.textureWidth, (float)uv[i][1] / this.textureHeight);
//            }
//        } finally {
//            this.addPolygon(verts);
//        }
//
//    }
//
//    public void addPolygon(PositionTextureVertex[] verts, int u1, int v1, int u2, int v2) {
//        this.copyTo(verts, new TexturedPolygon[]{this.addPolygonReturn(verts, u1, v1, u2, v2)});
//    }
//
//    private TexturedPolygon addPolygonReturn(PositionTextureVertex[] verts, int u1, int v1, int u2, int v2) {
//        if(verts.length < 3) {
//            return null;
//        } else {
//            float uOffs = 1.0F / (this.textureWidth * 10.0F);
//            float vOffs = 1.0F / (this.textureHeight * 10.0F);
//            if(verts.length < 4) {
//                float xMin = -1.0F;
//                float yMin = -1.0F;
//                float xMax = 0.0F;
//                float yMax = 0.0F;
//
//                float vMin;
//                float uSize;
//                for(int uMin = 0; uMin < verts.length; ++uMin) {
//                    vMin = verts[uMin].field_1147;
//                    uSize = verts[uMin].field_1148;
//                    xMax = Math.max(xMax, vMin);
//                    xMin = xMin < -1.0F ? vMin : Math.min(xMin, vMin);
//                    yMax = Math.max(yMax, uSize);
//                    yMin = yMin < -1.0F ? uSize : Math.min(yMin, uSize);
//                }
//
//                float var21 = (float)u1 / this.textureWidth + uOffs;
//                vMin = (float)v1 / this.textureHeight + vOffs;
//                uSize = (float)(u2 - u1) / this.textureWidth - uOffs * 2.0F;
//                float vSize = (float)(v2 - v1) / this.textureHeight - vOffs * 2.0F;
//                float xSize = xMax - xMin;
//                float ySize = yMax - yMin;
//
//                for(int i = 0; i < verts.length; ++i) {
//                    float xPos = verts[i].field_1147;
//                    float yPos = verts[i].field_1148;
//                    xPos = (xPos - xMin) / xSize;
//                    yPos = (yPos - yMin) / ySize;
//                    verts[i] = verts[i].func_78240_a(var21 + xPos * uSize, vMin + yPos * vSize);
//                }
//            } else {
//                verts[0] = verts[0].func_78240_a((float)u2 / this.textureWidth - uOffs, (float)v1 / this.textureHeight + vOffs);
//                verts[1] = verts[1].func_78240_a((float)u1 / this.textureWidth + uOffs, (float)v1 / this.textureHeight + vOffs);
//                verts[2] = verts[2].func_78240_a((float)u1 / this.textureWidth + uOffs, (float)v2 / this.textureHeight - vOffs);
//                verts[3] = verts[3].func_78240_a((float)u2 / this.textureWidth - uOffs, (float)v2 / this.textureHeight - vOffs);
//            }
//
//            return new TexturedPolygon(verts);
//        }
//    }
//
//    private TexturedPolygon addPolygonReturn(PositionTextureVertex[] verts, int u1, int v1, int u2, int v2, float q1, float q2, float q3, float q4) {
//        if(verts.length < 3) {
//            return null;
//        } else {
//            float uOffs = 1.0F / (this.textureWidth * 10.0F);
//            float vOffs = 1.0F / (this.textureHeight * 10.0F);
//            if(verts.length < 4) {
//                float xMin = -1.0F;
//                float yMin = -1.0F;
//                float xMax = 0.0F;
//                float yMax = 0.0F;
//
//                float vMin;
//                float uSize;
//                for(int uMin = 0; uMin < verts.length; ++uMin) {
//                    vMin = verts[uMin].field_1147;
//                    uSize = verts[uMin].field_1148;
//                    xMax = Math.max(xMax, vMin);
//                    xMin = xMin < -1.0F ? vMin : Math.min(xMin, vMin);
//                    yMax = Math.max(yMax, uSize);
//                    yMin = yMin < -1.0F ? uSize : Math.min(yMin, uSize);
//                }
//
//                float var21 = (float)u1 / this.textureWidth + uOffs;
//                vMin = (float)v1 / this.textureHeight + vOffs;
//                uSize = (float)(u2 - u1) / this.textureWidth - uOffs * 2.0F;
//                float vSize = (float)(v2 - v1) / this.textureHeight - vOffs * 2.0F;
//                float xSize = xMax - xMin;
//                float ySize = yMax - yMin;
//
//                for(int i = 0; i < verts.length; ++i) {
//                    float xPos = verts[i].field_1147;
//                    float yPos = verts[i].field_1148;
//                    xPos = (xPos - xMin) / xSize;
//                    yPos = (yPos - yMin) / ySize;
//                    verts[i] = verts[i].func_78240_a(var21 + xPos * uSize, vMin + yPos * vSize);
//                }
//            } else {
//                verts[0] = verts[0].setTexturePosition(((float)u2 / this.textureWidth - uOffs) * q1, ((float)v1 / this.textureHeight + vOffs) * q1, q1);
//                verts[1] = verts[1].setTexturePosition(((float)u1 / this.textureWidth + uOffs) * q2, ((float)v1 / this.textureHeight + vOffs) * q2, q2);
//                verts[2] = verts[2].setTexturePosition(((float)u1 / this.textureWidth + uOffs) * q3, ((float)v2 / this.textureHeight - vOffs) * q3, q3);
//                verts[3] = verts[3].setTexturePosition(((float)u2 / this.textureWidth - uOffs) * q4, ((float)v2 / this.textureHeight - vOffs) * q4, q4);
//
////                verts[0] = verts[0].method_983((float)u2 / this.textureWidth - uOffs, (float)v1 / this.textureHeight + vOffs);
////                verts[1] = verts[1].method_983((float)u1 / this.textureWidth + uOffs, (float)v1 / this.textureHeight + vOffs);
////                verts[2] = verts[2].method_983((float)u1 / this.textureWidth + uOffs, (float)v2 / this.textureHeight - vOffs);
////                verts[3] = verts[3].method_983((float)u2 / this.textureWidth - uOffs, (float)v2 / this.textureHeight - vOffs);
//            }
//
//            return new TexturedPolygon(verts);
//        }
//    }
//
//    public void addRectShape(float[] v, float[] v1, float[] v2, float[] v3, float[] v4, float[] v5, float[] v6, float[] v7, int w, int h, int d) {
//        float[] var1 = new float[]{1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
//        this.addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d, var1);
//    }
//
//    public void addRectShape(float[] v, float[] v1, float[] v2, float[] v3, float[] v4, float[] v5, float[] v6, float[] v7, int w, int h, int d, float[] qParam) {
//        PositionTextureVertex[] verts = new PositionTextureVertex[8];
//        TexturedPolygon[] poly = new TexturedPolygon[6];
//        PositionTextureVertex positionTexturevertex = new PositionTextureVertex(v[0], v[1], v[2], 0.0F, 0.0F);
//        PositionTextureVertex positionTexturevertex1 = new PositionTextureVertex(v1[0], v1[1], v1[2], 0.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex2 = new PositionTextureVertex(v2[0], v2[1], v2[2], 8.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex3 = new PositionTextureVertex(v3[0], v3[1], v3[2], 8.0F, 0.0F);
//        PositionTextureVertex positionTexturevertex4 = new PositionTextureVertex(v4[0], v4[1], v4[2], 0.0F, 0.0F);
//        PositionTextureVertex positionTexturevertex5 = new PositionTextureVertex(v5[0], v5[1], v5[2], 0.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex6 = new PositionTextureVertex(v6[0], v6[1], v6[2], 8.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex7 = new PositionTextureVertex(v7[0], v7[1], v7[2], 8.0F, 0.0F);
//        verts[0] = positionTexturevertex;
//        verts[1] = positionTexturevertex1;
//        verts[2] = positionTexturevertex2;
//        verts[3] = positionTexturevertex3;
//        verts[4] = positionTexturevertex4;
//        verts[5] = positionTexturevertex5;
//        verts[6] = positionTexturevertex6;
//        verts[7] = positionTexturevertex7;
//        poly[0] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex5, positionTexturevertex1, positionTexturevertex2, positionTexturevertex6}, this.textureOffsetX + d + w, this.textureOffsetY + d, this.textureOffsetX + d + w + d, this.textureOffsetY + d + h, 1.0F, qParam[7], qParam[10] * qParam[7], qParam[10]);
//        poly[1] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex, positionTexturevertex4, positionTexturevertex7, positionTexturevertex3}, this.textureOffsetX + 0, this.textureOffsetY + d, this.textureOffsetX + d, this.textureOffsetY + d + h, qParam[9] * qParam[6], qParam[9], 1.0F, qParam[6]);
//        poly[2] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex5, positionTexturevertex4, positionTexturevertex, positionTexturevertex1}, this.textureOffsetX + d, this.textureOffsetY + 0, this.textureOffsetX + d + w, this.textureOffsetY + d, 1.0F, qParam[8], qParam[1] * qParam[8], qParam[1]);
//        poly[3] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex2, positionTexturevertex3, positionTexturevertex7, positionTexturevertex6}, this.textureOffsetX + d + w, this.textureOffsetY + 0, this.textureOffsetX + d + w + w, this.textureOffsetY + d, qParam[3], qParam[3] * qParam[11], qParam[11], 1.0F);
//        poly[4] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex1, positionTexturevertex, positionTexturevertex3, positionTexturevertex2}, this.textureOffsetX + d, this.textureOffsetY + d, this.textureOffsetX + d + w, this.textureOffsetY + d + h, qParam[0], qParam[0] * qParam[4], qParam[4], 1.0F);
//        poly[5] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex4, positionTexturevertex5, positionTexturevertex6, positionTexturevertex7}, this.textureOffsetX + d + w + d, this.textureOffsetY + d, this.textureOffsetX + d + w + d + w, this.textureOffsetY + d + h, qParam[2] * qParam[5], qParam[2], 1.0F, qParam[5]);
//        if(this.i ^ this.flip) {
//            for(int l = 0; l < poly.length; ++l) {
//                poly[l].method_1925();
//            }
//        }
//
//        this.copyTo(verts, poly);
//    }
//
//    public ModelRendererTurbo_copy164 addBox(float x, float y, float z, int w, int h, int d) {
//        this.addBox(x, y, z, w, h, d, 0.0F);
//        return this;
//    }
//
//    public void addBox(float x, float y, float z, int w, int h, int d, float expansion) {
//        this.addBox(x, y, z, w, h, d, expansion, 1.0F);
//    }
//
//    public void addBox(float x, float y, float z, int w, int h, int d, float expansion, float scale) {
//        float scaleX = (float)w * scale;
//        float scaleY = (float)h * scale;
//        float scaleZ = (float)d * scale;
//        float x1 = x + scaleX;
//        float y1 = y + scaleY;
//        float z1 = z + scaleZ;
//        float expX = expansion + scaleX - (float)w;
//        float expY = expansion + scaleY - (float)h;
//        float expZ = expansion + scaleZ - (float)d;
//        x -= expX;
//        y -= expY;
//        z -= expZ;
//        x1 += expansion;
//        y1 += expansion;
//        z1 += expansion;
//        if(this.i) {
//            float v = x1;
//            x1 = x;
//            x = v;
//        }
//
//        float[] v0 = new float[]{x, y, z};
//        float[] v1 = new float[]{x1, y, z};
//        float[] v2 = new float[]{x1, y1, z};
//        float[] v3 = new float[]{x, y1, z};
//        float[] v4 = new float[]{x, y, z1};
//        float[] v5 = new float[]{x1, y, z1};
//        float[] v6 = new float[]{x1, y1, z1};
//        float[] v7 = new float[]{x, y1, z1};
//        this.addRectShape(v0, v1, v2, v3, v4, v5, v6, v7, w, h, d);
//    }
//
//    public void addTrapezoid(float x, float y, float z, int w, int h, int d, float scale, float bottomScale, int dir) {
//        float f4 = x + (float)w;
//        float f5 = y + (float)h;
//        float f6 = z + (float)d;
//        x -= scale;
//        y -= scale;
//        z -= scale;
//        f4 += scale;
//        f5 += scale;
//        f6 += scale;
//        int m = this.i ? -1 : 1;
//        if(this.i) {
//            float v = f4;
//            f4 = x;
//            x = v;
//        }
//
//        float[] v0 = new float[]{x, y, z};
//        float[] v1 = new float[]{f4, y, z};
//        float[] v2 = new float[]{f4, f5, z};
//        float[] v3 = new float[]{x, f5, z};
//        float[] v4 = new float[]{x, y, f6};
//        float[] v5 = new float[]{f4, y, f6};
//        float[] v6 = new float[]{f4, f5, f6};
//        float[] v7 = new float[]{x, f5, f6};
//        switch(dir) {
//            case 0:
//                v0[0] -= (float)m * bottomScale;
//                v0[1] -= bottomScale;
//                v1[0] += (float)m * bottomScale;
//                v1[1] -= bottomScale;
//                v2[0] += (float)m * bottomScale;
//                v2[1] += bottomScale;
//                v3[0] -= (float)m * bottomScale;
//                v3[1] += bottomScale;
//                break;
//            case 1:
//                v4[0] -= (float)m * bottomScale;
//                v4[1] -= bottomScale;
//                v5[0] += (float)m * bottomScale;
//                v5[1] -= bottomScale;
//                v6[0] += (float)m * bottomScale;
//                v6[1] += bottomScale;
//                v7[0] -= (float)m * bottomScale;
//                v7[1] += bottomScale;
//                break;
//            case 2:
//                v1[1] -= bottomScale;
//                v1[2] -= bottomScale;
//                v2[1] += bottomScale;
//                v2[2] -= bottomScale;
//                v5[1] -= bottomScale;
//                v5[2] += bottomScale;
//                v6[1] += bottomScale;
//                v6[2] += bottomScale;
//                break;
//            case 3:
//                v0[1] -= bottomScale;
//                v0[2] -= bottomScale;
//                v3[1] += bottomScale;
//                v3[2] -= bottomScale;
//                v4[1] -= bottomScale;
//                v4[2] += bottomScale;
//                v7[1] += bottomScale;
//                v7[2] += bottomScale;
//                break;
//            case 4:
//                v0[0] -= (float)m * bottomScale;
//                v0[2] -= bottomScale;
//                v1[0] += (float)m * bottomScale;
//                v1[2] -= bottomScale;
//                v4[0] -= (float)m * bottomScale;
//                v4[2] += bottomScale;
//                v5[0] += (float)m * bottomScale;
//                v5[2] += bottomScale;
//                break;
//            case 5:
//                v2[0] += (float)m * bottomScale;
//                v2[2] -= bottomScale;
//                v3[0] -= (float)m * bottomScale;
//                v3[2] -= bottomScale;
//                v6[0] += (float)m * bottomScale;
//                v6[2] += bottomScale;
//                v7[0] -= (float)m * bottomScale;
//                v7[2] += bottomScale;
//        }
//        float[] qValues = new float[]{Math.abs((v1[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v1[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v1[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v1[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v1[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v1[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2]))};
//        this.addRectShape(v0, v1, v2, v3, v4, v5, v6, v7, w, h, d, qValues);
//    }
//
//    public void addFlexBox(float x, float y, float z, int w, int h, int d, float scale, float bScale1, float bScale2, float bScale3, float bScale4, int dir) {
//        float f4 = x + (float)w;
//        float f5 = y + (float)h;
//        float f6 = z + (float)d;
//        x -= scale;
//        y -= scale;
//        z -= scale;
//        f4 += scale;
//        f5 += scale;
//        f6 += scale;
//        int m = this.i ? -1 : 1;
//        if(this.i) {
//            float v = f4;
//            f4 = x;
//            x = v;
//        }
//
//        float[] v0 = new float[]{x, y, z};
//        float[] v1 = new float[]{f4, y, z};
//        float[] v2 = new float[]{f4, f5, z};
//        float[] v3 = new float[]{x, f5, z};
//        float[] v4 = new float[]{x, y, f6};
//        float[] v5 = new float[]{f4, y, f6};
//        float[] v6 = new float[]{f4, f5, f6};
//        float[] v7 = new float[]{x, f5, f6};
//        switch(dir) {
//            case 0:
//                v1[0] -= (float)m * bScale4;
//                v1[1] -= bScale1;
//                v1[0] += (float)m * bScale3;
//                v1[1] -= bScale1;
//                v2[0] += (float)m * bScale3;
//                v2[1] += bScale2;
//                v3[0] -= (float)m * bScale4;
//                v3[1] += bScale2;
//                break;
//            case 1:
//                v4[0] -= (float)m * bScale4;
//                v4[1] -= bScale1;
//                v5[0] += (float)m * bScale3;
//                v5[1] -= bScale1;
//                v6[0] += (float)m * bScale3;
//                v6[1] += bScale2;
//                v7[0] -= (float)m * bScale4;
//                v7[1] += bScale2;
//                break;
//            case 2:
//                v1[1] -= bScale1;
//                v1[2] -= bScale3;
//                v2[1] += bScale2;
//                v2[2] -= bScale3;
//                v5[1] -= bScale1;
//                v5[2] += bScale4;
//                v6[1] += bScale2;
//                v6[2] += bScale4;
//                break;
//            case 3:
//                v1[1] -= bScale1;
//                v1[2] -= bScale3;
//                v3[1] += bScale2;
//                v3[2] -= bScale3;
//                v4[1] -= bScale1;
//                v4[2] += bScale4;
//                v7[1] += bScale2;
//                v7[2] += bScale4;
//                break;
//            case 4:
//                v1[0] -= (float)m * bScale1;
//                v1[2] -= bScale3;
//                v1[0] += (float)m * bScale2;
//                v1[2] -= bScale3;
//                v4[0] -= (float)m * bScale1;
//                v4[2] += bScale4;
//                v5[0] += (float)m * bScale2;
//                v5[2] += bScale4;
//                break;
//            case 5:
//                v2[0] += (float)m * bScale2;
//                v2[2] -= bScale3;
//                v3[0] -= (float)m * bScale1;
//                v3[2] -= bScale3;
//                v6[0] += (float)m * bScale2;
//                v6[2] += bScale4;
//                v7[0] -= (float)m * bScale1;
//                v7[2] += bScale4;
//        }
//
//        float[] qValues = new float[]{Math.abs((v1[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v1[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v1[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v1[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v1[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v1[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2]))};
//        this.addRectShape(v0, v1, v2, v3, v4, v5, v6, v7, w, h, d, qValues);
//    }
//
//    public void addFlexTrapezoid(float x, float y, float z, int w, int h, int d, float scale, float bScale1, float bScale2, float bScale3, float bScale4, float fScale1, float fScale2, int dir) {
//        float f4 = x + (float)w;
//        float f5 = y + (float)h;
//        float f6 = z + (float)d;
//        x -= scale;
//        y -= scale;
//        z -= scale;
//        f4 += scale;
//        f5 += scale;
//        f6 += scale;
//        int m = this.i ? -1 : 1;
//        if(this.i) {
//            float v = f4;
//            f4 = x;
//            x = v;
//        }
//
//        float[] v0 = new float[]{x, y, z};
//        float[] v1 = new float[]{f4, y, z};
//        float[] v2 = new float[]{f4, f5, z};
//        float[] v3 = new float[]{x, f5, z};
//        float[] v4 = new float[]{x, y, f6};
//        float[] v5 = new float[]{f4, y, f6};
//        float[] v6 = new float[]{f4, f5, f6};
//        float[] v7 = new float[]{x, f5, f6};
//        switch(dir) {
//            case 0:
//                v1[1] -= fScale1;
//                v5[1] -= fScale1;
//                v2[1] += fScale2;
//                v6[1] += fScale2;
//                v1[0] -= (float)m * bScale4;
//                v1[1] -= bScale1;
//                v1[0] += (float)m * bScale3;
//                v1[1] -= bScale1;
//                v2[0] += (float)m * bScale3;
//                v2[1] += bScale2;
//                v3[0] -= (float)m * bScale4;
//                v3[1] += bScale2;
//                break;
//            case 1:
//                v1[1] -= fScale1;
//                v5[1] -= fScale1;
//                v2[1] += fScale2;
//                v6[1] += fScale2;
//                v4[0] -= (float)m * bScale4;
//                v4[1] -= bScale1;
//                v5[0] += (float)m * bScale3;
//                v5[1] -= bScale1;
//                v6[0] += (float)m * bScale3;
//                v6[1] += bScale2;
//                v7[0] -= (float)m * bScale4;
//                v7[1] += bScale2;
//                break;
//            case 2:
//                v1[2] -= fScale1;
//                v1[2] -= fScale1;
//                v4[2] += fScale2;
//                v5[2] += fScale2;
//                v1[1] -= bScale1;
//                v1[2] -= bScale3;
//                v2[1] += bScale2;
//                v2[2] -= bScale3;
//                v5[1] -= bScale1;
//                v5[2] += bScale4;
//                v6[1] += bScale2;
//                v6[2] += bScale4;
//                break;
//            case 3:
//                v1[2] -= fScale1;
//                v1[2] -= fScale1;
//                v4[2] += fScale2;
//                v5[2] += fScale2;
//                v1[1] -= bScale1;
//                v1[2] -= bScale3;
//                v3[1] += bScale2;
//                v3[2] -= bScale3;
//                v4[1] -= bScale1;
//                v4[2] += bScale4;
//                v7[1] += bScale2;
//                v7[2] += bScale4;
//                break;
//            case 4:
//                v1[2] -= fScale1;
//                v2[2] -= fScale1;
//                v5[2] += fScale2;
//                v6[2] += fScale2;
//                v1[0] -= (float)m * bScale1;
//                v1[2] -= bScale3;
//                v1[0] += (float)m * bScale2;
//                v1[2] -= bScale3;
//                v4[0] -= (float)m * bScale1;
//                v4[2] += bScale4;
//                v5[0] += (float)m * bScale2;
//                v5[2] += bScale4;
//                break;
//            case 5:
//                v1[2] -= fScale1;
//                v2[2] -= fScale1;
//                v5[2] += fScale2;
//                v6[2] += fScale2;
//                v2[0] += (float)m * bScale2;
//                v2[2] -= bScale3;
//                v3[0] -= (float)m * bScale1;
//                v3[2] -= bScale3;
//                v6[0] += (float)m * bScale2;
//                v6[2] += bScale4;
//                v7[0] -= (float)m * bScale1;
//                v7[2] += bScale4;
//        }
//
//        float[] qValues = new float[]{Math.abs((v1[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v1[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v1[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v1[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v1[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v1[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2]))};
//        this.addRectShape(v0, v1, v2, v3, v4, v5, v6, v7, w, h, d, qValues);
//    }
//
//    public void addShape3D(float x, float y, float z, Coord2D[] coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction) {
//        this.addShape3D(x, y, z, (Coord2D[])coordinates, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, (float[])null);
//    }
//
//    public void addShape3D(float x, float y, float z, Coord2D[] coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction, float[] faceLengths) {
//        this.addShape3D(x, y, z, new Shape2D(coordinates), depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, faceLengths);
//    }
//
//    public void addShape3D(float x, float y, float z, ArrayList coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction) {
//        this.addShape3D(x, y, z, (ArrayList)coordinates, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, (float[])null);
//    }
//
//    public void addShape3D(float x, float y, float z, ArrayList coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction, float[] faceLengths) {
//        this.addShape3D(x, y, z, new Shape2D(coordinates), depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, faceLengths);
//    }
//
//    public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction) {
//        this.addShape3D(x, y, z, (Shape2D)shape, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, (float[])null);
//    }
//
//    public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction, float[] faceLengths) {
//        float rotX = 0.0F;
//        float rotY = 0.0F;
//        float rotZ = 0.0F;
//        switch(direction) {
//            case 0:
//                rotY = (float)Math.PI;
//            case 1:
//            default:
//                break;
//            case 2:
//                rotY = (float)Math.PI / 2F;
//                break;
//            case 3:
//                rotY = -1.5707964F;
//                break;
//            case 4:
//                rotX = (float)Math.PI / 2F;
//                break;
//            case 5:
//                rotX = -1.5707964F;
//        }
//
//        this.addShape3D(x, y, z, shape, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, rotX, rotY, rotZ, faceLengths);
//    }
//
//    public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, float rotX, float rotY, float rotZ) {
//        this.addShape3D(x, y, z, shape, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, rotX, rotY, rotZ, (float[])null);
//    }
//
//    public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, float rotX, float rotY, float rotZ, float[] faceLengths) {
//        Shape3D shape3D = shape.extrude(x, y, z, rotX, rotY, rotZ, depth, this.textureOffsetX, this.textureOffsetY, this.textureWidth, this.textureHeight, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, faceLengths);
//        if(this.flip) {
//            for(int idx = 0; idx < shape3D.faces.length; ++idx) {
//                shape3D.faces[idx].method_1925();
//            }
//        }
//
//        this.copyTo(shape3D.vertices, (TexturedPolygon[])shape3D.faces);
//    }
//
//    public void addPixel(float x, float y, float z, float width, float height, float length) {
//        this.addPixel(x, y, z, new float[]{width, height, length}, this.textureOffsetX, this.textureOffsetY);
//    }
//
//    public void addPixel(float x, float y, float z, float[] scale, int w, int h) {
//        PositionTextureVertex[] verts = new PositionTextureVertex[8];
//        TexturedPolygon[] poly = new TexturedPolygon[6];
//        float x1 = x + scale[0];
//        float y1 = y + scale[1];
//        float z1 = z + scale[2];
//        float[] f = new float[]{x, y, z};
//        float[] f1 = new float[]{x1, y, z};
//        float[] f2 = new float[]{x1, y1, z};
//        float[] f3 = new float[]{x, y1, z};
//        float[] f4 = new float[]{x, y, z1};
//        float[] f5 = new float[]{x1, y, z1};
//        float[] f6 = new float[]{x1, y1, z1};
//        float[] f7 = new float[]{x, y1, z1};
//        PositionTextureVertex positionTexturevertex = new PositionTextureVertex(f[0], f[1], f[2], 0.0F, 0.0F);
//        PositionTextureVertex positionTexturevertex1 = new PositionTextureVertex(f1[0], f1[1], f1[2], 0.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex2 = new PositionTextureVertex(f2[0], f2[1], f2[2], 8.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex3 = new PositionTextureVertex(f3[0], f3[1], f3[2], 8.0F, 0.0F);
//        PositionTextureVertex positionTexturevertex4 = new PositionTextureVertex(f4[0], f4[1], f4[2], 0.0F, 0.0F);
//        PositionTextureVertex positionTexturevertex5 = new PositionTextureVertex(f5[0], f5[1], f5[2], 0.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex6 = new PositionTextureVertex(f6[0], f6[1], f6[2], 8.0F, 8.0F);
//        PositionTextureVertex positionTexturevertex7 = new PositionTextureVertex(f7[0], f7[1], f7[2], 8.0F, 0.0F);
//        verts[0] = positionTexturevertex;
//        verts[1] = positionTexturevertex1;
//        verts[2] = positionTexturevertex2;
//        verts[3] = positionTexturevertex3;
//        verts[4] = positionTexturevertex4;
//        verts[5] = positionTexturevertex5;
//        verts[6] = positionTexturevertex6;
//        verts[7] = positionTexturevertex7;
//        poly[0] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex5, positionTexturevertex1, positionTexturevertex2, positionTexturevertex6}, w, h, w + 1, h + 1);
//        poly[1] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex, positionTexturevertex4, positionTexturevertex7, positionTexturevertex3}, w, h, w + 1, h + 1);
//        poly[2] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex5, positionTexturevertex4, positionTexturevertex, positionTexturevertex1}, w, h, w + 1, h + 1);
//        poly[3] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex2, positionTexturevertex3, positionTexturevertex7, positionTexturevertex6}, w, h, w + 1, h + 1);
//        poly[4] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex1, positionTexturevertex, positionTexturevertex3, positionTexturevertex2}, w, h, w + 1, h + 1);
//        poly[5] = this.addPolygonReturn(new PositionTextureVertex[]{positionTexturevertex4, positionTexturevertex5, positionTexturevertex6, positionTexturevertex7}, w, h, w + 1, h + 1);
//        this.copyTo(verts, poly);
//    }
//
//    public void addSprite(float x, float y, float z, int w, int h, float expansion) {
//        this.addSprite(x, y, z, w, h, 1, false, false, false, false, false, expansion);
//    }
//
//    public void addSprite(float x, float y, float z, int w, int h, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
//        this.addSprite(x, y, z, w, h, 1, rotX, rotY, rotZ, mirrorX, mirrorY, expansion);
//    }
//
//    public void addSprite(float x, float y, float z, int w, int h, int d, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
//        this.addSprite(x, y, z, w, h, d, 1.0F, rotX, rotY, rotZ, mirrorX, mirrorY, expansion);
//    }
//
//    public void addSprite(float x, float y, float z, int w, int h, int d, float pixelScale, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
//        String[] mask = new String[h];
//        char[] str = new char[w];
//        Arrays.fill(str, '1');
//        Arrays.fill(mask, new String(str));
//        this.addSprite(x, y, z, mask, d, pixelScale, rotX, rotY, rotZ, mirrorX, mirrorY, expansion);
//    }
//
//    public void addSprite(float x, float y, float z, String[] mask, int d, float pixelScale, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
//        int w = mask[0].length();
//        int h = mask.length;
//        float x1 = x - expansion;
//        float y1 = y - expansion;
//        float z1 = z - expansion;
//        boolean wDir = false;
//        boolean hDir = false;
//        boolean dDir = false;
//        float wScale = 1.0F + expansion / ((float)w * pixelScale);
//        float hScale = 1.0F + expansion / ((float)h * pixelScale);
//        byte var32;
//        byte var33;
//        byte var34;
//        if(!rotX) {
//            if(!rotY) {
//                if(!rotZ) {
//                    var32 = 0;
//                    var33 = 1;
//                    var34 = 2;
//                } else {
//                    var32 = 1;
//                    var33 = 0;
//                    var34 = 2;
//                }
//            } else if(!rotZ) {
//                var32 = 2;
//                var33 = 1;
//                var34 = 0;
//            } else {
//                var32 = 2;
//                var33 = 0;
//                var34 = 1;
//            }
//        } else if(!rotY) {
//            if(!rotZ) {
//                var32 = 0;
//                var33 = 2;
//                var34 = 1;
//            } else {
//                var32 = 1;
//                var33 = 2;
//                var34 = 0;
//            }
//        } else if(!rotZ) {
//            var32 = 2;
//            var33 = 0;
//            var34 = 1;
//        } else {
//            var32 = 2;
//            var33 = 1;
//            var34 = 0;
//        }
//
//        int texStartX = this.textureOffsetX + (mirrorX ? w * 1 - 1 : 0);
//        int texStartY = this.textureOffsetY + (mirrorY ? h * 1 - 1 : 0);
//        int texDirX = mirrorX ? -1 : 1;
//        int texDirY = mirrorY ? -1 : 1;
//        float wVoxSize = this.getPixelSize(wScale, hScale, (float)d * pixelScale + expansion * 2.0F, 0, 1, var32, 1, 1);
//        float hVoxSize = this.getPixelSize(wScale, hScale, (float)d * pixelScale + expansion * 2.0F, 0, 1, var33, 1, 1);
//        float dVoxSize = this.getPixelSize(wScale, hScale, (float)d * pixelScale + expansion * 2.0F, 0, 1, var34, 1, 1);
//
//        for(int i = 0; i < w; ++i) {
//            for(int j = 0; j < h; ++j) {
//                if(mask[j].charAt(i) == 49) {
//                    this.addPixel(x1 + this.getPixelSize(wScale, hScale, 0.0F, var32, var33, 0, i, j), y1 + this.getPixelSize(wScale, hScale, 0.0F, var32, var33, 1, i, j), z1 + this.getPixelSize(wScale, hScale, 0.0F, var32, var33, 2, i, j), new float[]{wVoxSize, hVoxSize, dVoxSize}, texStartX + texDirX * i, texStartY + texDirY * j);
//                }
//            }
//        }
//
//    }
//
//    private float getPixelSize(float wScale, float hScale, float dScale, int wDir, int hDir, int checkDir, int texPosX, int texPosY) {
//        return wDir == checkDir ? wScale * (float)texPosX : (hDir == checkDir ? hScale * (float)texPosY : dScale);
//    }
//
//    public void addSphere(float x, float y, float z, float r, int segs, int rings, int textureW, int textureH) {
//        if(segs < 3) {
//            segs = 3;
//        }
//
//        ++rings;
//        PositionTextureVertex[] tempVerts = new PositionTextureVertex[segs * (rings - 1) + 2];
//        TexturedPolygon[] poly = new TexturedPolygon[segs * rings];
//        tempVerts[0] = new PositionTextureVertex(x, y - r, z, 0.0F, 0.0F);
//        tempVerts[tempVerts.length - 1] = new PositionTextureVertex(x, y + r, z, 0.0F, 0.0F);
//        float uOffs = 1.0F / (this.textureWidth * 10.0F);
//        float vOffs = 1.0F / (this.textureHeight * 10.0F);
//        float texW = (float)textureW / this.textureWidth - 2.0F * uOffs;
//        float texH = (float)textureH / this.textureHeight - 2.0F * vOffs;
//        float segW = texW / (float)segs;
//        float segH = texH / (float)rings;
//        float startU = (float)this.textureOffsetX / this.textureWidth;
//        float startV = (float)this.textureOffsetY / this.textureHeight;
//        int currentFace = 0;
//
//        int i;
//        PositionTextureVertex[] var28;
//        for(i = 1; i < rings; ++i) {
//            for(int verts = 0; verts < segs; ++verts) {
//                float curVert = MathHelper.cos(-1.5707964F + (float)Math.PI / (float)rings * (float)i);
//                float yHeight = MathHelper.sin(-1.5707964F + (float)Math.PI / (float)rings * (float)i);
//                float xSize = MathHelper.sin((float)Math.PI / (float)segs * (float)verts * 2.0F + (float)Math.PI) * curVert;
//                float zSize = -MathHelper.cos((float)Math.PI / (float)segs * (float)verts * 2.0F + (float)Math.PI) * curVert;
//                int curVert1 = 1 + verts + segs * (i - 1);
//                tempVerts[curVert1] = new PositionTextureVertex(x + xSize * r, y + yHeight * r, z + zSize * r, 0.0F, 0.0F);
//                if(verts > 0) {
//                    PositionTextureVertex[] verts1;
//                    if(i == 1) {
//                        verts1 = new PositionTextureVertex[]{tempVerts[curVert1].func_78240_a(startU + segW * (float)verts, startV + segH * (float)i), tempVerts[curVert1 - 1].func_78240_a(startU + segW * (float)(verts - 1), startV + segH * (float)i), tempVerts[0].func_78240_a(startU + segW * (float)(verts - 1), startV), tempVerts[0].func_78240_a(startU + segW + segW * (float)verts, startV)};
//                    } else {
//                        verts1 = new PositionTextureVertex[]{tempVerts[curVert1].func_78240_a(startU + segW * (float)verts, startV + segH * (float)i), tempVerts[curVert1 - 1].func_78240_a(startU + segW * (float)(verts - 1), startV + segH * (float)i), tempVerts[curVert1 - 1 - segs].func_78240_a(startU + segW * (float)(verts - 1), startV + segH * (float)(i - 1)), tempVerts[curVert1 - segs].func_78240_a(startU + segW * (float)verts, startV + segH * (float)(i - 1))};
//                    }
//
//                    poly[currentFace] = new TexturedPolygon(verts1);
//                    ++currentFace;
//                }
//            }
//
//            if(i == 1) {
//                var28 = new PositionTextureVertex[]{tempVerts[1].func_78240_a(startU + segW * (float)segs, startV + segH * (float)i), tempVerts[segs].func_78240_a(startU + segW * (float)(segs - 1), startV + segH * (float)i), tempVerts[0].func_78240_a(startU + segW * (float)(segs - 1), startV), tempVerts[0].func_78240_a(startU + segW * (float)segs, startV)};
//            } else {
//                var28 = new PositionTextureVertex[]{tempVerts[1 + segs * (i - 1)].func_78240_a(startU + texW, startV + segH * (float)i), tempVerts[segs * (i - 1) + segs].func_78240_a(startU + texW - segW, startV + segH * (float)i), tempVerts[segs * (i - 1)].func_78240_a(startU + texW - segW, startV + segH * (float)(i - 1)), tempVerts[1 + segs * (i - 1) - segs].func_78240_a(startU + texW, startV + segH * (float)(i - 1))};
//            }
//
//            poly[currentFace] = new TexturedPolygon(var28);
//            ++currentFace;
//        }
//
//        for(i = 0; i < segs; ++i) {
//            var28 = new PositionTextureVertex[3];
//            int var29 = tempVerts.length - (segs + 1);
//            var28[0] = tempVerts[tempVerts.length - 1].func_78240_a(startU + segW * ((float)i + 0.5F), startV + texH);
//            var28[1] = tempVerts[var29 + i].func_78240_a(startU + segW * (float)i, startV + texH - segH);
//            var28[2] = tempVerts[var29 + (i + 1) % segs].func_78240_a(startU + segW * (float)(i + 1), startV + texH - segH);
//            poly[currentFace] = new TexturedPolygon(var28);
//            ++currentFace;
//        }
//
//        this.copyTo(tempVerts, poly);
//    }
//
//    public void addCone(float x, float y, float z, float radius, float length, int segments) {
//        this.addCone(x, y, z, radius, length, segments, 1.0F);
//    }
//
//    public void addCone(float x, float y, float z, float radius, float length, int segments, float baseScale) {
//        this.addCone(x, y, z, radius, length, segments, baseScale, 4);
//    }
//
//    public void addCone(float x, float y, float z, float radius, float length, int segments, float baseScale, int baseDirection) {
//        this.addCone(x, y, z, radius, length, segments, baseScale, baseDirection, (int)Math.floor((double)(radius * 2.0F)), (int)Math.floor((double)(radius * 2.0F)));
//    }
//
//    public void addCone(float x, float y, float z, float radius, float length, int segments, float baseScale, int baseDirection, int textureCircleDiameterW, int textureCircleDiameterH) {
//        this.addCylinder(x, y, z, radius, length, segments, baseScale, 0.0F, baseDirection, textureCircleDiameterW, textureCircleDiameterH, 1);
//    }
//
//    public void addCylinder(float x, float y, float z, float radius, float length, int segments) {
//        this.addCylinder(x, y, z, radius, length, segments, 1.0F, 1.0F);
//    }
//
//    public void addCylinder(float x, float y, float z, float radius, float length, int segments, float baseScale, float topScale) {
//        this.addCylinder(x, y, z, radius, length, segments, baseScale, topScale, 4);
//    }
//
//    public void addCylinder(float x, float y, float z, float radius, float length, int segments, float baseScale, float topScale, int baseDirection) {
//        this.addCylinder(x, y, z, radius, length, segments, baseScale, topScale, baseDirection, (int)Math.floor((double)(radius * 2.0F)), (int)Math.floor((double)(radius * 2.0F)), (int)Math.floor((double)length));
//    }
//
//    public void addCylinder(float x, float y, float z, float radius, float length, int segments, float baseScale, float topScale, int baseDirection, int textureCircleDiameterW, int textureCircleDiameterH, int textureH) {
//        boolean dirTop = baseDirection == 4 || baseDirection == 5;
//        boolean dirSide = baseDirection == 3 || baseDirection == 2;
//        boolean dirFront = baseDirection == 0 || baseDirection == 1;
//        boolean dirMirror = baseDirection == 2 || baseDirection == 5 || baseDirection == 1;
//        boolean coneBase = baseScale == 0.0F;
//        boolean coneTop = topScale == 0.0F;
//        if(coneBase && coneTop) {
//            baseScale = 1.0F;
//            coneBase = false;
//        }
//
//        PositionTextureVertex[] tempVerts = new PositionTextureVertex[segments * (!coneBase && !coneTop ? 2 : 1) + 2];
//        TexturedPolygon[] poly = new TexturedPolygon[segments * (!coneBase && !coneTop ? 3 : 2)];
//        float xLength = dirSide ? length : 0.0F;
//        float yLength = dirTop ? length : 0.0F;
//        float zLength = dirFront ? length : 0.0F;
//        float xStart = dirMirror ? x + xLength : x;
//        float yStart = dirMirror ? y + yLength : y;
//        float zStart = dirMirror ? z + zLength : z;
//        float xEnd = !dirMirror ? x + xLength : x;
//        float yEnd = !dirMirror ? y + yLength : y;
//        float zEnd = !dirMirror ? z + zLength : z;
//        tempVerts[0] = new PositionTextureVertex(xStart, yStart, zStart, 0.0F, 0.0F);
//        tempVerts[tempVerts.length - 1] = new PositionTextureVertex(xEnd, yEnd, zEnd, 0.0F, 0.0F);
//        float xCur = xStart;
//        float yCur = yStart;
//        float zCur = zStart;
//        float sCur = coneBase ? topScale : baseScale;
//
//        float uOffset;
//        float vOffset;
//        float uCircle;
//        float vCircle;
//        float uWidth;
//        for(int uScale = 0; uScale < (!coneBase && !coneTop ? 2 : 1); ++uScale) {
//            for(int vScale = 0; vScale < segments; ++vScale) {
//                uOffset = (float)(this.i ^ dirMirror ? -1 : 1) * MathHelper.sin((float)Math.PI / (float)segments * (float)vScale * 2.0F + (float)Math.PI) * radius * sCur;
//                vOffset = -MathHelper.cos((float)Math.PI / (float)segments * (float)vScale * 2.0F + (float)Math.PI) * radius * sCur;
//                uCircle = xCur + (!dirSide ? uOffset : 0.0F);
//                vCircle = yCur + (!dirTop ? vOffset : 0.0F);
//                uWidth = zCur + (dirSide ? uOffset : (dirTop ? vOffset : 0.0F));
//                tempVerts[1 + vScale + uScale * segments] = new PositionTextureVertex(uCircle, vCircle, uWidth, 0.0F, 0.0F);
//            }
//
//            xCur = xEnd;
//            yCur = yEnd;
//            zCur = zEnd;
//            sCur = topScale;
//        }
//
//        float var51 = 1.0F / this.textureWidth;
//        float var52 = 1.0F / this.textureHeight;
//        uOffset = var51 / 20.0F;
//        vOffset = var52 / 20.0F;
//        uCircle = (float)textureCircleDiameterW * var51;
//        vCircle = (float)textureCircleDiameterH * var52;
//        uWidth = (uCircle * 2.0F - uOffset * 2.0F) / (float)segments;
//        float vHeight = (float)textureH * var52 - uOffset * 2.0F;
//        float uStart = (float)this.textureOffsetX * var51;
//        float vStart = (float)this.textureOffsetY * var52;
//
//        for(int index = 0; index < segments; ++index) {
//            int index2 = (index + 1) % segments;
//            float uSize = MathHelper.sin((float)Math.PI / (float)segments * (float)index * 2.0F + (!dirTop ? 0.0F : (float)Math.PI)) * (0.5F * uCircle - 2.0F * uOffset);
//            float vSize = MathHelper.cos((float)Math.PI / (float)segments * (float)index * 2.0F + (!dirTop ? 0.0F : (float)Math.PI)) * (0.5F * vCircle - 2.0F * vOffset);
//            float uSize1 = MathHelper.sin((float)Math.PI / (float)segments * (float)index2 * 2.0F + (!dirTop ? 0.0F : (float)Math.PI)) * (0.5F * uCircle - 2.0F * uOffset);
//            float vSize1 = MathHelper.cos((float)Math.PI / (float)segments * (float)index2 * 2.0F + (!dirTop ? 0.0F : (float)Math.PI)) * (0.5F * vCircle - 2.0F * vOffset);
//            PositionTextureVertex[] vert = new PositionTextureVertex[]{tempVerts[0].func_78240_a(uStart + 0.5F * uCircle, vStart + 0.5F * vCircle), tempVerts[1 + index2].func_78240_a(uStart + 0.5F * uCircle + uSize1, vStart + 0.5F * vCircle + vSize1), tempVerts[1 + index].func_78240_a(uStart + 0.5F * uCircle + uSize, vStart + 0.5F * vCircle + vSize)};
//            poly[index] = new TexturedPolygon(vert);
//            if(this.i ^ this.flip) {
//                poly[index].method_1925();
//            }
//
//            if(!coneBase && !coneTop) {
//                vert = new PositionTextureVertex[]{tempVerts[1 + index].func_78240_a(uStart + uOffset + uWidth * (float)index, vStart + vOffset + vCircle), tempVerts[1 + index2].func_78240_a(uStart + uOffset + uWidth * (float)(index + 1), vStart + vOffset + vCircle), tempVerts[1 + segments + index2].func_78240_a(uStart + uOffset + uWidth * (float)(index + 1), vStart + vOffset + vCircle + vHeight), tempVerts[1 + segments + index].func_78240_a(uStart + uOffset + uWidth * (float)index, vStart + vOffset + vCircle + vHeight)};
//                poly[index + segments] = new TexturedPolygon(vert);
//                if(this.i ^ this.flip) {
//                    poly[index + segments].method_1925();
//                }
//            }
//
//            vert = new PositionTextureVertex[]{tempVerts[tempVerts.length - 1].func_78240_a(uStart + 1.5F * uCircle, vStart + 0.5F * vCircle), tempVerts[tempVerts.length - 2 - index].func_78240_a(uStart + 1.5F * uCircle + uSize1, vStart + 0.5F * vCircle + vSize1), tempVerts[tempVerts.length - (1 + segments) + (segments - index) % segments].func_78240_a(uStart + 1.5F * uCircle + uSize, vStart + 0.5F * vCircle + vSize)};
//            poly[poly.length - segments + index] = new TexturedPolygon(vert);
//            if(this.i ^ this.flip) {
//                poly[poly.length - segments + index].method_1925();
//            }
//        }
//
//        this.copyTo(tempVerts, poly);
//    }
//
//    public void addObj(String file) {
//        this.addModel(file, ModelPool.OBJ);
//    }
//
//    public void addModel(String file, Class modelFormat) {
//        ModelPoolEntry entry = ModelPool.addFile(file, modelFormat, this.transformGroup, this.textureGroup);
//        if(entry != null) {
//            PositionTextureVertex[] verts = (PositionTextureVertex[])Arrays.copyOf(entry.vertices, entry.vertices.length);
//            TexturedPolygon[] poly = (TexturedPolygon[])Arrays.copyOf(entry.faces, entry.faces.length);
//            if(this.flip) {
//                for(int l = 0; l < this.faces.length; ++l) {
//                    this.faces[l].method_1925();
//                }
//            }
//
//            this.copyTo(verts, poly, false);
//        }
//    }
//
//    public ModelRendererTurbo_copy164 setTextureOffset(int x, int y) {
//        this.textureOffsetX = x;
//        this.textureOffsetY = y;
//        return this;
//    }
//
//    public void setPosition(float x, float y, float z) {
//        this.rotationPointX = x;
//        this.rotationPointY = y;
//        this.rotationPointZ = z;
//    }
//
//    public void doMirror(boolean x, boolean y, boolean z) {
//        for(int i = 0; i < this.faces.length; ++i) {
//            PositionTextureVertex[] verts = this.faces[i].quadPoint;
//
//            for(int j = 0; j < verts.length; ++j) {
//                verts[j].pointVector.x *= (double)(x ? -1 : 1);
//                verts[j].pointVector.y *= (double)(y ? -1 : 1);
//                verts[j].pointVector.z *= (double)(z ? -1 : 1);
//            }
//
//            if(x ^ y ^ z) {
//                this.faces[i].method_1925();
//            }
//        }
//
//    }
//
//    public void setMirrored(boolean isMirrored) {
//        this.i = isMirrored;
//    }
//
//    public void setFlipped(boolean isFlipped) {
//        this.flip = isFlipped;
//    }
//
//    public void clear() {
//        this.vertices = new PositionTextureVertex[0];
//        this.faces = new TexturedPolygon[0];
//        this.transformGroup.clear();
//        this.transformGroup.put("0", new TransformGroupBone(new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D));
//        this.currentGroup = (TransformGroup)this.transformGroup.get("0");
//    }
//
//    public void copyTo(PositionTextureVertex[] verts, TexturedPolygon[] poly) {
//        this.copyTo(verts, poly, true);
//    }
//
//    public void copyTo(PositionTextureVertex[] verts, TexturedPolygon[] poly, boolean copyGroup) {
//        this.vertices = (PositionTextureVertex[])Arrays.copyOf(this.vertices, this.vertices.length + verts.length);
//        this.faces = (TexturedPolygon[])Arrays.copyOf(this.faces, this.faces.length + poly.length);
//
//        int idx;
//        for(idx = 0; idx < verts.length; ++idx) {
//            this.vertices[this.vertices.length - verts.length + idx] = verts[idx];
//            if(copyGroup && verts[idx] instanceof PositionTransformVertex) {
//                ((PositionTransformVertex)verts[idx]).addGroup(this.currentGroup);
//            }
//        }
//
//        for(idx = 0; idx < poly.length; ++idx) {
//            this.faces[this.faces.length - poly.length + idx] = poly[idx];
//            if(copyGroup) {
//                this.currentTextureGroup.addPoly(poly[idx]);
//            }
//        }
//
//    }
//
//    public void copyTo(PositionTextureVertex[] verts, TexturedQuad[] quad) {
//        TexturedPolygon[] poly = new TexturedPolygon[quad.length];
//
//        for(int idx = 0; idx < quad.length; ++idx) {
//            poly[idx] = new TexturedPolygon(quad[idx].quadPoint);
//        }
//
//        this.copyTo(verts, poly);
//    }
//
//    public void setGroup(String groupName) {
//        this.setGroup(groupName, new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D);
//    }
//
//    public void setGroup(String groupName, Bone bone, double weight) {
//        if(!this.transformGroup.containsKey(groupName)) {
//            this.transformGroup.put(groupName, new TransformGroupBone(bone, weight));
//        }
//
//        this.currentGroup = (TransformGroup)this.transformGroup.get(groupName);
//    }
//
//    public TransformGroup getGroup() {
//        return this.currentGroup;
//    }
//
//    public TransformGroup getGroup(String groupName) {
//        return !this.transformGroup.containsKey(groupName) ? null : (TransformGroup)this.transformGroup.get(groupName);
//    }
//
//    public void setTextureGroup(String groupName) {
//        if(!this.textureGroup.containsKey(groupName)) {
//            this.textureGroup.put(groupName, new TextureGroup());
//        }
//
//        this.currentTextureGroup = (TextureGroup)this.textureGroup.get(groupName);
//    }
//
//    public TextureGroup getTextureGroup() {
//        return this.currentTextureGroup;
//    }
//
//    public TextureGroup getTextureGroup(String groupName) {
//        return !this.textureGroup.containsKey(groupName) ? null : (TextureGroup)this.textureGroup.get(groupName);
//    }
//
//    public void setGroupTexture(String s) {
//        this.currentTextureGroup.texture = s;
//    }
//
//    public void setDefaultTexture(String s) {
//        this.defaultTexture = s;
//    }
//
//    public void render(float worldScale) {
//        if(!this.field_1402_i) {
//            if(this.j) {
//                if(!this.compiled || this.forcedRecompile) {
//                    this.compileDisplayList(worldScale);
//                }
//
//                int i;
//                if(this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
//                    if(this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
//                        this.callDisplayList();
//                        if(this.m != null) {
//                            for(i = 0; i < this.m.size(); ++i) {
//                                ((ModelRenderer)this.m.get(i)).render(worldScale);
//                            }
//                        }
//                    } else {
//                        GL11.glTranslatef(this.rotationPointX * worldScale, this.rotationPointY * worldScale, this.rotationPointZ * worldScale);
//                        this.callDisplayList();
//                        if(this.m != null) {
//                            for(i = 0; i < this.m.size(); ++i) {
//                                ((ModelRenderer)this.m.get(i)).render(worldScale);
//                            }
//                        }
//
//                        GL11.glTranslatef(-this.rotationPointX * worldScale, -this.rotationPointY * worldScale, -this.rotationPointZ * worldScale);
//                    }
//                } else {
//                    GL11.glPushMatrix();
//                    GL11.glTranslatef(this.rotationPointX * worldScale, this.rotationPointY * worldScale, this.rotationPointZ * worldScale);
//                    if(this.rotateAngleZ != 0.0F) {
//                        GL11.glRotatef(this.rotateAngleZ * 57.29578F, 0.0F, 0.0F, 1.0F);
//                    }
//
//                    if(this.rotateAngleY != 0.0F) {
//                        GL11.glRotatef(this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
//                    }
//
//                    if(this.rotateAngleX != 0.0F) {
//                        GL11.glRotatef(this.rotateAngleX * 57.29578F, 1.0F, 0.0F, 0.0F);
//                    }
//
//                    this.callDisplayList();
//                    if(this.m != null) {
//                        for(i = 0; i < this.m.size(); ++i) {
//                            ((ModelRenderer)this.m.get(i)).render(worldScale);
//                        }
//                    }
//
//                    GL11.glPopMatrix();
//                }
//
//            }
//        }
//    }
//
//    public void renderWithRotation(float f) {
//        if(!this.field_1402_i) {
//            if(this.j) {
//                if(!this.compiled) {
//                    this.compileDisplayList(f);
//                }
//
//                GL11.glPushMatrix();
//                GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
//                if(this.rotateAngleY != 0.0F) {
//                    GL11.glRotatef(this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
//                }
//
//                if(this.rotateAngleX != 0.0F) {
//                    GL11.glRotatef(this.rotateAngleX * 57.29578F, 1.0F, 0.0F, 0.0F);
//                }
//
//                if(this.rotateAngleZ != 0.0F) {
//                    GL11.glRotatef(this.rotateAngleZ * 57.29578F, 0.0F, 0.0F, 1.0F);
//                }
//
//                this.callDisplayList();
//                GL11.glPopMatrix();
//            }
//        }
//    }
//
//    public void postRender(float f) {
//        if(!this.field_1402_i) {
//            if(this.j) {
//                if(!this.compiled || this.forcedRecompile) {
//                    this.compileDisplayList(f);
//                }
//
//                if(this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
//                    if(this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
//                        GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
//                    }
//                } else {
//                    GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
//                    if(this.rotateAngleZ != 0.0F) {
//                        GL11.glRotatef(this.rotateAngleZ * 57.29578F, 0.0F, 0.0F, 1.0F);
//                    }
//
//                    if(this.rotateAngleY != 0.0F) {
//                        GL11.glRotatef(this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
//                    }
//
//                    if(this.rotateAngleX != 0.0F) {
//                        GL11.glRotatef(this.rotateAngleX * 57.29578F, 1.0F, 0.0F, 0.0F);
//                    }
//                }
//
//            }
//        }
//    }
//
//    private void callDisplayList() {
//        if(this.useLegacyCompiler) {
//            GL11.glCallList(this.displayList);
//        } else {
//            TextureManager renderEngine = EntityRenderDispatcher.INSTANCE.textureManager;
//            Collection textures = this.textureGroup.values();
//            Iterator itr = textures.iterator();
//
//            for(int i = 0; itr.hasNext(); ++i) {
//                TextureGroup curTexGroup = (TextureGroup)itr.next();
//                curTexGroup.loadTexture();
//                GL11.glCallList(this.displayListArray[i]);
//                if(!this.defaultTexture.equals("")) {
//                    renderEngine.bindTexture(renderEngine.getTextureId(this.defaultTexture));
//                }
//            }
//        }
//
//    }
//
//    private void compileDisplayList(float worldScale) {
//        if(this.useLegacyCompiler) {
//            this.compileLegacyDisplayList(worldScale);
//        } else {
//            Collection textures = this.textureGroup.values();
//            Iterator itr = textures.iterator();
//            this.displayListArray = new int[this.textureGroup.size()];
//
//            for(int i = 0; itr.hasNext(); ++i) {
//                this.displayListArray[i] = class_214.method_741(1);
//                GL11.glNewList(this.displayListArray[i], GL11.GL_COMPILE);
//                Tessellator tessellator = Tessellator.INSTANCE;
//                TextureGroup usedGroup = (TextureGroup)itr.next();
//
//                for(int j = 0; j < usedGroup.poly.size(); ++j) {
//                    ((TexturedPolygon)usedGroup.poly.get(j)).draw(tessellator, worldScale); //???
//                }
//
//                GL11.glEndList();
//            }
//        }
//
//        this.compiled = true;
//    }
//
//    private void compileLegacyDisplayList(float worldScale) {
//        this.displayList = class_214.method_741(1);
//        GL11.glNewList(this.displayList, GL11.GL_COMPILE);
//        Tessellator tessellator = Tessellator.INSTANCE;
//
//        for(int i = 0; i < this.faces.length; ++i) {
//            this.faces[i].draw(tessellator, worldScale);
//        }
//
//        GL11.glEndList();
//    }
//
//    private PositionTextureVertex[] vertices;
//    private TexturedPolygon[] faces;
//    private int textureOffsetX;
//    private int textureOffsetY;
//    private boolean compiled;
//    private int displayList;
//    private int[] displayListArray;
//    private Map transformGroup;
//    private Map textureGroup;
//    private TransformGroup currentGroup;
//    private TextureGroup currentTextureGroup;
//    public boolean i;
//    public boolean flip;
//    public boolean j;
//    public boolean field_1402_i;
//    public boolean forcedRecompile;
//    public boolean useLegacyCompiler;
//    public List l;
//    public List m;
//    public final String boxName;
//    private String defaultTexture;
//    public static final int MR_FRONT = 0;
//    public static final int MR_BACK = 1;
//    public static final int MR_LEFT = 2;
//    public static final int MR_RIGHT = 3;
//    public static final int MR_TOP = 4;
//    public static final int MR_BOTTOM = 5;
//    private static final float pi = (float)Math.PI;
//}
//
