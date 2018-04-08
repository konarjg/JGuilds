package com.konarjg.jguilds.listeners.region;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.scheduler.BukkitTask;

import com.konarjg.jguilds.Main;
import com.konarjg.jguilds.data.DataManager;
import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.Region;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.RankUtils;
import com.konarjg.jguilds.obj.utils.RegionUtils;

public class BlockDamageListener implements Listener {
	Map<String, BukkitTask> tp = new HashMap<String, BukkitTask> ();
	
	@EventHandler
	public void onDamage (BlockDamageEvent e) {
		Location l = e.getPlayer().getLocation();
		Region r = RegionUtils.inWich(l);
		final Player p = e.getPlayer();
		
		final Guild g = r.getGuild();
		final User u = User.get(e.getPlayer().getName());
		if(e.isCancelled()) return;
		if (!g.canConquer() && !u.getGuild().equals(g)) {
			e.getPlayer().sendMessage("§cJeszcze nie mozesz podbic tej gildii! Bedziesz mogl ja podbic po 24h od ostatniego podbicia!");
			e.setCancelled(true);
			return;
		}else { 
			if (e.getBlock().getType().equals(Material.ENDER_PORTAL_FRAME)) {
				if (u.hasGuild() && u.getGuild().equals(g)) {
					e.getPlayer().sendMessage("§cNie mozesz podbic swojej gildii!");
					e.setCancelled(true);
				}else {
					if (!u.hasGuild()) {
						u.getPlayer().sendMessage("§cMusisz byc w jakiejs gildii by moc podbijac inne gildie!");
						e.setCancelled(true);
					}else {
						if (g.getLives() != 0) {
							g.removeLive();
							e.getPlayer().sendMessage("§aZabrales jedno zycie gildii: " + "§6§l" + g.getTag() + "§a i otrzymales §6§l10 punktow rankingu oraz §6§l1 §adodatkowe zycie dla swojej gildii!");
							u.getRank().setRank(u.getRank().getRank()+10);
							u.getGuild().addLive();
							g.setConquer(false);
							if (tp.containsKey(p.getName())) {
								((BukkitTask)tp.remove(p.getName())).cancel();
							}
							BukkitTask bt = Bukkit.getScheduler().runTaskLater(Main.getInst(), new Runnable () { 
								@Override
								public void run() {
									g.setConquer(true);;
									((BukkitTask)tp.remove(p.getName())).cancel();
									if (!u.getGuild().equals(g)) p.sendMessage("§aJuz mozesz podbic gildie: §6§l" + g.getTag() + "§a!");
								}
							}, 86400*20);
							tp.put(p.getName(), bt);
							DataManager.save();
							DataManager.load();
							RankUtils.updateAll();
						}else {
							u.getRank().setRank(u.getRank().getRank()+30);
							u.getPlayer().sendMessage("§aPodbiles gildie: " + "§6" + g.getTag() + "§a i otrzymales 30 punktow rankingu!");
							for (User u2 : g.getMembers()) {
								if (u2.isOnline()) u2.getPlayer().sendMessage("§cWasza gildia zostala podbita i przestala istniec!");
							}
							if (g.getRegion().getCenter().getBlock().getType() == Material.ENDER_PORTAL_FRAME) g.getRegion().getCenter().getBlock().setType(Material.AIR);
							g.delete();
							u.setGuild(null);
						}
					}
				}
			}
		}
	}
}
