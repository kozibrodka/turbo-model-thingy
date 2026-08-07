package net.kozibrodka.tmt.TURBO_MODEL_2000;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class TmtVertex {
    public Vec3d pos;
    public float u;
    public float v;

    public TmtVertex(float x, float y, float z, float u, float v) {
        this(Vec3d.createVectorHelper(x, y, z), u, v);
    }

    public TmtVertex remap(float u, float v) {
        return new TmtVertex(this, u, v);
    }

    public TmtVertex(TmtVertex vertex, float u, float v) {
        this.pos = vertex.pos;
        this.u = u;
        this.v = v;
    }

    public TmtVertex(Vec3d pos, float u, float v) {
        this.pos = pos;
        this.u = u;
        this.v = v;
    }
}
