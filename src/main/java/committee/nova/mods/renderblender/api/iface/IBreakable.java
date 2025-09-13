package committee.nova.mods.renderblender.api.iface;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2025/1/22 02:48
 * @Description:
 */
public interface IBreakable {
    public default boolean breakAll(ItemStack stack, BlockPos pos, Player player) {
        return true;
    }
}
