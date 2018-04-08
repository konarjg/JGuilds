package com.konarjg.jguilds.listeners.region;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import com.konarjg.jguilds.obj.Region;

public class PlayerBucketListener implements Listener {
	
	@EventHandler
	public void onFill(PlayerBucketFillEvent e) { 
		if(e.isCancelled()) return;
		if(!Region.canBuild(e.getPlayer(), e.getBlockClicked())) e.setCancelled(true);
	}
	
	
	@EventHandler
	public void onEmpty(PlayerBucketEmptyEvent e) { 
		if(e.isCancelled()) return;
		if(!Region.canBuild(e.getPlayer(), e.getBlockClicked())) e.setCancelled(true);
	}
}
