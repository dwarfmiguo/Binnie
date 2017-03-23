package binnie.botany.craftgui;

import binnie.botany.api.IColourMix;
import binnie.craftgui.controls.core.Control;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.ITooltip;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.Tooltip;
import binnie.craftgui.core.geometry.Point;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.CraftGUITextureSheet;
import binnie.craftgui.resource.minecraft.StandardTexture;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ControlColourMixSymbol extends Control implements ITooltip {
	static Texture MutationPlus = new StandardTexture(2, 94, 16, 16, CraftGUITextureSheet.Controls2);
	static Texture MutationArrow = new StandardTexture(20, 94, 32, 16, CraftGUITextureSheet.Controls2);
	IColourMix value;
	int type;

	@Override
	@SideOnly(Side.CLIENT)
	public void onRenderBackground(int guiWidth, int guiHeight) {
		super.onRenderBackground(guiWidth, guiHeight);
		if (this.type == 0) {
			CraftGUI.render.texture(ControlColourMixSymbol.MutationPlus, Point.ZERO);
		} else {
			CraftGUI.render.texture(ControlColourMixSymbol.MutationArrow, Point.ZERO);
		}
	}

	protected ControlColourMixSymbol(final IWidget parent, final int x, final int y, final int type, final IColourMix value) {
		super(parent, x, y, 16 + type * 16, 16);
		this.value = value;
		this.type = type;
		this.addAttribute(Attribute.MouseOver);
	}

	public void setValue(final IColourMix value) {
		this.value = value;
		this.setColour(16777215);
	}

	@Override
	public void getTooltip(final Tooltip tooltip) {
		if (this.type == 1) {
			final float chance = this.value.getChance();
			tooltip.add("Current Chance - " + chance + "%");
		}
	}

}
