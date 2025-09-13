package committee.nova.mods.renderblender.init.data;

import committee.nova.mods.renderblender.init.data.provider.ModSpriteSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.CompletableFuture;

public class ModDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();

        if (event.includeClient()) {
            generator.addProvider(true, new ModSpriteSource(output, helper));
        }
    }
}
