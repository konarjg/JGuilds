package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.konarjg.jguilds.data.Settings;

public class CmdItemy {
	public void Itemy (CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage("§cPoprawne uzycie: /g itemy");
			return;
		}
		Player p = (Player) sender;
		showItems(p);
	}
	
	void showItems(Player p) {
		Settings set = Settings.getInst();
		int i = 0;
		Inventory inv = Bukkit.createInventory(null, 9, "§a§lItemy na gildie");
		if(p.hasPermission("jguilds.vip")) {
			for (String s : set.itemsVip) {
				String[] ss = s.split(" ");
				ItemStack is = new ItemStack(Material.getMaterial(ss[0].toUpperCase()), Integer.parseInt(ss[1]), Short.parseShort(ss[2]));
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(" ");
				is.setItemMeta(im);
				inv.setItem(i++, is);
			}
			p.openInventory(inv);
		}else {
			for (String s : set.items) {
				String[] ss = s.split(" ");
				ItemStack is = new ItemStack(Material.getMaterial(ss[0].toUpperCase()), Integer.parseInt(ss[1]), Short.parseShort(ss[2]));
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(" ");
				is.setItemMeta(im);
				inv.setItem(i++, is);
			}
			p.openInventory(inv);
		}
	}
}
