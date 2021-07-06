package net.minecraft.src;

import java.util.ArrayList;

public class ItemFoodPotion extends ItemFood {
	
	private boolean isShiny;
	public boolean isActuallyFood;
	public int itemUseDuration = 32;
	
	public ItemFoodPotion(int par1, int par2, boolean par3, boolean shiny) {
		super(par1, par2, par3);
		this.isShiny = shiny;
	}

	private ArrayList<Integer> potionIds = new ArrayList<Integer>();
	private ArrayList<Integer> potionDurations = new ArrayList<Integer>();
	private ArrayList<Integer> potionAmplifiers = new ArrayList<Integer>();
	private ArrayList<Float> potionEffectProbabilities = new ArrayList<Float>();
	
	 public ItemStack ItemStack(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        if (!isActuallyFood)
        {
            if (par1ItemStack.stackSize <= 0)
            {
                return new ItemStack(Item.glassBottle);
            }
            
            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
        }
        return par1ItemStack;
    }
	
	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote) {
        	for(int i=0; i<potionIds.size(); i++) {
        		int potionId = potionIds.get(i);
        		int potionDuration = potionDurations.get(i);
        		int potionAmplifier = potionAmplifiers.get(i);
        		float potionEffectProbability = potionEffectProbabilities.get(i);
        		
        		if(potionId > 0 && par2World.rand.nextFloat() < potionEffectProbability)
		        {
		            par3EntityPlayer.addPotionEffect(new PotionEffect(potionId, potionDuration * 20, potionAmplifier));
		        }
        	}
        }
    }
	
	/**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return itemUseDuration;
    }
    
	/**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
    	if(isActuallyFood) {
    		return EnumAction.eat;
    	}
        return EnumAction.drink;
    }
	
	/**
     * adds a potion effect on the item. Args: int potionId, int duration (will be multiplied by 20), int amplifier,
     * float probability of effect happening
     */
	@Override
    public ItemFood setPotionEffect(int par1, int par2, int par3, float par4)
    {
        return this.addPotionEffect(par1, par2, par3, par4);
    }
    
    /**
     * adds a potion effect to the item. Args: int potionId, int duration (will be multiplied by 20), int amplifier,
     * float probability of effect happening
     */
    public ItemFood addPotionEffect(int potionId, int duration, int amplifier, float par4)
    {
        this.potionIds.add(potionId);
        this.potionDurations.add(duration);
        this.potionAmplifiers.add(amplifier);
        this.potionEffectProbabilities.add(par4);
        return this;
    }
    
    @Override
    public boolean hasEffect(ItemStack itemstack)
    {
    	if(this.isShiny) {
            return true;
    	}
    	
    	return false;
    }
}
