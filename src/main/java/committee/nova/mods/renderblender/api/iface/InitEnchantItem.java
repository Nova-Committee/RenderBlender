package committee.nova.mods.renderblender.api.iface;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2024/11/15 14:42
 * @Description: 自带附魔
 */
public interface InitEnchantItem {
    int getInitEnchantLevel(ItemStack stack, Enchantment enchantment);
}
