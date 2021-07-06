package net.minecraft.src;

import java.util.Random;

public class BlockNetherQuartz extends Block {

	protected BlockNetherQuartz(int par1, int par2) {
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.9f);
		this.setResistance(11.0f);
	}
	
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int parFortuneModifier)
    {
        if (parFortuneModifier > 3)
        {
            parFortuneModifier = 3;
        }

        if(par2Random.nextInt(35 - parFortuneModifier * 10) == 0) {
        	return mod_Alex.itemQuartzAmethyst.itemID;
        }
        
        return par2Random.nextInt(10 - parFortuneModifier * 3) == 0 ? mod_Alex.itemQuartzRuby.itemID : this.blockID;
    }
    
}
