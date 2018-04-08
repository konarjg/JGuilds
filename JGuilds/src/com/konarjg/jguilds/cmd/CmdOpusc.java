package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;

public class CmdOpusc {
	public void Opusc (CommandSender sender, Command cmd, String label, String [] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage("§cPoprawne uzycie: /g opusc");
			return;
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		if(!u.hasGuild()) {
			sender.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		Guild g = u.getGuild();
		if (u.isMod()) g.removeMod(u);
		if (u.isAdmin()) g.delete();
		g.removeMember(u);
		u.setGuild(null);
		Bukkit.broadcastMessage("§cGracz §6§l" + u.getName() + " §copuszcza gildie §6§l" + g.getTag() + "§c!");
	}
}
