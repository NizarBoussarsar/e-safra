package services.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import domain.User;

@Remote
public interface UserServicesRemote {
	
	Boolean addUser(User user);

	Boolean deleteUser(User user);

	Boolean deleteUserById(Integer id);

	Boolean updateUser(User user);

	User findUserById(Integer id);

	List<User> findAllUsers();

}
