package com.konarjg.jguilds.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcsg.double0negative.tabapi.TabAPI;

import com.konarjg.jguilds.Main;
import com.konarjg.jguilds.obj.User;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void OnJoin(final PlayerJoinEvent e) {
		final User u = User.get(e.getPlayer().getName());
		
		new BukkitRunnable() {
			public void run() {
				TabAPI.setPriority(Main.getInst(), e.getPlayer(), 2);
				TabAPI.setTabString(Main.getInst(), e.getPlayer(), 1, 0, "§6§l"+u.getName(), 1);
				TabAPI.updateAll();
			}
		};
		
	}
}
