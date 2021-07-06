package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class TileEntityTotem extends TileEntity {

	//private int effectIndex;
	
	/**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
    	if(worldObj.isBlockIndirectlyGettingPowered(xCoord,yCoord,zCoord) || worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord + 1, zCoord)){
	        if (this.worldObj.getTotalWorldTime() % 200L == 0L)
	        {
	            this.applyEffects(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	        }
    	}
    }
    
    public void applyEffects(World world, int x, int y, int z)
    {
    	boolean isUlt = this.isUltimate(world, x, y, z);
    	int range = 7;
    	if(isUlt) {
    		range = 14;
    	}
    	
    	AxisAlignedBB bounds = AxisAlignedBB.getAABBPool().getAABB(
    			(double)x, (double)y, (double)z,
    			(double)(x + 1), (double)(y + 1), (double)(z + 1))
    			.expand(range, range, range);
        List entityList = world.getEntitiesWithinAABB(EntityPlayer.class, bounds);
        
        Iterator entityIterator = entityList.iterator();
        EntityPlayer player;
        
        int effectIndex = this.getEffectIndex(world, x, y, z);
        int duration = 300;
        int amplifier = 0;
        if(isUlt) {
        	duration = 600;
        	amplifier = 1;
        }
        
        while (entityIterator.hasNext())
        {
        	//System.out.println("APPLYING EFFECT - "+isUlt+" "+effectIndex+" "+duration+" "+amplifier);
        	player = (EntityPlayer)entityIterator.next();
        	player.addPotionEffect(new PotionEffect(BlockWiFi.effects[effectIndex], duration * 20, amplifier, true));
        }
    }
    
    private boolean isUltimate(World world, int x, int y, int z) {
    	return world.getBlockMetadata(x, y, z) > 7;
    }
    private int getEffectIndex(World world, int x, int y, int z) {
    	return world.getBlockMetadata(x, y, z) & 7;
    }
    
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        //this.effectIndex = par1NBTTagCompound.getInteger("effectIndex");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        //par1NBTTagCompound.setInteger("effectIndex", this.effectIndex);
    }
}
