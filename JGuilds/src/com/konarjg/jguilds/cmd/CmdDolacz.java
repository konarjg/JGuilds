package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;

public class CmdDolacz {
	public void Dolacz (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g dolacz <tag>");
			return;
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		if (u.hasGuild()) {
			p.sendMessage("§cJestes juz w gildii!");
			return;
		}
		Guild g = Guild.get(args[1]);
		if (!u.isInvited(g)) {
			p.sendMessage("§cNie jestes zaproszony do tej gildii!");
			return;
		}
		g.addMember(u);
		g.removeInvite(u);
		u.setGuild(g);
		p.sendMessage("§aWitaj w gildii §a§l" + g.getName() + "§a§l(§6§l" + g.getTag() + "§a§l)§a!");
	}
}
