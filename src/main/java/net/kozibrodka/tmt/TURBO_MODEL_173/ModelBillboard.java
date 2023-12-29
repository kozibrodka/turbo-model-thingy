package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelBillboard extends EntityModelBase
{

    public ModelBillboard(float f, float f1)
    {
        this(f, f1, 1, 1.0F, 1, 1.0F);
    }

    public ModelBillboard(float f, float f1, int i, float f2, int j, float f3)
    {
        this(f, f1, i, 1.0F / (float)j, f2, j, 1.0F, f3);
    }

    public ModelBillboard(float f, float f1, int i, float f2, float f3, int j, float f4,
                          float f5)
    {
        face = new ModelRendererTurbo[i];
        float f6 = f3 / f5;
        float f7 = f2 / f4;
        for(int k = 0; k < i; k++)
        {
            int l = (int)Math.floor(k / j);
            int i1 = k % j;
            float f8 = f7 * (float)i1;
            float f9 = (float)l * f6;
            QuadPoint apositiontexturevertex[] = {
                    new QuadPoint(-f / 2.0F, -f1 / 2.0F, 0.0F, f8, f9), new QuadPoint(f / 2.0F, -f1 / 2.0F, 0.0F, f8 + f7, f9), new QuadPoint(f / 2.0F, f1 / 2.0F, 0.0F, f8 + f7, f9 + f6), new QuadPoint(-f / 2.0F, f1 / 2.0F, 0.0F, f8, f9 + f6)
            };
            face[k] = new ModelRendererTurbo(0, 0);
            face[k].rotationPointY += f1 / 2.0F;
            face[k].addPolygon(apositiontexturevertex);
        }

    }

    public void render(float f, float f1, float f2, float f3)
    {
        setRotation(f, f1, f2);
        for(int i = 0; i < face.length; i++)
        {
            face[i].render(f3);
        }

    }

    public void setRotation(float f, float f1, float f2)
    {
        float f3 = (f2 - f - 180F / (float)face.length) + 180F;
        do
        {
            if(f3 >= 0.0F && f3 <= 360F)
            {
                break;
            }
            if(f3 < 0.0F)
            {
                f3 += 360F;
            }
            if(f3 > 360F)
            {
                f3 -= 360F;
            }
        } while(true);
        f3 = 360F - f3;
        int i = (int)Math.floor((float)face.length * (f3 / 360F));
        for(int j = 0; j < face.length; j++)
        {
            if(i != j)
            {
                face[j].showModel = false;
            } else
            {
                face[j].showModel = true;
            }
            face[j].yaw = (float)Math.toRadians(180F - f);
            face[j].pitch = (float)Math.toRadians(180F + f1);
        }

    }

    public ModelRendererTurbo face[];
}
