package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.util.maths.Vec3f;

public abstract class TransformGroup
{

    public TransformGroup()
    {
    }

    public abstract double getWeight();

    public abstract Vec3f doTransformation(PositionTransformVertex positiontransformvertex);
}

