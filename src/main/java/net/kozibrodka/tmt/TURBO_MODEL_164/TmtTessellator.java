package net.kozibrodka.tmt.TURBO_MODEL_164;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_214;
import net.minecraft.client.render.Tessellator;

import net.modificationstation.stationapi.mixin.arsenic.client.TessellatorAccessor;
import org.lwjgl.opengl.ARBBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Environment(EnvType.CLIENT)
public class TmtTessellator  { //TODO doesnt extent
	private static int nativeBufferSize = 2097152;
	private static int trivertsInBuffer = nativeBufferSize / 48 * 6;
	public static boolean renderingWorldRenderer = false;
	public boolean defaultTexture = false;
	private int rawBufferSize = 0;
	public int textureID = 0;
	private static boolean field_78396_b = false;
	private static boolean field_78397_c = false;
	private static ByteBuffer field_78394_d = class_214.method_744(nativeBufferSize * 4); //TODO?
	private static IntBuffer field_78395_e = field_78394_d.asIntBuffer();
	private static FloatBuffer field_78392_f = field_78394_d.asFloatBuffer();
	private static ShortBuffer field_78393_g = field_78394_d.asShortBuffer();
	private int[] field_78405_h;
	private int field_78406_i = 0;
	private double field_78403_j;
	private double field_78404_k;
	private double textureW;
	private int field_78401_l;
	private int field_78402_m;
	private boolean field_78399_n = false;
	private boolean field_78400_o = false;
	private boolean field_78414_p = false;
	private boolean field_78413_q = false;
	private int field_78412_r = 0;
	private int field_78411_s = 0;
	private boolean field_78410_t = false;
	public int field_78409_u;
	public double field_78408_v;
	public double field_78407_w;
	public double field_78417_x;
	private int field_78416_y;
	public static TmtTessellator field_78398_a = new TmtTessellator(2097152);
	public boolean field_78415_z = false;
	private static boolean field_78389_A = false;
	private static IntBuffer field_78390_B;
	private int field_78391_C = 0;
	private static int field_78387_D = 10;
	private int field_78388_E;

	private TmtTessellator(int par1) {
		//TODO:??
//		this.field_78388_E = par1;
//		this.field_78394_d = class_214.method_744(par1 * 4);
//		this.field_78395_e = this.field_78394_d.asIntBuffer();
//		this.field_78392_f = this.field_78394_d.asFloatBuffer();
//		this.field_78393_g = this.field_78394_d.asShortBuffer();
//		this.field_78405_h = new int[par1];
//		this.useVBO = field_78397_c && GLContext.getCapabilities().GL_ARB_vertex_buffer_object;
//		if(this.useVBO) {
//			this.vertexBuffers = GLAllocation.createDirectIntBuffer(this.vboCount);
//			ARBVertexBufferObject.glGenBuffersARB(this.vertexBuffers);
//		}

	}

//	public TmtTessellator() {
//	}

	public int func_78381_a() {
		if(!this.field_78415_z) {
			throw new IllegalStateException("Not tesselating!");
		} else {
			this.field_78415_z = false;
			int offs = 0;

			int var11;
			while(offs < this.field_78406_i) {
				boolean var1 = false;
				if(this.field_78409_u == 7 && field_78396_b) {
					var11 = Math.min(this.field_78406_i - offs, trivertsInBuffer);
				} else {
					var11 = Math.min(this.field_78406_i - offs, nativeBufferSize >> 5);
				}

				field_78395_e.clear();
				field_78395_e.put(this.field_78405_h, offs * 10, var11 * 10);
				field_78394_d.position(0);
				field_78394_d.limit(var11 * 40);
				offs += var11;
				if(field_78389_A) {
					this.field_78391_C = (this.field_78391_C + 1) % field_78387_D;
					ARBBufferObject.glBindBufferARB('\u8892', field_78390_B.get(this.field_78391_C));
					ARBBufferObject.glBufferDataARB('\u8892', field_78394_d, '\u88e0');
				}

				if(this.field_78400_o) {
					if(field_78389_A) {
						GL11.glTexCoordPointer(4, GL11.GL_FLOAT, 40, 12L);
					} else {
						field_78392_f.position(3);
						GL11.glTexCoordPointer(4, 40, field_78392_f);
					}

					GL11.glEnableClientState('\u8078');
				}

				if(this.field_78414_p) {
					OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit); //TODO
					if(field_78389_A) {
						GL11.glTexCoordPointer(2, GL11.GL_SHORT, 40, 36L);
					} else {
						field_78393_g.position(18);
						GL11.glTexCoordPointer(2, 40, field_78393_g);
					}

					GL11.glEnableClientState('\u8078');
					OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit); //TODO
				}

				if(this.field_78399_n) {
					if(field_78389_A) {
						GL11.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 40, 28L);
					} else {
						field_78394_d.position(28);
						GL11.glColorPointer(4, true, 40, field_78394_d);
					}

					GL11.glEnableClientState('\u8076');
				}

