package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.texture.TextureManager;

import java.util.ArrayList;

public class TextureGroup {

    public void addPoly(TexturedPolygon quad) {
        this.poly.add(quad);
    }

    public void loadTexture() {
        this.loadTexture(-1);
    }

    public void loadTexture(int defaultTexture) {
        if(!this.texture.equals("")) {
            TextureManager renderengine = EntityRenderDispatcher.INSTANCE.textureManager;
            renderengine.bindTexture(renderengine.getTextureId(this.texture));
        } else if(defaultTexture > -1) {
            EntityRenderDispatcher.INSTANCE.textureManager.bindTexture(defaultTexture);
        }

    }

    public ArrayList poly = new ArrayList();
    public String texture = "";
}
