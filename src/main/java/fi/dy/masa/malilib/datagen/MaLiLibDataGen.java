package fi.dy.masa.malilib.datagen;

import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.resource.ResourceType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.thinkingstudio.mafglib.helper.RuntimePackHelper;

import java.util.concurrent.CompletableFuture;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(AddPackFindersEvent.class, event -> {
            var type = event.getPackType();
            var registries = CompletableFuture.<RegistryWrapper.WrapperLookup>completedFuture(ServerDynamicRegistryType.createCombinedDynamicRegistries().getCombinedRegistryManager());
            if (type == ResourceType.SERVER_DATA) {
                var pack = RuntimePackHelper.simpleRuntimePack(modContainer, type);
                var output = pack.getPackOutput();
                var existingFileHelper = pack.getExistingFileHelper();
                pack.addDataProvider(new BlockTagDataGenerator(output, registries, existingFileHelper));
                event.addRepositorySource(pack);
            }
        });
    }
}
