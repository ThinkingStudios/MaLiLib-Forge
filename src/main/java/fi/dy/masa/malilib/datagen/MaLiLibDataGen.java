package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(GatherDataEvent.Client event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        event.addProvider(new BlockTagDataGenerator(output, lookupProvider));
        //event.createProvider(new ItemTagDataGenerator(output, lookupProvider));
    }
}
