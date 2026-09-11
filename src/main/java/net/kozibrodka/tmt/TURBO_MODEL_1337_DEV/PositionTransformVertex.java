package net.kozibrodka.tmt.TURBO_MODEL_1337_DEV;

import java.util.ArrayList;

public class PositionTransformVertex extends PositionTextureVertex {

    public PositionTransformVertex(float x, float y, float z, float u, float v) {
        this(Vec3d.createVectorHelper((double) x, (double) y, (double) z), u, v);
    }

    public PositionTransformVertex(TmtVertex vertex, float u, float v) {
        super((PositionTextureVertex) vertex, u, v); //TODO to może nie być OK
        this.transformGroups = new ArrayList();
        if (vertex instanceof PositionTransformVertex) {
            this.neutralVector = ((PositionTransformVertex) vertex).neutralVector;
        } else {
            this.neutralVector = Vec3d.createVectorHelper(vertex.pos.x, vertex.pos.y, vertex.pos.z);
        }

    }

    public PositionTransformVertex(TmtVertex vertex) {
        this(vertex, vertex.u, vertex.v);
    }

    public PositionTransformVertex(Vec3d vector, float u, float v) {
        super(vector, u, v);
        this.transformGroups = new ArrayList();
        this.neutralVector = Vec3d.createVectorHelper(vector.x, vector.y, vector.z);
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
