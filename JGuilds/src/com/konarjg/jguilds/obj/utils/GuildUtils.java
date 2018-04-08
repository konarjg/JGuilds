package com.konarjg.jguilds.obj.utils;

import java.util.ArrayList;
import java.util.List;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.User;

import net.minecraft.server.v1_7_R1.Material;

public class GuildUtils {
static List<Guild> guilds = new ArrayList<Guild> ();
	
	public static List<Guild> getGuilds () {
		return new ArrayList<Guild>(guilds);
	}
	
	public static void addGuild (Guild g) {
		if(!guilds.contains(g)) guilds.add(g);
	}

	public static void removeGuild (Guild g) {
		if(guilds.contains(g)) guilds.remove(g);
	}
	
	public static Guild byName(String name){
		for(Guild guild : guilds){
			if(guild.getName() != null && guild.getName().equalsIgnoreCase(name)) return guild;
		}
		return null;
	}
	
	public static Guild byTag(String tag){
		for(Guild guild : guilds){
			if(guild.getTag() != null && guild.getTag().equalsIgnoreCase(tag.toLowerCase())) return guild;
		}
		return null;
	}
		
	public static boolean nameExists(String name){
		for(Guild guild : guilds){
			if(guild.getName() != null && guild.getName().equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	public static boolean tagExists(String tag){
		for(Guild guild : guilds){
			if(guild.getTag() != null && guild.getTag().equalsIgnoreCase(tag)) return true;
		}
		return false;
	}
	
	public static List<String> toTags(List<Guild> guilds) {
		List<String> tags = new ArrayList<String>();
		for (Guild g : guilds) tags.add(g.getTag());
		return tags;
	}
	
	public static List<Guild> fromTags(List<String> tags) {
		List<Guild> guilds = new ArrayList<Guild>();
		for (String s : tags) guilds.add(Guild.get(s));
		return guilds;
	}
}
