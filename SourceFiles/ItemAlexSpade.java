package net.minecraft.src;

import java.util.ArrayList;

public class ItemAlexSpade extends ItemToolAlex {
	
    public ItemAlexSpade(int par1, EnumAlexToolMaterial par2EnumToolMaterial)
    {
        super(par1, 1, par2EnumToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }
    
    /** an array of the blocks this spade is effective against */
    private static ArrayList<Block> blocksEffectiveAgainst = new ArrayList<Block>();
    
    static {
    	blocksEffectiveAgainst.add(Block.grass);
    	blocksEffectiveAgainst.add(Block.dirt);
    	blocksEffectiveAgainst.add(Block.sand);
    	blocksEffectiveAgainst.add(Block.gravel);
    	blocksEffectiveAgainst.add(Block.snow);
    	blocksEffectiveAgainst.add(Block.blockSnow);
    	blocksEffectiveAgainst.add(Block.blockClay);
    	blocksEffectiveAgainst.add(Block.tilledField);
    	blocksEffectiveAgainst.add(Block.slowSand);
    	blocksEffectiveAgainst.add(Block.mycelium);
    }
}
