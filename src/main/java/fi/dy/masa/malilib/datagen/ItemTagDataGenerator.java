package fi.dy.masa.malilib.datagen;

import java.util.concurrent.CompletableFuture;

import fi.dy.masa.malilib.MaLiLibReference;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.tag.ItemTagProvider;
import net.minecraft.data.tag.TagProvider;
import net.minecraft.registry.RegistryWrapper;

public class ItemTagDataGenerator extends ItemTagProvider
{
    public ItemTagDataGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, CompletableFuture<TagProvider.TagLookup<Block>> blockTagLookupFuture)
    {
        super(output, completableFuture, blockTagLookupFuture, MaLiLibReference.MOD_ID);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        // todo
    }
}
