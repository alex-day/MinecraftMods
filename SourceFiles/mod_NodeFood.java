package net.minecraft.src;

import java.util.Random;

public class mod_NodeFood extends BaseMod {
	
	public static final Block blockStrawberry = new BlockStrawberry(171).setUnlocalizedName("blockStrawberry");
	
	public static final Item itemStrawberry = new ItemSeedFood(5302, 1, 0.3F, blockStrawberry.blockID, Block.tilledField.blockID).setUnlocalizedName("itemStrawberry");
	public static final Item itemStrawberryTart = new ItemFood(5303, 8, false).setUnlocalizedName("itemStrawberryTart");
	public static final Item itemHyperStrawberryTart = new ItemFoodPotion(5304, 4, false, true).setUnlocalizedName("itemHyperStrawberryTart");
	public static final Item itemFruitSmoothie = new ItemFoodPotion(5305, 2, false, false).setUnlocalizedName("itemFruitSmoothie");
	
	public static final Item itemDarkChocolate = new ItemFoodPotion(5306, 2, false, false).setUnlocalizedName("itemDarkChocolate");
	public static final Item itemDarkChocolateBar = new ItemFoodPotion(5307, 6, false, true).setUnlocalizedName("itemDarkChocolateBar");
	public static final Item itemMilkChocolate = new ItemFoodPotion(5308, 2, false, false).setUnlocalizedName("itemMilkChocolate");
	public static final Item itemMilkChocolateBar = new ItemFoodPotion(5309, 6, false, true).setUnlocalizedName("itemMilkChocolateBar");
	public static final Item itemWhiteChocolate = new ItemFoodPotion(5310, 2, false, false).setUnlocalizedName("itemWhiteChocolate");
	public static final Item itemWhiteChocolateBar = new ItemFoodPotion(5311, 6, false, true).setUnlocalizedName("itemWhiteChocolateBar");
	
	public static final Item itemChocolateStrawberry = new ItemFoodPotion(5312, 20, false, true).setUnlocalizedName("itemChocolateStrawberry");

	@Override
	public String getVersion() {
		return "Version 1.4.5";
	}

