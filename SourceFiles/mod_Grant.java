package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class mod_Grant extends BaseMod {

	
	public static final Block blockQuartz = new BlockMilkyQuartz(180).setUnlocalizedName("blockQuartz");
	public static final Block blockTomato = new BlockTomato (181).setUnlocalizedName("blockTomato");
	
	public static final Item itemRefinedQuartz = new Item (5210).setUnlocalizedName("itemRefinedQuartz").setCreativeTab(CreativeTabs.tabMaterials);
	
	public static final Item itemQuartzMilk = new ItemQuartzMilk (5200).setUnlocalizedName("itemQuartzMilk");
	public static final Item itemQuartzMilkPick = new ItemGrantPick (5201, EnumGrantToolMaterial.QUARTZ_MILK).setUnlocalizedName("itemQuartzMilkPick");
	public static final Item itemQuartzMilkShovel = new ItemGantSpade (5202, EnumGrantToolMaterial.QUARTZ_MILK).setUnlocalizedName("itemQuartzMilkShovel");
	public static final Item itemQuartzMilkAxe = new ItemGantAxe (5203, EnumGrantToolMaterial.QUARTZ_MILK).setUnlocalizedName("itemQuartzMilkAxe");
	public static final Item itemQuartzMilkHoe = new ItemGantHoe (5204, EnumGrantToolMaterial.QUARTZ_MILK).setUnlocalizedName("itemQuartzMilkHoe");
	public static final Item itemQuartzMilkSword = new ItemGrantSword (5205, EnumGrantToolMaterial.QUARTZ_MILK).setUnlocalizedName("itemQuartzMilkSword");
	public static final int armorIndexQuartz = ModLoader.addArmor("Quartz");
	public static final Item itemQuartzHelmet = new ItemGrantArmor (5206, EnumGrantArmorMaterial.QUARTZ,armorIndexQuartz, 0).setUnlocalizedName("itemQuartzHelmet");
	public static final Item itemQuartzChest = new ItemGrantArmor (5207, EnumGrantArmorMaterial.QUARTZ,armorIndexQuartz, 1).setUnlocalizedName("itemQuartzChest");
	public static final Item itemQuartzLeggings = new ItemGrantArmor (5208, EnumGrantArmorMaterial.QUARTZ,armorIndexQuartz, 2).setUnlocalizedName("itemQuartzLeggings");
	public static final Item itemQuartzBoots = new ItemGrantArmor (5209, EnumGrantArmorMaterial.QUARTZ,armorIndexQuartz, 3).setUnlocalizedName("itemQuartzBoots");
	public static final Item itemTomato = new ItemSeedFood(5211, 1, 0.3F, blockTomato.blockID, Block.tilledField.blockID).setUnlocalizedName("itemTomato");
	
	
	public static final Item itemPizzaBase = new ItemFood (5212, 6, false).setUnlocalizedName("itemPizzaBase");
	public static final Item itemPizzaMargherita = new ItemFoodPotion(5213, 8, false, true).setUnlocalizedName("itemPizzaMargherita");
	public static final Item itemPizzaGroundBeef = new ItemFoodPotion(5214, 10, false, true).setUnlocalizedName("itemPizzaGroundBeef");
	public static final Item itemPizzaBBQChicken = new ItemFoodPotion(5215, 10, false, true).setUnlocalizedName("itemPizzaBBQChicken");
	
	public static final Item itemQuartzShears = new ItemGrantShears (5216, EnumGrantToolMaterial.QUARTZ_MILK).setUnlocalizedName("itemQuartzShears");
	
	
    public static ArrayList<Integer> shearsItemIDs = new ArrayList<Integer>();
    static {
    	shearsItemIDs.add(Item.shears.itemID);
    	shearsItemIDs.add(itemQuartzShears.itemID);
    }
	
	@Override
	public String getVersion() {
		
		return "version1.4.5";
	}

	

	@Override
	public void load() {
		ModLoader.registerBlock(blockQuartz);
		ModLoader.addName(blockQuartz, "Quartz");
		
		ModLoader.registerBlock(blockTomato);
		ModLoader.addName(blockTomato, "Tomato Plant");
		ComponentVillageField.cropBlockIDs.add(blockTomato.blockID);
		
		ModLoader.addName(itemQuartzMilk, "Milky Quartz");
		
		ModLoader.addName(itemRefinedQuartz, "Refined Milky Quartz");
		ModLoader.addSmelting(blockQuartz.blockID, new ItemStack(itemRefinedQuartz), 1.0F);
		
		ModLoader.addName(itemQuartzMilkPick, "Milky Quartz Pickaxe");
		ModLoader.addRecipe(new ItemStack(itemQuartzMilkPick), new Object[] {
			"XXX"," y "," y ",Character.valueOf('X'),itemQuartzMilk, Character.valueOf('y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzMilkShovel, "Milky Quartz Shovel");
		ModLoader.addRecipe(new ItemStack(itemQuartzMilkShovel), new Object[] {
			"X","y","y",Character.valueOf('X'),itemQuartzMilk, Character.valueOf('y'), Item.stick
		});
		
		
		ModLoader.addName(itemQuartzMilkAxe, "Milky Quartz Axe");
		ModLoader.addRecipe(new ItemStack(itemQuartzMilkAxe), new Object[] {
			"XX","Xy"," y",Character.valueOf('X'),itemQuartzMilk, Character.valueOf('y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzMilkHoe, "Milky Quartz Hoe");
		ModLoader.addRecipe(new ItemStack(itemQuartzMilkHoe), new Object[] {
			"XX"," y"," y",Character.valueOf('X'),itemQuartzMilk, Character.valueOf('y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzMilkSword, "Milky Quartz Sword");
		ModLoader.addRecipe(new ItemStack(itemQuartzMilkSword), new Object[] {
			"X","X","y",Character.valueOf('X'),itemQuartzMilk, Character.valueOf('y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzHelmet, "Quartz Helmet");
		ModLoader.addRecipe(new ItemStack(itemQuartzHelmet), new Object[] {
			"XXX","X X",Character.valueOf('X'),itemRefinedQuartz
		});
		
		ModLoader.addName(itemQuartzChest, "Quartz Chestplate");
		ModLoader.addRecipe(new ItemStack(itemQuartzChest), new Object[] {
			"X X","XXX","XXX",Character.valueOf('X'),itemRefinedQuartz
		});
		
		ModLoader.addName(itemQuartzLeggings, "Quartz Leggings");
		ModLoader.addRecipe(new ItemStack(itemQuartzLeggings), new Object[] {
			"XXX","X X","X X",Character.valueOf('X'),itemRefinedQuartz
		});
		
		ModLoader.addName(itemQuartzBoots, "Quartz Boots");
		ModLoader.addRecipe(new ItemStack(itemQuartzBoots), new Object[] {
			"X X","X X",Character.valueOf('X'),itemRefinedQuartz
		});
	
		ModLoader.addName(itemTomato, "Tomato");
		itemTomato.setPotionEffect(PotionHelper.fermentedSpiderEyeEffect);
	
		ModLoader.addName(itemPizzaBase, "Pizza Base");
		ModLoader.addRecipe(new ItemStack(itemPizzaBase), new Object[] {
			"XXX","YYY", Character.valueOf('X'), itemTomato, Character.valueOf('Y'), Item.wheat
		});
	
		
		ModLoader.addName(itemPizzaMargherita, "Margherita Pizza");
		((ItemFoodPotion)itemPizzaMargherita).addPotionEffect(Potion.nightVision.id, 120, 0, 1.0f);
		((ItemFoodPotion)itemPizzaMargherita).isActuallyFood = true;
		((ItemFoodPotion)itemPizzaMargherita).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemPizzaMargherita), new Object[] {
			"X","Y", Character.valueOf('X'), itemTomato, Character.valueOf('Y'), itemPizzaBase
		});
		
		ModLoader.addName(itemPizzaGroundBeef, "Ground Beef Pizza");
		((ItemFoodPotion)itemPizzaGroundBeef).addPotionEffect(Potion.digSpeed.id, 120, 1, 1.0f);
		((ItemFoodPotion)itemPizzaGroundBeef).isActuallyFood = true;
		((ItemFoodPotion)itemPizzaGroundBeef).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemPizzaGroundBeef), new Object[] {
			"X","Y", Character.valueOf('X'), Item.beefCooked, Character.valueOf('Y'), itemPizzaBase
		});
		
		ModLoader.addName(itemPizzaBBQChicken, "BBQ Chicken Pizza");
		((ItemFoodPotion)itemPizzaBBQChicken).addPotionEffect(Potion.waterBreathing.id, 120, 0, 1.0f);
		((ItemFoodPotion)itemPizzaBBQChicken).isActuallyFood = true;
		((ItemFoodPotion)itemPizzaBBQChicken).setAlwaysEdible();
		ModLoader.addRecipe(new ItemStack(itemPizzaBBQChicken), new Object[] {
			"X","Y", Character.valueOf('X'), Item.chickenCooked, Character.valueOf('Y'), itemPizzaBase
		});
		
		ModLoader.addName(itemQuartzShears, "Milky Quartz Shears");
		ModLoader.addRecipe(new ItemStack(itemQuartzShears), new Object[] {
			" X","X ", Character.valueOf('X'), itemQuartzMilk
		});
		
	}

	
	
	@Override
	public void generateSurface(World parWorld, Random parRandom, int parChunkX, int parChunkY) {  
		useWorldGenerator(parChunkX, parChunkY, parWorld, parRandom, 12, new WorldGenMinable(blockQuartz.blockID, 12), 20, 85);
	}
	protected void useWorldGenerator(int x, int z, World world, Random random, int i, WorldGenMinable worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = x + random.nextInt(16);
            int j1 = random.nextInt(k - j) + j;
            int k1 = z + random.nextInt(16);
            worldgenerator.generate(world, random, i1, j1, k1);
        }

    }

	
}
