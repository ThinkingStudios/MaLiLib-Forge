package fi.dy.masa.malilib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.util.BufferAllocator;

/**
 * This is cursed.  Will remove if not required before release.
 */
@Mixin(BufferBuilder.class)
public interface IMixinBufferBuilder
{
    @Accessor("drawMode")
    VertexFormat.DrawMode getDrawMode();

    @Accessor("vertexCount")
    int getVertexCount();

    @Accessor("vertexSizeByte")
    int getVertexSize();

    @Accessor("building")
    boolean isBuilding();

    @Accessor("allocator")
    BufferAllocator getBuffer();
}
