package edu2.innotech;

import edu2.innotech.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Long> {
    /*
    @Query("select u from users u where u.username = ?1")
    Users findByUserName(String userName);
    */
}
