package binnie.extrabees.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;

import net.minecraftforge.fml.common.registry.GameRegistry;

import binnie.extrabees.genetics.ExtraBeesSpecies;

public class ExtraBeesCommonProxy {

	public void registerBeeModel(ExtraBeesSpecies type) {
	}

	public Block registerBlock(Block block) {
		return GameRegistry.register(block);
	}

	public Item registerItem(Item item) {
		return GameRegistry.register(item);
	}

	@SuppressWarnings("deprecation")
	public String localise(String s) {
		return I18n.translateToLocal("extrabees." + s);
	}
	
	public String localiseWithOutPrefix(String s) {
		return I18n.translateToLocal(s);
	}

	public void registerModel(Item item, int meta) {
	}
}
