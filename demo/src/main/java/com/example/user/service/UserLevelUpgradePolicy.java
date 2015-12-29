package com.example.user.service;

import com.example.user.User;

public interface UserLevelUpgradePolicy {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	public boolean canUpgradeLevel(User user);
	public void upgradeLevel(User user);
}
