package yungshun.chang.springbootticketmanagementcache.service;

import yungshun.chang.springbootticketmanagementcache.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Integer userid);

    void createUser(Integer userid, String username);

    void updateUser(Integer userid, String username);

    void deleteUser(Integer userid);
}
