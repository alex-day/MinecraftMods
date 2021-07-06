package net.minecraft.src;

import java.util.ArrayList;

public class BlockTomato extends BlockCrops {
	
	public static ArrayList<Icon> tomatoArray = new ArrayList<Icon>();
	
	public BlockTomato(int par1)
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
        	return tomatoArray.get(0);
        } else
        if (par2 <= 6)
        {
        	return tomatoArray.get(1);
        } else {
        	return tomatoArray.get(2);
        }
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return mod_Grant.itemTomato.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
    	return mod_Grant.itemTomato.itemID;
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 1);
    }
	public int getRenderType()
	{
		return 1;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon("blockTomato");
        this.tomatoArray.add(par1IconRegister.registerIcon("blockTomato1"));
        this.tomatoArray.add(par1IconRegister.registerIcon("blockTomato2"));
        this.tomatoArray.add(par1IconRegister.registerIcon("blockTomato3"));
    }
}
