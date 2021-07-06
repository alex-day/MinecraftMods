package net.minecraft.src;

import java.util.ArrayList;

public class BlockStrawberry extends BlockCrops {
	
	public static ArrayList<Icon> strawberryArray = new ArrayList<Icon>();
	
	public BlockStrawberry(int par1)
    {
        super(par1);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	if (par2 < 2)
        {
            return this.blockIcon;
        } else
        if (par2 < 4)
        {
        	return strawberryArray.get(0);
        } else
        if (par2 <= 6)
        {
        	return strawberryArray.get(1);
        } else {
        	return strawberryArray.get(2);
        }
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return mod_NodeFood.itemStrawberry.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
    	return mod_NodeFood.itemStrawberry.itemID;
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 1);
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon("blockStrawberry");
        this.strawberryArray.add(par1IconRegister.registerIcon("blockStrawberry1"));
        this.strawberryArray.add(par1IconRegister.registerIcon("blockStrawberry2"));
        this.strawberryArray.add(par1IconRegister.registerIcon("blockStrawberry3"));
    }
}
