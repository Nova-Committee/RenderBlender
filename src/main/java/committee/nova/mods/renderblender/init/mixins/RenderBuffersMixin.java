package committee.nova.mods.renderblender.init.mixins;

import com.mojang.blaze3d.vertex.BufferBuilder;
import committee.nova.mods.renderblender.client.glint.GlintLayerManager;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(RenderBuffers.class)
public class RenderBuffersMixin {
    @Inject(method = "put", at = @At("HEAD"))
    private static void registerGlints(Object2ObjectLinkedOpenHashMap<RenderType, BufferBuilder> builderStorage, RenderType layer, CallbackInfo ci) {
        GlintLayerManager.registerAll(builderStorage);
    }
}
