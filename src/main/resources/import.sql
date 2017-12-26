INSERT INTO `subject_reaction` (`reaction_type`) VALUES ('AGREE');
INSERT INTO `subject_reaction` (`reaction_type`) VALUES ('DISAGREE');
INSERT INTO `subject_reaction` (`reaction_type`) VALUES ('NETURAL');

INSERT INTO `post_reaction` (`reaction_type`) VALUES ('AGREE');
INSERT INTO `post_reaction` (`reaction_type`) VALUES ('DISAGREE');
INSERT INTO `post_reaction` (`reaction_type`) VALUES ('NETURAL');

INSERT INTO `bias` (`type`) VALUES ('EXTREME_LEFT');
INSERT INTO `bias` (`type`) VALUES ('LEFT');
INSERT INTO `bias` (`type`) VALUES ('MID');
INSERT INTO `bias` (`type`) VALUES ('RIGHT');
INSERT INTO `bias` (`type`) VALUES ('EXTREME_RIGHT');

INSERT INTO `subject` (`title`, `created_at`) VALUES ('노룩패스 실화인가', now());
