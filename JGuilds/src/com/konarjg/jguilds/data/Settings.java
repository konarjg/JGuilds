package com.konarjg.jguilds.data;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.konarjg.jguilds.Main;

public class Settings {
	static Settings inst;
	FileConfiguration cfg = Main.getInst().getConfig();
	public List<String> items;
	public List<String> itemsVip;
	
	Settings() {
		inst = this;
	}
	
	public void Load () {
		items = cfg.getStringList("Items");
		itemsVip = cfg.getStringList("ItemsVip");
	}
	
	public static Settings getInst() {
		if (inst == null) return new Settings ();
		return inst;
	}
	
}
