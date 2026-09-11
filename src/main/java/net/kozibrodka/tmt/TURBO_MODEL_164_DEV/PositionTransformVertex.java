package net.kozibrodka.tmt.TURBO_MODEL_164_DEV;

import java.util.ArrayList;
import net.minecraft.util.math.Vec3d;

public class PositionTransformVertex extends PositionTextureVertex {

    public PositionTransformVertex(float x, float y, float z, float u, float v) {
        this(Vec3d.create((double) x, (double) y, (double) z), u, v);
    }

    public PositionTransformVertex(PositionTextureVertex vertex, float u, float v) {
        super(vertex, u, v);
        this.transformGroups = new ArrayList();
        if (vertex instanceof PositionTransformVertex) {
            this.neutralVector = ((PositionTransformVertex) vertex).neutralVector;
        } else {
            this.neutralVector = Vec3d.create(vertex.pos.x, vertex.pos.y, vertex.pos.z);
        }

    }

    public PositionTransformVertex(PositionTextureVertex vertex) {
        this(vertex, vertex.u, vertex.v);
    }

    public PositionTransformVertex(Vec3d vector, float u, float v) {
        super(vector, u, v);
        this.transformGroups = new ArrayList();
        this.neutralVector = Vec3d.create(vector.x, vector.y, vector.z);
    }

    public void setTransformation() {
        if (this.transformGroups.size() == 0) {
            this.pos.x = this.neutralVector.x;
            this.pos.y = this.neutralVector.y;
            this.pos.z = this.neutralVector.z;
        } else {
            double weight = 0.0D;

            int i;
            for (i = 0; i < this.transformGroups.size(); ++i) {
                weight += ((TransformGroup) this.transformGroups.get(i)).getWeight();
            }

            this.pos.x = 0.0D;
            this.pos.y = 0.0D;
            this.pos.z = 0.0D;

            for (i = 0; i < this.transformGroups.size(); ++i) {
                TransformGroup group = (TransformGroup) this.transformGroups.get(i);
                double cWeight = group.getWeight() / weight;
                Vec3d vector = group.doTransformation(this);
                this.pos.x += cWeight * vector.x;
                this.pos.y += cWeight * vector.y;
                this.pos.z += cWeight * vector.z;
            }

        }
    }

    public void addGroup(TransformGroup group) {
        this.transformGroups.add(group);
    }

    public void removeGroup(TransformGroup group) {
        this.transformGroups.remove(group);
    }
    public Vec3d neutralVector;
    public ArrayList transformGroups;
}
