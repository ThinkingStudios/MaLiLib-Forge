package fi.dy.masa.malilib.util.game.wrap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import fi.dy.masa.malilib.util.data.ResourceLocation;

/**
 * Post-ReWrite code
 */
@ApiStatus.Experimental
public class RegistryUtils
{
    public static Block getBlockByIdStr(String name)
    {
        try
        {
            return getBlockById(ResourceLocation.of(name));
        }
        catch (Exception e)
        {
            return Blocks.AIR;
        }
    }

    public static Block getBlockById(ResourceLocation id)
    {
        return Registries.BLOCK.get(id.getId());
    }

    public static Block getBlockById(Identifier id)
    {
        return Registries.BLOCK.get(id);
    }

    public static @Nonnull ResourceLocation getBlockId(Block block)
    {
        return ResourceLocation.of(Registries.BLOCK.getId(block));
    }

    public static @Nonnull ResourceLocation getBlockId(BlockState state)
    {
        return getBlockId(state.getBlock());
    }

    public static String getBlockIdStr(Block block)
    {
        ResourceLocation id = getBlockId(block);
        return id.toString();
    }

    /**
     * Get a Block's Registry Entry.
     *
     * @param id
     * @param registry
     * @return
     */
    public static RegistryEntry<Block> getBlockEntry(ResourceLocation id, @Nonnull DynamicRegistryManager registry)
    {
        return getBlockEntry(id.getId(), registry);
    }

    public static RegistryEntry<Block> getBlockEntry(Identifier id, @Nonnull DynamicRegistryManager registry)
    {
        try
        {
            return registry.getOrThrow(Registries.BLOCK.getKey()).getEntry(id).orElseThrow();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Nullable
    public static RegistryEntry<BlockEntityType<?>> getBlockEntityType(ResourceLocation id, @Nonnull DynamicRegistryManager registry)
    {
        return getBlockEntityType(id.getId(), registry);
    }

    @Nullable
    public static RegistryEntry<BlockEntityType<?>> getBlockEntityType(Identifier id, @Nonnull DynamicRegistryManager registry)
    {
        try
        {
            return registry.getOrThrow(Registries.BLOCK_ENTITY_TYPE.getKey()).getEntry(id).orElse(null);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Nullable
    public static RegistryEntry<EntityType<?>> getEntityType(ResourceLocation id, @Nonnull DynamicRegistryManager registry)
    {
        return getEntityType(id.getId(), registry);
    }

    @Nullable
    public static RegistryEntry<EntityType<?>> getEntityType(Identifier id, @Nonnull DynamicRegistryManager registry)
    {
        try
        {
            return registry.getOrThrow(Registries.ENTITY_TYPE.getKey()).getEntry(id).orElse(null);
        }
            catch (Exception e)
        {
            return null;
        }
    }

    public static String getBlockIdStr(BlockState state)
    {
        return getBlockIdStr(state.getBlock());
    }

    public static Collection<ResourceLocation> getRegisteredBlockIds()
    {
        return ResourceLocation.of(new ArrayList<>(Registries.BLOCK.getIds()));
    }

    public static List<Block> getSortedBlockList()
    {
        List<Block> blocks = new ArrayList<>(Registries.BLOCK.stream().toList());

        blocks.sort(Comparator.comparing(RegistryUtils::getBlockIdStr));

        return blocks;
    }

    public static Item getItemByIdStr(String name)
    {
        try
        {
            return getItemById(ResourceLocation.of(name));
        }
        catch (Exception e)
        {
            return Items.AIR;
        }
    }

    public static Item getItemById(ResourceLocation id)
    {
        return Registries.ITEM.get(id.getId());
    }

    public static ResourceLocation getItemId(Item item)
    {
        return ResourceLocation.of(Registries.ITEM.getId(item));
    }

    public static String getItemIdStr(Item item)
    {
        ResourceLocation id = getItemId(item);
        return id.toString();
    }

    public static Collection<ResourceLocation> getRegisteredItemIds()
    {
        return ResourceLocation.of(new ArrayList<>(Registries.ITEM.getIds()));
    }

    public static List<Item> getSortedItemList()
    {
        List<Item> items = new ArrayList<>(Registries.ITEM.stream().toList());

        items.sort(Comparator.comparing(RegistryUtils::getItemIdStr));

        return items;
    }
}
