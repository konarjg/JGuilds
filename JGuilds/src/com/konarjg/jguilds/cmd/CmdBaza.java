package com.konarjg.jguilds.cmd;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.konarjg.jguilds.Main;
import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;

public class CmdBaza {
	
	Map<String, BukkitTask> tp = new HashMap<String, BukkitTask> ();
	
	public void Baza (CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cMusisz byc na serwerze!");
			return;
		}
		if (args.length != 1) {
			sender.sendMessage("§cPoprawne uzycie: /g baza");
			return;
		}
		final Player p = (Player) sender;
		User u = User.get(p.getName());
		if (!u.hasGuild()) {
			p.sendMessage("§cNie masz gildii!");
			return;
		}
		final Guild g = u.getGuild();
		
		if (tp.containsKey(p.getName())) {
			((BukkitTask)tp.remove(p.getName())).cancel();
		}
		BukkitTask bt = Bukkit.getScheduler().runTaskLater(Main.getInst(), new Runnable () { 
			@Override
			public void run() {
				p.teleport(g.getRegion().getCenter().add(0, 1, 0));
				((BukkitTask)tp.remove(p.getName())).cancel();
			}
		}, 10*20);
		tp.put(p.getName(), bt);
		p.sendMessage("§aTeleportacja do bazy za 10 sekund nie ruszaj sie!");
	}
}
