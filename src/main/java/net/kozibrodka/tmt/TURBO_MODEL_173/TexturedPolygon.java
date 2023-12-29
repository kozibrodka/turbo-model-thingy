package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TexturedQuad;
import net.minecraft.util.maths.Vec3f;

public class TexturedPolygon extends TexturedQuad
{

    public TexturedPolygon(QuadPoint apositiontexturevertex[])
    {
        super(apositiontexturevertex);
        invertNormal = false;
        normals = new float[0];
    }

    public void setInvertNormal(boolean flag)
    {
        invertNormal = flag;
    }

    public void setNormals(float f, float f1, float f2)
    {
        normals = (new float[] {
                f, f1, f2
        });
    }

    public void draw(Tessellator tessellator, float f)
    {
        if(field_2519 == 3)
        {
            tessellator.start(4);
        } else
        if(field_2519 == 4)
        {
            tessellator.start();
        } else
        {
            tessellator.start(9);
        }
        if(normals.length == 3)
        {
            if(invertNormal)
            {
                tessellator.setNormal(-normals[0], -normals[1], -normals[2]);
            } else
            {
                tessellator.setNormal(normals[0], normals[1], normals[2]);
            }
        } else
        if(quadPoint.length >= 3)
        {
            Vec3f vec3d = quadPoint[1].pointVector.method_1307(quadPoint[0].pointVector);
            Vec3f vec3d1 = quadPoint[1].pointVector.method_1307(quadPoint[2].pointVector);
            Vec3f vec3d2 = vec3d1.method_1309(vec3d).method_1296();
            if(invertNormal)
            {
                tessellator.setNormal(-(float)vec3d2.x, -(float)vec3d2.y, -(float)vec3d2.z);
            } else
            {
                tessellator.setNormal((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z);
            }
        } else
        {
            return;
        }
        for(int i = 0; i < field_2519; i++)
        {
            QuadPoint positiontexturevertex = quadPoint[i];
            if(positiontexturevertex instanceof PositionTransformVertex)
            {
                ((PositionTransformVertex)positiontexturevertex).setTransformation();
            }
            tessellator.vertex((float)positiontexturevertex.pointVector.x * f, (float)positiontexturevertex.pointVector.y * f, (float)positiontexturevertex.pointVector.z * f, positiontexturevertex.field_1147, positiontexturevertex.field_1148);
        }

        tessellator.draw();
    }

    private boolean invertNormal;
    private float normals[];
}
