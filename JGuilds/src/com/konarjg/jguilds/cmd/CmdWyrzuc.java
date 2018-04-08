package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.RankUtils;
import com.konarjg.jguilds.obj.utils.UserUtils;

public class CmdWyrzuc {
	public void Wyrzuc (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 2) {
			sender.sendMessage("§cPoprawne uzycie: /g wyrzuc <gracz>");
			return;
		}
		Player p = (Player) sender;
		User send = User.get(p.getName());
		if (!UserUtils.playedBefore(args[1])) {
			p.sendMessage("§cNie bylo takiego gracza!");
			return;
		}
		User u = User.get(args[1]);
		
		if(!send.hasGuild()) {
			sender.sendMessage("§cNie jestes w zadnej gildii!");
			return;
		}
		if(!u.hasGuild() || !u.getGuild().equals(send.getGuild())) {
			sender.sendMessage("§cTen gracz nie jest czlonkiem twojej gildii!");
			return;
		}
		Guild g = u.getGuild();
		if (!send.isAdmin() && !send.isMod()) {
			sender.sendMessage("§cMusisz byc min. moderatorem aby to zrobic!");
			return;
		}
		if (u.isMod() && send.isMod() || u.isAdmin()) {
			sender.sendMessage("§cNie mozesz wyrzucic tej osoby!");
			return;
		}
		if (u.isMod()) g.removeMod(u);
		g.removeMember(u);
		u.setGuild(null);
		if(u.isOnline()) u.getPlayer().sendMessage("§cZostales wyrzucony z gildii: §a§l" + g.getName() + "§a§l(§6§k" + g.getTag() + "§a§l)§c przez gracza: §6§l" + send.getName() + "§c !");
		for (User m : g.getMembers()) {
			if (m.isOnline()) m.getPlayer().sendMessage("§aGracz §6§l" + u.getName() + " §azostal wyrzucony z gildii!");
		}
		RankUtils.updateAll();
	}
}