				if(this.field_78413_q) {
					if(field_78389_A) {
						GL11.glNormalPointer(GL11.GL_UNSIGNED_BYTE, 40, 32L);
					} else {
						field_78394_d.position(32);
						GL11.glNormalPointer(40, field_78394_d);
					}

					GL11.glEnableClientState('\u8075');
				}

				if(field_78389_A) {
					GL11.glVertexPointer(3, GL11.GL_FLOAT, 40, 0L);
				} else {
					field_78392_f.position(0);
					GL11.glVertexPointer(3, 40, field_78392_f);
				}

				GL11.glEnableClientState('\u8074');
				if(this.field_78409_u == 7 && field_78396_b) {
					GL11.glDrawArrays(GL11.GL_TRIANGLES, GL11.GL_POINTS, var11);
				} else {
					GL11.glDrawArrays(this.field_78409_u, GL11.GL_POINTS, var11);
				}

				GL11.glDisableClientState('\u8074');
				if(this.field_78400_o) {
					GL11.glDisableClientState('\u8078');
				}

				if(this.field_78414_p) {
					OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit); //TODO
					GL11.glDisableClientState('\u8078');
					OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit); //TODO
				}

				if(this.field_78399_n) {
					GL11.glDisableClientState('\u8076');
				}

				if(this.field_78413_q) {
					GL11.glDisableClientState('\u8075');
				}
			}

			if(this.rawBufferSize > 131072 && this.field_78412_r < this.rawBufferSize << 3) {
				this.rawBufferSize = 0;
				this.field_78405_h = null;
			}

			var11 = this.field_78412_r * 4;
			this.func_78379_d();
			return var11;
		}
	}

	private void func_78379_d() {
		this.field_78406_i = 0;
		field_78394_d.clear();
		this.field_78412_r = 0;
		this.field_78411_s = 0;
	}

	public void func_78382_b() {
		this.func_78371_b(7);
	}

	public void func_78371_b(int par1) {
		if(this.field_78415_z) {
			throw new IllegalStateException("Already tesselating!");
		} else {
			this.field_78415_z = true;
			this.func_78379_d();
			this.field_78409_u = par1;
			this.field_78413_q = false;
			this.field_78399_n = false;
			this.field_78400_o = false;
			this.field_78414_p = false;
			this.field_78410_t = false;
		}
	}

	public void func_78385_a(double par1, double par3) {
		this.field_78400_o = true;
		this.field_78403_j = par1;
		this.field_78404_k = par3;
		this.textureW = 1.0D;
	}

	public void setTextureUVW(double par1, double par3, double par4) {
		this.field_78400_o = true;
		this.field_78403_j = par1;
		this.field_78404_k = par3;
		this.textureW = par4;
	}

	public void func_78380_c(int par1) {
		this.field_78414_p = true;
		this.field_78401_l = par1;
	}

	public void func_78386_a(float par1, float par2, float par3) {
		this.func_78376_a((int)(par1 * 255.0F), (int)(par2 * 255.0F), (int)(par3 * 255.0F));
	}

	public void func_78369_a(float par1, float par2, float par3, float par4) {
		this.func_78370_a((int)(par1 * 255.0F), (int)(par2 * 255.0F), (int)(par3 * 255.0F), (int)(par4 * 255.0F));
	}

	public void func_78376_a(int par1, int par2, int par3) {
		this.func_78370_a(par1, par2, par3, 255);
	}

	public void func_78370_a(int par1, int par2, int par3, int par4) {
		if(!this.field_78410_t) {
			if(par1 > 255) {
				par1 = 255;
			}

			if(par2 > 255) {
				par2 = 255;
			}

			if(par3 > 255) {
				par3 = 255;
			}

			if(par4 > 255) {
				par4 = 255;
			}

			if(par1 < 0) {
				par1 = 0;
			}

			if(par2 < 0) {
				par2 = 0;
			}

			if(par3 < 0) {
				par3 = 0;
			}

			if(par4 < 0) {
				par4 = 0;
			}

			this.field_78399_n = true;
			if(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
				this.field_78402_m = par4 << 24 | par3 << 16 | par2 << 8 | par1;
			} else {
				this.field_78402_m = par1 << 24 | par2 << 16 | par3 << 8 | par4;
			}
		}

	}

	public void func_78374_a(double par1, double par3, double par5, double par7, double par9) {
		this.func_78385_a(par7, par9);
		this.func_78377_a(par1, par3, par5);
	}

	public void addVertexWithUVW(double par1, double par3, double par5, double par7, double par9, double par10) {
		this.setTextureUVW(par7, par9, par10);
		this.func_78377_a(par1, par3, par5);
	}

	public void func_78377_a(double par1, double par3, double par5) {
		if(this.field_78412_r >= this.rawBufferSize - 40) {
			if(this.rawBufferSize == 0) {
				this.rawBufferSize = 65536;
				this.field_78405_h = new int[this.rawBufferSize];
			} else {
				this.rawBufferSize *= 2;
				this.field_78405_h = Arrays.copyOf(this.field_78405_h, this.rawBufferSize);
			}
		}

		++this.field_78411_s;
		if(this.field_78409_u == 7 && field_78396_b && this.field_78411_s % 4 == 0) {
			for(int var7 = 0; var7 < 2; ++var7) {
				int var8 = 10 * (3 - var7);
				if(this.field_78400_o) {
					this.field_78405_h[this.field_78412_r + 3] = this.field_78405_h[this.field_78412_r - var8 + 3];
					this.field_78405_h[this.field_78412_r + 4] = this.field_78405_h[this.field_78412_r - var8 + 4];
					this.field_78405_h[this.field_78412_r + 5] = this.field_78405_h[this.field_78412_r - var8 + 5];
					this.field_78405_h[this.field_78412_r + 6] = this.field_78405_h[this.field_78412_r - var8 + 6];
				}

				if(this.field_78414_p) {
					this.field_78405_h[this.field_78412_r + 9] = this.field_78405_h[this.field_78412_r - var8 + 9];
				}

				if(this.field_78399_n) {
					this.field_78405_h[this.field_78412_r + 7] = this.field_78405_h[this.field_78412_r - var8 + 7];
				}

				this.field_78405_h[this.field_78412_r + 0] = this.field_78405_h[this.field_78412_r - var8 + 0];
				this.field_78405_h[this.field_78412_r + 1] = this.field_78405_h[this.field_78412_r - var8 + 1];
				this.field_78405_h[this.field_78412_r + 2] = this.field_78405_h[this.field_78412_r - var8 + 2];
				++this.field_78406_i;
				this.field_78412_r += 10;
			}
		}

		if(this.field_78400_o) {
			this.field_78405_h[this.field_78412_r + 3] = Float.floatToRawIntBits((float)this.field_78403_j);
			this.field_78405_h[this.field_78412_r + 4] = Float.floatToRawIntBits((float)this.field_78404_k);
			this.field_78405_h[this.field_78412_r + 5] = Float.floatToRawIntBits(0.0F);
			this.field_78405_h[this.field_78412_r + 6] = Float.floatToRawIntBits((float)this.textureW);
		}

		if(this.field_78414_p) {
			this.field_78405_h[this.field_78412_r + 9] = this.field_78401_l;
		}

		if(this.field_78399_n) {
			this.field_78405_h[this.field_78412_r + 7] = this.field_78402_m;
		}

		if(this.field_78413_q) {
			this.field_78405_h[this.field_78412_r + 8] = this.field_78416_y;
		}

		this.field_78405_h[this.field_78412_r + 0] = Float.floatToRawIntBits((float)(par1 + this.field_78408_v));
		this.field_78405_h[this.field_78412_r + 1] = Float.floatToRawIntBits((float)(par3 + this.field_78407_w));
		this.field_78405_h[this.field_78412_r + 2] = Float.floatToRawIntBits((float)(par5 + this.field_78417_x));
		this.field_78412_r += 10;
		++this.field_78406_i;
	}

	public void func_78378_d(int par1) {
		int j = par1 >> 16 & 255;
		int k = par1 >> 8 & 255;
		int l = par1 & 255;
		this.func_78376_a(j, k, l);
	}

	public void func_78384_a(int par1, int par2) {
		int k = par1 >> 16 & 255;
		int l = par1 >> 8 & 255;
		int i1 = par1 & 255;
		this.func_78370_a(k, l, i1, par2);
	}

	public void func_78383_c() {
		this.field_78410_t = true;
	}

	public void func_78375_b(float par1, float par2, float par3) {
		this.field_78413_q = true;
		byte b0 = (byte)((int)(par1 * 127.0F));
		byte b1 = (byte)((int)(par2 * 127.0F));
		byte b2 = (byte)((int)(par3 * 127.0F));
		this.field_78416_y = b0 & 255 | (b1 & 255) << 8 | (b2 & 255) << 16;
	}

	public void func_78373_b(double par1, double par3, double par5) {
		this.field_78408_v = par1;
		this.field_78407_w = par3;
		this.field_78417_x = par5;
	}

	public void func_78372_c(float par1, float par2, float par3) {
		this.field_78408_v += (double)par1;
		this.field_78407_w += (double)par2;
		this.field_78417_x += (double)par3;
	}

	static {
		field_78398_a.defaultTexture = true;
		field_78389_A = field_78397_c && GLContext.getCapabilities().GL_ARB_vertex_buffer_object;
		if(field_78389_A) {
			field_78390_B = class_214.method_745(field_78387_D); //TODO?
			ARBBufferObject.glGenBuffersARB(field_78390_B);
		}

	}
}
