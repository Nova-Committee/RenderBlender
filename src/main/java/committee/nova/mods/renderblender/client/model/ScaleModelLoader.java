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

public class ScaleModelLoader implements IGeometryLoader<ScaleModelLoader.ScaleGeometry> {
    public static final ScaleModelLoader INSTANCE = new ScaleModelLoader();

    @Override
    public ScaleGeometry read(JsonObject modelContents, JsonDeserializationContext deserializationContext) throws JsonParseException {

        final JsonObject scale = modelContents.getAsJsonObject("scale");
        if (scale == null) {
            throw new IllegalStateException("Missing 'scale' object in model json.");
        }


        final float guiScale = GsonHelper.getAsFloat(scale, "gui_scale", 1.0f); // GUI中缩放比例（默认1.0）
        final float handScale = GsonHelper.getAsFloat(scale, "hand_scale", 1.0f); // 手中缩放比例（默认1.0）


        final JsonObject clean = modelContents.deepCopy();
        clean.remove("scale");
        clean.remove("loader");
        final BlockModel baseModel = deserializationContext.deserialize(clean, BlockModel.class);

        return new ScaleGeometry(baseModel, guiScale, handScale);
    }

    public static class ScaleGeometry implements IUnbakedGeometry<ScaleGeometry> {
        private final BlockModel baseModel;
        private final float guiScale;
        private final float handScale;

        public ScaleGeometry(BlockModel baseModel, float guiScale, float handScale) {
            this.baseModel = baseModel;
            this.guiScale = guiScale;
            this.handScale = handScale;
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
            BakedModel bakedBaseModel = this.baseModel.bake(baker, this.baseModel, spriteGetter, modelState, modelLocation, true);
            return new ScaleBakedModel(bakedBaseModel, guiScale, handScale);
        }

        @Override
        public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
            this.baseModel.resolveParents(modelGetter);
        }
    }
}
