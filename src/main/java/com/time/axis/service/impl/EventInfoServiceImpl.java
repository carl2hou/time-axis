package com.time.axis.service.impl;

import com.time.axis.dao.EventInfoMapper;
import com.time.axis.model.EventInfo;
import com.time.axis.service.EventInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description event_info
 * @author carl
 * @date 2025-06-20
 */
@Service
public class EventInfoServiceImpl implements EventInfoService {

	@Resource
	private EventInfoMapper eventInfoMapper;


	@Override
	public Integer insert(EventInfo eventInfo) {

		return eventInfoMapper.insert(eventInfo);
	}


	@Override
	public Integer delete(int id) {
		int ret = eventInfoMapper.delete(id);
		return ret;
	}


	@Override
	public Integer update(EventInfo eventInfo) {
		int ret = eventInfoMapper.update(eventInfo);
		return ret;
	}


	@Override
	public EventInfo load(int id) {
		return eventInfoMapper.load(id);
	}


}