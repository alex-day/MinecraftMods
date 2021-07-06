package net.minecraft.src;

public class BehaviorLightPowderDispense extends BehaviorProjectileDispense {

	/**
     * Return the projectile entity spawned by this dispense behavior.
     */
    @Override
    protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition)
    {
    	return new EntityLightPowder(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
    }

}
