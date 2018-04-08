package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.UserUtils;

public class CmdZapros {
	public void Zapros (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g zapros <gracz>");
			return;
		}
		Player p = (Player) sender;
		User send = User.get(p.getName());
		if(!send.hasGuild()) {
			sender.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		User u = User.get(args[1]);
		if (!UserUtils.playedBefore(args[1])) {
			p.sendMessage("§Nie bylo takiego gracza!");
			return;
		}
		if (u.hasGuild()) {
			p.sendMessage("§Ten gracz jest juz w jakiejs gildii!");
			return;
		}
		Guild g = send.getGuild();
		if (!send.isAdmin() && !send.isMod()) {
			sender.sendMessage("§cMusisz byc min. moderatorem aby to zrobic!");
			return;
		}
		if (u.isInvited(g)) {
			sender.sendMessage("§cTen gracz jest juz zaproszony!");
			return;
		}
		p.sendMessage("§aZaprosiles do gildii gracza §6§l" + u.getName() + "§a.");
		g.addInvite(u);
		if (u.isOnline()) u.getPlayer().sendMessage("§aZostales zaproszony do gildii: §a§l" + g.getName() + "§a§l(§6§l" + g.getTag() + "§a§l)" + "§a!");
	}
}
