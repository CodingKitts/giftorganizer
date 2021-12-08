package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    boolean existsFriendshipByOwnerEmailAndRequestedFriendEmail(String ownerEmail, String requestedFriendEmail);
    List<Friendship> findFriendshipsByOwnerEmail(String ownerEmail);
    int deleteFriendshipByOwnerEmailAndRequestedFriendEmail(String ownerEmail, String requestedFriendEmail);
}
