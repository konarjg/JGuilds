package com.konarjg.jguilds;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.konarjg.jguilds.cmd.MainCmd;
import com.konarjg.jguilds.cmd.admin.MainAdminCmd;
import com.konarjg.jguilds.data.DataManager;
import com.konarjg.jguilds.data.FileManager;
import com.konarjg.jguilds.data.Settings;
import com.konarjg.jguilds.listeners.EntityDamageByEntityListener;
import com.konarjg.jguilds.listeners.InventoryClickListener;
import com.konarjg.jguilds.listeners.PlayerDeathListener;
import com.konarjg.jguilds.listeners.PlayerJoinListener;
import com.konarjg.jguilds.listeners.RightClickListener;
import com.konarjg.jguilds.listeners.region.BlockBreakListener;
import com.konarjg.jguilds.listeners.region.BlockDamageListener;
import com.konarjg.jguilds.listeners.region.BlockPlaceListener;
import com.konarjg.jguilds.listeners.region.EntityExplodeListener;
import com.konarjg.jguilds.listeners.region.PlayerBucketListener;

public class Main extends JavaPlugin {
	
	static Main inst;
	static CheckRoom h;
	
	public Main () {
		inst = this;
	}
	
	@Override
	public void onEnable() {
		inst = this;
		FileManager.check();
		DataManager.load();
		Settings.getInst().Load();
		DataManager.checkObjects();
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerBucketListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockDamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new RightClickListener(), this);
		getCommand("g").setExecutor(new MainCmd());
		getCommand("ga").setExecutor(new MainAdminCmd());
	}
	
	@Override
	public void onDisable () {
		DataManager.save();
	}
	
	public static Main getInst() {
		if (inst == null) return new Main();
		return inst;
		
	}
	
}
