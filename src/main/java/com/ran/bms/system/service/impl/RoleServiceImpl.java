package com.ran.bms.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ran.bms.core.PageResult;
import com.ran.bms.core.exception.BusinessException;
import com.ran.bms.core.exception.ParameterException;
import com.ran.bms.core.utils.UUIDUtil;
import com.ran.bms.system.dao.RoleMapper;
import com.ran.bms.system.model.Role;
import com.ran.bms.system.model.RoleExample;
import com.ran.bms.system.service.RoleService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleBaseMapper;

	@Override
	public PageResult<Role> getRoles(int pageNum, int pageSize, String searchKey, String searchValue, Integer isDelete) {
		PageResult<Role> result = new PageResult<Role>();
		RoleExample example = new RoleExample();
		example.setOrderByClause("create_time asc");
		RoleExample.Criteria criteria = example.createCriteria();
		if(isDelete!=null){
			criteria.andIsDeleteEqualTo(isDelete);
		}
		if(searchKey!=null){
			if(searchKey.equals("role_id")){
				criteria.andRoleIdLike("%"+searchValue+"%");
			}else if(searchKey.equals("role_name")){
				criteria.andRoleNameLike("%"+searchValue+"%");
			}else if(searchKey.equals("comments")){
				criteria.andCommentsLike("%"+searchValue+"%");
			}
		}
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		result.setData(roleBaseMapper.selectByExample(example));
		result.setCount(startPage.getTotal());
		return result;
	}
	
	@Override
	public boolean addRole(Role role) {
		role.setRoleId(UUIDUtil.randomUUID8());
		role.setCreateTime(new Date());
		return roleBaseMapper.insertSelective(role)>0;
	}

	@Override
	public boolean updateRole(Role role) {
		return roleBaseMapper.updateByPrimaryKeySelective(role)>0;
	}

	@Override
	public boolean updateRoleStatus(String roleId, int isDelete) throws ParameterException {
		if(isDelete!=0&&isDelete!=1){
			throw new ParameterException();
		}
		Role role = new Role();
		role.setRoleId(roleId);
		role.setIsDelete(isDelete);
		return roleBaseMapper.updateByPrimaryKeySelective(role)>0;
	}
	
	@Override
	public Role getRoleById(String roleId) {
		return roleBaseMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public boolean deleteRole(String roleId) throws BusinessException {
		try {
			return roleBaseMapper.deleteByPrimaryKey(roleId)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("角色已被关联");
		}
	}
}
