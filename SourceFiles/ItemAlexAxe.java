package net.minecraft.src;

import java.util.ArrayList;

public class ItemAlexAxe extends ItemToolAlex {
	
    protected ItemAlexAxe(int par1, EnumAlexToolMaterial par2EnumToolMaterial)
    {
        super(par1, 3, par2EnumToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
    }
    
    /** an array of the blocks this axe is effective against */
    private static ArrayList<Block> blocksEffectiveAgainst = new ArrayList<Block>();
    
    static {
    	blocksEffectiveAgainst.add(Block.planks);
    	blocksEffectiveAgainst.add(Block.bookShelf);
    	blocksEffectiveAgainst.add(Block.wood);
    	blocksEffectiveAgainst.add(Block.chest);
    	blocksEffectiveAgainst.add(Block.woodDoubleSlab);
    	blocksEffectiveAgainst.add(Block.woodSingleSlab);
    	blocksEffectiveAgainst.add(Block.pumpkin);
    	blocksEffectiveAgainst.add(Block.pumpkinLantern);
    	
    	blocksEffectiveAgainst.add(Block.stairsWoodOak);
    	blocksEffectiveAgainst.add(Block.stairsWoodBirch);
    	blocksEffectiveAgainst.add(Block.stairsWoodJungle);
    	blocksEffectiveAgainst.add(Block.stairsWoodSpruce);
    	blocksEffectiveAgainst.add(Block.doorWood);
    }
    
}
