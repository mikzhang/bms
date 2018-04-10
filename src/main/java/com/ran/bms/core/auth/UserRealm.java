package com.ran.bms.core.auth;

import com.wf.etp.authz.IUserRealm;
import com.wf.etp.authz.SubjectUtil;
import com.ran.bms.system.model.Permission;
import com.ran.bms.system.model.User;
import com.ran.bms.system.service.PermissionService;
import com.ran.bms.system.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserRealm
 * 
 * @author wangfan
 * @date 2018-1-22 上午8:30:17
 */
public class UserRealm extends IUserRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public Set<String> getUserRoles(String userId) {
		Set<String> roles = new HashSet<String>();
		User user = userService.getUserById(userId);
		if(user != null){
			roles.add(user.getRoleId());
		}
		return roles;
	}

	@Override
	public Set<String> getUserPermissions(String userId) {
		Set<String> permissionValues = new HashSet<String>();
		List<String> userRoles = SubjectUtil.getInstance().getUserRoles(userId);
		if(userRoles.size()>0){
			List<Permission> permissions = permissionService.getPermissionsByRole(userRoles.get(0));
			for (int i = 0; i < permissions.size(); i++) {
				String permissionValue = permissions.get(i).getPermissionValue();
				if(permissionValue!=null && !permissionValue.isEmpty()){
					permissionValues.add(permissionValue);
				}
			}
		}
		return permissionValues;
	}
}