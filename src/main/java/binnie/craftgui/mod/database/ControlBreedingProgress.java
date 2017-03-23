package binnie.craftgui.mod.database;

import binnie.core.genetics.BreedingSystem;
import binnie.craftgui.controls.core.Control;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.Area;
import binnie.craftgui.core.geometry.Point;
import binnie.craftgui.core.renderer.RenderUtil;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.CraftGUITexture;
import binnie.craftgui.resource.minecraft.CraftGUITextureSheet;
import binnie.craftgui.resource.minecraft.StandardTexture;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

class ControlBreedingProgress extends Control {
	private static Texture Progress = new StandardTexture(80, 22, 4, 4, CraftGUITextureSheet.Controls2);
	private float percentage;
	private int colour;

	public ControlBreedingProgress(final IWidget parent, final int x, final int y, final int width, final int height, final BreedingSystem system, final float percentage) {
		super(parent, x, y, width, height);
		this.percentage = percentage;
		this.colour = system.getColour();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRenderBackground(int guiWidth, int guiHeight) {
		CraftGUI.render.texture(CraftGUITexture.PanelBlack, this.getArea());
		final Area area = this.getArea().inset(1);
		area.setSize(new Point(Math.round(area.size().x() * this.percentage), area.size().y()));
		RenderUtil.setColour(this.colour);
		CraftGUI.render.texture(ControlBreedingProgress.Progress, area);
	}
}
