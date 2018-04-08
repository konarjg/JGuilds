package com.konarjg.jguilds.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.RankChanger;
import com.konarjg.jguilds.obj.utils.RankUtils;

public class PlayerDeathListener implements Listener {
	@EventHandler
	public void OnDeath (PlayerDeathEvent e) {
		e.setDeathMessage(null);
		User dead = User.get(e.getEntity().getName());
		if (e.getEntity().getKiller() == null) return;
		User killer = User.get(e.getEntity().getKiller().getName());
		int deadTake = dead.getRank().getRank();
		int killerAdd = killer.getRank().getRank();
		int[] newRanks = RankChanger.getNewRanks(dead.getRank(), killer.getRank(), false);
		deadTake -= newRanks[0];
		killerAdd = newRanks[1] - killerAdd;
		dead.getRank().setRank(newRanks[0]);
		dead.getRank().addDeath();
		killer.getRank().setRank(newRanks[1]);
		killer.getRank().addKill();
		RankUtils.updateAll();
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("§6Gracz §a§l" + killer.getName() + "§a§l(+" + killerAdd + "§a§l)" + "§6zabija gracza §c§l" + dead.getName() + "§cl(-" + deadTake + "§c§l)§6!");
		}
	}
}
