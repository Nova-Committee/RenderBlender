package committee.nova.mods.renderblender.init.data.provider;

import committee.nova.mods.renderblender.RenderBlenderLib;
import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public class ModSpriteSource extends SpriteSourceProvider {
    public ModSpriteSource(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper, RenderBlenderLib.MOD_ID);
    }

    @Override
    protected void addSources() {
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new DirectoryLister("misc", "misc/"));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new DirectoryLister("models", "models/"));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new DirectoryLister("mask", "mask/"));
    }
}