package com.konarjg.jguilds.obj;

import com.konarjg.jguilds.obj.utils.RankType;
import com.konarjg.jguilds.obj.utils.RankUtils;

public class Rank implements Comparable<Rank>{

	int rank;
	int kills;
	int deaths;
	User user;
	Guild guild;
	RankType type;
	
	public Rank(User user) {
		type = RankType.USER;
		this.user = user;
		RankUtils.addRank(this);
	}
	
	public Rank(Guild guild) {
		type = RankType.GUILD;
		this.guild = guild;
		RankUtils.addRank(this);
	}

	public int getRank() {
		if(getType() == RankType.USER) return rank;
		int toReturn = 0;
		for (User u : guild.getMembers()) {
			toReturn += u.getRank().getRank();
		}
		return toReturn/guild.getMembers().size();
	}
	
	public void addKill () {
		this.kills++;
	}
	
	public void addDeath() {
		this.deaths++;
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public User getUser() {
		return user;
	}

	public Guild getGuild() {
		return guild;
	}

	public RankType getType() {
		return type;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public void setType(RankType type) {
		this.type = type;
	}
	
	public String getOwnerID() {
		if (this.type.equals(RankType.USER)) return user.getName();
		return guild.getTag();
	}
	
	public String toString() {
		return Integer.toString(rank);
	}

	@Override
	public int compareTo(Rank rank) {
		int i = Integer.compare(rank.getRank(), getRank());
		if (i == 0) i = getOwnerID().compareTo(rank.getOwnerID());
		return i;
	}

}
