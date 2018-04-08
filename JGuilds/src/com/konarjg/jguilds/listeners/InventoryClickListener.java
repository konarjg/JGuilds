package com.konarjg.jguilds.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.minecraft.server.v1_7_R1.Material;

public class InventoryClickListener implements Listener {
	@EventHandler
	public void OnClick (InventoryClickEvent e) {
		if(e.getInventory() == null) return;
		if (e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType().equals(Material.AIR)) return;
		if(!e.getCurrentItem().hasItemMeta()) return;
		if (e.getInventory().getTitle() != null && e.getInventory().getTitle().equals("§a§lItemy na gildie")) {
			e.setCancelled(true);
			e.getWhoClicked().openInventory(e.getInventory());
		}
	}
}
