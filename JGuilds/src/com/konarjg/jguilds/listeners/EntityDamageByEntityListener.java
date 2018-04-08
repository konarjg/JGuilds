package com.konarjg.jguilds.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.konarjg.jguilds.obj.User;

public class EntityDamageByEntityListener implements Listener {
	@EventHandler
	public void OnAttack (EntityDamageByEntityEvent e) {
		if(e.isCancelled()) return;
		if (!(e.getEntity() instanceof Player)) return;
		
		User attacked = User.get(((Player)e.getEntity()).getName());
		User attacker = null;
		if (e.getDamager() instanceof Player) {
			attacker = User.get(((Player)e.getDamager()).getName());
		}
		if (e.getDamager() instanceof Projectile) {
			ProjectileSource ps = ((Projectile) e.getDamager()).getShooter();
			if(ps instanceof Player) {
				attacker = User.get(((Player) ps).getName());
			}
		}
		if(attacker == null) return;
		if (!attacked.hasGuild() || !attacker.hasGuild()) return;
		if (attacker.getGuild().equals(attacked.getGuild())) {
			attacker.getPlayer().sendMessage("§cJESTESCIE W JEDNEJ GILDII!");
			attacked.getPlayer().sendMessage("§cJESTESCIE W JEDNEJ GILDII!");
			e.setCancelled(true);
			return;
		}
		if (attacker.getGuild().getAllies().contains(attacked.getGuild())) {
			attacker.getPlayer().sendMessage("§cJESTESCIE W SOJUSZU!");
			attacked.getPlayer().sendMessage("§cJESTESCIE W SOJUSZU!");
			e.setCancelled(true);
			return;
		}
	}
}
