package com.konarjg.jguilds.obj;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.konarjg.jguilds.data.FileManager;
import com.konarjg.jguilds.obj.utils.RegionUtils;

public class Region {
	
	String name;
	Guild guild;
	Location center;
	Location lowerLoc;
	Location upperLoc;
	int size;
	
	Region (String name) {
		this.name = name;
		RegionUtils.addRegion(this);
	}
	
	public Region (Guild guild, Location center, int size) {
		this.guild = guild;
		this.name = guild.getTag();
		this.center = center;
		this.size = size;
		RegionUtils.addRegion(this);
		reCalculate();
	}
	
	public static Region get(String name) {
		for (Region r : RegionUtils.getRegions()) {
			if (r.getName().equalsIgnoreCase(name)) return r;
		}
		return new Region(name);
	}
	
	public void reCalculate () {
		Vector low = new Vector(center.getBlockX() - size, 0, center.getBlockZ() - size);
		Vector up = new Vector(center.getBlockX() + size, 256, center.getBlockZ() + size);
		this.lowerLoc = low.toLocation(center.getWorld());
		this.upperLoc = up.toLocation(center.getWorld());
	}

	public String getName() {
		return name;
	}

	public Guild getGuild() {
		return guild;
	}

	public Location getCenter() {
		return center;
	}

	public Location getLowerLoc() {
		return lowerLoc;
	}

	public Location getUpperLoc() {
		return upperLoc;
	}

	public int getSize() {
		return size;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public void setCenter(Location center) {
		this.center = center;
		reCalculate();
	}

	public void setLowerLoc(Location lowerLoc) {
		this.lowerLoc = lowerLoc;
	}

	public void setUpperLoc(Location upperLoc) {
		this.upperLoc = upperLoc;
	}

	public void setSize(int size) {
		this.size = size;
		reCalculate();
	}
	
	public void delete () {
		RegionUtils.removeRegion(this);
		this.getCenter().getBlock().setType(Material.AIR);
		this.guild.setRegion(null);
		this.guild = null;
		this.center = null;
		this.lowerLoc = null;
		this.upperLoc = null;
		if (new  File (FileManager.getRegionsFolder(), getName() + ".yml").exists()) {
			new  File (FileManager.getRegionsFolder(), getName() + ".yml").delete();
		}
		this.name = null;
	}
	
	public boolean isIn(Location l) {
		reCalculate();
		if (lowerLoc == null || upperLoc == null || l == null) return false;
		if(((l.getBlockX()>getLowerLoc().getBlockX() &&( l.getBlockX() < getUpperLoc().getBlockX()) && 
		  (l.getBlockY()>getLowerLoc().getBlockY() &&( l.getBlockY() < getUpperLoc().getBlockY())) 
		  && (l.getBlockZ()>getLowerLoc().getBlockZ() &&( l.getBlockZ() < getUpperLoc().getBlockZ()))))) return true;
		
		return false;
	}
	
	public static boolean canBuild(Player p, Block b) {
		if (p == null || b == null) return false;
		Location l = p.getLocation();
		if (!RegionUtils.isIn(l)) return true;
		Region r = RegionUtils.inWich(l);
		User u = User.get(p.getName());
		Guild g = r.getGuild();
		if (!u.hasGuild() || !g.equals(u.getGuild())) return false;
		if (p.getLocation().equals(r.getCenter())) return false;
		return true;
	}
	
}
