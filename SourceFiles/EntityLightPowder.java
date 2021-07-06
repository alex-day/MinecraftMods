package net.minecraft.src;

public class EntityLightPowder extends EntityThrowable {
	
	public EntityLightPowder(World par1World)
    {
        super(par1World);
    }

    public EntityLightPowder(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityLightPowder(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
	
	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		
		if (par1MovingObjectPosition.entityHit == null) {
			if (!this.worldObj.isRemote)
	        {
				int placePosX = par1MovingObjectPosition.blockX;
				int placePosY = par1MovingObjectPosition.blockY;
				int placePosZ = par1MovingObjectPosition.blockZ;
				
				int sideHit = par1MovingObjectPosition.sideHit;
				switch(sideHit) {
				case 0:
					placePosY--;
					break;
				case 1:
					placePosY++;
					sideHit = 5;
					break;
				case 2:
					placePosZ--;
					sideHit = 4;
					break;
				case 3:
					placePosZ++;
					break;
				case 4:
					placePosX--;
					sideHit = 2;
					break;
				case 5:
					placePosX++;
					sideHit = 1;
					break;
				}
				
				if(this.worldObj.isAirBlock(placePosX, placePosY, placePosZ) && mod_Alex.blockPoswderLight.canPlaceBlockAt(worldObj, placePosX, placePosY, placePosZ)) {
					this.worldObj.setBlock(placePosX, placePosY, placePosZ, mod_Alex.blockPoswderLight.blockID, sideHit, 2);
				} else {
					//spawn an item stack because it clearly didn't work
					ItemStack itemStack = new ItemStack(mod_Alex.itemLightPowder, 1);
                    EntityItem entityItem = new EntityItem(worldObj, this.posX, this.posY, this.posZ, itemStack);
                    this.worldObj.spawnEntityInWorld(entityItem);
				}
				
				this.setDead();
	        }
		}
	}

}
