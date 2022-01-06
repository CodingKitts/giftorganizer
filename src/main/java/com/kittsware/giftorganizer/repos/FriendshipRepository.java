package com.kittsware.giftorganizer.repos;

import com.kittsware.giftorganizer.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    boolean existsFriendshipBySenderEmailAndRecipientEmail(String senderEmail, String recipientEmail);
    List<Friendship> findFriendshipsBySenderEmailAndAcceptedTrue(String senderEmail);
    List<Friendship> findFriendshipsByRecipientEmailAndAcceptedTrue(String recipientEmail);
    int deleteFriendshipBySenderEmailAndRecipientEmail(String senderEmail, String recipientEmail);

    List<Friendship> findFriendshipsBySenderEmailAndAcceptedTrueOrRecipientEmailAndAcceptedTrue(String senderEmail, String recipientEmail);

    List<Friendship> findFriendshipsByRecipientEmailAndAcceptedFalse(String recipientEmail);
    List<Friendship> findFriendshipsBySenderEmailAndAcceptedFalse(String senderEmail);

    @Modifying
    @Query(value = "update friendship f set f.is_accepted = true where f.friendship_id = :friendshipId", nativeQuery = true)
    Friendship acceptFriendshipRequest(Long friendshipId);
}
