package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.GuildUtils;

public class CmdNeutralOdrzuc {
	public void NeutralOdrzuc (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g odrzucneutral <tag>");
			return;
		}
		Player p = (Player) sender;
		User u = User.get(p.getName());
		if(!u.hasGuild()) {
			sender.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		if (!GuildUtils.tagExists(args[1])) {
			p.sendMessage("§Nie ma takiej gildii!");
			return;
		}
		Guild g = Guild.get(args[1]);
		if (!u.isAdmin()) {
			sender.sendMessage("§cMusisz byc wlascicielem aby to zrobic!");
			return;
		}
		if (!u.getGuild().getNeutralInvs().contains(g)) {
			sender.sendMessage("§cTa gildia nie zaproponowala ci przerwania wojny!");
			return;
		}
		u.getGuild().removeNeutralInv(g);
		for (User m : g.getMembers()) {
			if (m.isOnline()) {
				sender.sendMessage("§cGildia §6§l" + u.getGuild().getTag() + " §codrzuca propozycje pokoju!");
			}
		}
	}
}
