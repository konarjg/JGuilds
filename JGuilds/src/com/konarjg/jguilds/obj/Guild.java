package com.konarjg.jguilds.obj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import com.konarjg.jguilds.data.FileManager;
import com.konarjg.jguilds.obj.utils.GuildUtils;
import com.konarjg.jguilds.obj.utils.RankUtils;
import com.konarjg.jguilds.obj.utils.RegionUtils;

public class Guild {
	String name;
	String tag;
	User admin;
	List<User> members;
	List<User> mods;
	List<User> invited;
	List<Guild> allies;
	List<Guild> enemies;
	List<Guild> allyInvs;
	List<Guild> neutralInvs;
	Region region;
	Rank rank;
	int lives;
	boolean canConquer;
	
	Guild(String tag) {
		this.tag = tag;
		this.lives = 3;
		GuildUtils.addGuild(this);
		canConquer = true;
	}
	
	public static Guild get(String tag) {
		for (Guild g : GuildUtils.getGuilds()) {
			if(g.getTag().equalsIgnoreCase(tag)) return g;
		}
		return new Guild (tag);
	}
	
	public void delete () {
		GuildUtils.removeGuild(this);
		this.region.delete();
		for(Guild g : allies) g.removeAlly(this);
		for(Guild g : enemies) g.removeEnemy(this);
		for(Guild g : GuildUtils.getGuilds()) {
			if (g.getAllyInvs().contains(this)) g.removeAllyInv(this);
			if (g.getNeutralInvs().contains(this)) g.removeNeutralInv(this);
		}
		RankUtils.removeRank(this.rank);
		RankUtils.updateAll();
		for (User u : members) u.setGuild(null);
		if (new  File (FileManager.getGuildsFolder(), getTag() + ".yml").exists()) {
			new  File (FileManager.getGuildsFolder(), getTag() + ".yml").delete();
		}
	}
	
	public boolean hasRegion() {
		return (region!=null);
	}
	
	public String getName() {
		return name;
	}
	public String getTag() {
		return tag;
	}
	public User getAdmin() {
		return admin;
	}
	public List<User> getMembers() {
		if(members == null) members = new ArrayList<User> ();
		return members;
	}
	public List<User> getMods() {
		if(mods == null) mods = new ArrayList<User> ();
		return mods;
	}
	public List<User> getInvited() {
		if(invited == null) invited = new ArrayList<User> ();
		return invited;
	}
	public List<Guild> getAllies() {
		if(allies == null) allies = new ArrayList<Guild> ();
		return allies;
	}
	public List<Guild> getEnemies() {
		if(enemies == null) enemies = new ArrayList<Guild> ();
		return enemies;
	}
	public List<Guild> getAllyInvs() {
		if(allyInvs == null) allyInvs = new ArrayList<Guild> ();
		return allyInvs;
	}
	public List<Guild> getNeutralInvs() {
		if(neutralInvs == null) neutralInvs = new ArrayList<Guild> ();
		return neutralInvs;
	}
	public Region getRegion() {
		return region;
	}
	public Rank getRank() {
		if(rank == null) rank = new Rank(this);
		return rank;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public void setMods(List<User> mods) {
		this.mods = mods;
	}
	public void setInvited(List<User> invited) {
		this.invited = invited;
	}
	public void setAllies(List<Guild> allies) {
		this.allies = allies;
	}
	public void setEnemies(List<Guild> enemies) {
		this.enemies = enemies;
	}
	public void setAllyInvs(List<Guild> allyInvs) {
		this.allyInvs = allyInvs;
	}
	public void setNeutralInvs(List<Guild> neutralInvs) {
		this.neutralInvs = neutralInvs;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public void addInvite(User u) {
		if(invited == null) invited = new ArrayList<User> ();
		invited.add(u);
	}
	public void addMember(User u) {
		if(members == null) members = new ArrayList<User> ();
		members.add(u);
		u.setGuild(this);
		//TODO add to region
	}
	public void addMod(User u) {
		if(mods == null) mods = new ArrayList<User> ();
		mods.add(u);
	}
	public void addAlly(Guild g) {
		if(allies == null) allies = new ArrayList<Guild> ();
		allies.add(g);
	}
	public void addEnemy(Guild g) {
		if(enemies == null) enemies = new ArrayList<Guild> ();
		enemies.add(g);
	}
	public void addAllyInv(Guild g) {
		if(allyInvs == null) allyInvs = new ArrayList<Guild> ();
		allyInvs.add(g);
	}
	public void addNeutralInv(Guild g) {
		if(neutralInvs == null) neutralInvs = new ArrayList<Guild> ();
		neutralInvs.add(g);
	}
	
	public void removeInvite (User u) {
		invited.remove(u);
	}
	public void removeMember (User u) {
		u.setGuild(null);
		members.remove(u);
	}
	public void removeMod (User u) {
		mods.remove(u);
	}
	public void removeAlly (Guild g) {
		allies.remove(g);
	}
	
	public void removeEnemy (Guild g) {
		enemies.remove(g);
	}
	public void removeAllyInv (Guild g) {
		allyInvs.remove(g);
	}
	public void removeNeutralInv (Guild g) {
		neutralInvs.remove(g);
	}
	public void addLive() {
		this.lives++;
	}
	public void removeLive() {
		this.lives--;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean canConquer() {
		return canConquer;
	}

	public void setConquer(boolean canConquer) {
		this.canConquer = canConquer;
	}

	public String toString () {
		return tag;
	}
	
	
}
