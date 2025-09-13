package committee.nova.mods.renderblender.api.common.caps;

import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2024/12/28 20:18
 * @Description:
 */
public interface IFilters {
    List<ItemStack> getAllFilters();
    void addFilter(ItemStack stack);
    void removeFilter(ItemStack stack);
    void removeAll();
}
