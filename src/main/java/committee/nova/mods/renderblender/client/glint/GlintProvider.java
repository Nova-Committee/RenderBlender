package committee.nova.mods.renderblender.client.glint;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface GlintProvider {
    @Nullable GlintManager.GlintHolder getGlint(ItemStack stack);

    boolean alwaysGlint(ItemStack stack);
}
