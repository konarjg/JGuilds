package com.konarjg.jguilds.obj;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.konarjg.jguilds.data.FileManager;
import com.konarjg.jguilds.obj.utils.ChatType;
import com.konarjg.jguilds.obj.utils.RankUtils;
import com.konarjg.jguilds.obj.utils.UserUtils;

public class User {
	String name;
	Guild guild;
	Rank rank;
	ChatType chat;
	
	User(String name) {
		this.name = name;
		UserUtils.addUser(this);
		RankUtils.update(this);
	}
	
	
	public String getName() {
		return name;
	}
	public Guild getGuild() {
		return guild;
	}
	public Rank getRank() {
		if (this.rank != null) return rank;
		rank = new Rank (this);
		rank.setKills(0);
		rank.setDeaths(0);
		rank.setRank(1000);
		return rank;
	}
	public ChatType getChat() {
		if (chat != null) return chat;
		chat = ChatType.PUBLIC;
		return chat;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGuild(Guild guild) {
		this.guild = guild;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	public void setChat(ChatType chat) {
		this.chat = chat;
	}
	
	public boolean isOnline () {
		return (Bukkit.getPlayer(name)!=null);
	}
	
	public Player getPlayer () {
		return Bukkit.getPlayer(name);
	}
	
	public boolean isInvited (Guild g) {
		for (User u : g.getInvited()) {
			if(u.equals(this)) return true;
		}
		return false;
	}
	
	public boolean isAdmin() {
		if(!hasGuild()) return false;
		if(guild.getAdmin().equals(this)) return true;
		return false;
	}
	
	public boolean isMod () {
		if(!hasGuild()) return false;
		if(guild.getMods().contains(this)) return true;
		return false;
	}
	
	public boolean hasGuild () {
		return (guild!=null);
	}
	
	public String toString () {
		return name;
	}
	
	public static User get(String name) {
		for (User u : UserUtils.getUsers()) {
			if(u.getName().equalsIgnoreCase(name)) return u;
		}
		return new User(name);
	}
	
	public static User get(Player p) {
		for (User u : UserUtils.getUsers()) {
			if(u.getName().equalsIgnoreCase(p.getName())) return u;
			if(!u.getName().equalsIgnoreCase(p.getName())) {
				if (new  File (FileManager.getUsersFolder(), u.getName() + ".yml").exists()) {
					new  File (FileManager.getUsersFolder(), u.getName() + ".yml").delete();
				}
				u.setName(p.getName());
			}
		}
		return new User(p.getName());
	}
	
	
	
}
