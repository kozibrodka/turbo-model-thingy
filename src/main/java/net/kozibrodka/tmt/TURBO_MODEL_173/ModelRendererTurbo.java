package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.class_214;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.QuadPoint;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TexturedQuad;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ModelRendererTurbo extends Cuboid
{

    public ModelRendererTurbo(int i, int j)
    {
        this(i, j, 64, 32);
    }

    public ModelRendererTurbo(int i, int j, int k, int l)
    {
        super(i, j);
        flip = false;
        compiled = false;
        displayList = 0;
        mirror = false;
        showModel = true;
        field_1402_i = false;
        textureOffsetX = i;
        textureOffsetY = j;
        textureWidth = k;
        textureHeight = l;
        vertices = new QuadPoint[0];
        faces = new TexturedPolygon[0];
        forcedRecompile = false;
        transformGroup = new HashMap();
        transformGroup.put("0", new TransformGroupBone(new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D));
        currentGroup = (TransformGroupBone)transformGroup.get("0");
    }

    public void addPolygon(QuadPoint apositiontexturevertex[])
    {
        faces = (TexturedPolygon[])Arrays.copyOf(faces, faces.length + 1);
        faces[faces.length - 1] = new TexturedPolygon(apositiontexturevertex);
    }

    public void addPolygon(QuadPoint apositiontexturevertex[], int ai[][])
    {
        try
        {
            for(int i = 0; i < apositiontexturevertex.length; i++)
            {
                apositiontexturevertex[i] = apositiontexturevertex[i].method_983((float)ai[i][0] / (float)textureWidth, (float)ai[i][1] / (float)textureHeight);
            }

        }
        finally
        {
            addPolygon(apositiontexturevertex);
        }
    }

    public void addPolygon(QuadPoint apositiontexturevertex[], int i, int j, int k, int l)
    {
        faces = (TexturedPolygon[])Arrays.copyOf(faces, faces.length + 1);
        faces[faces.length - 1] = addPolygonReturn(apositiontexturevertex, i, j, k, l);
    }

    private TexturedPolygon addPolygonReturn(QuadPoint apositiontexturevertex[], int i, int j, int k, int l)
    {
        if(apositiontexturevertex.length < 3)
        {
            return null;
        }
        float f = 1.0F / ((float)textureWidth * 10F);
        float f1 = 1.0F / ((float)textureHeight * 10F);
        if(apositiontexturevertex.length < 4)
        {
            float f2 = -1F;
            float f3 = -1F;
            float f4 = 0.0F;
            float f5 = 0.0F;
            for(int i1 = 0; i1 < apositiontexturevertex.length; i1++)
            {
                float f7 = apositiontexturevertex[i1].field_1147;
                float f9 = apositiontexturevertex[i1].field_1148;
                f4 = Math.max(f4, f7);
                f2 = f2 >= -1F ? Math.min(f2, f7) : f7;
                f5 = Math.max(f5, f9);
                f3 = f3 >= -1F ? Math.min(f3, f9) : f9;
            }

            float f6 = (float)i / (float)textureWidth + f;
            float f8 = (float)j / (float)textureHeight + f1;
            float f10 = (float)(k - i) / (float)textureWidth - f * 2.0F;
            float f11 = (float)(l - j) / (float)textureHeight - f1 * 2.0F;
            float f12 = f4 - f2;
            float f13 = f5 - f3;
            for(int j1 = 0; j1 < apositiontexturevertex.length; j1++)
            {
                float f14 = apositiontexturevertex[j1].field_1147;
                float f15 = apositiontexturevertex[j1].field_1148;
                f14 = (f14 - f2) / f12;
                f15 = (f15 - f3) / f13;
                apositiontexturevertex[j1] = apositiontexturevertex[j1].method_983(f6 + f14 * f10, f8 + f15 * f11);
            }

        } else
        {
            apositiontexturevertex[0] = apositiontexturevertex[0].method_983((float)k / (float)textureWidth - f, (float)j / (float)textureHeight + f1);
            apositiontexturevertex[1] = apositiontexturevertex[1].method_983((float)i / (float)textureWidth + f, (float)j / (float)textureHeight + f1);
            apositiontexturevertex[2] = apositiontexturevertex[2].method_983((float)i / (float)textureWidth + f, (float)l / (float)textureHeight - f1);
            apositiontexturevertex[3] = apositiontexturevertex[3].method_983((float)k / (float)textureWidth - f, (float)l / (float)textureHeight - f1);
        }
        return new TexturedPolygon(apositiontexturevertex);
    }

    public void addRectShape(float af[], float af1[], float af2[], float af3[], float af4[], float af5[], float af6[],
                             float af7[], int i, int j, int k)
    {
        QuadPoint apositiontexturevertex[] = new QuadPoint[8];
        TexturedPolygon atexturedpolygon[] = new TexturedPolygon[6];
        QuadPoint positiontexturevertex = new QuadPoint(af[0], af[1], af[2], 0.0F, 0.0F);
        QuadPoint positiontexturevertex1 = new QuadPoint(af1[0], af1[1], af1[2], 0.0F, 8F);
        QuadPoint positiontexturevertex2 = new QuadPoint(af2[0], af2[1], af2[2], 8F, 8F);
        QuadPoint positiontexturevertex3 = new QuadPoint(af3[0], af3[1], af3[2], 8F, 0.0F);
        QuadPoint positiontexturevertex4 = new QuadPoint(af4[0], af4[1], af4[2], 0.0F, 0.0F);
        QuadPoint positiontexturevertex5 = new QuadPoint(af5[0], af5[1], af5[2], 0.0F, 8F);
        QuadPoint positiontexturevertex6 = new QuadPoint(af6[0], af6[1], af6[2], 8F, 8F);
        QuadPoint positiontexturevertex7 = new QuadPoint(af7[0], af7[1], af7[2], 8F, 0.0F);
        apositiontexturevertex[0] = positiontexturevertex;
        apositiontexturevertex[1] = positiontexturevertex1;
        apositiontexturevertex[2] = positiontexturevertex2;
        apositiontexturevertex[3] = positiontexturevertex3;
        apositiontexturevertex[4] = positiontexturevertex4;
        apositiontexturevertex[5] = positiontexturevertex5;
        apositiontexturevertex[6] = positiontexturevertex6;
        apositiontexturevertex[7] = positiontexturevertex7;
        atexturedpolygon[0] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6
        }, textureOffsetX + k + i, textureOffsetY + k, textureOffsetX + k + i + k, textureOffsetY + k + j);
        atexturedpolygon[1] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex, positiontexturevertex4, positiontexturevertex7, positiontexturevertex3
        }, textureOffsetX + 0, textureOffsetY + k, textureOffsetX + k, textureOffsetY + k + j);
        atexturedpolygon[2] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1
        }, textureOffsetX + k, textureOffsetY + 0, textureOffsetX + k + i, textureOffsetY + k);
        atexturedpolygon[3] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6
        }, textureOffsetX + k + i, textureOffsetY + 0, textureOffsetX + k + i + i, textureOffsetY + k);
        atexturedpolygon[4] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2
        }, textureOffsetX + k, textureOffsetY + k, textureOffsetX + k + i, textureOffsetY + k + j);
        atexturedpolygon[5] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex4, positiontexturevertex5, positiontexturevertex6, positiontexturevertex7
        }, textureOffsetX + k + i + k, textureOffsetY + k, textureOffsetX + k + i + k + i, textureOffsetY + k + j);
        if(mirror ^ flip)
        {
            for(int l = 0; l < atexturedpolygon.length; l++)
            {
                atexturedpolygon[l].method_1925();
            }

        }
        copyTo(apositiontexturevertex, atexturedpolygon);
    }

    public void addBox(float f, float f1, float f2, int i, int j, int k)
    {
        addBox(f, f1, f2, i, j, k, 0.0F);
    }

    public void addBox(float f, float f1, float f2, int i, int j, int k, float f3)
    {
        addBox(f, f1, f2, i, j, k, f3, 1.0F);
    }

    public void addBox(float f, float f1, float f2, int i, int j, int k, float f3,
                       float f4)
    {
        float f5 = (float)i * f4;
        float f6 = (float)j * f4;
        float f7 = (float)k * f4;
        float f8 = f + f5;
        float f9 = f1 + f6;
        float f10 = f2 + f7;
        float f11 = (f3 + f5) - (float)i;
        float f12 = (f3 + f6) - (float)j;
        float f13 = (f3 + f7) - (float)k;
        f -= f11;
        f1 -= f12;
        f2 -= f13;
        f8 += f3;
        f9 += f3;
        f10 += f3;
        if(mirror)
        {
            float f14 = f8;
            f8 = f;
            f = f14;
        }
        float af[] = {
                f, f1, f2
        };
        float af1[] = {
                f8, f1, f2
        };
        float af2[] = {
                f8, f9, f2
        };
        float af3[] = {
                f, f9, f2
        };
        float af4[] = {
                f, f1, f10
        };
        float af5[] = {
                f8, f1, f10
        };
        float af6[] = {
                f8, f9, f10
        };
        float af7[] = {
                f, f9, f10
        };
        addRectShape(af, af1, af2, af3, af4, af5, af6, af7, i, j, k);
    }

    public void addTrapezoid(float f, float f1, float f2, int i, int j, int k, float f3,
                             float f4, int l)
    {
        float f5 = f + (float)i;
        float f6 = f1 + (float)j;
        float f7 = f2 + (float)k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f5 += f3;
        f6 += f3;
        f7 += f3;
        int i1 = mirror ? -1 : 1;
        if(mirror)
        {
            float f8 = f5;
            f5 = f;
            f = f8;
        }
        float af[] = {
                f, f1, f2
        };
        float af1[] = {
                f5, f1, f2
        };
        float af2[] = {
                f5, f6, f2
        };
        float af3[] = {
                f, f6, f2
        };
        float af4[] = {
                f, f1, f7
        };
        float af5[] = {
                f5, f1, f7
        };
        float af6[] = {
                f5, f6, f7
        };
        float af7[] = {
                f, f6, f7
        };
        switch(l)
        {
            case 3: // '\003'
                af[1] -= f4;
                af[2] -= f4;
                af3[1] += f4;
                af3[2] -= f4;
                af4[1] -= f4;
                af4[2] += f4;
                af7[1] += f4;
                af7[2] += f4;
                break;

            case 2: // '\002'
                af1[1] -= f4;
                af1[2] -= f4;
                af2[1] += f4;
                af2[2] -= f4;
                af5[1] -= f4;
                af5[2] += f4;
                af6[1] += f4;
                af6[2] += f4;
                break;

            case 0: // '\0'
                af[0] -= (float)i1 * f4;
                af[1] -= f4;
                af1[0] += (float)i1 * f4;
                af1[1] -= f4;
                af2[0] += (float)i1 * f4;
                af2[1] += f4;
                af3[0] -= (float)i1 * f4;
                af3[1] += f4;
                break;

            case 1: // '\001'
                af4[0] -= (float)i1 * f4;
                af4[1] -= f4;
                af5[0] += (float)i1 * f4;
                af5[1] -= f4;
                af6[0] += (float)i1 * f4;
                af6[1] += f4;
                af7[0] -= (float)i1 * f4;
                af7[1] += f4;
                break;

            case 4: // '\004'
                af[0] -= (float)i1 * f4;
                af[2] -= f4;
                af1[0] += (float)i1 * f4;
                af1[2] -= f4;
                af4[0] -= (float)i1 * f4;
                af4[2] += f4;
                af5[0] += (float)i1 * f4;
                af5[2] += f4;
                break;

            case 5: // '\005'
                af2[0] += (float)i1 * f4;
                af2[2] -= f4;
                af3[0] -= (float)i1 * f4;
                af3[2] -= f4;
                af6[0] += (float)i1 * f4;
                af6[2] += f4;
                af7[0] -= (float)i1 * f4;
                af7[2] += f4;
                break;
        }
        addRectShape(af, af1, af2, af3, af4, af5, af6, af7, i, j, k);
    }

    public void addPixel(float f, float f1, float f2, float af[], int i, int j)
    {
        if(vertices == null)
        {
            vertices = new QuadPoint[8];
        } else
        {
            vertices = (QuadPoint[])Arrays.copyOf(vertices, vertices.length + 8);
        }
        if(faces == null)
        {
            faces = new TexturedPolygon[6];
        } else
        {
            faces = (TexturedPolygon[])Arrays.copyOf(faces, faces.length + 6);
        }
        int k = vertices.length - 8;
        int l = faces.length - 6;
        float f3 = f + af[0];
        float f4 = f1 + af[1];
        float f5 = f2 + af[2];
        float af1[] = {
                f, f1, f2
        };
        float af2[] = {
                f3, f1, f2
        };
        float af3[] = {
                f3, f4, f2
        };
        float af4[] = {
                f, f4, f2
        };
        float af5[] = {
                f, f1, f5
        };
        float af6[] = {
                f3, f1, f5
        };
        float af7[] = {
                f3, f4, f5
        };
        float af8[] = {
                f, f4, f5
        };
        QuadPoint positiontexturevertex = new QuadPoint(af1[0], af1[1], af1[2], 0.0F, 0.0F);
        QuadPoint positiontexturevertex1 = new QuadPoint(af2[0], af2[1], af2[2], 0.0F, 8F);
        QuadPoint positiontexturevertex2 = new QuadPoint(af3[0], af3[1], af3[2], 8F, 8F);
        QuadPoint positiontexturevertex3 = new QuadPoint(af4[0], af4[1], af4[2], 8F, 0.0F);
        QuadPoint positiontexturevertex4 = new QuadPoint(af5[0], af5[1], af5[2], 0.0F, 0.0F);
        QuadPoint positiontexturevertex5 = new QuadPoint(af6[0], af6[1], af6[2], 0.0F, 8F);
        QuadPoint positiontexturevertex6 = new QuadPoint(af7[0], af7[1], af7[2], 8F, 8F);
        QuadPoint positiontexturevertex7 = new QuadPoint(af8[0], af8[1], af8[2], 8F, 0.0F);
        vertices[k + 0] = positiontexturevertex;
        vertices[k + 1] = positiontexturevertex1;
        vertices[k + 2] = positiontexturevertex2;
        vertices[k + 3] = positiontexturevertex3;
        vertices[k + 4] = positiontexturevertex4;
        vertices[k + 5] = positiontexturevertex5;
        vertices[k + 6] = positiontexturevertex6;
        vertices[k + 7] = positiontexturevertex7;
        faces[l + 0] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6
        }, i, j, i + 1, j + 1);
        faces[l + 1] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex, positiontexturevertex4, positiontexturevertex7, positiontexturevertex3
        }, i, j, i + 1, j + 1);
        faces[l + 2] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1
        }, i, j, i + 1, j + 1);
        faces[l + 3] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6
        }, i, j, i + 1, j + 1);
        faces[l + 4] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2
        }, i, j, i + 1, j + 1);
        faces[l + 5] = addPolygonReturn(new QuadPoint[] {
                positiontexturevertex4, positiontexturevertex5, positiontexturevertex6, positiontexturevertex7
        }, i, j, i + 1, j + 1);
    }

    public void addSprite(float f, float f1, float f2, int i, int j, float f3)
    {
        addSprite(f, f1, f2, i, j, 1, false, false, false, false, false, f3);
    }

    public void addSprite(float f, float f1, float f2, int i, int j, boolean flag, boolean flag1,
                          boolean flag2, boolean flag3, boolean flag4, float f3)
    {
        addSprite(f, f1, f2, i, j, 1, flag, flag1, flag2, flag3, flag4, f3);
    }

    public void addSprite(float f, float f1, float f2, int i, int j, int k, boolean flag,
                          boolean flag1, boolean flag2, boolean flag3, boolean flag4, float f3)
    {
        addSprite(f, f1, f2, i, j, k, 1.0F, flag, flag1, flag2, flag3, flag4, f3);
    }

    public void addSprite(float f, float f1, float f2, int i, int j, int k, float f3,
                          boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, float f4)
    {
        String as[] = new String[j];
        char ac[] = new char[i];
        Arrays.fill(ac, '1');
        Arrays.fill(as, new String(ac));
        addSprite(f, f1, f2, as, k, f3, flag, flag1, flag2, flag3, flag4, f4);
    }

    public void addSprite(float f, float f1, float f2, String as[], int i, float f3, boolean flag,
                          boolean flag1, boolean flag2, boolean flag3, boolean flag4, float f4)
    {
        int j = as[0].length();
        int k = as.length;
        float f5 = f - f4;
        float f6 = f1 - f4;
        float f7 = f2 - f4;
        byte byte0 = 0;
        byte byte1 = 0;
        byte byte2 = 0;
        float f8 = 1.0F + f4 / ((float)j * f3);
        float f9 = 1.0F + f4 / ((float)k * f3);
        if(!flag)
        {
            if(!flag1)
            {
                if(!flag2)
                {
                    byte0 = 0;
                    byte1 = 1;
                    byte2 = 2;
                } else
                {
                    byte0 = 1;
                    byte1 = 0;
                    byte2 = 2;
                }
            } else
            if(!flag2)
            {
                byte0 = 2;
                byte1 = 1;
                byte2 = 0;
            } else
            {
                byte0 = 2;
                byte1 = 0;
                byte2 = 1;
            }
        } else
        if(!flag1)
        {
            if(!flag2)
            {
                byte0 = 0;
                byte1 = 2;
                byte2 = 1;
            } else
            {
                byte0 = 1;
                byte1 = 2;
                byte2 = 0;
            }
        } else
        if(!flag2)
        {
            byte0 = 2;
            byte1 = 0;
            byte2 = 1;
        } else
        {
            byte0 = 2;
            byte1 = 1;
            byte2 = 0;
        }
        int l = textureOffsetX + (flag3 ? j * 1 - 1 : 0);
        int i1 = textureOffsetY + (flag4 ? k * 1 - 1 : 0);
        byte byte3 = ((byte)(flag3 ? -1 : 1));
        byte byte4 = ((byte)(flag4 ? -1 : 1));
        float f10 = getPixelSize(f8, f9, (float)i * f3 + f4 * 2.0F, 0, 1, byte0, 1, 1);
        float f11 = getPixelSize(f8, f9, (float)i * f3 + f4 * 2.0F, 0, 1, byte1, 1, 1);
        float f12 = getPixelSize(f8, f9, (float)i * f3 + f4 * 2.0F, 0, 1, byte2, 1, 1);
        for(int j1 = 0; j1 < j; j1++)
        {
            for(int k1 = 0; k1 < k; k1++)
            {
                if(as[k1].charAt(j1) == '1')
                {
                    addPixel(f5 + getPixelSize(f8, f9, 0.0F, byte0, byte1, 0, j1, k1), f6 + getPixelSize(f8, f9, 0.0F, byte0, byte1, 1, j1, k1), f7 + getPixelSize(f8, f9, 0.0F, byte0, byte1, 2, j1, k1), new float[] {
                            f10, f11, f12
                    }, l + byte3 * j1, i1 + byte4 * k1);
                }
            }

        }

    }

    private float getPixelSize(float f, float f1, float f2, int i, int j, int k, int l,
                               int i1)
    {
        return i != k ? j != k ? f2 : f1 * (float)i1 : f * (float)l;
    }

    public void addSphere(float f, float f1, float f2, float f3, int i, int j, int k,
                          int l)
    {
        if(i < 3)
        {
            i = 3;
        }
        j++;
        QuadPoint apositiontexturevertex[] = new QuadPoint[i * (j - 1) + 2];
        TexturedPolygon atexturedpolygon[] = new TexturedPolygon[i * j];
        apositiontexturevertex[0] = new QuadPoint(f, f1 - f3, f2, 0.0F, 0.0F);
        apositiontexturevertex[apositiontexturevertex.length - 1] = new QuadPoint(f, f1 + f3, f2, 0.0F, 0.0F);
        float f4 = 1.0F / ((float)textureWidth * 10F);
        float f5 = 1.0F / ((float)textureHeight * 10F);
        float f6 = (float)k / (float)textureWidth - 2.0F * f4;
        float f7 = (float)l / (float)textureHeight - 2.0F * f5;
        float f8 = f6 / (float)i;
        float f9 = f7 / (float)j;
        float f10 = (float)textureOffsetX / (float)textureWidth;
        float f11 = (float)textureOffsetY / (float)textureHeight;
        int i1 = 0;
        for(int j1 = 1; j1 < j; j1++)
        {
            for(int l1 = 0; l1 < i; l1++)
            {
                float f12 = MathHelper.cos(-1.570796F + (3.141593F / (float)j) * (float)j1);
                float f13 = MathHelper.sin(-1.570796F + (3.141593F / (float)j) * (float)j1);
                float f14 = MathHelper.sin((3.141593F / (float)i) * (float)l1 * 2.0F + 3.141593F) * f12;
                float f15 = -MathHelper.cos((3.141593F / (float)i) * (float)l1 * 2.0F + 3.141593F) * f12;
                int j2 = 1 + l1 + i * (j1 - 1);
                apositiontexturevertex[j2] = new QuadPoint(f + f14 * f3, f1 + f13 * f3, f2 + f15 * f3, 0.0F, 0.0F);
                if(l1 <= 0)
                {
                    continue;
                }
                QuadPoint apositiontexturevertex3[];
                if(j1 == 1)
                {
                    apositiontexturevertex3 = new QuadPoint[4];
                    apositiontexturevertex3[0] = apositiontexturevertex[j2].method_983(f10 + f8 * (float)l1, f11 + f9 * (float)j1);
                    apositiontexturevertex3[1] = apositiontexturevertex[j2 - 1].method_983(f10 + f8 * (float)(l1 - 1), f11 + f9 * (float)j1);
                    apositiontexturevertex3[2] = apositiontexturevertex[0].method_983(f10 + f8 * (float)(l1 - 1), f11);
                    apositiontexturevertex3[3] = apositiontexturevertex[0].method_983(f10 + f8 + f8 * (float)l1, f11);
                } else
                {
                    apositiontexturevertex3 = new QuadPoint[4];
                    apositiontexturevertex3[0] = apositiontexturevertex[j2].method_983(f10 + f8 * (float)l1, f11 + f9 * (float)j1);
                    apositiontexturevertex3[1] = apositiontexturevertex[j2 - 1].method_983(f10 + f8 * (float)(l1 - 1), f11 + f9 * (float)j1);
                    apositiontexturevertex3[2] = apositiontexturevertex[j2 - 1 - i].method_983(f10 + f8 * (float)(l1 - 1), f11 + f9 * (float)(j1 - 1));
                    apositiontexturevertex3[3] = apositiontexturevertex[j2 - i].method_983(f10 + f8 * (float)l1, f11 + f9 * (float)(j1 - 1));
                }
                atexturedpolygon[i1] = new TexturedPolygon(apositiontexturevertex3);
                i1++;
            }

            QuadPoint apositiontexturevertex1[];
            if(j1 == 1)
            {
                apositiontexturevertex1 = new QuadPoint[4];
                apositiontexturevertex1[0] = apositiontexturevertex[1].method_983(f10 + f8 * (float)i, f11 + f9 * (float)j1);
                apositiontexturevertex1[1] = apositiontexturevertex[i].method_983(f10 + f8 * (float)(i - 1), f11 + f9 * (float)j1);
                apositiontexturevertex1[2] = apositiontexturevertex[0].method_983(f10 + f8 * (float)(i - 1), f11);
                apositiontexturevertex1[3] = apositiontexturevertex[0].method_983(f10 + f8 * (float)i, f11);
            } else
            {
                apositiontexturevertex1 = new QuadPoint[4];
                apositiontexturevertex1[0] = apositiontexturevertex[1 + i * (j1 - 1)].method_983(f10 + f6, f11 + f9 * (float)j1);
                apositiontexturevertex1[1] = apositiontexturevertex[i * (j1 - 1) + i].method_983((f10 + f6) - f8, f11 + f9 * (float)j1);
                apositiontexturevertex1[2] = apositiontexturevertex[i * (j1 - 1)].method_983((f10 + f6) - f8, f11 + f9 * (float)(j1 - 1));
                apositiontexturevertex1[3] = apositiontexturevertex[(1 + i * (j1 - 1)) - i].method_983(f10 + f6, f11 + f9 * (float)(j1 - 1));
            }
            atexturedpolygon[i1] = new TexturedPolygon(apositiontexturevertex1);
            i1++;
        }

        for(int k1 = 0; k1 < i; k1++)
        {
            QuadPoint apositiontexturevertex2[] = new QuadPoint[3];
            int i2 = apositiontexturevertex.length - (i + 1);
            apositiontexturevertex2[0] = apositiontexturevertex[apositiontexturevertex.length - 1].method_983(f10 + f8 * ((float)k1 + 0.5F), f11 + f7);
            apositiontexturevertex2[1] = apositiontexturevertex[i2 + k1].method_983(f10 + f8 * (float)k1, (f11 + f7) - f9);
            apositiontexturevertex2[2] = apositiontexturevertex[i2 + (k1 + 1) % i].method_983(f10 + f8 * (float)(k1 + 1), (f11 + f7) - f9);
            atexturedpolygon[i1] = new TexturedPolygon(apositiontexturevertex2);
            i1++;
        }

        copyTo(apositiontexturevertex, atexturedpolygon);
    }

    public void addCone(float f, float f1, float f2, float f3, float f4, int i)
    {
        addCone(f, f1, f2, f3, f4, i, 1.0F);
    }

    public void addCone(float f, float f1, float f2, float f3, float f4, int i, float f5)
    {
        addCone(f, f1, f2, f3, f4, i, f5, 4);
    }

    public void addCone(float f, float f1, float f2, float f3, float f4, int i, float f5,
                        int j)
    {
        addCone(f, f1, f2, f3, f4, i, f5, j, (int)Math.floor(f3 * 2.0F), (int)Math.floor(f3 * 2.0F));
    }

    public void addCone(float f, float f1, float f2, float f3, float f4, int i, float f5,
                        int j, int k, int l)
    {
        addCylinder(f, f1, f2, f3, f4, i, f5, 0.0F, j, k, l, 1);
    }

    public void addCylinder(float f, float f1, float f2, float f3, float f4, int i)
    {
        addCylinder(f, f1, f2, f3, f4, i, 1.0F, 1.0F);
    }

    public void addCylinder(float f, float f1, float f2, float f3, float f4, int i, float f5,
                            float f6)
    {
        addCylinder(f, f1, f2, f3, f4, i, f5, f6, 4);
    }

    public void addCylinder(float f, float f1, float f2, float f3, float f4, int i, float f5,
                            float f6, int j)
    {
        addCylinder(f, f1, f2, f3, f4, i, f5, f6, j, (int)Math.floor(f3 * 2.0F), (int)Math.floor(f3 * 2.0F), (int)Math.floor(f4));
    }

    public void addCylinder(float f, float f1, float f2, float f3, float f4, int i, float f5,
                            float f6, int j, int k, int l, int i1)
    {
        boolean flag = j == 4 || j == 5;
        boolean flag1 = j == 3 || j == 2;
        boolean flag2 = j == 0 || j == 1;
        boolean flag3 = j == 2 || j == 5 || j == 1;
        boolean flag4 = f5 == 0.0F;
        boolean flag5 = f6 == 0.0F;
        if(flag4 && flag5)
        {
            f5 = 1.0F;
            flag4 = false;
        }
        QuadPoint apositiontexturevertex[] = new QuadPoint[i * (!flag4 && !flag5 ? 2 : 1) + 2];
        TexturedPolygon atexturedpolygon[] = new TexturedPolygon[i * (!flag4 && !flag5 ? 3 : 2)];
        float f7 = flag1 ? f4 : 0.0F;
        float f8 = flag ? f4 : 0.0F;
        float f9 = flag2 ? f4 : 0.0F;
        float f10 = flag3 ? f + f7 : f;
        float f11 = flag3 ? f1 + f8 : f1;
        float f12 = flag3 ? f2 + f9 : f2;
        float f13 = flag3 ? f : f + f7;
        float f14 = flag3 ? f1 : f1 + f8;
        float f15 = flag3 ? f2 : f2 + f9;
        apositiontexturevertex[0] = new QuadPoint(f10, f11, f12, 0.0F, 0.0F);
        apositiontexturevertex[apositiontexturevertex.length - 1] = new QuadPoint(f13, f14, f15, 0.0F, 0.0F);
        float f16 = f10;
        float f17 = f11;
        float f18 = f12;
        float f19 = flag4 ? f6 : f5;
        for(int j1 = 0; j1 < (!flag4 && !flag5 ? 2 : 1); j1++)
        {
            for(int k1 = 0; k1 < i; k1++)
            {
                float f22 = (float)(mirror ^ flag3 ? -1 : 1) * MathHelper.sin((3.141593F / (float)i) * (float)k1 * 2.0F + 3.141593F) * f3 * f19;
                float f24 = -MathHelper.cos((3.141593F / (float)i) * (float)k1 * 2.0F + 3.141593F) * f3 * f19;
                float f26 = f16 + (flag1 ? 0.0F : f22);
                float f28 = f17 + (flag ? 0.0F : f24);
                float f30 = f18 + (flag1 ? f22 : flag ? f24 : 0.0F);
                apositiontexturevertex[1 + k1 + j1 * i] = new QuadPoint(f26, f28, f30, 0.0F, 0.0F);
            }

            f16 = f13;
            f17 = f14;
            f18 = f15;
            f19 = f6;
        }

        float f20 = 1.0F / (float)textureWidth;
        float f21 = 1.0F / (float)textureHeight;
        float f23 = f20 / 20F;
        float f25 = f21 / 20F;
        float f27 = (float)k * f20;
        float f29 = (float)l * f21;
        float f31 = (f27 * 2.0F - f23 * 2.0F) / (float)i;
        float f32 = (float)i1 * f21 - f23 * 2.0F;
        float f33 = (float)textureOffsetX * f20;
        float f34 = (float)textureOffsetY * f21;
        for(int l1 = 0; l1 < i; l1++)
        {
            int i2 = (l1 + 1) % i;
            float f35 = MathHelper.sin((3.141593F / (float)i) * (float)l1 * 2.0F + (flag ? 3.141593F : 0.0F)) * (0.5F * f27 - 2.0F * f23);
            float f36 = MathHelper.cos((3.141593F / (float)i) * (float)l1 * 2.0F + (flag ? 3.141593F : 0.0F)) * (0.5F * f29 - 2.0F * f25);
            float f37 = MathHelper.sin((3.141593F / (float)i) * (float)i2 * 2.0F + (flag ? 3.141593F : 0.0F)) * (0.5F * f27 - 2.0F * f23);
            float f38 = MathHelper.cos((3.141593F / (float)i) * (float)i2 * 2.0F + (flag ? 3.141593F : 0.0F)) * (0.5F * f29 - 2.0F * f25);
            QuadPoint apositiontexturevertex1[] = new QuadPoint[3];
            apositiontexturevertex1[0] = apositiontexturevertex[0].method_983(f33 + 0.5F * f27, f34 + 0.5F * f29);
            apositiontexturevertex1[1] = apositiontexturevertex[1 + i2].method_983(f33 + 0.5F * f27 + f37, f34 + 0.5F * f29 + f38);
            apositiontexturevertex1[2] = apositiontexturevertex[1 + l1].method_983(f33 + 0.5F * f27 + f35, f34 + 0.5F * f29 + f36);
            atexturedpolygon[l1] = new TexturedPolygon(apositiontexturevertex1);
            if(mirror ^ flip)
            {
                atexturedpolygon[l1].method_1925();
            }
            if(!flag4 && !flag5)
            {
                apositiontexturevertex1 = new QuadPoint[4];
                apositiontexturevertex1[0] = apositiontexturevertex[1 + l1].method_983(f33 + f23 + f31 * (float)l1, f34 + f25 + f29);
                apositiontexturevertex1[1] = apositiontexturevertex[1 + i2].method_983(f33 + f23 + f31 * (float)(l1 + 1), f34 + f25 + f29);
                apositiontexturevertex1[2] = apositiontexturevertex[1 + i + i2].method_983(f33 + f23 + f31 * (float)(l1 + 1), f34 + f25 + f29 + f32);
                apositiontexturevertex1[3] = apositiontexturevertex[1 + i + l1].method_983(f33 + f23 + f31 * (float)l1, f34 + f25 + f29 + f32);
                atexturedpolygon[l1 + i] = new TexturedPolygon(apositiontexturevertex1);
                if(mirror ^ flip)
                {
                    atexturedpolygon[l1 + i].method_1925();
                }
            }
            apositiontexturevertex1 = new QuadPoint[3];
            apositiontexturevertex1[0] = apositiontexturevertex[apositiontexturevertex.length - 1].method_983(f33 + 1.5F * f27, f34 + 0.5F * f29);
            apositiontexturevertex1[1] = apositiontexturevertex[apositiontexturevertex.length - 2 - l1].method_983(f33 + 1.5F * f27 + f37, f34 + 0.5F * f29 + f38);
            apositiontexturevertex1[2] = apositiontexturevertex[(apositiontexturevertex.length - (1 + i)) + (i - l1) % i].method_983(f33 + 1.5F * f27 + f35, f34 + 0.5F * f29 + f36);
            atexturedpolygon[(atexturedpolygon.length - i) + l1] = new TexturedPolygon(apositiontexturevertex1);
            if(mirror ^ flip)
            {
                atexturedpolygon[(atexturedpolygon.length - i) + l1].method_1925();
            }
        }

        copyTo(apositiontexturevertex, atexturedpolygon);
    }

    public void addObj(String s)
    {
        ModelPoolEntry modelpoolentry = ModelPool.addFile(s, ModelPool.OBJ, transformGroup);
        if(modelpoolentry == null)
        {
            return;
        }
        QuadPoint apositiontexturevertex[] = (QuadPoint[])Arrays.copyOf(modelpoolentry.vertices, modelpoolentry.vertices.length);
        TexturedPolygon atexturedpolygon[] = (TexturedPolygon[])Arrays.copyOf(modelpoolentry.faces, modelpoolentry.faces.length);
        if(flip)
        {
            for(int i = 0; i < faces.length; i++)
            {
                faces[i].method_1925();
            }

        }
        copyTo(apositiontexturevertex, atexturedpolygon);
    }

    public void addModel(String s, Class class1)
    {
        ModelPoolEntry modelpoolentry = ModelPool.addFile(s, class1, transformGroup);
        if(modelpoolentry == null)
        {
            return;
        }
        QuadPoint apositiontexturevertex[] = (QuadPoint[])Arrays.copyOf(modelpoolentry.vertices, modelpoolentry.vertices.length);
        TexturedPolygon atexturedpolygon[] = (TexturedPolygon[])Arrays.copyOf(modelpoolentry.faces, modelpoolentry.faces.length);
        if(flip)
        {
            for(int i = 0; i < faces.length; i++)
            {
                faces[i].method_1925();
            }

        }
        copyTo(apositiontexturevertex, atexturedpolygon);
    }

    public void setTextureOffset(int i, int j)
    {
        textureOffsetX = i;
        textureOffsetY = j;
    }

    public void setPosition(float f, float f1, float f2)
    {
        rotationPointX = f;
        rotationPointY = f1;
        rotationPointZ = f2;
    }

    public void doMirror(boolean flag, boolean flag1, boolean flag2)
    {
        for(int i = 0; i < faces.length; i++)
        {
            QuadPoint apositiontexturevertex[] = faces[i].quadPoint;
            for(int j = 0; j < apositiontexturevertex.length; j++)
            {
                apositiontexturevertex[j].pointVector.x *= flag ? -1 : 1;
                apositiontexturevertex[j].pointVector.y *= flag1 ? -1 : 1;
                apositiontexturevertex[j].pointVector.z *= flag2 ? -1 : 1;
            }

            if(flag ^ flag1 ^ flag2)
            {
                faces[i].method_1925();
            }
        }

    }

    public void setMirrored(boolean flag)
    {
        mirror = flag;
    }

    public void setFlipped(boolean flag)
    {
        flip = flag;
    }

    public void clear()
    {
        vertices = new QuadPoint[0];
        faces = new TexturedPolygon[0];
        transformGroup.clear();
        transformGroup.put("0", new TransformGroupBone(new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D));
        currentGroup = (TransformGroupBone)transformGroup.get("0");
    }

    public void copyTo(QuadPoint apositiontexturevertex[], TexturedPolygon atexturedpolygon[])
    {
        vertices = (QuadPoint[])Arrays.copyOf(vertices, vertices.length + apositiontexturevertex.length);
        faces = (TexturedPolygon[])Arrays.copyOf(faces, faces.length + atexturedpolygon.length);
        for(int i = 0; i < apositiontexturevertex.length; i++)
        {
            vertices[(vertices.length - apositiontexturevertex.length) + i] = apositiontexturevertex[i];
            if(apositiontexturevertex[i] instanceof PositionTransformVertex)
            {
                ((PositionTransformVertex)apositiontexturevertex[i]).addGroup(currentGroup);
            }
        }

        for(int j = 0; j < atexturedpolygon.length; j++)
        {
            faces[(faces.length - atexturedpolygon.length) + j] = atexturedpolygon[j];
        }

    }

    public void copyTo(QuadPoint apositiontexturevertex[], TexturedQuad atexturedquad[])
    {
        TexturedPolygon atexturedpolygon[] = new TexturedPolygon[atexturedquad.length];
        for(int i = 0; i < atexturedquad.length; i++)
        {
            atexturedpolygon[i] = new TexturedPolygon(atexturedquad[i].quadPoint);
        }

        copyTo(apositiontexturevertex, atexturedpolygon);
    }

    public void setGroup(String s)
    {
        setGroup(s, new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D);
    }

    public void setGroup(String s, Bone bone, double d1)
    {
        if(!transformGroup.containsKey(s))
        {
            transformGroup.put(s, new TransformGroupBone(bone, d1));
        }
        currentGroup = (TransformGroupBone)transformGroup.get(s);
    }

    public TransformGroupBone getGroup()
    {
        return currentGroup;
    }

    public TransformGroupBone getGroup(String s)
    {
        if(!transformGroup.containsKey(s))
        {
            return null;
        } else
        {
            return (TransformGroupBone)transformGroup.get(s);
        }
    }

    public void render(float f)
    {
        if(field_1402_i)
        {
            return;
        }
        if(!showModel)
        {
            return;
        }
        if(!compiled || forcedRecompile)
        {
            d(f);
        }
        if(pitch != 0.0F || yaw != 0.0F || roll != 0.0F)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);
            if(roll != 0.0F)
            {
                GL11.glRotatef(roll * 57.29578F, 0.0F, 0.0F, 1.0F);
            }
            if(yaw != 0.0F)
            {
                GL11.glRotatef(yaw * 57.29578F, 0.0F, 1.0F, 0.0F);
            }
            if(pitch != 0.0F)
            {
                GL11.glRotatef(pitch * 57.29578F, 1.0F, 0.0F, 0.0F);
            }
            GL11.glCallList(displayList);
            GL11.glPopMatrix();
        } else
        if(rotationPointX != 0.0F || rotationPointY != 0.0F || rotationPointZ != 0.0F)
        {
            GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);
            GL11.glCallList(displayList);
            GL11.glTranslatef(-rotationPointX * f, -rotationPointY * f, -rotationPointZ * f);
        } else
        {
            GL11.glCallList(displayList);
        }
    }

    public void postRender(float f)
    {
        if(field_1402_i)
        {
            return;
        }
        if(!showModel)
        {
            return;
        }
        if(!compiled || forcedRecompile)
        {
            d(f);
        }
        if(pitch != 0.0F || yaw != 0.0F || roll != 0.0F)
        {
            GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);
            if(roll != 0.0F)
            {
                GL11.glRotatef(roll * 57.29578F, 0.0F, 0.0F, 1.0F);
            }
            if(yaw != 0.0F)
            {
                GL11.glRotatef(yaw * 57.29578F, 0.0F, 1.0F, 0.0F);
            }
            if(pitch != 0.0F)
            {
                GL11.glRotatef(pitch * 57.29578F, 1.0F, 0.0F, 0.0F);
            }
        } else
        if(rotationPointX != 0.0F || rotationPointY != 0.0F || rotationPointZ != 0.0F)
        {
            GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);
        }
    }

    private void d(float f)
    {
        displayList = class_214.method_741(1);
        GL11.glNewList(displayList, 4864 /*GL_COMPILE*/);
        Tessellator tessellator = Tessellator.INSTANCE;
        for(int i = 0; i < faces.length; i++)
        {
            faces[i].draw(tessellator, f);
        }

        GL11.glEndList();
        compiled = true;
    }

    private QuadPoint vertices[];
    private TexturedPolygon faces[];
    private int textureOffsetX;
    private int textureOffsetY;
    private int textureWidth;
    private int textureHeight;
    private boolean compiled;
    private int displayList;
    private Map transformGroup;
    private TransformGroupBone currentGroup;
    public boolean mirror;
    public boolean flip;
    public boolean showModel;
    public boolean field_1402_i;
    public boolean forcedRecompile;
    public static final int MR_FRONT = 0;
    public static final int MR_BACK = 1;
    public static final int MR_LEFT = 2;
    public static final int MR_RIGHT = 3;
    public static final int MR_TOP = 4;
    public static final int MR_BOTTOM = 5;
    private static final float pi = 3.141593F;
}
