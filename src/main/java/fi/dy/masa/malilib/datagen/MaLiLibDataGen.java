package fi.dy.masa.malilib.datagen;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(IEventBus modEventBus)
    {
        modEventBus.addListener(GatherDataEvent.class, event -> {
            var generator = event.getGenerator();
            var output = generator.getPackOutput();
            var lookupProvider = event.getLookupProvider();
            var existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(event.includeClient(), new BlockTagDataGenerator(output, lookupProvider, existingFileHelper));
            //generator.addProvider(event.includeClient(), ItemTagGenerator::new);
        });
    }
}
