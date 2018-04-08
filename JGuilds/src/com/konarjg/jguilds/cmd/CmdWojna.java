package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.GuildUtils;

public class CmdWojna {
	public void Wojna (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g neutral <tag>");
			return;
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		if (!u.hasGuild()) {
			sender.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		if (!u.isAdmin()) {
			sender.sendMessage("§cMusisz byc wlascicielem gildii aby to zrobic!");
			return;
		}
		if (!GuildUtils.tagExists(args[1])) {
			sender.sendMessage("§cNie ma takiej gildii!");
			return;
		}
		Guild g = Guild.get(args[1]);
		if (g.equals(u.getGuild())) {
			sender.sendMessage("§cPodaj gildie inna niz twoja!");
			return;
		}
		if (u.getGuild().getEnemies().contains(g)) {
			sender.sendMessage("§cNie mozesz wypowiedziec wojny bez zerwania sojuszu!");
			return;
		}
		if (u.getGuild().getEnemies().contains(g)) {
			sender.sendMessage("§cJuz prowadzisz wojne z ta gildia!");
			return;
		}
		g.addEnemy(u.getGuild());
		u.getGuild().addEnemy(g);
		Bukkit.broadcastMessage("§cGildia §6§l" + u.getGuild().getTag() + " §cwypowiedziala wojne gildii §6§l" + g.getTag() + "§c!");
	}
}
