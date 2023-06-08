USE iyo8916;


DROP TABLE IF EXISTS DECLARATION_T;
DROP TABLE IF EXISTS BOOK_REVIEW_T;
DROP TABLE IF EXISTS MEETING_NOTICE_T;
DROP TABLE IF EXISTS MEETING_CHAT_T;
DROP TABLE IF EXISTS MEETING_MEMBER_T;
DROP TABLE IF EXISTS USER_LEAVE_T;
DROP TABLE IF EXISTS USER_ACCESS_T;
DROP TABLE IF EXISTS USER_SLEEP_T;
DROP TABLE IF EXISTS PAYMENT_T;
DROP TABLE IF EXISTS QNA_T;
DROP TABLE IF EXISTS CART_DETAIL_T;
DROP TABLE IF EXISTS USER_RECOMMENED_T;
DROP TABLE IF EXISTS MSG_T;
DROP TABLE IF EXISTS OPEN_TALK_BOARD_DETAIL_T;
DROP TABLE IF EXISTS CART_T;
DROP TABLE IF EXISTS ANNOUNCEMENT_T;
DROP TABLE IF EXISTS OPEN_TALK_BOARD_T;
DROP TABLE IF EXISTS MEETING_T;
DROP TABLE IF EXISTS BOOK_T;
DROP TABLE IF EXISTS USER_T;








CREATE TABLE MEETING_T (
	MEETING_NO			INT				NOT NULL 	AUTO_INCREMENT,
	USER_NO				INT				NOT NULL,
	MEETING_NAME		VARCHAR(100)	NOT NULL,
	MEETING_COUNT		INT				NOT NULL,
	IMAGE				VARCHAR(1000)	NOT NULL,
	CAPACITY			INT				NULL,
	MEETING_START_AT	DATETIME		NOT NULL,
	END_AT				DATETIME		NOT NULL,
	CATEGORY			VARCHAR(50)		NOT NULL,
    CONSTRAINT PK_MEETING PRIMARY KEY(MEETING_NO)
);

CREATE TABLE BOOK_REVIEW_T (
	BOOK_REVIEW_NO				INT			NOT NULL 	AUTO_INCREMENT ,
	BOOK_NO						VARCHAR(60)	NOT NULL,
	USER_NO						INT			NOT	NULL,
	BOOK_REVIEW					TEXT		NULL,
	BOOK_RATING					DOUBLE		NULL,
	BOOK_REVIEW_CREATED_AT		DATETIME	NULL,
    CONSTRAINT PK_BOOK_REVIEW PRIMARY KEY(BOOK_REVIEW_NO)
);

CREATE TABLE USER_T (
	USER_NO					INT				NOT NULL 	AUTO_INCREMENT,
	USER_ID					VARCHAR(40)		NOT NULL 	UNIQUE,
	USER_PASSWORD			VARCHAR(64)		NOT NULL,
	USER_NAME				VARCHAR(40)		NULL,
	USER_GENDER				VARCHAR(2)		NULL,
	USER_EMAIL				VARCHAR(100)	NOT NULL 	UNIQUE,
	USER_MOBILE				VARCHAR(15)		NULL,
	USER_BIRTHYEAR			VARCHAR(4)		NULL,
	USER_BIRTHDATE			VARCHAR(4)		NULL,
	POSTCODE				VARCHAR(5)		NULL,
	ROAD_ADDRESS			VARCHAR(100)	NULL,
	JIBUN_ADDRESS			VARCHAR(100)	NULL,
	DETAIL_ADDRESS			VARCHAR(100)	NULL,
	EXTRA_ADDRESS			VARCHAR(100)	NULL,
	AGREECODE				INT				NOT NULL,
	JOINED_AT				DATETIME		NULL,
	PW_MODIFIED_AT			DATETIME		NULL,
	AUTOLOGIN_ID			VARCHAR(32)		NULL,
	AUTOLOGIN_EXPIRED_AT	DATETIME		NULL,
	USER_PROFILE			VARCHAR(300)	NULL,
	USER_INTRODUCE			VARCHAR(300)	NULL,
	USER_POINT				INT				NULL,
	USER_GRADE				INT				NOT NULL,
	USER_SOCIAL				INT				NULL,
    CONSTRAINT PK_USER PRIMARY KEY(USER_NO)
);

CREATE TABLE USER_ACCESS_T (
	USER_ACCESS_NO	INT			NOT NULL,
	USER_ACCESS_ID	VARCHAR(40)	NOT NULL 	UNIQUE,
	LAST_LOGIN_AT	DATETIME	NULL
);

