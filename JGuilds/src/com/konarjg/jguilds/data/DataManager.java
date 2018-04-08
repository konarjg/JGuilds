package com.konarjg.jguilds.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.Rank;
import com.konarjg.jguilds.obj.Region;
import com.konarjg.jguilds.obj.User;
import com.konarjg.jguilds.obj.utils.ChatType;
import com.konarjg.jguilds.obj.utils.GuildUtils;
import com.konarjg.jguilds.obj.utils.RegionUtils;
import com.konarjg.jguilds.obj.utils.UserUtils;

public class DataManager {
	
	public static void load() {
		loadGuilds();
		loadUsers();
		loadRegions();
		DataManager.checkObjects();
	}
	
	public static void save() {
		saveGuilds();
		saveUsers();
		saveRegions();
	}
	
	static Location fromString (String s) {
		String[] ss = s.split(" ");
		return new Location(Bukkit.getWorld(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), Integer.parseInt(ss[3]));
	}
	
	static String fromLocation (Location l) {
		return new String(l.getWorld().getName() + " " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
	}
	
	public static void checkObjects () {
		int removed = 0;
		for (Guild g : GuildUtils.getGuilds()) {
			if (g.getName() == null || g.getTag() == null || g.getAdmin() == null || g.getLives() == 0) {
				GuildUtils.removeGuild(g);
				g.delete();
				removed++;
			}
		}
		for (User u : UserUtils.getUsers()) {
			if (u.getName() == null) {
				UserUtils.removeUser(u);
				removed++;
			}
		}
		for (Region r : RegionUtils.getRegions()) {
			if (r.getName() == null || r.getCenter() == null || r.getGuild() == null) {
				RegionUtils.removeRegion(r);
				removed++;
			}
			if (r.getLowerLoc() == null || r.getUpperLoc() == null) {
				r.reCalculate();
			}
		}
		sendConsole("&6&l" + removed + " &a&lusunietych obiektow!");
	}
	
	public static void loadGuilds () {
		int loaded = 0;
		for (File f : FileManager.getGuildsFolder().listFiles()) {
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			Guild g = Guild.get(yml.getString("Tag"));
			g.setName(yml.getString("Name"));
			g.setAdmin(User.get(yml.getString("Admin")));
			g.setMembers(UserUtils.fromNames(yml.getStringList("Members")));
			g.setInvited(UserUtils.fromNames(yml.getStringList("Invited")));
			g.setMods(UserUtils.fromNames(yml.getStringList("Mods")));
			g.setAllies(GuildUtils.fromTags(yml.getStringList("Allies")));
			g.setEnemies(GuildUtils.fromTags(yml.getStringList("Enemies")));
			g.setAllyInvs(GuildUtils.fromTags(yml.getStringList("AllyInvites")));
			g.setNeutralInvs(GuildUtils.fromTags(yml.getStringList("NeutralInvites")));
			g.setLives(Integer.parseInt(yml.getString("Lives")));
			g.setConquer(yml.getBoolean("CanConquer"));
			if (yml.get("Region")!=null) {
				g.setRegion(Region.get(yml.getString("Region")));
				Region r = Region.get(yml.getString("Region"));
				sendConsole("&2"+r.getName());
			}
			g.setRank(new Rank(g));
			loaded++;
		}
		sendConsole("&6&l" + loaded + "&a&l" + "wczytanych gildii!");
	}
	
	public static void loadUsers () {
		int loaded = 0;
		for (File f : FileManager.getUsersFolder().listFiles()) {
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			User u = User.get(yml.getString("Name"));
			u.setChat(ChatType.valueOf(yml.getString("ChatType").toUpperCase()));
			u.getRank().setRank(yml.getInt("Rank"));
			u.getRank().setKills(yml.getInt("Kills"));
			u.getRank().setDeaths(yml.getInt("Deaths"));
			if(yml.get("Guild")!=null) {
				u.setGuild(Guild.get(yml.getString("Guild")));
			}
			loaded++;
		}
		sendConsole("&6&l" + loaded + "&a&l" + "wczytanych graczy!");
	}
	
	public static void loadRegions () {
		int loaded = 0;
		for (File f : FileManager.getRegionsFolder().listFiles()) {
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			Region r = Region.get(yml.getString("Name"));
			r.setGuild(Guild.get(yml.getString("Guild")));
			Guild g = Guild.get(yml.getString("Guild"));
			sendConsole(g.toString());
			r.setCenter(fromString(yml.getString("Center")));
			r.setSize(yml.getInt("Size"));
			r.setLowerLoc(fromString(yml.getString("LowerLoc")));
			r.setUpperLoc(fromString(yml.getString("UpperLoc")));
			loaded++;
		}
		sendConsole("&6&l" + loaded + "&a&l" + "wczytanych regionow!");
	}
	
	static void sendConsole (String msg) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public static void saveGuilds () {
		int saved = 0;
		for (Guild g : GuildUtils.getGuilds()) {
			File f = new File(FileManager.getGuildsFolder(), g.getTag() + ".yml");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("Name", g.getName());
			yml.set("Tag", g.getTag());
			yml.set("Admin", g.getAdmin().getName());
			yml.set("Members", UserUtils.toNames(g.getMembers()));
			yml.set("Invited", UserUtils.toNames(g.getInvited()));
			yml.set("Mods", UserUtils.toNames(g.getMods()));
			yml.set("Allies", GuildUtils.toTags(g.getAllies()));
			yml.set("Enemies", GuildUtils.toTags(g.getEnemies()));
			yml.set("AllyInvites", GuildUtils.toTags(g.getAllyInvs()));
			yml.set("NeutralInvites", GuildUtils.toTags(g.getNeutralInvs()));
			yml.set("Region", g.getRegion().getName());
			yml.set("Lives", g.getLives());
			yml.set("CanConquer", g.canConquer());
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saved++;
		}
		sendConsole("&6&l" + saved + "&a&l" + "zapisanych graczy!");
	}
	
	public static void saveUsers () {
		int saved = 0;
		for (User u : UserUtils.getUsers()) {
			File f = new File(FileManager.getUsersFolder(), u.getName() + ".yml");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("Name", u.getName());
			yml.set("Rank", u.getRank().getRank());
			yml.set("Kills", u.getRank().getKills());
			yml.set("Deaths", u.getRank().getDeaths());
			yml.set("ChatType", u.getChat().toString());
			if(u.hasGuild()) yml.set("Guild", u.getGuild().getTag());
			else yml.set("Guild", null);
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saved++;
		}
		sendConsole("&6&l" + saved + "&a&l" + "zapisanych graczy!");
	}
	
	public static void saveRegions () {
		int saved = 0;
		for (Region r : RegionUtils.getRegions()) {
			File f = new File(FileManager.getRegionsFolder(), r.getName() + ".yml");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("Name", r.getName());
			yml.set("Guild", r.getGuild().getTag());
			yml.set("Center", fromLocation(r.getCenter()));
			yml.set("Size", r.getSize());
			yml.set("LowerLoc", fromLocation(r.getLowerLoc()));
			yml.set("UpperLoc", fromLocation(r.getUpperLoc()));
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saved++;
		}
		sendConsole("&6&l" + saved + "&a&l" + "zapisanych regionow!");
	}
	
	
	
	
}
