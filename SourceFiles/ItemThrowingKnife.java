package net.minecraft.src;

public class ItemThrowingKnife extends Item {

	public static final int TYPE_IRON = 0;
	public static final int TYPE_DIAMOND = 1;
	public static final int TYPE_QUARTZ_RUBY = 2;
	public static final int TYPE_QUARTZ_AMETHYST = 3;
	
	private int knifeType;
	
	protected ItemThrowingKnife(int par1, int type) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.knifeType = type;
	}
	
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.25F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.1F));

        if (!par2World.isRemote)
        {
        	EntityThrowingKnife throwingKnife = null;
        	if(this.knifeType == TYPE_IRON) {
        		throwingKnife = new EntityThrowingKnifeIron(par2World, par3EntityPlayer, 2.0F);
        	} else
        	if(this.knifeType == TYPE_DIAMOND) {
        		throwingKnife = new EntityThrowingKnifeDiamond(par2World, par3EntityPlayer, 2.0F);
        	} else
    		if(this.knifeType == TYPE_QUARTZ_RUBY) {
    			throwingKnife = new EntityThrowingKnifeQuartzRuby(par2World, par3EntityPlayer, 2.0F);
        	} else
    		if(this.knifeType == TYPE_QUARTZ_AMETHYST) {
    			throwingKnife = new EntityThrowingKnifeQuartzAmethyst(par2World, par3EntityPlayer, 2.0F);
        	}
            par2World.spawnEntityInWorld(throwingKnife);
        }
        
        return par1ItemStack;
    }
}
