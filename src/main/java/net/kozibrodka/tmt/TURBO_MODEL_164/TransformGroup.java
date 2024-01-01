package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.minecraft.util.maths.Vec3f;

public abstract class TransformGroup {
    public abstract double getWeight();

    public abstract Vec3f doTransformation(PositionTransformVertex var1);
}
