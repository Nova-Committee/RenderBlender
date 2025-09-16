package committee.nova.mods.renderblender.client.shader;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import committee.nova.mods.renderblender.RenderBlenderLib;
import committee.nova.mods.renderblender.Res;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

import static net.minecraft.client.renderer.RenderStateShard.*;

public class RBRenderTypes {
    public static RenderType VOID = RenderType.create(
            RenderBlenderLib.rl("void_hemisphere").toString(),
            DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.TRIANGLES, 256,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_ENTITY_SHADOW_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(Res.VOID, false, false))
                    .setCullState(RenderType.NO_CULL)
                    .createCompositeState(false));

    public static RenderType VOID_HALO = RenderType.create(
            RenderBlenderLib.rl("void_halo").toString(),
            DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS, 256,
            RenderType.CompositeState.builder()
                    .setShaderState(RenderType.POSITION_COLOR_TEX_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(Res.VOID_HALO, false, false))
                    .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                    .setWriteMaskState(RenderType.COLOR_WRITE)
                    .createCompositeState(false));

    public static RenderType COSMIC = RenderType.create(
            RenderBlenderLib.rl("cosmic").toString(), DefaultVertexFormat.BLOCK,
            VertexFormat.Mode.QUADS, 2097152, true, false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.COSMIC_SHADER))
                    .setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setTextureState(RenderStateShard.BLOCK_SHEET_MIPPED)
                    .createCompositeState(true)
    );
    public static RenderType ETERNAL = RenderType.create(
            RenderBlenderLib.rl("eternal").toString(), DefaultVertexFormat.BLOCK,
            VertexFormat.Mode.QUADS, 2097152, true, false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.ETERNAL_SHADER))
                    .setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setTextureState(RenderStateShard.BLOCK_SHEET_MIPPED)
                    .createCompositeState(true)
    );

    public static RenderType HELL = RenderType.create(
            RenderBlenderLib.rl("hell").toString(), DefaultVertexFormat.BLOCK,
            VertexFormat.Mode.QUADS, 2097152, true, false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.HELL_SHADER))
                    .setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setTextureState(RenderStateShard.BLOCK_SHEET_MIPPED)
                    .createCompositeState(true)
    );
    public static RenderType UNSTABLE = RenderType.create(
            RenderBlenderLib.rl("unstable").toString(), DefaultVertexFormat.BLOCK,
            VertexFormat.Mode.QUADS, 2097152, true, false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.UNSTABLE_SHADER))
                    .setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setTextureState(RenderStateShard.BLOCK_SHEET_MIPPED)
                    .createCompositeState(true)
    );

    public static final RenderType COSMIC_ARMOR = RenderType.create(
            RenderBlenderLib.rl("cosmic").toString(), DefaultVertexFormat.NEW_ENTITY,
            VertexFormat.Mode.QUADS, 2097152, true, false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.COSMIC_ARMOR_SHADER))
                    .setDepthTestState(RenderStateShard.LEQUAL_DEPTH_TEST)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLayeringState(RenderType.VIEW_OFFSET_Z_LAYERING)
                    .setTextureState(RenderStateShard.BLOCK_SHEET)
                    .createCompositeState(true));


    public static RenderType armorMask(final ResourceLocation tex) {
        return RenderType.create(RenderBlenderLib.rl( "armor_mask").toString(),
                DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 2097152, true, false,
                RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.COSMIC_ARMOR_SHADER))
                        .setTextureState(new RenderStateShard.TextureStateShard(tex, false, false))
                        .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                        .setLightmapState(RenderType.LIGHTMAP)
                        .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                        .setCullState(RenderType.NO_CULL)
                        .setLayeringState(RenderType.VIEW_OFFSET_Z_LAYERING)
                        .createCompositeState(true));
    }

    public static RenderType armorMask2(final ResourceLocation tex) {
        return RenderType.create(RenderBlenderLib.rl( "armor_mask2").toString(),
                DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 2097152, true, false,
                RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(() -> RBShaders.COSMIC_ARMOR_SHADER))
                        .setTextureState(new RenderStateShard.TextureStateShard(tex, false, false))
                        .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                        .setLightmapState(RenderType.LIGHTMAP)
                        .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                        .setCullState(RenderType.NO_CULL)
                        .createCompositeState(true));
    }

    public static final RenderType GLOWING_OUTLINE = RenderType.create(
            RenderBlenderLib.rl("glowing_outline").toString(),
            DefaultVertexFormat.NEW_ENTITY,
            VertexFormat.Mode.QUADS,
            256,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_OUTLINE_SHADER)
                    .setTextureState(RenderStateShard.BLOCK_SHEET_MIPPED)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                    .createCompositeState(false));


}
