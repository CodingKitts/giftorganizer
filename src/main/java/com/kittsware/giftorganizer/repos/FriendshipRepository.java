package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    boolean existsFriendshipByListOwnerIdAndRequestedFriendId(Long listOwnerId, Long requestedFriendId);
    List<Friendship> findFriendshipsByListOwnerId(Long listOwnerId);
}