CREATE TABLE USER_LEAVE_T (
	USER_LEAVE_ID		VARCHAR(40)		NOT NULL UNIQUE,
	USER_LEAVE_EMAIL	VARCHAR(100)	NOT NULL UNIQUE,
	JOINED_LEAVE_AT		DATETIME		NULL,
	LEAVED_AT			DATETIME		NULL
);

CREATE TABLE USER_SLEEP_T (
	USER_SLEEP_NO				INT				NOT NULL 	AUTO_INCREMENT,
	USER_SLEEP_ID				VARCHAR(40)		NOT NULL 	UNIQUE,
	USER_SLEEP_PASSWORD			VARCHAR(64)		NOT NULL,
	USER_SLEEP_NAME				VARCHAR(40)		NULL,
	USER_SLEEP_GENDER			VARCHAR(2)		NULL,
	USER_SLEEP_EMAIL			VARCHAR(100)	NOT NULL 	UNIQUE,
	USER_SLEEP_MOBILE			VARCHAR(15)		NULL,
	USER_SLEEP_BIRTHYEAR		VARCHAR(4)		NULL,
	USER_SLEEP_BIRTHDATE		VARCHAR(4)		NULL,
	USER_SLEEP_POSTCODE			VARCHAR(5)		NULL,
	USER_SLEEP_ROAD_ADDRESS		VARCHAR(100)	NULL,
	USER_SLEEP_JIBUN_ADDRESS	VARCHAR(100)	NULL,
	USER_SLEEP_DETAIL_ADDRESS	VARCHAR(100)	NULL,
	USER_SLEEP_EXTRA_ADDRESS	VARCHAR(100)	NULL,
	USER_SLEEP_AGREECODE		INT				NOT NULL,
	USER_SLEEP_JOINED_AT		DATETIME		NULL,
	USER_SLEEP_PW_MODIFIED_AT	DATETIME		NULL,
	USER_SLEEP_PROFILE			VARCHAR(300)	NULL,
	USER_SLEEP_INTRODUCE		VARCHAR(300)	NULL,
	USER_SLEEP_POINT			INT				NULL,
	USER_SLEEP_GRADE			INT				NULL,
	USER_SLEEP_SOCIAL			INT				NULL,
	USER_SLEEP_AT				DATETIME		NULL,
    CONSTRAINT PK_USER_SLEEP PRIMARY KEY(USER_SLEEP_NO)
);

CREATE TABLE MEETING_MEMBER_T (
	MEETING_MEMBER_NO		INT	NOT NULL 	AUTO_INCREMENT,
	USER_NO					INT	NOT NULL,
	MEETING_NO				INT	NOT NULL,
	MEETIN_MEMBER_STATUS	INT	NULL,
    CONSTRAINT PK_MEETING_MEMBER PRIMARY KEY(MEETING_MEMBER_NO)
);

CREATE TABLE OPEN_TALK_BOARD_T (
	OPEN_TALK_BOARD_NO	INT				NOT NULL 	AUTO_INCREMENT,
	MEETING_NO			INT				NOT NULL,
	BOARD_CONTENT		LONGTEXT		NOT NULL,
	BOARD_START_AT		DATETIME		NOT NULL,
	BOARD_NAME			VARCHAR(100)	NOT NULL,
    CONSTRAINT PK_OPEN_TALK_BOARD PRIMARY KEY(OPEN_TALK_BOARD_NO)
);

CREATE TABLE DECLARATION_T (
	DECLARATION_NO		INT				NOT NULL 	AUTO_INCREMENT,
	USER_NO				INT				NOT NULL,
	BOOK_REVIEW_NO		INT				NULL,
	DECLARATION_CONTENT	VARCHAR(300)	NOT NULL,
	DECLARATION_AT		DATETIME		NOT NULL,
	DEC_STATE			INT				NOT NULL,	
	RECEIVED_USER_NO	INT				NOT NULL,
    CONSTRAINT PK_DECLARATION PRIMARY KEY(DECLARATION_NO)
);

CREATE TABLE USER_RECOMMENED_T (
	USER_NO		INT			NOT NULL,
	BOOK_NO		VARCHAR(60)	NOT NULL,	
	RECOMMENED	INT			NULL	
);

CREATE TABLE MEETING_CHAT_T (
	CHAT_NO				INT			NOT NULL 	AUTO_INCREMENT,
	MEETING_MEMBER_NO	INT			NOT NULL,
	CHAT_CONTENT		LONGTEXT	NULL,
	SEND_AT				DATETIME	NOT NULL,
	SENDER				VARCHAR(40)	NOT NULL,
    CONSTRAINT PK_MEETING PRIMARY KEY(CHAT_NO)
);

CREATE TABLE CART_T (
	CART_NO			INT			NOT NULL 	AUTO_INCREMENT,
	USER_NO			INT			NOT NULL,
	CART_CREATED_AT	DATETIME	NULL,
    CONSTRAINT PK_CART PRIMARY KEY(CART_NO)
);

