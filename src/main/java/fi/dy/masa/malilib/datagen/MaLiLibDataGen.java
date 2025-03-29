package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.thinkingstudio.mafglib.loader.entrypoints.DataGeneratorEntrypoint;

public class MaLiLibDataGen implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(GatherDataEvent.Client event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        event.addProvider(new BlockTagDataGenerator(output, lookupProvider));
        //event.createProvider(new ItemTagDataGenerator(output, lookupProvider));
    }
}
