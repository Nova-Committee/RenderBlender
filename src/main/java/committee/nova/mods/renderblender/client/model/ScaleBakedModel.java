package committee.nova.mods.renderblender.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScaleBakedModel extends BakedModelWrapper<BakedModel> {
    private final float guiScale;
    private final float handScale;

    public ScaleBakedModel(BakedModel originalModel, float guiScale, float handScale) {
        super(originalModel);
        this.guiScale = guiScale;
        this.handScale = handScale;
    }

    @Override
    public @NotNull BakedModel applyTransform(@NotNull ItemDisplayContext transformType, @NotNull PoseStack poseStack, boolean applyLeftHandTransform) {

        switch (transformType) {
            case GUI:
                if (guiScale != 1.0f) {
                    poseStack.scale(guiScale, guiScale, guiScale);
                }
                break;
            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
                if (handScale != 1.0f) {
                    poseStack.scale(handScale, handScale, handScale);
                }
                break;
            default:
                break;
        }


        return super.applyTransform(transformType, poseStack, applyLeftHandTransform);
    }

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return super.getOverrides();
    }

    @Override
    public boolean usesBlockLight() {
        return super.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return super.isCustomRenderer();
    }

    @Override
    public boolean isGui3d() {
        return super.isGui3d();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return super.useAmbientOcclusion();
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return super.getParticleIcon();
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand) {
        return super.getQuads(state, side, rand);
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData data, @Nullable net.minecraft.client.renderer.RenderType renderType) {
        return super.getQuads(state, side, rand, data, renderType);
    }
}
