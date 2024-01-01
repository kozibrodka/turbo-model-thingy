package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.maths.Vec3f;

import java.util.ArrayList;


public class TexturedPolygon {
	public PositionTextureVertex[] vertexPositions;
	public int nVertices;
	private boolean invertNormal;
	private float[] normals;
	private ArrayList<Vec3f> iNormals;

	public TexturedPolygon(PositionTextureVertex[] apositionTexturevertex) {
		this.invertNormal = false;
		this.vertexPositions = apositionTexturevertex;
		this.nVertices = apositionTexturevertex.length;
		this.iNormals = new ArrayList();
		this.normals = new float[0];
	}

	public TexturedPolygon(PositionTextureVertex[] apositionTexturevertex, int par2, int par3, int par4, int par5, float par6, float par7) {
		this(apositionTexturevertex);
		float var8 = 0.0F / par6;
		float var9 = 0.0F / par7;
		apositionTexturevertex[0] = apositionTexturevertex[0].func_78240_a((float)par4 / par6 - var8, (float)par3 / par7 + var9);
		apositionTexturevertex[1] = apositionTexturevertex[1].func_78240_a((float)par2 / par6 + var8, (float)par3 / par7 + var9);
		apositionTexturevertex[2] = apositionTexturevertex[2].func_78240_a((float)par2 / par6 + var8, (float)par5 / par7 - var9);
		apositionTexturevertex[3] = apositionTexturevertex[3].func_78240_a((float)par4 / par6 - var8, (float)par5 / par7 - var9);
	}

	public void setInvertNormal(boolean isSet) {
		this.invertNormal = isSet;
	}

	public void setNormals(float x, float y, float z) {
		this.normals = new float[]{x, y, z};
	}

	public void flipFace() {
		PositionTextureVertex[] var1 = new PositionTextureVertex[this.vertexPositions.length];

		for(int var2 = 0; var2 < this.vertexPositions.length; ++var2) {
			var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
		}

		this.vertexPositions = var1;
	}

	public void setNormals(ArrayList<Vec3f> vec) {
		this.iNormals = vec;
	}

	public void draw(TmtTessellator tessellator, float f) {
		if(this.nVertices == 3) {
			tessellator.func_78371_b(4);
		} else if(this.nVertices == 4) {
			tessellator.func_78382_b();
		} else {
			tessellator.func_78371_b(9);
		}

		if(this.iNormals.size() == 0) {
			if(this.normals.length == 3) {
				if(this.invertNormal) {
					tessellator.func_78375_b(-this.normals[0], -this.normals[1], -this.normals[2]);
				} else {
					tessellator.func_78375_b(this.normals[0], this.normals[1], this.normals[2]);
				}
			} else {
				if(this.vertexPositions.length < 3) {
					return;
				}

				Vec3f i = this.vertexPositions[1].pointVector.method_1307(this.vertexPositions[0].pointVector);
				Vec3f positionTexturevertex = this.vertexPositions[1].pointVector.method_1307(this.vertexPositions[2].pointVector);
				Vec3f Vec32 = positionTexturevertex.method_1309(i).method_1296();
				if(this.invertNormal) {
					tessellator.func_78375_b(-((float)Vec32.x), -((float)Vec32.y), -((float)Vec32.z));
				} else {
					tessellator.func_78375_b((float)Vec32.x, (float)Vec32.y, (float)Vec32.z);
				}
			}
		}

		for(int var6 = 0; var6 < this.nVertices; ++var6) {
			PositionTextureVertex var7 = this.vertexPositions[var6];
			if(var7 instanceof PositionTransformVertex) {
				((PositionTransformVertex)var7).setTransformation();
			}

			if(var6 < this.iNormals.size()) {
				if(this.invertNormal) {
					tessellator.func_78375_b(-((float)((Vec3f)this.iNormals.get(var6)).x), -((float)((Vec3f)this.iNormals.get(var6)).y), -((float)((Vec3f)this.iNormals.get(var6)).z));
				} else {
					tessellator.func_78375_b((float)((Vec3f)this.iNormals.get(var6)).x, (float)((Vec3f)this.iNormals.get(var6)).y, (float)((Vec3f)this.iNormals.get(var6)).z);
				}
			}

			tessellator.addVertexWithUVW((double)((float)var7.pointVector.x * f), (double)((float)var7.pointVector.y * f), (double)((float)var7.pointVector.z * f), (double)var7.field_1147, (double)var7.field_1148, (double)var7.texturePositionW);
		}

		tessellator.func_78381_a();
	}
}
