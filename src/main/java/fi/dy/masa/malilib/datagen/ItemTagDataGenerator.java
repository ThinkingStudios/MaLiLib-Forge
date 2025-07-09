package fi.dy.masa.malilib.datagen;

import java.util.concurrent.CompletableFuture;

import fi.dy.masa.malilib.MaLiLibReference;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.ItemTagProvider;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemTagDataGenerator extends ItemTagProvider
{
    public ItemTagDataGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, CompletableFuture<TagProvider.TagLookup<Item>> completableFuture2, CompletableFuture<TagProvider.TagLookup<Block>> completableFuture3, ExistingFileHelper existingFileHelper)
    {
        super(output, completableFuture, completableFuture2, completableFuture3, MaLiLibReference.PORT_ID, existingFileHelper);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        // todo
    }
}
