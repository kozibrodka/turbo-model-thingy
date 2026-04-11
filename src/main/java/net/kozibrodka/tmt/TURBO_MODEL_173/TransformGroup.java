package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.util.math.Vec3d;

public abstract class TransformGroup
{

    public TransformGroup()
    {
    }

    public abstract double getWeight();

    public abstract Vec3d doTransformation(PositionTransformVertex positiontransformvertex);
}

