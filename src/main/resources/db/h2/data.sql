INSERT INTO code_group (group_code, group_name) VALUES ('A01', 'job');
INSERT INTO code_group (group_code, group_name) VALUES ('A02', 'hobby');

INSERT INTO code_detail (group_code, code_value, code_name, sort_seq, use_yn) VALUES ('A01', '001', 'A01001', 0, 'Y');
INSERT INTO code_detail (group_code, code_value, code_name, sort_seq, use_yn) VALUES ('A01', '002', 'A01002', 1, 'Y');
INSERT INTO code_detail (group_code, code_value, code_name, sort_seq, use_yn) VALUES ('A02', '001', 'A02001', 0, 'Y');

INSERT INTO member(user_id, user_pw, user_name) VALUES ('user', '$2a$10$P2NAIK2DW/3nBVPhxVzwgOafS8tnTwt63.DgeGrUS76W3WBSZtijS', '유저'); -- 비밀번호: 1234
INSERT INTO member(user_id, user_pw, user_name) VALUES ('admin', '$2a$10$P2NAIK2DW/3nBVPhxVzwgOafS8tnTwt63.DgeGrUS76W3WBSZtijS', '관리자'); -- 비밀번호: 1234

INSERT INTO member_auth(user_no, auth) VALUES ((SELECT user_no FROM member WHERE user_id = 'user'), 'ROLE_MEMBER');
INSERT INTO member_auth(user_no, auth) VALUES ((SELECT user_no FROM member WHERE user_id = 'admin'), 'ROLE_ADMIN');



