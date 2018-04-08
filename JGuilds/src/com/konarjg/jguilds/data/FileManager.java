package com.konarjg.jguilds.data;

import java.io.File;

import com.konarjg.jguilds.Main;

public class FileManager {
	static File mainDir = Main.getInst().getDataFolder();
	static File cfgFile = new File (mainDir, "config.yml");
	static File users = new File (mainDir, "users");
	static File guilds = new File (mainDir, "guilds");
	static File regions = new File (mainDir, "regions");
	
	public static void check () {
		if (!mainDir.exists()) mainDir.mkdirs();
		if (!users.exists()) users.mkdirs();
		if (!guilds.exists()) guilds.mkdirs();
		if (!regions.exists()) regions.mkdirs();
		if (!cfgFile.exists()) Main.getInst().saveDefaultConfig();
		
	}
	
	public static File getUsersFolder () {
		return users;
	}
	
	public static File getGuildsFolder () {
		return guilds;
	}
	
	public static File getRegionsFolder () {
		return regions;
	}
}


