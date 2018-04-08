package com.konarjg.jguilds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.GuildUtils;

public class CmdSojusz {
	public void Sojusz (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g sojusz <tag>");
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
		if (u.getGuild().getAllies().contains(g)) {
			p.sendMessage("§cWasze gildie sa juz ze soba w sojuszu!");
			return;
		}
		if (u.getGuild().getEnemies().contains(g)) {
			sender.sendMessage("§cNie mozesz proponowac sojuszu swojemu wrogowi!");
			return;
		}
		if (u.getGuild().getAllyInvs().contains(g)) {
			u.getGuild().removeAllyInv(g);
			u.getGuild().addAlly(g);
			g.addAlly(u.getGuild());
			Bukkit.broadcastMessage("§aGildia §6" + u.getGuild().getTag() + " §a jest teraz w sojuszu z gildia §6" + g.getTag());
			return;
		}
		if (g.getAllyInvs().contains(u.getGuild())) {
			sender.sendMessage("§cJuz poprosiles te gildie o sojusz!");
			return;
		}
		g.addAllyInv(u.getGuild());
		sender.sendMessage("§aPoprosiles gildie §6" + g.getTag() + "§a o sojusz!" );
		for (User m : g.getMembers()) {
			if (m.isOnline()) m.getPlayer().sendMessage("§aGildia §6" + u.getGuild().getTag() + " §aprosi o sojusz!");
			return;
		}
	}
}
