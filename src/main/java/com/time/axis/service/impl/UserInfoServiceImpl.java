package com.time.axis.service.impl;

import com.time.axis.dao.UserInfoMapper;
import com.time.axis.model.UserInfo;
import com.time.axis.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description user_info
 * @author carl
 * @date 2025-06-20
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper userInfoMapper;


	@Override
	public Integer insert(UserInfo userInfo) {
		Date now = new Date();
		userInfo.setCreateTime(now);
		userInfo.setUpdateTime(now);
		userInfo.setLoginTime(now);
		return userInfoMapper.insert(userInfo);
	}


	@Override
	public Integer delete(int id) {

		int ret = userInfoMapper.delete(id);
		return ret;
	}


	@Override
	public Integer update(UserInfo userInfo) {
		Date now = new Date();
		userInfo.setUpdateTime(now);
		return userInfoMapper.update(userInfo);
	}


	@Override
	public UserInfo load(int id) {
		return userInfoMapper.load(id);
	}

	@Override
	public UserInfo loadByOpenId(String openId) {
		return userInfoMapper.loadByOpenId(openId);
	}

	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<UserInfo> pageList = userInfoMapper.pageList(offset, pagesize);
		int totalCount = userInfoMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}