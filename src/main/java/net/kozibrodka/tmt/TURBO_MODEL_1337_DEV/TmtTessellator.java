package net.kozibrodka.tmt.TURBO_MODEL_1337_DEV;


import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.GlAllocationUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.nio.*;
import java.util.Arrays;


public class TmtTessellator extends Tessellator
{
    private static int nativeBufferSize = 2097152;
    private static int trivertsInBuffer = nativeBufferSize / 48 * 6;
    public static boolean renderingWorldRenderer = false;
    public boolean defaultTexture = false;
    private int rawBufferSize = 0;
    public int textureID = 0;
    private static boolean convertQuadsToTriangles = false;
    private static boolean tryVBO = false;
    private static ByteBuffer byteBuffer = GlAllocationUtils.allocateByteBuffer(nativeBufferSize * 4);
    private static IntBuffer intBuffer = byteBuffer.asIntBuffer();
    private static FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
    private static ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
    private int[] rawBuffer;
    private int vertexCount = 0;
    private double textureU;
    private double textureV;
    private double textureW;
    private int brightness;
    private int color;
    private boolean hasColor = false;
    private boolean hasTexture = false;
    private boolean hasBrightness = false;
    private boolean hasNormals = false;
    private int rawBufferIndex = 0;
    private int addedVertices = 0;
    private boolean isColorDisabled = false;
    public int u;
    public double v;
    public double w;
    public double x;
    private int normal;
    public static TmtTessellator instance = new TmtTessellator(2097152);
    public boolean z = false;
    private static boolean useVBO = false;
    private static IntBuffer vertexBuffers;
    private int vboIndex = 0;
    private static int vboCount = 10;
    private int bufferSize;

	private TmtTessellator(int par1) {
        super(par1);
    }

//	public TmtTessellator() {
//
//    }

    /**
     * Draws the data set up in this tessellator and resets the state to prepare for new drawing.
     */
    public void draw() //TODO zmieniałem INT -> VOID
    {
        if (!this.z)
        {
            throw new IllegalStateException("Not tesselating!");
        }
        else
        {
            this.z = false;
            int var1 = 0;
            int var3;

            while (var1 < this.vertexCount)
            {
                boolean var2 = false;

                if (this.u == 7 && convertQuadsToTriangles)
                {
                    var3 = Math.min(this.vertexCount - var1, trivertsInBuffer);
                }
                else
                {
                    var3 = Math.min(this.vertexCount - var1, nativeBufferSize >> 5);
                }

                intBuffer.clear();
                intBuffer.put(this.rawBuffer, var1 * 10, var3 * 10);
                byteBuffer.position(0);
                byteBuffer.limit(var3 * 40);
                var1 += var3;

                if (useVBO)
                {
                    this.vboIndex = (this.vboIndex + 1) % vboCount;
                    ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertexBuffers.get(this.vboIndex));
                    ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, byteBuffer, ARBVertexBufferObject.GL_STREAM_DRAW_ARB);
                }

                if (this.hasTexture)
                {
                    if (useVBO)
                    {
                        GL11.glTexCoordPointer(4, GL11.GL_FLOAT, 40, 12L);
                    }
                    else
                    {
                        floatBuffer.position(3);
                        GL11.glTexCoordPointer(4, 40, floatBuffer);
                    }

                    GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                }

                if (this.hasBrightness)
                {
                    OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);

                    if (useVBO)
                    {
                        GL11.glTexCoordPointer(2, GL11.GL_SHORT, 40, 36L);
                    }
                    else
                    {
                        shortBuffer.position(18);
                        GL11.glTexCoordPointer(2, 40, shortBuffer);
                    }

                    GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                    OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                }

                if (this.hasColor)
                {
                    if (useVBO)
                    {
                        GL11.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 40, 28L);
                    }
                    else
                    {
                        byteBuffer.position(28);
                        GL11.glColorPointer(4, true, 40, byteBuffer);
                    }

                    GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
                }

                if (this.hasNormals)
                {
                    if (useVBO)
                    {
                        GL11.glNormalPointer(GL11.GL_UNSIGNED_BYTE, 40, 32L);
                    }
                    else
                    {
                        byteBuffer.position(32);
                        GL11.glNormalPointer(40, byteBuffer);
                    }

                    GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
                }

                if (useVBO)
                {
                    GL11.glVertexPointer(3, GL11.GL_FLOAT, 40, 0L);
                }
                else
                {
                    floatBuffer.position(0);
                    GL11.glVertexPointer(3, 40, floatBuffer);
                }

                GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

                if (this.u == 7 && convertQuadsToTriangles)
                {
                    GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, var3);
                }
                else
                {
                    GL11.glDrawArrays(this.u, 0, var3);
                }

                GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);

                if (this.hasTexture)
                {
                    GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                }

                if (this.hasBrightness)
                {
                    OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
                    GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                    OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                }

                if (this.hasColor)
                {
                    GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
                }

                if (this.hasNormals)
                {
                    GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
                }
            }

            if (this.rawBufferSize > 131072 && this.rawBufferIndex < this.rawBufferSize << 3)
            {
                this.rawBufferSize = 0;
                this.rawBuffer = null;
            }

            var3 = this.rawBufferIndex * 4;
            this.reset();
