package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var registriesFuture = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new BlockTagDataGenerator(output, registriesFuture, existingFileHelper));
        //pack.addProvider(ItemTagGenerator::new);
    }
}
