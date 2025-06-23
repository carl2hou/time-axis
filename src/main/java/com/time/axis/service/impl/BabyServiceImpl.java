package com.time.axis.service.impl;

import com.time.axis.dao.BabyMapper;
import com.time.axis.model.Baby;
import com.time.axis.service.BabyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description t_baby
 * @author carl
 * @date 2025-06-20
 */
@Service
public class BabyServiceImpl implements BabyService {

	@Resource
	private BabyMapper tBabyMapper;


	@Override
	public Integer insert(Baby tBaby) {
		Date now = new Date();
		tBaby.setCreateTime(now);
		tBaby.setUpdateTime(now);
		return tBabyMapper.insert(tBaby);
	}


	@Override
	public Integer delete(int id) {
		int ret = tBabyMapper.delete(id);
		return ret;
	}


	@Override
	public Integer update(Baby tBaby) {
		int ret = tBabyMapper.update(tBaby);
		return ret;
	}


	@Override
	public Baby load(int id) {
		return tBabyMapper.load(id);
	}



}