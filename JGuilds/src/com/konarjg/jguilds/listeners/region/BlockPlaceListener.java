package com.konarjg.jguilds.listeners.region;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.konarjg.jguilds.obj.Region;

public class BlockPlaceListener implements Listener {
	@EventHandler
	public void OnBreak(BlockPlaceEvent e) {
		if(e.isCancelled()) return;
		if(!Region.canBuild(e.getPlayer(), e.getBlock())) e.setCancelled(true);
	}
}
