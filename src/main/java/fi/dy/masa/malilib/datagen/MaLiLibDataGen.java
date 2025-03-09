package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        event.addProvider(event.includeClient(), new BlockTagDataGenerator(output, lookupProvider, existingFileHelper));
        //event.addProvider(event.includeClient(), new ItemTagGenerator(output, lookupProvider, existingFileHelper));
    }
}
