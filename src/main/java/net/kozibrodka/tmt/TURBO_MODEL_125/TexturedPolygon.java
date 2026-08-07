package net.kozibrodka.tmt.TURBO_MODEL_125;

import net.minecraft.client.model.Quad;
import net.minecraft.client.model.Vertex;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.math.Vec3d;
import java.util.ArrayList;

public class TexturedPolygon extends Quad {

    private boolean invertNormal = false;
    private float[] normals = new float[0];
    private ArrayList iNormals = new ArrayList();

    public TexturedPolygon(Vertex[] apositionTexturevertex) {
        super(apositionTexturevertex);
    }

    public void setInvertNormal(boolean isSet) {
        this.invertNormal = isSet;
    }

    public void setNormals(float x, float y, float z) {
        this.normals = new float[]{x, y, z};
    }

    public void setNormals(ArrayList vec) {
        this.iNormals = vec;
    }

    public void draw(Tessellator tessellator, float f) {
        if(this.verticesCount == 3) {
            tessellator.start(4);
        } else if(this.verticesCount == 4) {
            tessellator.startQuads();
        } else {
            tessellator.start(9);
        }

        Vec3d vec3d = this.vertices[1].pos.relativize(this.vertices[0].pos);
        Vec3d vec3d1 = this.vertices[1].pos.relativize(this.vertices[2].pos);
        Vec3d vec3d2 = vec3d1.crossProduct(vec3d).normalize();
        if(this.iNormals.size() == 0) {
            if(this.normals.length == 3) {
                if(this.invertNormal) {
                    tessellator.normal(-this.normals[0], -this.normals[1], -this.normals[2]);
                } else {
                    tessellator.normal(this.normals[0], this.normals[1], this.normals[2]);
                }
            } else {
                if(this.vertices.length < 3) {
                    return;
                }

                if(this.invertNormal) {
                    tessellator.normal(-((float)vec3d2.x), -((float)vec3d2.y), -((float)vec3d2.z));
                } else {
                    tessellator.normal((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z);
                }
            }
        }

        for(int i = 0; i < this.verticesCount; ++i) {
            Vertex positionTexturevertex = this.vertices[i];
            if(positionTexturevertex instanceof PositionTransformVertex) {
                ((PositionTransformVertex)positionTexturevertex).setTransformation();
            }

            if(i < this.iNormals.size()) {
                if(this.invertNormal) {
                    tessellator.normal(-((float)((Vec3d)this.iNormals.get(i)).x), -((float)((Vec3d)this.iNormals.get(i)).y), -((float)((Vec3d)this.iNormals.get(i)).z));
                } else {
                    tessellator.normal((float)((Vec3d)this.iNormals.get(i)).x, (float)((Vec3d)this.iNormals.get(i)).y, (float)((Vec3d)this.iNormals.get(i)).z);
                }
            } else if(this.normals.length == 3) {
                if(this.invertNormal) {
                    tessellator.normal(-this.normals[0], -this.normals[1], -this.normals[2]);
                } else {
                    tessellator.normal(this.normals[0], this.normals[1], this.normals[2]);
                }
            } else {
                if(this.vertices.length < 3) {
                    return;
                }

                if(this.invertNormal) {
                    tessellator.normal(-((float)vec3d2.x), -((float)vec3d2.y), -((float)vec3d2.z));
                } else {
                    tessellator.normal((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z);
                }
            }

            tessellator.vertex((double)((float)positionTexturevertex.pos.x * f), (double)((float)positionTexturevertex.pos.y * f), (double)((float)positionTexturevertex.pos.z * f), (double)positionTexturevertex.u, (double)positionTexturevertex.v);
        }

        tessellator.draw();
    }

}
