package com.dist.service;

import com.dist.bean.Boss;

public interface BossBussinessService {

	void saveBoss(Boss boss);

	void removeBossById(String boss_id);

	void updateBossById(Boss boss);

}
