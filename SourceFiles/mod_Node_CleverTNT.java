package net.minecraft.src;

import net.minecraft.src.NodePack.CleverTnt.Node_BlockCleverTNT;
import net.minecraft.src.NodePack.CleverTnt.Node_TileEntityCleverTNT;

public class mod_Node_CleverTNT extends BaseMod {

	@Override
	public String getVersion() {
    	return "3.0 for Minecraft 1.0.0";
	}
    
    @Override
    public String getName() {
    	return "Clever TNT";
    }

	public mod_Node_CleverTNT() {
		
	}
	
	@Override
	public String getPriorities() {
		return "after:mod_Node_PackLoader";
	}
	
	@Override
	public void load() {
		int cleverTNTID = 172;
		if(cleverTNTID > 0) {
			cleverTNT = new Node_BlockCleverTNT(cleverTNTID);
			cleverTNT.setHardness(0.5F);
			cleverTNT.setResistance(1.0F);
			cleverTNT.setStepSound(Block.soundPowderFootstep);
			cleverTNT.setUnlocalizedName("cleverTNT");
			ModLoader.registerBlock(cleverTNT);
			ModLoader.addName(cleverTNT, "Clever TNT");
			ModLoader.registerTileEntity(Node_TileEntityCleverTNT.class, "CleverTNT");
		}
        
		AddRecipes();
	}
	
	public void AddRecipes()
    {
		if(cleverTNT != null) {
			ModLoader.addRecipe(new ItemStack(cleverTNT, 8), new Object[] {
		        "###", "XXX", "###", Character.valueOf('#'), Item.diamond,
		        Character.valueOf('X'), Block.tnt
		    });
		}
    }
	
	public static Block cleverTNT;
	
}
