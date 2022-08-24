DROP TABLE SUGANG_STUDENT;
CREATE TABLE SUGANG_STUDENT (
    STU_NUM NUMBER(5) NOT NULL,
    STU_NAME VARCHAR2(30 BYTE) NOT NULL,
    STU_AGE NUMBER(3) NULL,
    CONSTRAINT STU_NUM_PK PRIMARY KEY(STU_NUM)
);

DROP TABLE SUGANG_SUBJECT;
CREATE TABLE SUGANG_SUBJECT (
    SJ_CODE VARCHAR2(20 BYTE) NOT NULL,
    SJ_NAME VARCHAR2(20 BYTE) NULL,
    PROP_NAME VARCHAR2(30 BYTE) NULL,
    CONSTRAINT SJ_CODE_PK PRIMARY KEY(SJ_CODE)
);

DROP TABLE SUGANGSINCHUNG;
CREATE TABLE SUGANGSINCHUNG (
    APPLI_CODE NUMBER(3) NOT NULL,
    STU_NUM NUMBER(5) NOT NULL,
    SJ_CODE VARCHAR2(20 BYTE) NOT NULL,
    PRIMARY KEY(APPLI_CODE),
    FOREIGN KEY(STU_NUM) REFERENCES SUGANG_STUDENT(STU_NUM),
    FOREIGN KEY(SJ_CODE) REFERENCES SUGANG_SUBJECT(SJ_CODE)
);