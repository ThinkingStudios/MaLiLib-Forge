package fi.dy.masa.malilib.util.position;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.util.math.Direction;

import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.util.StringUtils;

/**
 * Post-ReWrite code
 */
@ApiStatus.Experimental
public enum BlockRotation
{
    NONE    (0, net.minecraft.util.BlockRotation.NONE, "none"),
    CW_90   (1, net.minecraft.util.BlockRotation.CLOCKWISE_90, "rotate_90"),
    CW_180  (2, net.minecraft.util.BlockRotation.CLOCKWISE_180, "rotate_180"),
    CCW_90  (3, net.minecraft.util.BlockRotation.COUNTERCLOCKWISE_90, "rotate_270");

    public static final BlockRotation[] VALUES = values();

    private final int index;
    private final String name;
    private final String translationKey;
    private final net.minecraft.util.BlockRotation vanillaRotation;

    BlockRotation(int index, net.minecraft.util.BlockRotation vanillaRotation, String name)
    {
        this.index = index;
        this.vanillaRotation = vanillaRotation;
        this.name = name;
        this.translationKey = MaLiLibReference.MOD_ID + ".label.block_rotation." + name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDisplayName()
    {
        return StringUtils.translate(this.translationKey);
    }

    public BlockRotation add(BlockRotation rotation)
    {
        int index = (this.index + rotation.index) & 3;
        return VALUES[index];
    }

    public Direction rotate(Direction direction)
    {
        if (direction.getAxis() != Direction.Axis.Y)
        {
            switch(this)
            {
                case CW_90:     return direction.rotateYClockwise();
                case CW_180:    return direction.getOpposite();
                case CCW_90:    return direction.rotateYCounterclockwise();
            }
        }

        return direction;
    }

    public BlockRotation getReverseRotation()
    {
        switch (this)
        {
            case CCW_90:    return BlockRotation.CW_90;
            case CW_90:     return BlockRotation.CCW_90;
            case CW_180:    return BlockRotation.CW_180;
            default:
        }

        return this;
    }

    public BlockRotation cycle(boolean reverse)
    {
        int index = (this.index + (reverse ? -1 : 1)) & 3;
        return VALUES[index];
    }

    public net.minecraft.util.BlockRotation getVanillaRotation()
    {
        return this.vanillaRotation;
    }

    public static BlockRotation byName(String name)
    {
        for (BlockRotation rot : VALUES)
        {
            if (rot.name.equalsIgnoreCase(name))
            {
                return rot;
            }
        }

        return NONE;
    }
}
