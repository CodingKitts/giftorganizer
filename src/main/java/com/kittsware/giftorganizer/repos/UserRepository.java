package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserEmail(String userEmail);

    //The int will return the # of deleted entities, because email is unique, should only be 1.
    int deleteUserByUserEmail(String userEmail);

    @Modifying
    @Query(value = "update User u set u.user_name = :userName where u.user_email = :userEmail", nativeQuery = true)
    int updateUserName(@Param("userName") String userName, @Param("userEmail") String userEmail);

    boolean existsByUserEmail(String userEmail);
}