	@Override
	public void load() {
		//fix the rotten flesh
		Item.rottenFlesh.setPotionEffect(PotionHelper.spiderEyeEffect);
		
		ModLoader.registerBlock(blockStrawberry);
		ModLoader.addName(blockStrawberry, "Strawberry Plant");
		ComponentVillageField.cropBlockIDs.add(blockStrawberry.blockID);
		
		ModLoader.addName(itemStrawberry, "Strawberry");
		itemStrawberry.setPotionEffect(PotionHelper.speckledMelonEffect);
		
		ModLoader.addName(itemStrawberryTart, "Strawberry Tart");
		ModLoader.addRecipe(new ItemStack(itemStrawberryTart), new Object[] {
			"XY","Z ", Character.valueOf('X'), itemStrawberry, Character.valueOf('Y'), Item.sugar, Character.valueOf('Z'), Item.egg
		});
		
		ModLoader.addName(itemHyperStrawberryTart, "Strawberry Tart");
		itemHyperStrawberryTart.iconIndex = itemStrawberryTart.iconIndex;
		((ItemFoodPotion)itemHyperStrawberryTart).isActuallyFood = true;
		((ItemFoodPotion)itemHyperStrawberryTart).setAlwaysEdible();
		((ItemFoodPotion)itemHyperStrawberryTart).addPotionEffect(Potion.moveSpeed.id, 30, 1, 1.0f);
		ModLoader.addRecipe(new ItemStack(itemHyperStrawberryTart), new Object[] {
			"YY","XY","Z ", Character.valueOf('X'), itemStrawberry, Character.valueOf('Y'), Item.sugar, Character.valueOf('Z'), Item.egg
		});
		
		ModLoader.addName(itemFruitSmoothie, "Fruit Smoothie");
		((ItemFoodPotion)itemFruitSmoothie).addPotionEffect(Potion.regeneration.id, 8, 0, 1.0f);
		((ItemFoodPotion)itemFruitSmoothie).itemUseDuration = 16;
		((ItemFoodPotion)itemFruitSmoothie).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemFruitSmoothie), new Object[] {
			"Y","X","Z", Character.valueOf('X'), itemStrawberry, Character.valueOf('Y'), Item.melon, Character.valueOf('Z'), Item.glassBottle
		});
		
		ModLoader.addName(itemDarkChocolate, "Dark Chocolate");
		((ItemFoodPotion)itemDarkChocolate).itemUseDuration = 16;
		((ItemFoodPotion)itemDarkChocolate).isActuallyFood = true;
		ModLoader.addRecipe(new ItemStack(itemDarkChocolate, 3), new Object[] {
			"X","X","Z", Character.valueOf('X'), new ItemStack(Item.dyePowder,1,3), Character.valueOf('Z'), Item.bucketMilk
		});
		ModLoader.addName(itemMilkChocolate, "Milk Chocolate");
		((ItemFoodPotion)itemMilkChocolate).itemUseDuration = 16;
		((ItemFoodPotion)itemMilkChocolate).isActuallyFood = true;
		ModLoader.addRecipe(new ItemStack(itemMilkChocolate, 3), new Object[] {
			"Y","X","Z", Character.valueOf('X'), new ItemStack(Item.dyePowder,1,3), Character.valueOf('Y'), Item.sugar, Character.valueOf('Z'), Item.bucketMilk
		});
		ModLoader.addName(itemWhiteChocolate, "White Chocolate");
		((ItemFoodPotion)itemWhiteChocolate).itemUseDuration = 16;
		((ItemFoodPotion)itemWhiteChocolate).isActuallyFood = true;
		ModLoader.addRecipe(new ItemStack(itemWhiteChocolate, 3), new Object[] {
			" Y ","YXY"," Z ", Character.valueOf('X'), new ItemStack(Item.dyePowder,1,3), Character.valueOf('Y'), Item.sugar, Character.valueOf('Z'), Item.bucketMilk
		});
		
		ModLoader.addName(itemDarkChocolateBar, "Dark Chocolate Bar");
		((ItemFoodPotion)itemDarkChocolateBar).addPotionEffect(Potion.damageBoost.id, 60, 0, 1.0f);
		((ItemFoodPotion)itemDarkChocolateBar).itemUseDuration = 48;
		((ItemFoodPotion)itemDarkChocolateBar).isActuallyFood = true;
		((ItemFoodPotion)itemDarkChocolateBar).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemDarkChocolateBar), new Object[] {
			"XX","XX","XX", Character.valueOf('X'), itemDarkChocolate
		});
		ModLoader.addName(itemMilkChocolateBar, "Milk Chocolate Bar");
		((ItemFoodPotion)itemMilkChocolateBar).addPotionEffect(Potion.jump.id, 60, 0, 1.0f);
		((ItemFoodPotion)itemMilkChocolateBar).itemUseDuration = 48;
		((ItemFoodPotion)itemMilkChocolateBar).isActuallyFood = true;
		((ItemFoodPotion)itemMilkChocolateBar).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemMilkChocolateBar), new Object[] {
			"XX","XX","XX", Character.valueOf('X'), itemMilkChocolate
		});
		ModLoader.addName(itemWhiteChocolateBar, "White Chocolate Bar");
		((ItemFoodPotion)itemWhiteChocolateBar).addPotionEffect(Potion.digSpeed.id, 60, 0, 1.0f);
		((ItemFoodPotion)itemWhiteChocolateBar).itemUseDuration = 48;
		((ItemFoodPotion)itemWhiteChocolateBar).isActuallyFood = true;
		((ItemFoodPotion)itemWhiteChocolateBar).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemWhiteChocolateBar), new Object[] {
			"XX","XX","XX", Character.valueOf('X'), itemWhiteChocolate
		});
		
		ModLoader.addName(itemChocolateStrawberry, "Chocolate Covered Strawberry");
		((ItemFoodPotion)itemChocolateStrawberry).addPotionEffect(Potion.moveSpeed.id, 360, 1, 1.0f);
		((ItemFoodPotion)itemChocolateStrawberry).addPotionEffect(Potion.digSpeed.id, 360, 1, 1.0f);
		((ItemFoodPotion)itemChocolateStrawberry).addPotionEffect(Potion.jump.id, 360, 1, 1.0f);
		((ItemFoodPotion)itemChocolateStrawberry).isActuallyFood = true;
		((ItemFoodPotion)itemChocolateStrawberry).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemChocolateStrawberry), new Object[] {
			"XXX","WYW","ZZZ",
			Character.valueOf('X'), itemMilkChocolate,
			Character.valueOf('W'), itemWhiteChocolate,
			Character.valueOf('Z'), itemDarkChocolate,
			Character.valueOf('Y'), itemStrawberry
		});
	}
	
	@Override
	public void generateSurface(World parWorld, Random parRandom, int chunkX, int chunkz) {
		
	}
}
