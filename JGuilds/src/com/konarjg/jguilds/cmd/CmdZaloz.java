package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.konarjg.jguilds.data.Settings;
import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.Region;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.GuildUtils;
import com.konarjg.jguilds.obj.utils.RankUtils;
import com.konarjg.jguilds.obj.utils.RegionUtils;

public class CmdZaloz {
	
	public void Zaloz (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length < 3) {
			sender.sendMessage("§cPoprawne uzycie: /g zaloz <tag> <nazwa>");
		}
		if (args.length > 3) {
			sender.sendMessage("§cNazwa nie moze skladac sie z kilku wyrazow!");
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		Settings s = Settings.getInst();
		//Zabezpieczenia
		if (u.hasGuild()) {
			p.sendMessage("§cMasz juz gildie!");
			return;
		}
		if (GuildUtils.tagExists(args[1])) {
			p.sendMessage("§cGildia o takim tagu juz istnieje!");
			return;
		}
		if (GuildUtils.nameExists(args[2])) {
			p.sendMessage("§cGildia o takiej nazwie juz istnieje!");
			return;
		}
		if (args[1].length() < 3) {
			p.sendMessage("§cTag musi miec od 3-5 znakow!");
			return;
		}
		if (args[1].length() > 5) {
			p.sendMessage("§cTag musi miec od 3-5 znakow!");
			return;
		}
		if (args[2].length() < 5) {
			p.sendMessage("§cNazwa musi miec od 5-30 znakow!");
			return;
		}
		if (args[2].length() > 330) {
			p.sendMessage("§cNazwa musi miec od 5-30 znakow!");
			return;
		}
		if(!args[1].matches("[A-Za-z]+")) {
			p.sendMessage("§cTag moze zawierac tylko litery!");
			return;
		}
		if(!args[2].matches("[A-Za-z]+")) {
			p.sendMessage("§cNazwa moze zawierac tylko litery!");
			return;
		}
		Location loc = p.getLocation();
		if (RegionUtils.isIn(loc)) {
			p.sendMessage("§cW tym miejscu jest juz gildia!");
			return;
		}
		if (RegionUtils.isNear(loc, 100, 100)) {
			p.sendMessage("§cJestes zbyt blisko innej gildii!");
			return;
		}
		if (loc.distance(loc.getWorld().getSpawnLocation())<250) { 
			p.sendMessage("§cJestes zbyt blisko spawnu!");
			return;
		}
		
		//Tworzenie gildii
		if(!hasItems(p)) {
			showItems(p);
			return;
		}else {
			takeItems(p);
			Guild g = Guild.get(args[1]);
			g.setName(args[2]);
			g.setAdmin(u);
			g.addMember(u);
			RankUtils.update(g);
			g.setRegion(new Region(g, p.getLocation(), 100));
			
			g.getRegion().getCenter().getBlock().setType(Material.ENDER_PORTAL_FRAME);
			p.sendMessage("§6"+ u.getName()+ " §7zalozyl gildie " + g.getName() + "§7(§6" + g.getTag() + "§7)!");
			Bukkit.broadcastMessage("§a§lGracz§6" + p.getName() + " §a§lzalozyl gildie" + " §6§l" + g.getName() + "§a(" + g.getTag() + "§a)");
		}
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
	
	boolean hasItems(Player p) {
		Settings set = Settings.getInst();
		if(p.hasPermission("jguilds.vip")) {
			for (String s : set.itemsVip) {
				String[] ss = s.split(" ");
				ItemStack is = new ItemStack(Material.getMaterial(ss[0].toUpperCase()), 1, Short.parseShort(ss[2]));
				if (!p.getInventory().containsAtLeast(is, Integer.parseInt(ss[1]))) return false;
			}
		}else {
			for (String s : set.items) {
				String[] ss = s.split(" ");
				ItemStack is = new ItemStack(Material.getMaterial(ss[0].toUpperCase()), 1, Short.parseShort(ss[2]));
				if (!p.getInventory().containsAtLeast(is, Integer.parseInt(ss[1]))) return false;
			}
		}
		return true;
	}
	
	void takeItems(Player p) {
		Settings set = Settings.getInst();
		if(p.hasPermission("jguilds.vip")) {
			for (String s : set.itemsVip) {
				String[] ss = s.split(" ");
				ItemStack is = new ItemStack(Material.getMaterial(ss[0].toUpperCase()), Integer.parseInt(ss[1]), Short.parseShort(ss[2]));
				p.getInventory().removeItem(is);
			}
		}else {
			for (String s : set.items) {
				String[] ss = s.split(" ");
				ItemStack is = new ItemStack(Material.getMaterial(ss[0].toUpperCase()), Integer.parseInt(ss[1]), Short.parseShort(ss[2]));
				p.getInventory().removeItem(is);
			}
		}
	}
}
