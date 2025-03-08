package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public class MaLiLibDataGen
{
    public static void onInitializeDataGenerator(GatherDataEvent.Client event)
    {
        event.createProvider(BlockTagDataGenerator::new);
        //event.createProvider(ItemTagGenerator::new);
    }
}
