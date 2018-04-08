package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.User;

public class CmdRank {
	public void Rank(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage("§cPoprawne uzycie: /g rank");
			return;
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		p.sendMessage("§aTwoj ranking: ");
		p.sendMessage("§6§l " + u.getName() + "§a(§6§l" + u.getRank().getRank() + "§a)");
	}
}
