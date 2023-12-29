package net.kozibrodka.tmt.TURBO_MODEL_173;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

public class RenderBillboard extends LivingEntityRenderer
{

    public RenderBillboard(float f, float f1)
    {
        this(f, f1, 1, 1.0F, 1, 1.0F);
    }

    public RenderBillboard(float f, float f1, int i, float f2, int j, float f3)
    {
        this(f, f1, i, f2, j, f3, 1.0F);
    }

    public RenderBillboard(float f, float f1, int i, float f2, int j, float f3, float f4)
    {
        super(null, 0.9F);
        billboardModel = new ModelBillboard(f, f1, i, f2, j, f3);
        pitch = f4;
    }

    public RenderBillboard(float f, float f1, int i, float f2, float f3, int j, float f4,
                           float f5, float f6)
    {
        super(null, 0.9F);
        billboardModel = new ModelBillboard(f, f1, i, f2, f3, j, f4, f5);
        pitch = f6;
    }

    public void method_822(Living entityliving, double d, double d1, double d2,
                               float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glDisable(2884 /*GL_CULL_FACE*/);
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        method_2027(entityliving.skinUrl, entityliving.getTextured());
        float f2 = entityliving.field_1013 + (entityliving.field_1012 - entityliving.field_1013) * f1;
        billboardModel.render(dispatcher.field_2497, dispatcher.field_2498 * pitch, f2, 0.625F);
        float f3 = entityliving.getBrightnessAtEyes(f1);
        int i = method_817(entityliving, f3, f1);
        if((i >> 24 & 0xff) > 0 || entityliving.hurtTime > 0 || entityliving.deathTime > 0)
        {
            GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
            GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(770, 771);
            GL11.glDepthFunc(514);
            if(entityliving.hurtTime > 0 || entityliving.deathTime > 0)
            {
                GL11.glColor4f(f3, 0.0F, 0.0F, 0.4F);
                billboardModel.render(dispatcher.field_2497, dispatcher.field_2498 / 2.0F, f2, 0.625F);
            }
            if((i >> 24 & 0xff) > 0)
            {
                float f4 = (float)(i >> 16 & 0xff) / 255F;
                float f5 = (float)(i >> 8 & 0xff) / 255F;
                float f6 = (float)(i & 0xff) / 255F;
                float f7 = (float)(i >> 24 & 0xff) / 255F;
                GL11.glColor4f(f4, f5, f6, f7);
                billboardModel.render(dispatcher.field_2497, dispatcher.field_2498 / 2.0F, f2, 0.625F);
            }
            GL11.glDepthFunc(515);
            GL11.glDisable(3042 /*GL_BLEND*/);
            GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
            GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        }
        GL11.glEnable(2884 /*GL_CULL_FACE*/);
        GL11.glPopMatrix();
    }

    protected ModelBillboard billboardModel;
    protected float pitch;
}
