package fi.dy.masa.malilib.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.data.DataOutput;
import net.minecraft.registry.RegistryWrapper;

import fi.dy.masa.malilib.MaLiLibReference;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class ItemTagDataGenerator extends ItemTagsProvider
{
    public ItemTagDataGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture, MaLiLibReference.PORT_ID);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        // todo
    }
}