CREATE TABLE PAYMENT_T (
	PAYMENT_NO				INT				NOT NULL 	AUTO_INCREMENT,
	USER_NO					INT				NOT NULL,
	CART_NO					INT				NOT NULL,
	PAYMENT_AT				DATETIME		NOT NULL,
	PAYMENT_OPTION			VARCHAR(50)		NOT NULL,
	PAYMENT_CARD_COMPANY	VARCHAR(100)	NULL,
	CARD_NO					VARCHAR(50)		NULL,
	TOTAL_PAYMENT_COST		INT				NOT NULL,
    CONSTRAINT PK_PAYMENT PRIMARY KEY(PAYMENT_NO)
);

CREATE TABLE QNA_T (
	QNA_NO		INT				NOT NULL 	AUTO_INCREMENT,
	USER_NO		INT				NOT NULL,
	TITLE		VARCHAR(4000)	NOT NULL,
	ANSWER		VARCHAR(300)	NULL,
	QNA_STATE	INT				NOT NULL,
	ANSWER_AT	DATETIME		NULL,
	DEPTH		INT	NOT 		NULL,
	GROUP_NO	INT	NOT 		NULL,
    CONSTRAINT PK_QNA PRIMARY KEY(QNA_NO)
);

CREATE TABLE ANNOUNCEMENT_T (
	ANM_NO			INT				NOT NULL 	AUTO_INCREMENT,
	TITLE			VARCHAR(100)	NOT NULL,
	ANM_CONTENT		VARCHAR(500)	NOT NULL,
	REGISTRATION	DATETIME		NOT NULL,
	IMAGE			VARCHAR(300)	NULL,
	ANM_COUNT		INT				NULL,
    CONSTRAINT PK_ANNOUNCEMENT PRIMARY KEY(ANM_NO)
);

CREATE TABLE MEETING_NOTICE_T (
	NOTICE_NO		INT				NOT NULL 	AUTO_INCREMENT,
	MEETING_NO		INT				NOT NULL,
	NOTICE_NAME		VARCHAR(300)	NOT NULL,
	NOTICE_CONTENT	LONGTEXT		NOT NULL,
	NOTICE_AT		DATETIME		NOT NULL,
    CONSTRAINT PK_MEETING_NOTICE PRIMARY KEY(NOTICE_NO)
);

CREATE TABLE OPEN_TALK_BOARD_DETAIL_T (
	OPEN_TALK_BOARD_DETAIL_NO	INT			NOT NULL 	AUTO_INCREMENT,
	OPEN_TALK_BOARD_NO			INT			NOT NULL,
	USER_NO						INT			NOT NULL,
	BOARD_DETAIL_CONTENT		LONGTEXT	NOT NULL,
    CONSTRAINT PK_OPEN_TALK_BOARD_DETAIL PRIMARY KEY(OPEN_TALK_BOARD_DETAIL_NO)
);

CREATE TABLE MSG_T (
	MSG_NO			INT				NOT NULL 	AUTO_INCREMENT,
	USER_NO			INT				NOT NULL,
	MSG_STATE		INT				NOT NULL,
	MSG_TITLE		VARCHAR(100)	NOT NULL,
	MSG_CONTENT		VARCHAR(500)	NOT NULL,
	SEND_AT			DATETIME		NULL,
	RECEIVE_STATE	INT				NULL,
	SEND_STATE		INT				NULL,
	RECEIVER		INT	NOT			NULL,
	RECEIVE_AT		DATETIME		NULL,
    CONSTRAINT PK_MSG PRIMARY KEY(MSG_NO)
);

CREATE TABLE CART_DETAIL_T (
	CART_DETAIL_NO		INT			NOT NULL 	AUTO_INCREMENT,
	CART_NO				INT			NOT NULL,
	BOOK_NO				VARCHAR(60)	NOT NULL,
	CART_DETAIL_COUNT	INT			NOT NULL,
    CONSTRAINT PK_CART_DETAIL PRIMARY KEY(CART_DETAIL_NO)
);

CREATE TABLE BOOK_T (
	BOOK_NO				VARCHAR(600)	NOT NULL,
	BOOK_TITLE			VARCHAR(60)		NOT NULL,
	BOOK_PRICE			INT				NULL,
	BOOK_AT				DATETIME		NULL,
	BOOK_AUTHOR			VARCHAR(60)		NULL,
	BOOK_IMAGE			VARCHAR(1000)	NULL,	
	BOOK_DESCRIPTION	VARCHAR(300)	NULL,
    CONSTRAINT PK_BOOK PRIMARY KEY(BOOK_NO)
);




