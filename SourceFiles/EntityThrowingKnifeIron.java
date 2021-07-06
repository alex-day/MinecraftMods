package net.minecraft.src;

public class EntityThrowingKnifeIron extends EntityThrowingKnife {

	public EntityThrowingKnifeIron(World par1World) {
		super(par1World);
		this.knifeType = ItemThrowingKnife.TYPE_IRON;
		this.setDamage(2.0);
	}
	public EntityThrowingKnifeIron(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        this.knifeType = ItemThrowingKnife.TYPE_IRON;
		this.setDamage(1.25);
    }
    
    public EntityThrowingKnifeIron(World par1World, EntityLiving par2EntityLiving, float par3)
    {
        super(par1World, par2EntityLiving, par3, ItemThrowingKnife.TYPE_IRON);
		this.setDamage(2.0);
    }
}
