package net.minecraft.src;

import java.util.ArrayList;

public class ItemGrantPick extends ItemToolGrant {

   
    protected ItemGrantPick(int par1, EnumGrantToolMaterial par2EnumToolMaterial)
    {
        super(par1, 2, par2EnumToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        if( par1Block == Block.obsidian){
        	return this.toolMaterial.getHarvestLevel() == 3;
        }
        if(par1Block == Block.blockDiamond || par1Block == Block.oreDiamond ){
        	return this.toolMaterial.getHarvestLevel() >= 2;
        }
        	
        if(par1Block == Block.oreEmerald || par1Block == Block.blockEmerald){
        	return this.toolMaterial.getHarvestLevel() >= 2;
        }
        if (par1Block == Block.blockGold || par1Block == Block.oreGold ){
        	return this.toolMaterial.getHarvestLevel() >= 2;
        }	
        if (par1Block == Block.blockSteel || par1Block == Block.oreIron ){
        	return this.toolMaterial.getHarvestLevel() >= 1;
        }	
        if (par1Block == Block.blockLapis || par1Block == Block.oreLapis ){
        	return this.toolMaterial.getHarvestLevel() >= 1;
        }		
        if (par1Block == Block.oreRedstone || par1Block == Block.oreRedstoneGlowing ){
        	return this.toolMaterial.getHarvestLevel() >= 2;
        }
        if (par1Block.blockMaterial == Material.rock){
        	return true;
        }
        if (par1Block.blockMaterial == Material.iron){
        	return true;
        }
        if (par1Block.blockMaterial == Material.anvil){
        	return true;
        
        }
        return false;
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
    }
    
    /** an array of the blocks this pickaxe is effective against */
    private static ArrayList<Block> blocksEffectiveAgainst = new ArrayList<Block>();
    
    static{
    	blocksEffectiveAgainst.add(Block.cobblestone);
    	blocksEffectiveAgainst.add(Block.stoneDoubleSlab);
    	blocksEffectiveAgainst.add( Block.stoneSingleSlab);
    	blocksEffectiveAgainst.add(Block.stone);
    	blocksEffectiveAgainst.add(Block.sandStone);
    	blocksEffectiveAgainst.add(Block.cobblestoneMossy);
    	blocksEffectiveAgainst.add(Block.oreIron);
    	blocksEffectiveAgainst.add(Block.blockSteel);
    	blocksEffectiveAgainst.add(Block.oreCoal);
    	blocksEffectiveAgainst.add(Block.blockGold);
    	blocksEffectiveAgainst.add(Block.oreGold);
    	blocksEffectiveAgainst.add(Block.oreDiamond);
    	blocksEffectiveAgainst.add(Block.blockDiamond);
    	blocksEffectiveAgainst.add(Block.ice);
    	blocksEffectiveAgainst.add(Block.netherrack);
    	blocksEffectiveAgainst.add(Block.oreLapis);
    	blocksEffectiveAgainst.add(Block.blockLapis);
    	blocksEffectiveAgainst.add(Block.oreRedstone);
    	blocksEffectiveAgainst.add(Block.oreRedstoneGlowing);
    	blocksEffectiveAgainst.add(Block.rail);
    	blocksEffectiveAgainst.add(Block.railDetector);
    	blocksEffectiveAgainst.add(Block.railPowered);
    	
    	blocksEffectiveAgainst.add(Block.stairsCobblestone);
    	blocksEffectiveAgainst.add(Block.stairsBrick);
    	blocksEffectiveAgainst.add(Block.stairsNetherBrick);
    	blocksEffectiveAgainst.add(Block.stairsSandStone);
    	blocksEffectiveAgainst.add(Block.stairsStoneBrick);
    	blocksEffectiveAgainst.add(Block.fenceIron);
    	blocksEffectiveAgainst.add(Block.doorSteel);
    	blocksEffectiveAgainst.add(Block.furnaceBurning);
    	blocksEffectiveAgainst.add(Block.furnaceIdle);
    	blocksEffectiveAgainst.add(Block.dispenser);
    	blocksEffectiveAgainst.add(Block.stoneBrick);
    	blocksEffectiveAgainst.add(Block.netherFence);
    	blocksEffectiveAgainst.add(Block.netherBrick);
	
    	blocksEffectiveAgainst.add(mod_Grant.blockQuartz);
    }
}
