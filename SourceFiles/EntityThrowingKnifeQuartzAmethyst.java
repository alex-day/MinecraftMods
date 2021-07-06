package net.minecraft.src;

public class EntityThrowingKnifeQuartzAmethyst extends EntityThrowingKnife {
	public EntityThrowingKnifeQuartzAmethyst(World par1World) {
		super(par1World);
		this.knifeType = ItemThrowingKnife.TYPE_QUARTZ_AMETHYST;
		this.setDamage(8.0);
	}
	public EntityThrowingKnifeQuartzAmethyst(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        this.knifeType = ItemThrowingKnife.TYPE_QUARTZ_AMETHYST;
		this.setDamage(6.0);
    }
    
    public EntityThrowingKnifeQuartzAmethyst(World par1World, EntityLiving par2EntityLiving, float par3)
    {
        super(par1World, par2EntityLiving, par3, ItemThrowingKnife.TYPE_QUARTZ_AMETHYST);
		this.setDamage(8.0);
    }
}
