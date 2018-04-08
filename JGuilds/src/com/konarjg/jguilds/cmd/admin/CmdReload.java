package com.konarjg.jguilds.cmd.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.konarjg.jguilds.data.DataManager;

public class CmdReload {

	public void Reload(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("jguilds.guildadmin.reload") || !sender.hasPermission("jguilds.guildadmin.*")) {
			sender.sendMessage("§cNie masz uprawnien!");
			return;
		}
		DataManager.save();
		DataManager.load();
		sender.sendMessage("§aPrzeladowano plugin!");
	}

}
