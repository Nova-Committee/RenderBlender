package committee.nova.mods.renderblender.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import committee.nova.mods.renderblender.api.client.model.PerspectiveModelState;
import committee.nova.mods.renderblender.api.client.model.bakedmodels.WrappedItemModel;
import committee.nova.mods.renderblender.api.client.render.item.IItemRenderer;
import committee.nova.mods.renderblender.api.client.util.TransformUtils;
import committee.nova.mods.renderblender.client.shader.RBRenderTypes;
import committee.nova.mods.renderblender.client.shader.RBShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GlowEdgeBakedModel extends WrappedItemModel implements IItemRenderer {
    private final BakedModel parentModel;
    private final int glowColor;
    private final float glowWidth;
    private final float glowOffset; // 控制边缘偏移量

    public GlowEdgeBakedModel(BakedModel wrapped, int glowColor, float glowWidth, float glowOffset) {
        super(wrapped);
        this.parentModel = wrapped;
        this.glowColor = glowColor;
        this.glowWidth = Math.max(0.5f, glowWidth);
        this.glowOffset = glowOffset;
    }

    public GlowEdgeBakedModel(BakedModel wrapped, int glowColor, float glowWidth) {
        this(wrapped, glowColor, glowWidth, 0.02f); // 默认偏移量
    }

    @Override
    public @Nullable PerspectiveModelState getModelState() {
        return (PerspectiveModelState) this.parentState;
    }

    @Override
    public void renderItem(ItemStack stack, ItemDisplayContext ctx, PoseStack pStack, MultiBufferSource source, int light, int overlay) {
        // 首先渲染基础模型
        this.parentState = TransformUtils.DEFAULT_ITEM;
        this.renderWrapped(stack, pStack, source, light, overlay, true);

        // 然后渲染发光边缘效果，模仿光灵箭的发光轮廓效果
        renderGlowEdge(stack, ctx, pStack, source, light, overlay);
    }

    private void renderGlowEdge(ItemStack stack, ItemDisplayContext ctx, PoseStack pStack, MultiBufferSource source, int light, int overlay) {
        // 使用Minecraft的轮廓渲染机制，模仿光灵箭效果
        VertexConsumer consumer = source.getBuffer(RBRenderTypes.GLOWING_OUTLINE);

        // 应用轻微的模型扩展来创建边缘效果，并添加偏移
        PoseStack tempStack = new PoseStack();
        tempStack.mulPoseMatrix(pStack.last().pose());

        // 先进行偏移，再进行缩放，这样边缘会向下一些
        tempStack.translate(0.0f, glowOffset, 0.0f); // 向下偏移

        // 根据glowWidth扩展模型
        float expansionFactor = 1.0f + (glowWidth * 0.05f);
        tempStack.scale(expansionFactor, expansionFactor, expansionFactor);

        // 渲染扩展后的模型作为发光边缘
        Minecraft.getInstance().getItemRenderer()
                .renderModelLists(parentModel, stack, light, overlay, tempStack, consumer);
    }
}
