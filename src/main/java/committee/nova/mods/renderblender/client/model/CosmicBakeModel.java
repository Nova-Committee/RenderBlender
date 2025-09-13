package committee.nova.mods.renderblender.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import committee.nova.mods.renderblender.RenderBlenderLib;
import committee.nova.mods.renderblender.api.client.model.PerspectiveModelState;
import committee.nova.mods.renderblender.api.client.model.bakedmodels.WrappedItemModel;
import committee.nova.mods.renderblender.api.client.util.TransformUtils;
import committee.nova.mods.renderblender.client.shader.RBRenderTypes;
import committee.nova.mods.renderblender.client.shader.RBShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2024/11/14 22:58
 * @Description:
 */
public class CosmicBakeModel extends WrappedItemModel {
    public static final float[] COSMIC_UVS = new float[40];
    private final List<ResourceLocation> maskSprite;

    public CosmicBakeModel(final BakedModel wrapped, final List<ResourceLocation> maskSprite) {
        super(wrapped);
        this.maskSprite = maskSprite;
    }

    @Override
    public void renderItem(ItemStack stack, ItemDisplayContext transformType, PoseStack pStack, MultiBufferSource source, int light, int overlay) {
        this.parentState = TransformUtils.DEFAULT_ITEM;
        this.renderWrapped(stack, pStack, source, light, overlay, true);
        if (source instanceof MultiBufferSource.BufferSource bs) {
            bs.endBatch();
        }
        final Minecraft mc = Minecraft.getInstance();
        float yaw = 0.0f;
        float pitch = 0.0f;
        float scale = 1f;
        if (RBShaders.inventoryRender || transformType == ItemDisplayContext.GUI) {
            scale = 100.0F;
        } else {
            yaw = (float) (mc.player.getYRot() * 2.0f * Math.PI / 360.0);
            pitch = -(float) (mc.player.getXRot() * 2.0f * Math.PI / 360.0);
        }

        RBShaders.cosmicTime
                .set((System.currentTimeMillis() - RBShaders.renderTime) / 2000.0F);
        RBShaders.cosmicYaw.set(yaw);
        RBShaders.cosmicPitch.set(pitch);
        RBShaders.cosmicExternalScale.set(scale);
        RBShaders.cosmicOpacity.set(1.0F);

        for (int i = 0; i < 10; ++i) {
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(RenderBlenderLib.rl("misc/cosmic_" + i));
            COSMIC_UVS[i * 4] = sprite.getU0();
            COSMIC_UVS[i * 4 + 1] = sprite.getV0();
            COSMIC_UVS[i * 4 + 2] = sprite.getU1();
            COSMIC_UVS[i * 4 + 3] = sprite.getV1();
        }
        if (RBShaders.cosmicUVs != null) {
            RBShaders.cosmicUVs.set(COSMIC_UVS);
        }

        final VertexConsumer cons = source.getBuffer(RBRenderTypes.COSMIC);
        List<TextureAtlasSprite> atlasSprite = new ArrayList<>();
        for (ResourceLocation res : maskSprite) {
            atlasSprite.add(Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(res));
        }
        mc.getItemRenderer().renderQuadList(pStack, cons, bakeItem(atlasSprite), stack, light, overlay);
    }

    @Override
    public @Nullable PerspectiveModelState getModelState() {
        return (PerspectiveModelState) this.parentState;
    }

    @Override
    public boolean isCosmic() {
        return true;
    }
}
