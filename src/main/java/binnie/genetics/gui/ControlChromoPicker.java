package binnie.genetics.gui;

import binnie.Binnie;
import binnie.craftgui.controls.core.Control;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.ITooltip;
import binnie.craftgui.core.Tooltip;
import binnie.craftgui.core.geometry.Point;
import binnie.craftgui.events.EventValueChanged;
import binnie.craftgui.events.EventWidget;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.extrabees.core.ExtraBeeTexture;
import forestry.api.genetics.IChromosomeType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ControlChromoPicker extends Control implements ITooltip {
	Texture Selected;
	Texture Texture;
	IChromosomeType type;
	ControlChromosome parent;

	public ControlChromoPicker(final ControlChromosome parent, final int x, final int y, final IChromosomeType chromo) {
		super(parent, x, y, 16, 16);
		this.Selected = new StandardTexture(160, 18, 16, 16, ExtraBeeTexture.GUIPunnett);
		this.Texture = new StandardTexture(160, 34, 16, 16, ExtraBeeTexture.GUIPunnett);
		this.type = chromo;
		this.addAttribute(Attribute.MouseOver);
		this.parent = parent;
		this.addSelfEventHandler(new EventWidget.StartMouseOver.Handler() {
			@Override
			public void onEvent(final EventWidget.StartMouseOver event) {
				ControlChromoPicker.this.callEvent(new EventValueChanged<Object>(ControlChromoPicker.this.getWidget(), ControlChromoPicker.this.type));
			}
		});
		this.addSelfEventHandler(new EventWidget.EndMouseOver.Handler() {
			@Override
			public void onEvent(final EventWidget.EndMouseOver event) {
				ControlChromoPicker.this.callEvent(new EventValueChanged<>(ControlChromoPicker.this.getWidget(), null));
			}
		});
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRenderBackground(int guiWidth, int guiHeight) {
		super.onRenderBackground(guiWidth, guiHeight);
		final boolean selected = this.isMouseOver();
		final Texture text = selected ? this.Selected : this.Texture;
		CraftGUI.render.texture(text, Point.ZERO);
	}

	@Override
	public void getTooltip(final Tooltip tooltip) {
		tooltip.add(Binnie.GENETICS.getSystem(this.parent.getRoot()).getChromosomeName(this.type));
	}
}
