package com.konarjg.jguilds.cmd.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainAdminCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ga") || cmd.getName().equalsIgnoreCase("guildadmin")) {
			if (!sender.hasPermission("guildadmin")) {
				sender.sendMessage("§cNie masz uprawnien!");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("§bKomendy admina JGuilds: ");
				sender.sendMessage("§b/ga zycia <tag> - §6zmienia ilosc zyc podanej gildii");
				sender.sendMessage("§b/ga reload - §6przeladowuje plugin");
				return true;
			}
			if (args[0].equalsIgnoreCase("zycia") || args[0].equalsIgnoreCase("lives")) {
				new CmdZycia().Zycia(sender, cmd, label, args);
				return true;
			}else if (args[0].equalsIgnoreCase("reload")) {
				new CmdReload().Reload(sender, cmd, label, args);
				return true;
			}
		}
		return false;
	}

}
