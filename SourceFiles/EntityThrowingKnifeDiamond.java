package net.minecraft.src;

public class EntityThrowingKnifeDiamond extends EntityThrowingKnife {
	public EntityThrowingKnifeDiamond(World par1World) {
		super(par1World);
		this.knifeType = ItemThrowingKnife.TYPE_DIAMOND;
		this.setDamage(6.0);
	}
	public EntityThrowingKnifeDiamond(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        this.knifeType = ItemThrowingKnife.TYPE_DIAMOND;
		this.setDamage(4.0);
    }
    
    public EntityThrowingKnifeDiamond(World par1World, EntityLiving par2EntityLiving, float par3)
    {
        super(par1World, par2EntityLiving, par3, ItemThrowingKnife.TYPE_DIAMOND);
		this.setDamage(6.0);
    }
}
