package com.trent.system.service.impl.menu;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.menu.SysMenuMapper;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.pojo.menu.SysMenu;
import com.trent.system.service.login.IAdminService;
import com.trent.system.service.menu.ISysMenuService;
import nl.basjes.shaded.org.antlr.v4.runtime.atn.AmbiguityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@Service
public class SysMenuServiceImpl  implements ISysMenuService{
	@Autowired
	private IAdminService adminService;
	@Autowired
	private SysMenuMapper menuMapper;
	
	@Override
	public List<SysMenu> getMenuList(Principal principal){
		Admin admin = adminService.getAdminByUserName(principal.getName());
		List<SysMenu> menuList = null;
		if( admin.isAdmin() ){
			menuList = menuMapper.getMenuListAll();
		}
		
		return getChildPerms(menuList,0);
	}
	
	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list 分类表
	 * @param parentId 传入的父节点ID
	 * @return String
	 */
	public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId){
		List<SysMenu> returnList = new ArrayList<SysMenu>();
		for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
		{
			SysMenu t = (SysMenu) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == parentId)
			{
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 *
	 * @param list
	 * @param t
	 */
	private void recursionFn(List<SysMenu> list, SysMenu t)
	{
		// 得到子节点列表
		List<SysMenu> childList = getChildList(list, t);
		t.setChildren(childList);
		for (SysMenu tChild : childList)
		{
			if (hasChild(list, tChild)){
				// 判断是否有子节点
				Iterator<SysMenu> it = childList.iterator();
				while (it.hasNext())
				{
					SysMenu n = (SysMenu) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	/**
	 * 得到子节点列表
	 */
	private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
	{
		List<SysMenu> tlist = new ArrayList<SysMenu>();
		Iterator<SysMenu> it = list.iterator();
		while (it.hasNext()) {
			SysMenu n = (SysMenu) it.next();
			if (n.getParentId().longValue() == t.getMenuId().longValue())
			{
				tlist.add(n);
			}
		}
		return tlist;
	}
	
	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysMenu> list, SysMenu t)
	{
		return getChildList(list, t).size() > 0 ? true : false;
	}
}
