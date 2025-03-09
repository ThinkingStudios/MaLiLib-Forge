package fi.dy.masa.malilib.datagen;

import java.util.concurrent.CompletableFuture;

import fi.dy.masa.malilib.MaLiLibReference;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.ItemTagProvider;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ItemTagDataGenerator extends ItemTagProvider
{
    public ItemTagDataGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, CompletableFuture<TagProvider.TagLookup<Block>> blockTagLookupFuture, ExistingFileHelper existingFileHelper)
    {
        super(output, completableFuture, blockTagLookupFuture, MaLiLibReference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        // todo
    }
}
