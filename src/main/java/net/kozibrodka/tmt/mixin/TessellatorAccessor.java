package net.kozibrodka.tmt.mixin;

import net.minecraft.client.render.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Tessellator.class)
public interface TessellatorAccessor {

}
