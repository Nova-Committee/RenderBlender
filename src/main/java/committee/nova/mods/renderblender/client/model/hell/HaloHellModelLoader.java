package committee.nova.mods.renderblender.client.model.hell;

import com.google.gson.*;
import committee.nova.mods.renderblender.client.model.HaloModelLoader;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HaloHellModelLoader implements IGeometryLoader<HaloHellModelLoader.HaloHellGeometry> {
    public static final HaloHellModelLoader INSTANCE = new HaloHellModelLoader();

    @Override
    public HaloHellGeometry read(JsonObject modelContents, JsonDeserializationContext deserializationContext) throws JsonParseException {
        final JsonObject halo = modelContents.getAsJsonObject("halo");
        if (halo == null) {
            throw new IllegalStateException("Missing 'halo' object.");
        }

        final JsonObject hell = modelContents.getAsJsonObject("hell");
        if (hell == null) {
            throw new IllegalStateException("Missing 'hell' object.");
        }

        final IntArrayList layerColors = new IntArrayList();
        final JsonArray layerColorsArr = modelContents.getAsJsonArray("layerColors");
        if (layerColorsArr != null) {
            for (final JsonElement jsonElement : layerColorsArr) {
                layerColors.add(jsonElement.getAsInt());
            }
        }

        // Halo properties
        final String texture = GsonHelper.getAsString(halo, "texture");
        final int color = GsonHelper.getAsInt(halo, "color");
        final int size = GsonHelper.getAsInt(halo, "size");
        final boolean pulse = GsonHelper.getAsBoolean(halo, "pulse");

        // Hell properties
        List<String> maskTexture = new ArrayList<>();
        if (hell.has("mask") && hell.get("mask").isJsonArray()) {
            JsonArray masks = hell.getAsJsonArray("mask");
            for (int i = 0; i < masks.size(); i++) {
                maskTexture.add(masks.get(i).getAsString());
            }
        } else {
            maskTexture.add(GsonHelper.getAsString(hell, "mask"));
        }

        final JsonObject clean = modelContents.deepCopy();
        clean.remove("halo");
        clean.remove("hell");
        clean.remove("loader");
        final BlockModel baseModel = deserializationContext.deserialize(clean, BlockModel.class);
        return new HaloHellGeometry(baseModel, layerColors, texture, color, size, pulse, maskTexture);
    }

    public static class HaloHellGeometry implements IUnbakedGeometry<HaloHellGeometry> {
        private final BlockModel baseModel;
        private final IntList layerColors;
        private final String texture;
        private final int color;
        private final int size;
        private final boolean pulse;
        private final List<String> maskTextures;

        public HaloHellGeometry(final BlockModel baseModel, final IntList layerColors, final String texture,
                                final int color, final int size, final boolean pulse, final List<String> maskTextures) {
            this.baseModel = baseModel;
            this.layerColors = layerColors;
            this.texture = texture;
            this.color = color;
            this.size = size;
            this.pulse = pulse;
            this.maskTextures = maskTextures;
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {

            BakedModel bakedBaseModel = this.baseModel.bake(baker, this.baseModel, spriteGetter, modelState, modelLocation, true);


            Material particleLocation = this.baseModel.getMaterial(this.texture);
            TextureAtlasSprite particle = spriteGetter.apply(particleLocation);


            List<ResourceLocation> textures = new ArrayList<>();
            this.maskTextures.forEach(mask -> textures.add(new ResourceLocation(mask)));

            return new HaloHellBakedModel(HaloModelLoader.HaloItemModelGeometry.tintLayers(bakedBaseModel, layerColors),
                    particle, this.color, this.size, this.pulse, textures);
        }

        @Override
        public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
            this.baseModel.resolveParents(modelGetter);
        }
    }
}
