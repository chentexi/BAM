package com.trent.system.service.impl.menu;

import com.trent.system.mapper.menu.SysMenuMapper;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.pojo.menu.SysMenu;
import com.trent.system.service.menu.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class SysMenuServiceImpl implements ISysMenuService{
	
	@Autowired
	private SysMenuMapper menuMapper;
	
	@Override
	public List<SysMenu> getMenuList(Admin admin){
		
		
		List<SysMenu> menuList = null;
		if( admin.isAdmin() ){
			menuList = menuMapper.getMenuListAll();
		}
		
		return getChildPerms(menuList, 0);
	}
	
	@Override
	public List<SysMenu> getMenuLists(SysMenu sysMenuParams, Admin admin){
		List<SysMenu> menuList = null;
		if( admin.isAdmin() ){
			menuList = menuMapper.getMenuLists(sysMenuParams);
		}
		if( StringUtils.isNotBlank(sysMenuParams.getMenuName()) ){
			List<SysMenu> sysMenuList = new ArrayList<>();
			List<SysMenu> menus =new ArrayList<>();
	        getChilds(menuList, sysMenuList,menus);
			menuList=menus;
			return getChildPerms(menuList, menuList.get(0).getParentId());
		}
		System.out.println(menuList);
		return getChildPerms(menuList, 0);
	}
	private void getChilds(List<SysMenu> menuList, List<SysMenu> sysMenuList,List<SysMenu> menus){
		menus.addAll(menuList);
		menuList.forEach(o -> {
			setChilders(o, sysMenuList,menus);
		});
		
	}
	public boolean whetherChildrenMenu(Integer id){
		List<SysMenu> sysMenus = menuMapper.getMenuListById(id);
		return CollectionUtils.isEmpty(sysMenus);
	}
	
	public void setChilders(SysMenu sysMenu, List<SysMenu> menuList,List<SysMenu> menus){
		List<SysMenu> menuListById = menuMapper.getMenuListById(sysMenu.getMenuId());
		//sysMenu.setChildren(menuListById);
		//menus.add(sysMenu);
		menuListById.forEach(o -> {
			menus.add(o);
			if( !whetherChildrenMenu(o.getMenuId()) ){
				setChilders(o, menuMapper.getMenuListById(o.getMenuId()),menus);
			}
		});
		
	}
	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list     分类表
	 * @param parentId 传入的父节点ID
	 * @return String
	 */
	public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId){
		List<SysMenu> returnList = new ArrayList<SysMenu>();
		for( Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext(); ){
			SysMenu t = (SysMenu) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if( t.getParentId() == parentId ){
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
	private void recursionFn(List<SysMenu> list, SysMenu t){
		// 得到子节点列表
		List<SysMenu> childList = getChildList(list, t);
		t.setChildren(childList);
		for( SysMenu tChild: childList ){
			if( hasChild(list, tChild) ){
				// 判断是否有子节点
				Iterator<SysMenu> it = childList.iterator();
				while( it.hasNext() ){
					SysMenu n = (SysMenu) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	/**
	 * 得到子节点列表
	 */
	private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t){
		List<SysMenu> tlist = new ArrayList<SysMenu>();
		Iterator<SysMenu> it = list.iterator();
		while( it.hasNext() ){
			SysMenu n = (SysMenu) it.next();
			if( n.getParentId().longValue() == t.getMenuId().longValue() ){
				tlist.add(n);
			}
		}
		return tlist;
	}
	
	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysMenu> list, SysMenu t){
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
	@Override
	public int delectMenuById(Integer id){
		return menuMapper.delectMenuById(id);
	}
	
	@Override
	public int updateMenuVisible(String menuId, String visible){
		return menuMapper.updateMenuVisible(menuId, visible);
	}
	@Override
	public int updateMenuVisible(SysMenu sysMenu){
		return menuMapper.updateMenuVisible(sysMenu);
	}
	@Override
	public int updateEnable(String menuId, String enable){
		return menuMapper.updateEnable(menuId, enable);
	}
	
	@Override
	public int addMenu(SysMenu sysMenu){
		return menuMapper.addMenu(sysMenu);
	}
	@Override
	public int updateMenu(SysMenu sysMenu){
		return menuMapper.updateMenu(sysMenu);
	}
}
