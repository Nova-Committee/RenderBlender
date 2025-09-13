package committee.nova.mods.renderblender.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;

import java.util.function.Function;

public class GlowEdgeModelLoader implements IGeometryLoader<GlowEdgeModelLoader.GlowEdgeGeometry> {
    public static final GlowEdgeModelLoader INSTANCE = new GlowEdgeModelLoader();

    @Override
    public GlowEdgeGeometry read(JsonObject modelContents, JsonDeserializationContext deserializationContext) throws JsonParseException {
        // 读取发光边缘配置
        final JsonObject glowEdge = modelContents.getAsJsonObject("glow_edge");
        if (glowEdge == null) {
            throw new IllegalStateException("Missing 'glow_edge' object in model json.");
        }

        // 解析发光边缘参数
        final int color = GsonHelper.getAsInt(glowEdge, "color", 16777215); // 默认白色 (0xFFFFFF)
        final float width = GsonHelper.getAsFloat(glowEdge, "width", 1.0f); // 默认宽度
        final float offset = GsonHelper.getAsFloat(glowEdge, "offset", 0.02f); // 默认偏移量

        // 移除自定义配置，保留基础模型信息
        final JsonObject clean = modelContents.deepCopy();
        clean.remove("glow_edge");
        clean.remove("loader");
        final BlockModel baseModel = deserializationContext.deserialize(clean, BlockModel.class);

        return new GlowEdgeGeometry(baseModel, color, width, offset);
    }

    public static class GlowEdgeGeometry implements IUnbakedGeometry<GlowEdgeGeometry> {
        private final BlockModel baseModel;
        private final int color;
        private final float width;
        private final float offset;

        public GlowEdgeGeometry(BlockModel baseModel, int color, float width, float offset) {
            this.baseModel = baseModel;
            this.color = color;
            this.width = width;
            this.offset = offset;
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
            BakedModel bakedBaseModel = this.baseModel.bake(baker, this.baseModel, spriteGetter, modelState, modelLocation, true);
            return new GlowEdgeBakedModel(bakedBaseModel, color, width, offset);
        }

        @Override
        public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
            this.baseModel.resolveParents(modelGetter);
        }
    }
}
