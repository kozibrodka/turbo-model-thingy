package net.kozibrodka.tmt.TURBO_MODEL_2000_DEV;

import java.util.ArrayList;

public class TexturedPolygon {

    public PositionTextureVertex[] vertexPositions;
    public int verticesCount;
    private boolean invertNormal;
    private float[] normals;
    private ArrayList iNormals;

    public TexturedPolygon(PositionTextureVertex[] var1)
    {
        this.invertNormal = false;
        this.vertexPositions = var1;
        this.verticesCount = var1.length;
        this.iNormals = new ArrayList();
        this.normals = new float[0];
    }

    public TexturedPolygon(PositionTextureVertex[] var1, int var2, int var3, int var4, int var5, float var6, float var7)
    {
        this(var1);
        float var8 = 0.0F / var6;
        float var9 = 0.0F / var7;
        var1[0] = var1[0].setTexturePosition((float)var4 / var6 - var8, (float)var3 / var7 + var9);
        var1[1] = var1[1].setTexturePosition((float)var2 / var6 + var8, (float)var3 / var7 + var9);
        var1[2] = var1[2].setTexturePosition((float)var2 / var6 + var8, (float)var5 / var7 - var9);
        var1[3] = var1[3].setTexturePosition((float)var4 / var6 - var8, (float)var5 / var7 - var9);
    }

    public void setInvertNormal(boolean isSet) {
        this.invertNormal = isSet;
    }

    public void setNormals(float x, float y, float z) {
        this.normals = new float[]{x, y, z};
    }

    public void flipFace()
    {
        PositionTextureVertex[] var1 = new PositionTextureVertex[this.vertexPositions.length];

        for (int var2 = 0; var2 < this.vertexPositions.length; ++var2)
        {
            var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
        }

        this.vertexPositions = var1;
    }

    public void setNormals(ArrayList vec) {
        this.iNormals = vec;
    }

    /// DRAW NEW 1.6.4 - TMT tesselator //TODO kluczowe miejsce
    public void draw(TmtTessellator tessellator, float var2)
    {
        if (this.verticesCount == 3)
        {
            tessellator.startDrawing(4);
        }
        else if (this.verticesCount == 4)
        {
            tessellator.startDrawingQuads();
        }
        else
        {
            tessellator.startDrawing(9);
        }

        if (this.iNormals.size() == 0)
        {
            if (this.normals.length == 3)
            {
                if (this.invertNormal)
                {
                    tessellator.setNormal(-this.normals[0], -this.normals[1], -this.normals[2]);
                }
                else
                {
                    tessellator.setNormal(this.normals[0], this.normals[1], this.normals[2]);
                }
            }
            else
            {
                if (this.vertexPositions.length < 3)
                {
                    return;
                }

                Vec3d var3 = this.vertexPositions[1].pos.relativize(this.vertexPositions[0].pos);
                Vec3d var4 = this.vertexPositions[1].pos.relativize(this.vertexPositions[2].pos);
                Vec3d var5 = var4.crossProduct(var3).normalize();

                if (this.invertNormal)
                {
                    tessellator.setNormal(-((float)var5.x), -((float)var5.y), -((float)var5.z));
                }
                else
                {
                    tessellator.setNormal((float)var5.x, (float)var5.y, (float)var5.z);
                }
            }
        }

        for (int var6 = 0; var6 < this.verticesCount; ++var6)
        {
            PositionTextureVertex var7 = this.vertexPositions[var6];

            if (var7 instanceof PositionTransformVertex)
            {
                ((PositionTransformVertex)var7).setTransformation();
            }

            if (var6 < this.iNormals.size())
            {
                if (this.invertNormal)
                {
                    tessellator.setNormal(-((float)((Vec3d)this.iNormals.get(var6)).x), -((float)((Vec3d)this.iNormals.get(var6)).y), -((float)((Vec3d)this.iNormals.get(var6)).z));
                }
                else
                {
                    tessellator.setNormal((float)((Vec3d)this.iNormals.get(var6)).x, (float)((Vec3d)this.iNormals.get(var6)).y, (float)((Vec3d)this.iNormals.get(var6)).z);
                }
            }

            tessellator.addVertexWithUVW((double)((float)var7.pos.x * var2), (double)((float)var7.pos.y * var2), (double)((float)var7.pos.z * var2), (double)var7.u, (double)var7.v, (double)var7.texturePositionW);
        }

        tessellator.draw();
    }


}
