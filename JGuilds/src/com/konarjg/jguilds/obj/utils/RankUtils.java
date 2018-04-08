package com.konarjg.jguilds.obj.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.konarjg.jguilds.obj.Guild;
import com.konarjg.jguilds.obj.Rank;
import com.konarjg.jguilds.obj.User;

public class RankUtils{

	static List<Rank> users = new ArrayList<Rank> ();
	static List<Rank> guilds = new ArrayList<Rank> ();
	
	public static List<Rank> getUserRanks () {
		return new ArrayList<Rank>(users);
	}
	
	public static List<Rank> getGuildRanks () {
		return new ArrayList<Rank>(guilds);
	}
	
	public static void update (User u) {
		if (!users.contains(u.getRank())) users.add(u.getRank());
		Collections.sort(users);
		if (u.hasGuild()) update (u.getGuild());
	}
	
	public static void update (Guild g) {
		if (!users.contains(g.getRank())) users.add(g.getRank());
		Collections.sort(guilds);
	}
	
	public static void updateAll () {
		Collections.sort(users);
		Collections.sort(guilds);
	}
	
	public static void addRank (Rank r) {
		if (r.getType() == RankType.USER) {
			users.add(r);
		}
		else {
			guilds.add(r);
		}
		updateAll();
	}
	
	public static void removeRank (Rank r) {
		if (r.getType() == RankType.USER) {
			users.remove(r);
		}
		else {
			guilds.remove(r);
		}
		updateAll();
	}
	
	public static Rank getByTag (String tag) {
		for (Rank r : guilds) {
			if (r.getGuild().getTag().equalsIgnoreCase(tag)) return r;
		}
		return null;
	}
	
	public static int getPosition (User u) {
		return users.indexOf(u.getRank())+1;
	}
	
	public static int getPosition (Guild g) {
		return guilds.indexOf(g.getRank())+1;
	}
	
	public static User getUserByPosition (int pos) {
		return users.get(pos-1).getUser();
	}
	public static Guild getGuildByPosition (int pos) {
		return guilds.get(pos-1).getGuild();
	}
	
}
