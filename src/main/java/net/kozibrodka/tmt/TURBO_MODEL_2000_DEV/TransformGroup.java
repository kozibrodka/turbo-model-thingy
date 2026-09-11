package net.kozibrodka.tmt.TURBO_MODEL_2000_DEV;

public abstract class TransformGroup {
    public abstract double getWeight();

    public abstract Vec3d doTransformation(PositionTransformVertex var1);
}
