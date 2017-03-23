package binnie.extratrees.machines.craftgui;

import binnie.core.BinnieCore;
import binnie.craftgui.controls.ControlText;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.Area;
import binnie.craftgui.core.geometry.Point;
import binnie.craftgui.core.geometry.TextJustification;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlItemDisplay;
import binnie.craftgui.mod.database.DatabaseTab;
import binnie.craftgui.mod.database.PageAbstract;
import binnie.craftgui.mod.database.WindowAbstractDatabase;
import binnie.extratrees.ExtraTrees;
import binnie.extratrees.block.DoorType;
import binnie.extratrees.block.IPlankType;
import binnie.extratrees.block.WoodManager;
import binnie.extratrees.block.decor.FenceType;
import net.minecraft.item.ItemStack;

public class PagePlanksOverview extends PageAbstract<ItemStack> {
	public PagePlanksOverview(final IWidget parent, final DatabaseTab tab) {
		super(parent, tab);
	}

	@Override
	public void onValueChanged(final ItemStack species) {
		this.deleteAllChildren();
		final WindowAbstractDatabase database = Window.get(this);
		new ControlText(this, new Area(0, 0, this.size().x(), 24), species.getDisplayName(), TextJustification.MiddleCenter);
		new ControlText(this, new Area(12, 24, this.size().x() - 24, 24), ExtraTrees.proxy.localise("gui.database.planks.use"), TextJustification.MIDDLE_LEFTt);
		final IPlankType type = WoodManager.get(species);
		int x = 12;
		if (type != null) {
			final ItemStack fence = WoodManager.getFence(type, new FenceType(0), 1);
			final ItemStack gate = WoodManager.getGate(type);
			final ItemStack door = WoodManager.getDoor(type, DoorType.Standard);
			if (!fence.isEmpty()) {
				new ControlItemDisplay(this, x, 48).setItemStack(fence);
				x += 22;
			}
			if (!gate.isEmpty()) {
				new ControlItemDisplay(this, x, 48).setItemStack(gate);
				x += 22;
			}
			if (!door.isEmpty()) {
				new ControlItemDisplay(this, x, 48).setItemStack(door);
				x += 22;
			}
		}
		final ControlText controlDescription = new ControlText(this, new Area(8, 84, this.getSize().x() - 16, 0), "", TextJustification.MiddleCenter);
		final ControlText controlSignature = new ControlText(this, new Area(8, 84, this.getSize().x() - 16, 0), "", TextJustification.BottomRight);
		String desc = "";
		if (type != null) {
			desc = type.getDescription();
		}
		String descBody = "§o";
		String descSig = "";
		if (desc == null || desc.length() == 0) {
			descBody += BinnieCore.getBinnieProxy().localise("gui.database.nodescription");
		} else {
			final String[] descStrings = desc.split("\\|");
			descBody += descStrings[0];
			for (int i = 1; i < descStrings.length - 1; ++i) {
				descBody = descBody + " " + descStrings[i];
			}
			if (descStrings.length > 1) {
				descSig += descStrings[descStrings.length - 1];
			}
		}
		controlDescription.setValue(descBody + "§r");
		controlSignature.setValue(descSig + "§r");
		final int descHeight = CraftGUI.render.textHeight(controlDescription.getValue(), controlDescription.getSize().x());
		controlSignature.setPosition(new Point(controlSignature.pos().x(), controlDescription.getPosition().y() + descHeight + 10));
	}
}
