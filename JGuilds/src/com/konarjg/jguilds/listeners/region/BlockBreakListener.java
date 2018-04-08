package com.konarjg.jguilds.listeners.region;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.konarjg.jguilds.obj.Region;

public class BlockBreakListener implements Listener {
	
	
	@EventHandler
	public void OnBreak(BlockBreakEvent e) {
		if(e.isCancelled()) return;
		
		if (!e.getBlock().getType().equals(Material.ENDER_PORTAL_FRAME)) {
			if(!Region.canBuild(e.getPlayer(), e.getBlock())) e.setCancelled(true);
		}else {
			e.setCancelled(true);
		}
	}
}
