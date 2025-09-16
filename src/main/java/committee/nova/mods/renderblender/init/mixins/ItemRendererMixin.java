package committee.nova.mods.renderblender.init.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.vertex.PoseStack;
import committee.nova.mods.renderblender.api.client.model.PerspectiveModel;
import committee.nova.mods.renderblender.client.glint.GlintLayerManager;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * IItemRendererMixin
 *
 * @author cnlimiter
 * @version 1.0
 * @description
 * @date 2024/4/2 20:37
 */
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 0)
    )
    public void renderblender$onRenderItem(ItemStack stack, ItemDisplayContext context, boolean leftHand, PoseStack mStack, MultiBufferSource buffers, int packedLight, int packedOverlay, BakedModel modelIn, CallbackInfo ci) {
        if (modelIn instanceof PerspectiveModel iItemRenderer) {
            mStack.pushPose();
            final PerspectiveModel renderer = (PerspectiveModel) iItemRenderer.applyTransform(context, mStack, leftHand);
            mStack.translate(-0.5D, -0.5D, -0.5D);
            renderer.renderItem(stack, context, mStack, buffers, packedLight, packedOverlay);
            mStack.popPose();
        }
    }

    @Unique
    private static ItemStack reforge_stone$tempStack = ItemStack.EMPTY;

    @Inject(method = "render", at = @At("HEAD"))
    private void onStartRenderItem(ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        reforge_stone$tempStack = stack;
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onEndRenderItem(ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        reforge_stone$tempStack = ItemStack.EMPTY;
    }

    @ModifyVariable(method = "getFoilBufferDirect", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static boolean changeGlint(boolean glint) {
        return glint || GlintLayerManager.shouldAlwaysGlint(reforge_stone$tempStack);
    }

    @ModifyExpressionValue(method = "getFoilBufferDirect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;glintDirect()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderType onGetDirectItemGlintConsumer(RenderType original) {
        return GlintLayerManager.processStack(original, reforge_stone$tempStack);
    }

}
