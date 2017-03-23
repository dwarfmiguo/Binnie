package binnie.craftgui.mod.database;

import binnie.craftgui.controls.listbox.ControlList;
import binnie.craftgui.controls.listbox.ControlTextOption;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.geometry.CraftGUIUtil;
import binnie.craftgui.core.geometry.Point;
import forestry.api.genetics.IAlleleSpecies;

class ControlSpeciexBoxOption extends ControlTextOption<IAlleleSpecies> {
	private ControlDatabaseIndividualDisplay controlBee;

	public ControlSpeciexBoxOption(final ControlList<IAlleleSpecies> controlList, final IAlleleSpecies option, final int y) {
		super(controlList, option, option.getName(), y);
		this.setSize(new Point(this.getSize().x(), 20));
		(this.controlBee = new ControlDatabaseIndividualDisplay(this, 2, 2)).setSpecies(this.getValue(), EnumDiscoveryState.Undetermined);
		if (this.controlBee.discovered == EnumDiscoveryState.Discovered) {
			this.controlBee.discovered = EnumDiscoveryState.Show;
		}
		this.textWidget.setValue((this.controlBee.discovered == EnumDiscoveryState.Show) ? option.getName() : "Undiscovered");
		if (this.controlBee.discovered == EnumDiscoveryState.Show) {
			this.addAttribute(Attribute.MouseOver);
		}
		CraftGUIUtil.moveWidget(this.textWidget, new Point(22, 0));
		this.textWidget.setSize(this.textWidget.getSize().sub(new Point(24, 0)));
		final int th = CraftGUI.render.textHeight(this.textWidget.getValue(), this.textWidget.getSize().x());
		final int height = Math.max(20, th + 6);
		this.setSize(new Point(this.size().x(), height));
		this.textWidget.setSize(new Point(this.textWidget.getSize().x(), height));
		this.controlBee.setPosition(new Point(this.controlBee.pos().x(), (height - 18) / 2));
	}
}
