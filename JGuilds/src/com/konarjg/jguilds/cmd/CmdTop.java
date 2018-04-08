package com.konarjg.jguilds.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.RankUtils;

public class CmdTop {
	public void Top (CommandSender sender, Command cmd, String label, String[] args) {
		if (args[1].equalsIgnoreCase("gildie") || args[1].equalsIgnoreCase("guilds")) {
				sender.sendMessage("§bTopka gildii!");
				for (int i = 1; i <= 10; i++) {
					if (i <= RankUtils.getGuildRanks().size()) {
						Guild g = RankUtils.getGuildByPosition(i);
						sender.sendMessage(" §c§l" + i + ". §a" + g.getTag() + " §7(§6" + g.getRank().getRank() + "§7)");
					}else {
						sender.sendMessage(" §c§l" + i + ". §aBRAK");
					}
				}
		}
		else if (args[1].equalsIgnoreCase("gracze") || args[1].equalsIgnoreCase("users")) {
			sender.sendMessage("§bTopka graczy!");
			for (int i = 1; i <= 10; i++) {
				if (i <= RankUtils.getUserRanks().size()) {
					User u = RankUtils.getUserByPosition(i);
					sender.sendMessage(" §c§l" + i + ". §a" + u.getName() + " §7(§6" + u.getRank().getRank() + "§7)");
				}else {
					sender.sendMessage(" §c§l" + i + ". §aBRAK");
				}
			}
		}
	}
}
