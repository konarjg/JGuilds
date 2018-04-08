package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.UserUtils;

public class CmdOdzapros {
	public void Odzapros (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g odzapros <gracz>");
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
		if (!send.isAdmin() && !send.isMod()) {
			sender.sendMessage("§cMusisz byc min. moderatorem aby to zrobic!");
			return;
		}
		Guild g = send.getGuild();
		if (!g.getInvited().contains(u)) {
			p.sendMessage("§Ten gracz nie zostal zaproszony!");
			return;
		}
		g.removeInvite(u);
		p.sendMessage("§aGracz §6§l" + u.getName() + " §anie jest juz zaproszony!");
		if (u.isOnline()) u.getPlayer().sendMessage("§aJuz nie jestes zaproszony do gildii: §a§l" + g.getName() + "§a§l(§6§l" + g.getTag() + "§a§l)" + "§a!");
	}
}
