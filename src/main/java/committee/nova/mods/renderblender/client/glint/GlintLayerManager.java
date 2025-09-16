package committee.nova.mods.renderblender.client.glint;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

@OnlyIn(Dist.CLIENT)
public class GlintLayerManager extends RenderType {
    private static final Map<GlintManager.GlintHolder, RenderType> DIRECT_LAYERS = new HashMap<>();
    private static final Map<GlintManager.GlintHolder, RenderType> ARMOR_LAYERS = new HashMap<>();

    static {
        for (GlintManager.GlintHolder holder : GlintManager.HOLDERS) {
            if (holder.texture() == null) continue;
            DIRECT_LAYERS.put(holder, RenderType.create("direct_glint_" + holder.id(),
                    DefaultVertexFormat.POSITION_TEX,
                    VertexFormat.Mode.QUADS,
                    256,
                    CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER)
                            .setTextureState(new TextureStateShard(holder.texture(), true, false))
                            .setWriteMaskState(COLOR_WRITE)
                            .setCullState(NO_CULL)
                            .setDepthTestState(EQUAL_DEPTH_TEST)
                            .setTransparencyState(GLINT_TRANSPARENCY)
                            .setTexturingState(GLINT_TEXTURING)
                            .createCompositeState(false)));
            ARMOR_LAYERS.put(holder, RenderType.create("armor_glint_" + holder.id(),
                    DefaultVertexFormat.POSITION_TEX,
                    VertexFormat.Mode.QUADS,
                    256,
                    CompositeState.builder().setShaderState(RENDERTYPE_ARMOR_ENTITY_GLINT_SHADER)
                            .setTextureState(new TextureStateShard(holder.texture(), true, false))
                            .setWriteMaskState(COLOR_WRITE)
                            .setCullState(NO_CULL)
                            .setDepthTestState(EQUAL_DEPTH_TEST)
                            .setTransparencyState(GLINT_TRANSPARENCY)
                            .setTexturingState(ENTITY_GLINT_TEXTURING)
                            .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                            .createCompositeState(false)));
        }
    }

    public GlintLayerManager(String name, VertexFormat vertexFormat, VertexFormat.Mode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    @ApiStatus.Internal
    public static void registerAll(SortedMap<RenderType, BufferBuilder> map) {
        for (Map.Entry<GlintManager.GlintHolder, RenderType> entry : DIRECT_LAYERS.entrySet())
            if (!map.containsKey(entry.getValue()))
                map.put(entry.getValue(), new BufferBuilder(entry.getValue().bufferSize()));
    }

    public static boolean shouldAlwaysGlint(ItemStack stack) {
        if (stack.getItem() instanceof GlintProvider provider) return provider.alwaysGlint(stack);
        return !stack.isEmpty() &&
                stack.getTag() != null &&
                stack.getTag().contains(GlintManager.GLINT_KEY, Tag.TAG_STRING) &&
                GlintManager.BY_ID.containsKey(stack.getOrCreateTag().getString(GlintManager.GLINT_KEY)) &&
                stack.getTag().getBoolean(GlintManager.GLINT_ALWAYS_KEY);
    }

    public static RenderType processStack(RenderType origin, ItemStack stack) {
        if (stack.getItem() instanceof GlintProvider provider)
            return DIRECT_LAYERS.getOrDefault(provider.getGlint(stack), origin);
        if (!stack.isEmpty() && stack.getTag() != null && stack.getTag().contains(GlintManager.GLINT_KEY, Tag.TAG_STRING)) {
            String id = stack.getOrCreateTag().getString(GlintManager.GLINT_KEY);
            if (stack.getTag().getBoolean(GlintManager.GLINT_ALWAYS_KEY))
                return DIRECT_LAYERS.getOrDefault(GlintManager.BY_ID.getOrDefault(id, GlintManager.DEFAULT), RenderType.glintDirect());
        }
        return origin;
    }

    public static RenderType processArmor(RenderType origin, ItemStack stack) {
        if (stack.getItem() instanceof GlintProvider provider)
            return DIRECT_LAYERS.getOrDefault(provider.getGlint(stack), origin);
        if (!stack.isEmpty() && stack.getTag() != null && stack.getTag().contains(GlintManager.GLINT_KEY, Tag.TAG_STRING)) {
            String id = stack.getOrCreateTag().getString(GlintManager.GLINT_KEY);
            if (stack.getTag().getBoolean(GlintManager.GLINT_ALWAYS_KEY))
                return ARMOR_LAYERS.getOrDefault(GlintManager.BY_ID.getOrDefault(id, GlintManager.DEFAULT), RenderType.glintDirect());
        }
        return origin;
    }
}