//            return var3; //TODO usunąłem !
        }
    }

    private void reset()
    {
        this.vertexCount = 0;
        byteBuffer.clear();
        this.rawBufferIndex = 0;
        this.addedVertices = 0;
    }

    /**
     * Sets draw mode in the tessellator to draw quads.
     */
    public void startDrawingQuads()
    {
        this.startDrawing(7);
    }

    /**
     * Resets tessellator state and prepares for drawing (with the specified draw mode).
     */
    public void startDrawing(int var1)
    {
        if (this.z)
        {
            throw new IllegalStateException("Already tesselating!");
        }
        else
        {
            this.z = true;
            this.reset();
            this.u = var1;
            this.hasNormals = false;
            this.hasColor = false;
            this.hasTexture = false;
            this.hasBrightness = false;
            this.isColorDisabled = false;
        }
    }

    /**
     * Sets the texture coordinates.
     */
    public void setTextureUV(double var1, double var3)
    {
        this.hasTexture = true;
        this.textureU = var1;
        this.textureV = var3;
        this.textureW = 1.0D;
    }

    public void setTextureUVW(double var1, double var3, double var5)
    {
        this.hasTexture = true;
        this.textureU = var1;
        this.textureV = var3;
        this.textureW = var5;
    }

    public void setBrightness(int var1)
    {
        this.hasBrightness = true;
        this.brightness = var1;
    }

    /**
     * Sets the RGB values as specified, converting from floats between 0 and 1 to integers from 0-255.
     */
    public void setColorOpaque_F(float var1, float var2, float var3)
    {
        this.setColorOpaque((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F));
    }

    /**
     * Sets the RGBA values for the color, converting from floats between 0 and 1 to integers from 0-255.
     */
    public void setColorRGBA_F(float var1, float var2, float var3, float var4)
    {
        this.setColorRGBA((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
    }

    /**
     * Sets the RGB values as specified, and sets alpha to opaque.
     */
    public void setColorOpaque(int var1, int var2, int var3)
    {
        this.setColorRGBA(var1, var2, var3, 255);
    }

    /**
     * Sets the RGBA values for the color. Also clamps them to 0-255.
     */
    public void setColorRGBA(int var1, int var2, int var3, int var4)
    {
        if (!this.isColorDisabled)
        {
            if (var1 > 255)
            {
                var1 = 255;
            }

            if (var2 > 255)
            {
                var2 = 255;
            }

            if (var3 > 255)
            {
                var3 = 255;
            }

            if (var4 > 255)
            {
                var4 = 255;
            }

            if (var1 < 0)
            {
                var1 = 0;
            }

            if (var2 < 0)
            {
                var2 = 0;
            }

            if (var3 < 0)
            {
                var3 = 0;
            }

            if (var4 < 0)
            {
                var4 = 0;
            }

            this.hasColor = true;

            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
            {
                this.color = var4 << 24 | var3 << 16 | var2 << 8 | var1;
            }
            else
            {
                this.color = var1 << 24 | var2 << 16 | var3 << 8 | var4;
            }
        }
    }

    /**
     * Adds a vertex specifying both x,y,z and the texture u,v for it.
     */
    public void addVertexWithUV(double var1, double var3, double var5, double var7, double var9)
    {
        this.setTextureUV(var7, var9);
        this.addVertex(var1, var3, var5);
    }

    public void addVertexWithUVW(double var1, double var3, double var5, double var7, double var9, double var11)
    {
        this.setTextureUVW(var7, var9, var11);
        this.addVertex(var1, var3, var5);
    }

    /**
     * Adds a vertex with the specified x,y,z to the current draw call. It will trigger a draw() if the buffer gets
     * full.
     */
    public void addVertex(double var1, double var3, double var5)
    {
        if (this.rawBufferIndex >= this.rawBufferSize - 40)
        {
            if (this.rawBufferSize == 0)
            {
                this.rawBufferSize = 65536;
                this.rawBuffer = new int[this.rawBufferSize];
            }
            else
            {
                this.rawBufferSize *= 2;
                this.rawBuffer = Arrays.copyOf(this.rawBuffer, this.rawBufferSize);
            }
        }

        ++this.addedVertices;

        if (this.u == 7 && convertQuadsToTriangles && this.addedVertices % 4 == 0)
        {
            for (int var7 = 0; var7 < 2; ++var7)
            {
                int var8 = 10 * (3 - var7);

                if (this.hasTexture)
                {
                    this.rawBuffer[this.rawBufferIndex + 3] = this.rawBuffer[this.rawBufferIndex - var8 + 3];
                    this.rawBuffer[this.rawBufferIndex + 4] = this.rawBuffer[this.rawBufferIndex - var8 + 4];
                    this.rawBuffer[this.rawBufferIndex + 5] = this.rawBuffer[this.rawBufferIndex - var8 + 5];
                    this.rawBuffer[this.rawBufferIndex + 6] = this.rawBuffer[this.rawBufferIndex - var8 + 6];
                }

                if (this.hasBrightness)
                {
                    this.rawBuffer[this.rawBufferIndex + 9] = this.rawBuffer[this.rawBufferIndex - var8 + 9];
                }

                if (this.hasColor)
                {
                    this.rawBuffer[this.rawBufferIndex + 7] = this.rawBuffer[this.rawBufferIndex - var8 + 7];
                }

                this.rawBuffer[this.rawBufferIndex + 0] = this.rawBuffer[this.rawBufferIndex - var8 + 0];
                this.rawBuffer[this.rawBufferIndex + 1] = this.rawBuffer[this.rawBufferIndex - var8 + 1];
                this.rawBuffer[this.rawBufferIndex + 2] = this.rawBuffer[this.rawBufferIndex - var8 + 2];
                ++this.vertexCount;
                this.rawBufferIndex += 10;
            }
        }

        if (this.hasTexture)
        {
            this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)this.textureU);
            this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)this.textureV);
            this.rawBuffer[this.rawBufferIndex + 5] = Float.floatToRawIntBits(0.0F);
            this.rawBuffer[this.rawBufferIndex + 6] = Float.floatToRawIntBits((float)this.textureW);
        }

        if (this.hasBrightness)
        {
            this.rawBuffer[this.rawBufferIndex + 9] = this.brightness;
        }

        if (this.hasColor)
        {
            this.rawBuffer[this.rawBufferIndex + 7] = this.color;
        }

        if (this.hasNormals)
        {
            this.rawBuffer[this.rawBufferIndex + 8] = this.normal;
        }

        this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float)(var1 + this.v));
        this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(var3 + this.w));
        this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(var5 + this.x));
        this.rawBufferIndex += 10;
        ++this.vertexCount;
    }

    /**
     * Sets the color to the given opaque value (stored as byte values packed in an integer).
     */
    public void setColorOpaque_I(int var1)
    {
        int var2 = var1 >> 16 & 255;
        int var3 = var1 >> 8 & 255;
        int var4 = var1 & 255;
        this.setColorOpaque(var2, var3, var4);
    }

    /**
     * Sets the color to the given color (packed as bytes in integer) and alpha values.
     */
    public void setColorRGBA_I(int var1, int var2)
    {
        int var3 = var1 >> 16 & 255;
        int var4 = var1 >> 8 & 255;
        int var5 = var1 & 255;
        this.setColorRGBA(var3, var4, var5, var2);
    }

    /**
     * Disables colors for the current draw call.
     */
    public void disableColor()
    {
        this.isColorDisabled = true;
    }

    /**
     * Sets the normal for the current draw call.
     */
    public void setNormal(float var1, float var2, float var3)
    {
        this.hasNormals = true;
        byte var4 = (byte)((int)(var1 * 127.0F));
        byte var5 = (byte)((int)(var2 * 127.0F));
        byte var6 = (byte)((int)(var3 * 127.0F));
        this.normal = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
    }

    /**
     * Sets the translation for all vertices in the current draw call.
     */
    public void setTranslation(double var1, double var3, double var5)
    {
        this.v = var1;
        this.w = var3;
        this.x = var5;
    }

    /**
     * Offsets the translation for all vertices in the current draw call.
     */
    public void addTranslation(float var1, float var2, float var3)
    {
        this.v += (double)var1;
        this.w += (double)var2;
        this.x += (double)var3;
    }

    static
    {
        instance.defaultTexture = true;
        useVBO = tryVBO && GLContext.getCapabilities().GL_ARB_vertex_buffer_object;

        if (useVBO)
        {
            vertexBuffers = GlAllocationUtils.allocateIntBuffer(vboCount);
            ARBVertexBufferObject.glGenBuffersARB(vertexBuffers);
        }
    }
}
