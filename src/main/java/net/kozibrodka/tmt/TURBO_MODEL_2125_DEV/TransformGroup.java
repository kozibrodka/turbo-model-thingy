package net.kozibrodka.tmt.TURBO_MODEL_2125_DEV;

import net.minecraft.util.math.Vec3d;

public abstract class TransformGroup {
    public abstract double getWeight();

    public abstract Vec3d doTransformation(PositionTransformVertex var1);
}
