package pl.coderslab.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from User u where not u=?1")
    List<User> findAllWithOutUser(User user);
}
