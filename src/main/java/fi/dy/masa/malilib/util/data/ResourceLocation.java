package fi.dy.masa.malilib.util.data;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.util.Identifier;

/**
 * Post-ReWrite code
 */
@ApiStatus.Experimental
public class ResourceLocation
{
    private final Identifier id;

    public ResourceLocation(String str)
    {
        this.id = Identifier.of(str);
    }

    public ResourceLocation(String name, String path)
    {
        this.id = Identifier.of(name, path);
    }

    public ResourceLocation(Identifier id)
    {
        this.id = id;
    }

    public static ResourceLocation of(String str)
    {
        return new ResourceLocation(str);
    }

    public static ResourceLocation of(String name, String path)
    {
        return new ResourceLocation(name, path);
    }

    public static ResourceLocation of(Identifier id)
    {
        return new ResourceLocation(id);
    }

    public static List<ResourceLocation> of(List<Identifier> list)
    {
        List<ResourceLocation> newList = new ArrayList<>();

        list.forEach((id) -> newList.add(ResourceLocation.of(id)));

        return newList;
    }

    public @Nullable Identifier getId()
    {
        return this.id;
    }

    public String getNamespace()
    {
        return this.id.getNamespace();
    }

    public String getPath()
    {
        return this.id.getPath();
    }

    @Override
    public String toString()
    {
        return this.id.toString();
    }
}
