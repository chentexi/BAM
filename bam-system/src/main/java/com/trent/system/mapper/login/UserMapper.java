package com.trent.system.mapper.login;

import com.trent.system.pojo.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
@Repository
public interface UserMapper{
	User login(String userName, String password);
	
	User selectUserByName(String userName);
	
	List<User> findUser(User user);
	
	int upadteEnabled(User user);
	
	int delUserById(String[] split);
	
	int addUser(User user);
	
	int updateUser(User user);
	
}
