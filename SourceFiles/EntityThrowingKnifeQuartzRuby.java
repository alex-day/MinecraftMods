package net.minecraft.src;

public class EntityThrowingKnifeQuartzRuby extends EntityThrowingKnife {
	public EntityThrowingKnifeQuartzRuby(World par1World) {
		super(par1World);
		this.knifeType = ItemThrowingKnife.TYPE_QUARTZ_RUBY;
		this.setDamage(3.0);
	}
	public EntityThrowingKnifeQuartzRuby(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        this.knifeType = ItemThrowingKnife.TYPE_QUARTZ_RUBY;
		this.setDamage(1.75);
    }
    
    public EntityThrowingKnifeQuartzRuby(World par1World, EntityLiving par2EntityLiving, float par3)
    {
        super(par1World, par2EntityLiving, par3, ItemThrowingKnife.TYPE_QUARTZ_RUBY);
		this.setDamage(3.0);
    }
}
