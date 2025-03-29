package fi.dy.masa.malilib.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.thinkingstudio.mafglib.loader.entrypoints.DataGeneratorEntrypoint;

public class MaLiLibDataGen implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        event.addProvider(event.includeClient(), new BlockTagDataGenerator(output, lookupProvider, existingFileHelper));
        //event.addProvider(event.includeClient(), new ItemTagGenerator(output, lookupProvider, existingFileHelper));
    }
}
