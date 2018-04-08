package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.UserUtils;

public class CmdOdmoderator {
	public void Unmod (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g unmod <gracz>");
			return;
		}
		Player p = (Player) sender;
		User a = User.get(p.getName());
		if (!a.hasGuild()) {
			sender.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		if(!a.isAdmin()) {
			sender.sendMessage("§cMusisz byc wlascicielem aby to zrobic!");
			return;
		}
		if (!UserUtils.playedBefore(args[1])) {
			p.sendMessage("§Nie bylo takiego gracza!");
			return;
		}
		Guild g = a.getGuild();
		User u = User.get(args[1]);
		if (!u.hasGuild()) {
			sender.sendMessage("§cTen gracz nie jest w zadnej gildii!");
			return;
		}
		if (!g.getMembers().contains(u)) {
			sender.sendMessage("§cTen gracz nie jest w twojej gildii!");
			return;
		}
		if (!g.getMods().contains(u)) {
			sender.sendMessage("§cTen gracz nie jest moderatorem!");
			return;
		}
		g.removeMod(u);
		for (User m : g.getMembers()) {
			m.getPlayer().sendMessage("§aGracz §6§l" + u.getName() + " §ajuz nie jest moderatorem!");
		}
	}
}
