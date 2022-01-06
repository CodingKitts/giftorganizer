INSERT INTO user (user_id, user_password, user_name, user_email) VALUES ( '1', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'john', 'user@user.com' );
INSERT INTO user (user_id, user_password, user_name, user_email) VALUES ( '2', '$2a$10$vKVdI.pYbGnl9mpNsTFt4u8uQtdQwspy.SVVFQBrpW4sdRD1MTjAO', 'greg', 'greg@user.com' );
INSERT INTO user (user_id, user_password, user_name, user_email) VALUES ( '3', '$2a$10$.KCclfTrXPgsOSSC5AnAMu4pZVKsxfblEmuwmIU.UXjJdBJguqqP2', 'bill', 'bill@user.com' );



INSERT INTO friendship (friendship_id, sender_email, recipient_email, accepted) VALUES ( '1', 'bob@user.com', 'user@user.com', 'true' );
INSERT INTO friendship (friendship_id, sender_email, recipient_email, accepted) VALUES ( '2', 'bob@user.com', 'greg@user.com', 'true' );
INSERT INTO friendship (friendship_id, sender_email, recipient_email, accepted) VALUES ( '3', 'user@user.com', 'bob@user.com', 'true' );

