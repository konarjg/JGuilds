package com.konarjg.jguilds.listeners.region;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;

import com.konarjg.jguilds.obj.Region;

public class BlockIgniteListener {
	@EventHandler
	public void onIgnite (BlockIgniteEvent e) {
		if(e.isCancelled()) return;
		if(!Region.canBuild(e.getPlayer(), e.getBlock())) e.setCancelled(true);
	}
}
