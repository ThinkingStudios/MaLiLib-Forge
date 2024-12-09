package fi.dy.masa.malilib.util.position;

import javax.annotation.Nullable;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.util.math.Direction;

import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.util.StringUtils;

/**
 * Post-ReWrite code
 */
@ApiStatus.Experimental
public enum BlockMirror
{
    NONE (0, "none", null, net.minecraft.util.BlockMirror.NONE),
    X    (1, "x", Direction.Axis.X, net.minecraft.util.BlockMirror.FRONT_BACK),
    Y    (2, "y", Direction.Axis.Y, net.minecraft.util.BlockMirror.NONE),
    Z    (3, "z", Direction.Axis.Z, net.minecraft.util.BlockMirror.LEFT_RIGHT);

    public static final BlockMirror[] VALUES = values();

    private final int index;
    private final String name;
    private final String translationKey;
    private final net.minecraft.util.BlockMirror vanillaMirror;
    @Nullable private final Direction.Axis axis;

    BlockMirror(int index, String name, @Nullable Direction.Axis axis, net.minecraft.util.BlockMirror vanillaMirror)
    {
        this.index = index;
        this.name = name;
        this.vanillaMirror = vanillaMirror;
        this.translationKey = MaLiLibReference.MOD_ID + ".label.block_mirror." + name;
        this.axis = axis;
    }

    public int getIndex()
    {
        return this.index;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDisplayName()
    {
        return StringUtils.translate(this.translationKey);
    }

    /**
     * Determines the rotation that is equivalent to this mirror if the rotating object faces in the given direction
     */
    public BlockRotation toRotation(Direction direction)
    {
        if (direction.getAxis() == this.axis)
        {
            return BlockRotation.CW_180;
        }

        return BlockRotation.NONE;
    }

    /**
     * Mirror the given direction according to this mirror
     */
    public Direction mirror(Direction direction)
    {
        if (direction.getAxis() == this.axis)
        {
            return direction.getOpposite();
        }

        return direction;
    }

    public BlockMirror cycle(boolean reverse)
    {
        int index = (this.index + (reverse ? -1 : 1)) & 3;
        return VALUES[index];
    }

    public net.minecraft.util.BlockMirror getVanillaMirror()
    {
        return this.vanillaMirror;
    }

    public static BlockMirror byName(String name)
    {
        for (BlockMirror mirror : VALUES)
        {
            if (mirror.name.equalsIgnoreCase(name))
            {
                return mirror;
            }
        }

        return NONE;
    }
}
