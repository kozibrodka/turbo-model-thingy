package net.kozibrodka.tmt.TURBO_MODEL_1337_DEV;

import net.minecraft.util.math.MathHelper;

public class Vec3d {
    /**
     * A global Vec3Pool that always creates new vectors instead of reusing them and is thread-safe.
     */
    public static final Vec3Pool fakePool = new Vec3Pool(-1, -1);
    public final Vec3Pool myVec3LocalPool;

    /** X coordinate of Vec3D */
    public double x;

    /** Y coordinate of Vec3D */
    public double y;

    /** Z coordinate of Vec3D */
    public double z;

    /**
     * Static method for creating a new Vec3D given the three x,y,z values. This is only called from the other static
     * method which creates and places it in the list.
     */
    public static Vec3d createVectorHelper(double par0, double par2, double par4)
    {
        return new Vec3d(fakePool, par0, par2, par4);
    }

    protected Vec3d(Vec3Pool par1Vec3Pool, double par2, double par4, double par6)
    {
        if (par2 == -0.0D)
        {
            par2 = 0.0D;
        }

        if (par4 == -0.0D)
        {
            par4 = 0.0D;
        }

        if (par6 == -0.0D)
        {
            par6 = 0.0D;
        }

        this.x = par2;
        this.y = par4;
        this.z = par6;
        this.myVec3LocalPool = par1Vec3Pool;
    }

    /**
     * Sets the x,y,z components of the vector as specified.
     */
    protected Vec3d setComponents(double par1, double par3, double par5)
    {
        this.x = par1;
        this.y = par3;
        this.z = par5;
        return this;
    }

    /**
     * Returns a new vector with the result of the specified vector minus this.
     */
    public Vec3d relativize(Vec3d par1Vec3)
    {
        return this.myVec3LocalPool.getVecFromPool(par1Vec3.x - this.x, par1Vec3.y - this.y, par1Vec3.z - this.z);
    }

    /**
     * Normalizes the vector to a length of 1 (except if it is the zero vector)
     */
    public Vec3d normalize()
    {
        double var1 = (double)MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return var1 < 1.0E-4D ? this.myVec3LocalPool.getVecFromPool(0.0D, 0.0D, 0.0D) : this.myVec3LocalPool.getVecFromPool(this.x / var1, this.y / var1, this.z / var1);
    }

    public double dotProduct(Vec3d par1Vec3)
    {
        return this.x * par1Vec3.x + this.y * par1Vec3.y + this.z * par1Vec3.z;
    }

    /**
     * Returns a new vector with the result of this vector x the specified vector.
     */
    public Vec3d crossProduct(Vec3d par1Vec3)
    {
        return this.myVec3LocalPool.getVecFromPool(this.y * par1Vec3.z - this.z * par1Vec3.y, this.z * par1Vec3.x - this.x * par1Vec3.z, this.x * par1Vec3.y - this.y * par1Vec3.x);
    }

    /**
     * Adds the specified x,y,z vector components to this vector and returns the resulting vector. Does not change this
     * vector.
     */
    public Vec3d add(double par1, double par3, double par5)
    {
        return this.myVec3LocalPool.getVecFromPool(this.x + par1, this.y + par3, this.z + par5);
    }

    /**
     * Euclidean distance between this and the specified vector, returned as double.
     */
    public double distanceTo(Vec3d par1Vec3)
    {
        double var2 = par1Vec3.x - this.x;
        double var4 = par1Vec3.y - this.y;
        double var6 = par1Vec3.z - this.z;
        return (double)MathHelper.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
    }

    /**
     * The square of the Euclidean distance between this and the specified vector.
     */
    public double squareDistanceTo(Vec3d par1Vec3)
    {
        double var2 = par1Vec3.x - this.x;
        double var4 = par1Vec3.y - this.y;
        double var6 = par1Vec3.z - this.z;
        return var2 * var2 + var4 * var4 + var6 * var6;
    }

    /**
     * The square of the Euclidean distance between this and the vector of x,y,z components passed in.
     */
    public double squareDistanceTo(double par1, double par3, double par5)
    {
        double var7 = par1 - this.x;
        double var9 = par3 - this.y;
        double var11 = par5 - this.z;
        return var7 * var7 + var9 * var9 + var11 * var11;
    }

    /**
     * Returns the length of the vector.
     */
    public double length()
    {
        return (double)MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    public Vec3d interpolateByX(Vec3d par1Vec3, double par2)
    {
        double var4 = par1Vec3.x - this.x;
        double var6 = par1Vec3.y - this.y;
        double var8 = par1Vec3.z - this.z;

        if (var4 * var4 < 1.0E-7F)
        {
            return null;
        }
        else
        {
            double var10 = (par2 - this.x) / var4;
            return var10 >= 0.0D && var10 <= 1.0D ? this.myVec3LocalPool.getVecFromPool(this.x + var4 * var10, this.y + var6 * var10, this.z + var8 * var10) : null;
        }
    }

    /**
     * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    public Vec3d interpolateByY(Vec3d par1Vec3, double par2)
    {
        double var4 = par1Vec3.x - this.x;
        double var6 = par1Vec3.y - this.y;
        double var8 = par1Vec3.z - this.z;

        if (var6 * var6 < 1.0E-7F)
        {
            return null;
        }
        else
        {
            double var10 = (par2 - this.y) / var6;
            return var10 >= 0.0D && var10 <= 1.0D ? this.myVec3LocalPool.getVecFromPool(this.x + var4 * var10, this.y + var6 * var10, this.z + var8 * var10) : null;
        }
    }

    /**
     * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    public Vec3d interpolateByZ(Vec3d par1Vec3, double par2)
    {
        double var4 = par1Vec3.x - this.x;
        double var6 = par1Vec3.y - this.y;
        double var8 = par1Vec3.z - this.z;

        if (var8 * var8 < 1.0E-7F)
        {
            return null;
        }
        else
        {
            double var10 = (par2 - this.z) / var8;
            return var10 >= 0.0D && var10 <= 1.0D ? this.myVec3LocalPool.getVecFromPool(this.x + var4 * var10, this.y + var6 * var10, this.z + var8 * var10) : null;
        }
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    /**
     * Rotates the vector around the x axis by the specified angle.
     */
    public void rotateX(float par1)
    {
        float var2 = MathHelper.cos(par1);
        float var3 = MathHelper.sin(par1);
        double var4 = this.x;
        double var6 = this.y * (double)var2 + this.z * (double)var3;
        double var8 = this.z * (double)var2 - this.y * (double)var3;
        this.x = var4;
        this.y = var6;
        this.z = var8;
    }

    /**
     * Rotates the vector around the y axis by the specified angle.
     */
    public void rotateY(float par1)
    {
        float var2 = MathHelper.cos(par1);
        float var3 = MathHelper.sin(par1);
        double var4 = this.x * (double)var2 + this.z * (double)var3;
        double var6 = this.y;
        double var8 = this.z * (double)var2 - this.x * (double)var3;
        this.x = var4;
        this.y = var6;
        this.z = var8;
    }

    /**
     * Rotates the vector around the z axis by the specified angle.
     */
    public void rotateAroundZ(float par1)
    {
        float var2 = MathHelper.cos(par1);
        float var3 = MathHelper.sin(par1);
        double var4 = this.x * (double)var2 + this.y * (double)var3;
        double var6 = this.y * (double)var2 - this.x * (double)var3;
        double var8 = this.z;
        this.x = var4;
        this.y = var6;
        this.z = var8;
    }
}
