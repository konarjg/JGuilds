package com.konarjg.jguilds.listeners.region;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.konarjg.jguilds.obj.Region;
import com.konarjg.jguilds.obj.utils.RegionUtils;

public class EntityExplodeListener implements Listener {
	
	@EventHandler
	public void onExplode (EntityExplodeEvent e) {
		if(e.isCancelled()) return;
		List<Block> blocks = e.blockList();
		Location l = e.getLocation();
		if (RegionUtils.isIn(l)) return;
		Region r = RegionUtils.inWich(l);
		Location c = r.getCenter().subtract(0, 1, 0);
		for (Block b : blocks) {
			if (b.getLocation().equals(c))  {
				b.getState().update(true, false);
			}
		}
	}
}
