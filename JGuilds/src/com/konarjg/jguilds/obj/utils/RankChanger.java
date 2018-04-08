package com.konarjg.jguilds.obj.utils;

import com.konarjg.jguilds.obj.Rank;

public class RankChanger {
	
	public static double[] getExpectations (Rank a, Rank b) {
		double[] expecs = new double[2];
		expecs[0] = 1.0D / (1.0D + Math.pow(10.0D, (b.getRank() - a.getRank()) / 400.0D));
		expecs[1] = 1.0D / (1.0D + Math.pow(10.0D, (a.getRank() - b.getRank()) / 400.0D));
		return expecs;
	}
	
	public static int getConstant (int rank) {
		if (rank < 2000) return 32;
		if (rank < 2401) return 24;
		return 16;
	}
	
	public static int[] getNewRanks (Rank a, Rank b, boolean victoryA) {
		double[] ests = getExpectations(a, b);
		int[] ret = new int[2];
		int newA = (int) (a.getRank() + getConstant(a.getRank()) * ((victoryA ? 1 : 0) - ests[0]));
		int newB = (int) (b.getRank() + getConstant(b.getRank()) * ((victoryA ? 0 : 1) - ests[1]));
		ret[0] = Math.round(newA);
		ret[1] = Math.round(newB);
		return ret;
	}
}
