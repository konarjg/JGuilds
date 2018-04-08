package com.konarjg.jguilds.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.RegionUtils;

public class RightClickListener implements Listener {
	@EventHandler
	public void OnRightClick (PlayerInteractEvent e) {
			Player p = e.getPlayer();
			Location l = p.getLocation();
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME) {
					User u = User.get(p.getName());
					Guild g = RegionUtils.inWich(l).getGuild();
					send("&a&lInfo o gildii: " + g.getTag(), p);
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
