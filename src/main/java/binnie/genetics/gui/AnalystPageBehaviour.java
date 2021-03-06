package binnie.genetics.gui;

import net.minecraft.util.math.Vec3i;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IBee;
import forestry.api.genetics.IIndividual;
import forestry.api.lepidopterology.EnumButterflyChromosome;
import forestry.api.lepidopterology.IAlleleButterflyEffect;
import forestry.api.lepidopterology.IButterfly;
import forestry.apiculture.PluginApiculture;

import binnie.Binnie;
import binnie.core.craftgui.CraftGUI;
import binnie.core.craftgui.IWidget;
import binnie.core.craftgui.controls.ControlText;
import binnie.core.craftgui.controls.ControlTextCentered;
import binnie.core.craftgui.geometry.Area;
import binnie.core.craftgui.geometry.TextJustification;
import binnie.core.util.I18N;

//TODO:localise
@SideOnly(Side.CLIENT)
public class AnalystPageBehaviour extends ControlAnalystPage {
	public AnalystPageBehaviour(final IWidget parent, final Area area, final IIndividual ind) {
		super(parent, area);
		this.setColour(6684723);
		int y = 4;
		new ControlTextCentered(this, y, "§nBehaviour").setColour(this.getColour());
		y += 12;
		if (ind instanceof IBee) {
			final IBee bee = (IBee) ind;
			y += 8;
			final int fertility = bee.getGenome().getFlowering();
			new ControlTextCentered(this, y, "Pollinates nearby\n" + bee.getGenome().getFlowerProvider().getDescription()).setColour(this.getColour());
			y += 20;
			new ControlTextCentered(this, y, "§oEvery " + this.getTimeString(PluginApiculture.ticksPerBeeWorkCycle * 100 / fertility)).setColour(this.getColour());
			y += 22;
			final IAlleleBeeEffect effect = bee.getGenome().getEffect();
			final Vec3i t = bee.getGenome().getTerritory();
			if (!effect.getUID().contains("None")) {
				final String effectDesc = I18N.localiseOrBlank("binniecore.allele." + effect.getUID() + ".desc");
				final String loc = effectDesc.equals("") ? ("Effect: " + effect.getName()) : effectDesc;
				new ControlText(this, new Area(4, y, this.width() - 8, 0), loc, TextJustification.TOP_CENTER).setColour(this.getColour());
				y += CraftGUI.render.textHeight(loc, this.width() - 8) + 1;
				new ControlTextCentered(this, y, "§oWithin " + t.getX() / 2 + " blocks").setColour(this.getColour());
				y += 22;
			}
			new ControlTextCentered(this, y, "Territory: §o" + t.getX() + "x" + t.getY() + "x" + t.getZ()).setColour(this.getColour());
			y += 22;
		}
		if (ind instanceof IButterfly) {
			final IButterfly bee2 = (IButterfly) ind;
			new ControlTextCentered(this, y, "§oMetabolism: " + Binnie.GENETICS.mothBreedingSystem.getAlleleName(EnumButterflyChromosome.METABOLISM, ind.getGenome().getActiveAllele(EnumButterflyChromosome.METABOLISM))).setColour(this.getColour());
			y += 20;
			new ControlTextCentered(this, y, "Pollinates nearby\n" + bee2.getGenome().getFlowerProvider().getDescription()).setColour(this.getColour());
			y += 20;
			new ControlTextCentered(this, y, "§oEvery " + this.getTimeString(1500)).setColour(this.getColour());
			y += 22;
			final IAlleleButterflyEffect effect2 = bee2.getGenome().getEffect();
			if (!effect2.getUID().contains("None")) {
				final String effectDesc2 = I18N.localiseOrBlank("binniecore.allele." + effect2.getUID() + ".desc");
				final String loc2 = effectDesc2.equals("") ? ("Effect: " + effect2.getName()) : effectDesc2;
				new ControlText(this, new Area(4, y, this.width() - 8, 0), loc2, TextJustification.TOP_CENTER).setColour(this.getColour());
				y += CraftGUI.render.textHeight(loc2, this.width() - 8) + 1;
				y += 22;
			}
		}
	}

	@Override
	public String getTitle() {
		return "Behaviour";
	}
}
