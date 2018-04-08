package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.GuildUtils;

public class CmdNeutral {
	public void Neutral (CommandSender sender, Command cmd, String label, String[] args) {
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
		if(u.getGuild().getAllies().contains(g)) {
			u.getGuild().removeAlly(g);
			g.removeAlly(u.getGuild());
			Bukkit.broadcastMessage("§aGildia §6" + u.getGuild().getTag() + "§azerwala sojusz z gildia §6" + g.getTag() + "§a!");
			return;
		}
		if(u.getGuild().getEnemies().contains(g)) {
			if (u.getGuild().getNeutralInvs().contains(g)) {
				u.getGuild().removeNeutralInv(g);
				g.removeEnemy(u.getGuild());
				u.getGuild().removeEnemy(g);
				Bukkit.broadcastMessage("§aGildia §6" + u.getGuild().getTag() + "§azakonczyla wojne z gildia §6" + g.getTag() + "§a!");
				return;
			}
		}
		if (g.getNeutralInvs().contains(u.getGuild())) {
			p.sendMessage("§cJuz poprosiles te gildie o neutralnosc!");
			return;
		}
		g.addNeutralInv(u.getGuild());
		sender.sendMessage("§aPoprosiles gildie §6" + g.getTag() + "§a o neutralnosc!" );
		for (User m : g.getMembers()) {
			if (m.isOnline()) m.getPlayer().sendMessage("§aGildia §6" + u.getGuild().getTag() + " §aprosi o neutralnosc!");
			return;
		}
		p.sendMessage("§cWasze gildie sa juz neutralne wzgledem siebie!");
	}
}
