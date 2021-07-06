package net.minecraft.src;

public class ItemWiFi extends ItemBlock {
	
	private String[] blockNames;
	
	public ItemWiFi(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return mod_Alex.blockWiFi.getBlockTextureFromSideAndMetadata(2, BlockWiFi.getBlockFromDye(par1));
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    /**
     * Sets the array of strings to be used for name lookups from item damage to metadata
     */
    public ItemWiFi setBlockNames(String[] par1ArrayOfStr)
    {
        this.blockNames = par1ArrayOfStr;
        return this;
    }
    
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
    	return super.getUnlocalizedName() + "." + BlockWiFi.wifiNames[BlockWiFi.getBlockFromDye(par1ItemStack.getItemDamage())];
    }
}
