package com.ran.bms.core.auth;

import com.wf.etp.authz.IEtpCache;
import com.ran.bms.core.utils.RedisUtil;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限缓存的实现
 * 
 * @author wangfan
 * @date 2018-2-2 上午11:35:42
 */
public class EtpCache extends IEtpCache {
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public List<String> getSet(String key) {
		return redisUtil.lRange(key, 0, -1);
	}

	@Override
	public boolean putSet(String key, Set<String> values) {
		return redisUtil.lLeftPushAll(key, values) > 0;
	}

	@Override
	public boolean removeSet(String key, String value) {
		return redisUtil.lRemove(key, 0, value) > 0;
	}

	@Override
	public boolean delete(String key) {
		redisUtil.delete(key);
		return true;
	}

	@Override
	public boolean delete(Collection<String> keys) {
		redisUtil.delete(keys);
		return true;
	}

	@Override
	public Set<String> keys(String pattern) {
		return redisUtil.keys(pattern);
	}

}
