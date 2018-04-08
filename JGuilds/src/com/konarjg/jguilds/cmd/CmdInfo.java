package com.konarjg.jguilds.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.GuildUtils;

public class CmdInfo {
	public void Info (CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		User u = User.get(p.getName());
		if (args.length == 1) {
			if (!u.hasGuild()) {
				send ("&cMusisz byc w gildii by zobaczyc informacje o niej lub podaj tag innej gildii!", p);
				return;
			}
			for (Guild g : GuildUtils.getGuilds()) {
				if (u.hasGuild() && g.equals(u.getGuild())) {
					send("&a&lInfo o gildii: " + g.getTag(), p);
					send("&bRank: " + g.getRank().getRank(), p);
					send("&bNazwa: " + g.getName(), p);
					send("&bWlasciciel: " + g.getAdmin().getName(), p);
					send("&bCzlonkowie: " + g.getMembers().toString(), p);
					send("&bZycia: " + g.getLives(), p);
					return;
				}
			}
		}
			if (args[1].length() < 3) {
				send("&cPodany tag jest za krotki!", p);
				return;
			}
			for (Guild g : GuildUtils.getGuilds()) {
				if (g.getTag().equalsIgnoreCase(args[1])) {
					send("&a&lInfo o gildii: " + args[1], p);
					send("&bRank: " + g.getRank().getRank(), p);
					send("&bNazwa: " + g.getName(), p);
					send("&bWlasciciel: " + g.getAdmin().getName(), p);
					send("&bCzlonkowie: " + g.getMembers().toString(), p);
					send("&bZycia: " + g.getLives(), p);
				}
			}
		
	}
	
	void send (String msg, Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
}
