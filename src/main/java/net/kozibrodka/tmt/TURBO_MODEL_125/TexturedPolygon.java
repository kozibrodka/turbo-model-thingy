package net.kozibrodka.tmt.TURBO_MODEL_125;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TexturedQuad;
import net.minecraft.util.maths.Vec3f;

import java.util.ArrayList;

public class TexturedPolygon extends TexturedQuad {

    public TexturedPolygon(QuadPoint[] apositionTexturevertex) {
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
        if(this.field_2519 == 3) {
            tessellator.start(4);
        } else if(this.field_2519 == 4) {
            tessellator.start();
        } else {
            tessellator.start(9);
        }

        Vec3f vec3d = this.quadPoint[1].pointVector.method_1307(this.quadPoint[0].pointVector);
        Vec3f vec3d1 = this.quadPoint[1].pointVector.method_1307(this.quadPoint[2].pointVector);
        Vec3f vec3d2 = vec3d1.method_1309(vec3d).method_1296();
        if(this.iNormals.size() == 0) {
            if(this.normals.length == 3) {
                if(this.invertNormal) {
                    tessellator.setNormal(-this.normals[0], -this.normals[1], -this.normals[2]);
                } else {
                    tessellator.setNormal(this.normals[0], this.normals[1], this.normals[2]);
                }
            } else {
                if(this.quadPoint.length < 3) {
                    return;
                }

                if(this.invertNormal) {
                    tessellator.setNormal(-((float)vec3d2.x), -((float)vec3d2.y), -((float)vec3d2.z));
                } else {
                    tessellator.setNormal((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z);
                }
            }
        }

        for(int i = 0; i < this.field_2519; ++i) {
            QuadPoint positionTexturevertex = this.quadPoint[i];
            if(positionTexturevertex instanceof PositionTransformVertex) {
                ((PositionTransformVertex)positionTexturevertex).setTransformation();
            }

            if(i < this.iNormals.size()) {
                if(this.invertNormal) {
                    tessellator.setNormal(-((float)((Vec3f)this.iNormals.get(i)).x), -((float)((Vec3f)this.iNormals.get(i)).y), -((float)((Vec3f)this.iNormals.get(i)).z));
                } else {
                    tessellator.setNormal((float)((Vec3f)this.iNormals.get(i)).x, (float)((Vec3f)this.iNormals.get(i)).y, (float)((Vec3f)this.iNormals.get(i)).z);
                }
            } else if(this.normals.length == 3) {
                if(this.invertNormal) {
                    tessellator.setNormal(-this.normals[0], -this.normals[1], -this.normals[2]);
                } else {
                    tessellator.setNormal(this.normals[0], this.normals[1], this.normals[2]);
                }
            } else {
                if(this.quadPoint.length < 3) {
                    return;
                }

                if(this.invertNormal) {
                    tessellator.setNormal(-((float)vec3d2.x), -((float)vec3d2.y), -((float)vec3d2.z));
                } else {
                    tessellator.setNormal((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z);
                }
            }

            tessellator.vertex((double)((float)positionTexturevertex.pointVector.x * f), (double)((float)positionTexturevertex.pointVector.y * f), (double)((float)positionTexturevertex.pointVector.z * f), (double)positionTexturevertex.field_1147, (double)positionTexturevertex.field_1148);
        }

        tessellator.draw();
    }

    private boolean invertNormal = false;
    private float[] normals = new float[0];
    private ArrayList iNormals = new ArrayList();
}
