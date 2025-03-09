package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(GatherDataEvent event)
    {
        event.createProvider(event.includeClient(), BlockTagDataGenerator::new);
        //event.addProvider(event.includeClient(), new ItemTagGenerator(output, lookupProvider, existingFileHelper));
    }
}
