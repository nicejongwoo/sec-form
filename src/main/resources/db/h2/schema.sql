-- 코드
CREATE TABLE code_group (
    group_code CHAR(3) NOT NULL,
    group_name VARCHAR(30) NOT NULL,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y',
    reg_date TIMESTAMP NOT NULL DEFAULT now(),
    update_date TIMESTAMP DEFAULT now(),
    PRIMARY KEY (group_code)
);

CREATE TABLE code_detail(
    group_code CHAR(3) NOT NULL,
    code_value VARCHAR(3) NOT NULL,
    code_name VARCHAR(30) NOT NULL,
    sort_seq INT NOT NULL,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y',
    reg_date TIMESTAMP NOT NULL DEFAULT now(),
    update_date TIMESTAMP DEFAULT now(),
    PRIMARY KEY (group_code, code_value)
);

-- 회원 및 권한
CREATE TABLE member(
    user_no INT(5) AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_pw VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    job VARCHAR(3) NOT NULL DEFAULT '00',
    coin INT(10) DEFAULT 0,
    reg_date TIMESTAMP NOT NULL DEFAULT now(),
    update_date TIMESTAMP DEFAULT now(),
    enabled CHAR(1) DEFAULT '1',
    PRIMARY KEY (user_no)
);

CREATE TABLE member_auth(
    user_no INT(5) NOT NULL,
    auth VARCHAR(50) NOT NULL
);

ALTER TABLE member_auth ADD CONSTRAINT fk_member_auth_user_no
FOREIGN KEY (user_no) REFERENCES
member(user_no);

-- 게시판
CREATE TABLE board (
    board_no INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(50) NOT NULL,
    reg_date TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (board_no)
);

-- 공지사항
CREATE TABLE notice (
    notice_no INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    reg_date TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (notice_no)
);

-- 상품
CREATE TABLE item (
    item_id INT(5) AUTO_INCREMENT,
    item_name VARCHAR(20),
    price INT(6),
    description VARCHAR(50),
    picture_url VARCHAR(200),
    PRIMARY KEY (item_id)
);

-- 자료실 및 첨부
CREATE TABLE pds (
    item_id INT(5) AUTO_INCREMENT,
    item_name VARCHAR(20),
    view_cnt INT(6) DEFAULT 0,
    description VARCHAR(50),
    PRIMARY KEY (item_id)
);

CREATE TABLE pds_attach (
    fullName VARCHAR(150) NOT NULL,
    item_id INT(5) NOT NULL,
    down_cnt INT(6) DEFAULT 0,
    reg_date TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (fullName)
);


