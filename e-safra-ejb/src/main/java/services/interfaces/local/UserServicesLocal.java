package services.interfaces.local;

import java.util.List;

import javax.ejb.Local;

import domain.User;

@Local
public interface UserServicesLocal {

	Boolean addUser(User user);

	Boolean deleteUser(User user);

	Boolean deleteUserById(Integer id);

	Boolean updateUser(User user);

	User findUserById(Integer id);

	List<User> findAllUsers();
}
