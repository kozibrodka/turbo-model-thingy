package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.client.model.Quad;
import net.minecraft.client.model.Vertex;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.math.Vec3d;

public class TexturedPolygon extends Quad
{

    public TexturedPolygon(Vertex apositiontexturevertex[])
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
        if(verticesCount == 3)
        {
            tessellator.start(4);
        } else
        if(verticesCount == 4)
        {
            tessellator.startQuads();
        } else
        {
            tessellator.start(9);
        }
        if(normals.length == 3)
        {
            if(invertNormal)
            {
                tessellator.normal(-normals[0], -normals[1], -normals[2]);
            } else
            {
                tessellator.normal(normals[0], normals[1], normals[2]);
            }
        } else
        if(vertices.length >= 3)
        {
            Vec3d vec3d = vertices[1].pos.relativize(vertices[0].pos);
            Vec3d vec3d1 = vertices[1].pos.relativize(vertices[2].pos);
            Vec3d vec3d2 = vec3d1.crossProduct(vec3d).normalize();
            if(invertNormal)
            {
                tessellator.normal(-(float)vec3d2.x, -(float)vec3d2.y, -(float)vec3d2.z);
            } else
            {
                tessellator.normal((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z);
            }
        } else
        {
            return;
        }
        for(int i = 0; i < verticesCount; i++)
        {
            Vertex positiontexturevertex = vertices[i];
            if(positiontexturevertex instanceof PositionTransformVertex)
            {
                ((PositionTransformVertex)positiontexturevertex).setTransformation();
            }
            tessellator.vertex((float)positiontexturevertex.pos.x * f, (float)positiontexturevertex.pos.y * f, (float)positiontexturevertex.pos.z * f, positiontexturevertex.u, positiontexturevertex.v);
        }

        tessellator.draw();
    }

    private boolean invertNormal;
    private float normals[];
}