ALTER TABLE MEETING_MEMBER_T ADD CONSTRAINT FK_MEETING_T_TO_MEETING_MEMBER_T_1 FOREIGN KEY (
	MEETING_NO
)
REFERENCES MEETING_T (
	MEETING_NO
);

ALTER TABLE OPEN_TALK_BOARD_T ADD CONSTRAINT FK_MEETING_T_TO_OPEN_TALK_BOARD_T_1 FOREIGN KEY (
	MEETING_NO
)
REFERENCES MEETING_T (
	MEETING_NO
);

ALTER TABLE MEETING_NOTICE_T ADD CONSTRAINT FK_MEETING_T_TO_MEETING_NOTICE_T_1 FOREIGN KEY (
	MEETING_NO
)
REFERENCES MEETING_T (
	MEETING_NO
);

ALTER TABLE DECLARATION_T ADD CONSTRAINT FK_BOOK_REVIEW_T_TO_DECLARATION_T_1 FOREIGN KEY (
	BOOK_REVIEW_NO
)
REFERENCES BOOK_REVIEW_T (
	BOOK_REVIEW_NO
);

ALTER TABLE BOOK_REVIEW_T ADD CONSTRAINT FK_USER_T_TO_BOOK_REVIEW_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE MEETING_T ADD CONSTRAINT FK_USER_T_TO_MEETING_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE USER_ACCESS_T ADD CONSTRAINT FK_USER_T_TO_USER_ACCESS_T_1 FOREIGN KEY (
	USER_ACCESS_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE MEETING_MEMBER_T ADD CONSTRAINT FK_USER_T_TO_MEETING_MEMBER_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE DECLARATION_T ADD CONSTRAINT FK_USER_T_TO_DECLARATION_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE USER_RECOMMENED_T ADD CONSTRAINT FK_USER_T_TO_USER_RECOMMENED_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE CART_T ADD CONSTRAINT FK_USER_T_TO_CART_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE PAYMENT_T ADD CONSTRAINT FK_USER_T_TO_PAYMENT_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE QNA_T ADD CONSTRAINT FK_USER_T_TO_QNA_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE OPEN_TALK_BOARD_DETAIL_T ADD CONSTRAINT FK_USER_T_TO_OPEN_TALK_BOARD_DETAIL_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE MSG_T ADD CONSTRAINT FK_USER_T_TO_MSG_T_1 FOREIGN KEY (
	USER_NO
)
REFERENCES USER_T (
	USER_NO
);

ALTER TABLE BOOK_REVIEW_T ADD CONSTRAINT FK_BOOK_T_TO_BOOK_REVIEW_T_1 FOREIGN KEY (
	BOOK_NO
)
REFERENCES BOOK_T (
	BOOK_NO
);

ALTER TABLE USER_RECOMMENED_T ADD CONSTRAINT FK_BOOK_T_TO_USER_RECOMMENED_1 FOREIGN KEY (
	BOOK_NO
)
REFERENCES BOOK_T (
	BOOK_NO
);

ALTER TABLE MEETING_CHAT_T ADD CONSTRAINT FK_MEETING_MEMBER_T_TO_MEETING_CHAT_T_1 FOREIGN KEY (
	MEETING_MEMBER_NO
)
REFERENCES MEETING_MEMBER_T (
	MEETING_MEMBER_NO
);

ALTER TABLE PAYMENT_T ADD CONSTRAINT FK_CART_T_TO_PAYMENT_T_1 FOREIGN KEY (
	CART_NO
)
REFERENCES CART_T (
	CART_NO
);

ALTER TABLE OPEN_TALK_BOARD_DETAIL_T ADD CONSTRAINT FK_OPEN_TALK_BOARD_T_TO_OPEN_TALK_BOARD_DETAIL_T_1 FOREIGN KEY (
	OPEN_TALK_BOARD_NO
)
REFERENCES OPEN_TALK_BOARD_T (
	OPEN_TALK_BOARD_NO
);

ALTER TABLE CART_DETAIL_T ADD CONSTRAINT FK_CART_T_TO_CART_DETAIL_T_1 FOREIGN KEY (
	CART_NO
)
REFERENCES CART_T (
	CART_NO
);

ALTER TABLE CART_DETAIL_T ADD CONSTRAINT FK_BOOK_T_TO_CART_DETAIL_T_1 FOREIGN KEY (
	BOOK_NO
)
REFERENCES BOOK_T (
	BOOK_NO
);

INSERT INTO USER_T (USER_ID, USER_PASSWORD, USER_NAME, USER_EMAIL, AGREECODE, USER_GRADE)
	VALUES('admin', '1111', 'admin', 'admin123@naver.com', 1, 1);
COMMIT;
