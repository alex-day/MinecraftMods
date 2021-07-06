package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class BehaviorThrowingKnifeDispense extends BehaviorProjectileDispense {
	
	public int knifeType;
	
	public BehaviorThrowingKnifeDispense(int type) {
		this.knifeType = type;
	}
	
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    @Override
    protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition)
    {
    	EntityThrowingKnife throwingKnife = null;
    	if(this.knifeType == ItemThrowingKnife.TYPE_IRON) {
    		throwingKnife = new EntityThrowingKnifeIron(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ()); 
    	} else
		if(this.knifeType == ItemThrowingKnife.TYPE_DIAMOND) {
    		throwingKnife = new EntityThrowingKnifeDiamond(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ()); 
    	} else
		if(this.knifeType == ItemThrowingKnife.TYPE_QUARTZ_RUBY) {
    		throwingKnife = new EntityThrowingKnifeQuartzRuby(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ()); 
    	} else
		if(this.knifeType == ItemThrowingKnife.TYPE_QUARTZ_AMETHYST) {
    		throwingKnife = new EntityThrowingKnifeQuartzAmethyst(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ()); 
    	}
    	throwingKnife.canBePickedUp = 1;
    	return throwingKnife;
    }

}
