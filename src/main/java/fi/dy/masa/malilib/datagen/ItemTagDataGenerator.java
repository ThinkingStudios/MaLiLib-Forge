package fi.dy.masa.malilib.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.tag.ItemTagProvider;
import net.minecraft.data.tag.TagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import fi.dy.masa.malilib.MaLiLibReference;

public class ItemTagDataGenerator extends ItemTagProvider
{
    public ItemTagDataGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, CompletableFuture<TagProvider.TagLookup<Item>> parentTagLookupFuture, CompletableFuture<TagProvider.TagLookup<Block>> blockTagLookupFuture)
    {
        super(output, completableFuture, parentTagLookupFuture, blockTagLookupFuture, MaLiLibReference.PORT_ID);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        // todo
    }
}
