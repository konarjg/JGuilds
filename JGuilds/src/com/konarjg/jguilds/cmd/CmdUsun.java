package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;

public class CmdUsun {
	public void Usun(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage("§cPoprawne uzycie: /g usun");
			return;
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		//Zabezpieczenia
		if (!u.hasGuild()) {
			p.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		//Usuwanie
		Guild g = u.getGuild();
		if(!u.isAdmin()) {
			p.sendMessage("§cNie jestes wlascicielem gildii!");
			return;
		}
		g.getRegion().getCenter().getBlock().setType(Material.AIR);
		g.delete();
		Bukkit.broadcastMessage("§a§lGracz§6" + p.getName() + " §a§l usunal gildie!");
	}
}
