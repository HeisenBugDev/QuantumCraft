package quantumcraft.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class Coords {
    public int x;
    public int y;
    public int z;
    public ForgeDirection orientation;

    public Coords(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = ForgeDirection.UNKNOWN;
    }

    public Coords(int x, int y, int z, ForgeDirection corientation) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = corientation;
    }

    public Coords(Coords p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        this.orientation = p.orientation;
    }

    public Coords(NBTTagCompound nbttagcompound) {
        this.x = nbttagcompound.getInteger("i");
        this.y = nbttagcompound.getInteger("j");
        this.z = nbttagcompound.getInteger("k");

        this.orientation = ForgeDirection.UNKNOWN;
    }

    public Coords(TileEntity tile) {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.orientation = ForgeDirection.UNKNOWN;
    }

    public Coords copy() {
        return new Coords(this.x, this.y, this.z, this.orientation);
    }

    /**
     * Saves this Coords to NBT
     *
     * @return An NBTTagCompound containing this Coords's data.
     */
    public NBTTagCompound write() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("x", x);
        nbt.setInteger("y", y);
        nbt.setInteger("z", z);
        return nbt;
    }

    /**
     * Reads a Coords from NBT
     *
     * @param nbt NBTTagCompound containing the Coords's data.
     * @return a Coords object in case of success, null otherwise.
     */
    public static Coords read(NBTTagCompound nbt) {
        Coords Coords = null;
        if (nbt.hasKey("x") && nbt.hasKey("y") && nbt.hasKey("z")) {
            int x = nbt.getInteger("x");
            int y = nbt.getInteger("y");
            int z = nbt.getInteger("z");
            Coords = new Coords(x, y, z);
        }
        return Coords;
    }

    public void moveRight(int step) {
        switch (orientation) {
            case SOUTH:
                this.x -= step;
                break;
            case NORTH:
                this.x += step;
                break;
            case EAST:
                this.z += step;
                break;
            case WEST:
                this.z -= step;
                break;
            default:
                break;
        }
    }

    public void moveLeft(int step) {
        moveRight(-step);
    }

    public void moveForwards(int step) {
        switch (orientation) {
            case UP:
                this.y += step;
                break;
            case DOWN:
                this.y -= step;
                break;
            case SOUTH:
                this.z += step;
                break;
            case NORTH:
                this.z -= step;
                break;
            case EAST:
                this.x += step;
                break;
            case WEST:
                this.x -= step;
                break;
            default:
                break;
        }
    }

    public void moveBackwards(int step) {
        moveForwards(-step);
    }

    public void moveUp(int step) {
        switch (orientation) {
            case SOUTH:
            case NORTH:
            case EAST:
            case WEST:
                this.y += step;
                break;
            default:
                break;
        }
    }

    public void moveDown(int step) {
        moveUp(-step);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setDouble("i", this.x);
        nbttagcompound.setDouble("j", this.y);
        nbttagcompound.setDouble("k", this.z);
    }

    public String toString() {
        if (this.orientation == null) {
            return "{" + this.x + ", " + this.y + ", " + this.z + "}";
        }
        return "{" + this.x + ", " + this.y + ", " + this.z + ";" + this.orientation.toString() + "}";
    }

    public boolean equalsWithOrientation(Object obj) {
        if (!(obj instanceof Coords)) {
            return false;
        }
        Coords bp = (Coords) obj;
        return (bp.x == this.x) && (bp.y == this.y) && (bp.z == this.z) && (bp.orientation == this.orientation);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coords) {
            Coords l = (Coords) o;
            return (x == l.x && y == l.y && z == l.z);
        }
        return false;
    }

    public int getXCoord() {
        return x;
    }

    public void setXCoord(int x) {
        this.x = x;
    }

    public int getYCoord() {
        return y;
    }

    public void setYCoord(int y) {
        this.y = y;
    }

    public int getZCoord() {
        return z;
    }

    public void setZCoord(int z) {
        this.z = z;
    }

    public ForgeDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {
        this.orientation = orientation;
    }

    public boolean compareCoords(Coords source) {
        return source.getXCoord() == getXCoord() && source.getYCoord() == getYCoord() &&
                source.getZCoord() == getZCoord();
    }

    public int hashCode() {
        return this.x & 0xFFF | this.y & 0xFF00 | this.z & 0xFFF000;
    }

    public Coords min(Coords p) {
        return new Coords(p.x > this.x ? this.x : p.x, p.y > this.y ? this.y : p.y, p.z > this.z ? this.z : p.z);
    }

    public Coords max(Coords p) {
        return new Coords(p.x < this.x ? this.x : p.x, p.y < this.y ? this.y : p.y, p.z < this.z ? this.z : p.z);
    }

    public List<Coords> getAdjacent(boolean includeVertical) {
        List<Coords> a = new ArrayList<Coords>();
        a.add(new Coords(this.x + 1, this.y, this.z, ForgeDirection.EAST));
        a.add(new Coords(this.x - 1, this.y, this.z, ForgeDirection.WEST));
        a.add(new Coords(this.x, this.y, this.z + 1, ForgeDirection.SOUTH));
        a.add(new Coords(this.x, this.y, this.z - 1, ForgeDirection.NORTH));
        if (includeVertical) {
            a.add(new Coords(this.x, this.y + 1, this.z, ForgeDirection.UP));
            a.add(new Coords(this.x, this.y - 1, this.z, ForgeDirection.DOWN));
        }
        return a;
    }

    public TileEntity getTileEntity(World world, Class clazz) {
        return BasicUtils.getTileEntity(world, this, clazz);
    }

    public static TileEntity getAdjacentTileEntity(TileEntity start, ForgeDirection direction) {
        Coords p = new Coords(start);
        p.orientation = direction;
        p.moveForwards(1);
        return start.getWorldObj().getTileEntity(p.x, p.y, p.z);
    }

    public static TileEntity getAdjacentTileEntity(TileEntity start, ForgeDirection direction, Class targetClass) {
        TileEntity te = getAdjacentTileEntity(start, direction);
        if (targetClass.isAssignableFrom(te.getClass())) {
            return te;
        }

        return null;
    }
}