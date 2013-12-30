package quantumcraft.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.Coords;
import quantumcraft.util.QInterdimensionalGeneratorDataObject;
import quantumcraft.util.QInterdimensionalGeneratorUtil;

public class TileQInterdimensionalGenerator extends TileEnergySource {

    private int outputRate = 0;
    private int generatorsInChunk = 0;

    @Override
    public int getMaxEnergy() {
        return 300;
    }

    @Override
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {
        System.out.println("onblockBreak");
        QInterdimensionalGeneratorUtil.removeGeneratorFromNewCoords(
                new QInterdimensionalGeneratorDataObject(new Coords(xCoord, yCoord, zCoord)), worldObj);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (generatorsInChunk == 0) return;
        if (worldObj.getWorldTime() % 20 == 0) {
            this.addEnergy((int) Math.floor(10 / generatorsInChunk));
            outputRate = (int) Math.floor(10 / generatorsInChunk);
        }
    }

    public void onQIGChange() {
        int xChunk = BasicUtils.getChunk(xCoord);
        int zChunk = BasicUtils.getChunk(zCoord);

        generatorsInChunk = 0;

        for (QInterdimensionalGeneratorDataObject generator : QInterdimensionalGeneratorUtil.getGenerators()) {
            if (generator == null) return;
            int localXChunk = BasicUtils.getChunk(generator.getCoords().x);
            int localZChunk = BasicUtils.getChunk(generator.getCoords().z);
            if (xChunk == localXChunk && zChunk == localZChunk) generatorsInChunk++;
        }
    }

    @Override
    public String getStatusText() {
        EnumChatFormatting color = outputRate > 5 ? EnumChatFormatting.GREEN : EnumChatFormatting.YELLOW;
        return color + "" + outputRate + " QEU/t";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        QInterdimensionalGeneratorUtil
                .addGenerator(new QInterdimensionalGeneratorDataObject(new Coords(xCoord, yCoord, zCoord)));
        QInterdimensionalGeneratorUtil.updateAllGenerators(worldObj);
    }
}
