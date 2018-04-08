package com.konarjg.jguilds.cmd.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.utils.GuildUtils;

public class CmdZycia {
	public void Zycia (CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("jguilds.guildadmin.lives") || !sender.hasPermission("jguilds.guildadmin.*")) {
			sender.sendMessage("§cNie masz uprawnien!");
			return;
		}
		if (args.length < 3) {
			sender.sendMessage("§cPoprawne uzycie: /ga zycia <tag> <zycia>!");
			return;
		}
		if (args[1].length() < 3) {
			sender.sendMessage("§cPodany tag jest za krotki!");
			return;
		}
		if (!args[2].matches("[0-9]+")) {
			sender.sendMessage("§cPoprawne uzycie: /ga zycia <tag> <zycia>!");
			return;
		}
		if (Integer.parseInt(args[2]) == 0) {
			sender.sendMessage("§cNie mozesz ustawic ilosci zyc gildii na §6§l 0!");
			return;
		}
		if (GuildUtils.tagExists(args[1])) {
			Guild g = Guild.get(args[1]);
			g.setLives(Integer.parseInt(args[2]));
			sender.sendMessage("§aUstawiono ilosc zyc gildii: §6§l" + args[1] + "§a na: §b§l" + args[2]);
		}else {
			sender.sendMessage("§cTaka gildia nie istnieje!");
			return;
		}
	}
}
