-- team 스키마 사용
USE team;

-- 쿼리문 실행
-- 1. 단독실행 : ctrl + enter
-- 2. 블록실행 : 블록 잡고 ctrl + shift + enter
-- 3. 전체실행 : ctrl + shift + enter

-- DROP TABLE
DROP TABLE IF EXISTS ATTACH;
DROP TABLE IF EXISTS UPLOAD;

-- NUMBER
-- 조금 : SMALLINT
-- 적당 : INT
-- 많이 : BIGINT

CREATE TABLE UPLOAD
(
    UPLOAD_NO INT NOT NULL AUTO_INCREMENT,  -- AUTO_INCREMENT를 사용하기 위해서는 PK가 필요해서 PK를 CREATE문 안쪽에 선언해주어야 한다.
    TITLE VARCHAR(100),   -- 제목
    CONTENT VARCHAR(100), -- 내용
    CREATE_DATE TIMESTAMP,      -- 작성일
    MODIFY_DATE TIMESTAMP,       -- 수정일
    CONSTRAINT PK_UPLOAD PRIMARY KEY(UPLOAD_NO)
);

CREATE TABLE ATTACH
(
    ATTACH_NO INT NOT NULL AUTO_INCREMENT, -- SEQ = AUTO_INCREMENT
    PATH VARCHAR(300),       -- 파일의 경로
    ORIGIN VARCHAR(300),     -- 파일의 원래 이름
    FILESYSTEM VARCHAR(42),  -- 파일의 저장된 이름
    DOWNLOAD_CNT INT,           -- 다운로드 횟수
    HAS_THUMBNAIL INT,          -- 썸네일이 있으면 1, 없으면 0
    UPLOAD_NO INT,               -- 게시글번호, FK
    CONSTRAINT PK_ATTACH PRIMARY KEY(ATTACH_NO),
    CONSTRAINT FK_ATTACH_UPLOAD FOREIGN KEY(UPLOAD_NO) REFERENCES UPLOAD(UPLOAD_NO)
);
