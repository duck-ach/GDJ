DROP TABLE PROCEEDING;
DROP TABLE EMPLOYEE;
DROP TABLE COMP_PROJECT;
DROP TABLE DEPARTMENT;

CREATE TABLE DEPARTMENT (
    DEPT_NO VARCHAR2(15 BYTE) NOT NULL, -- 부서번호
    DEPT_NAME VARCHAR2(30 BYTE) NULL, -- 부서명
    DEPT_LOCATION VARCHAR2(30 BYTE) NULL
);


CREATE TABLE COMP_PROJECT (
    PJT_NO NUMBER NOT NULL,
    PJT_NAME VARCHAR2(30 BYTE) NULL,
    BEGIN_DATE DATE NULL,
    END_DATE DATE NULL
);


CREATE TABLE EMPLOYEE (
    EMP_NO NUMBER NOT NULL,
    DEPT_NO VARCHAR2 (15 BYTE) NULL,
    ENP_POSITION CHAR(10 BYTE) NULL,
    EMP_NAME VARCHAR2(15 BYTE) NULL,
    HIRE_DATE DATE NULL,
    SALARY NUMBER NULL
);

CREATE TABLE PROCEEDING (
    PCD_NO NUMBER NOT NULL,
    EMP_NO NUMBER NOT NULL,
    PJT_NO NUMBER NULL,
    PJT_STATE NUMBER NOT NULL
);

-- 기본키 추가
ALTER TABLE DEPARTMENT
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);

ALTER TABLE EMPLOYEE
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);
    
ALTER TABLE COMP_PROJECT
    ADD CONSTRAINT PK_PROJECT PRIMARY KEY(PJT_NO);

ALTER TABLE PROCEEDING
    ADD CONSTRAINT PK_PROCEEDING PRIMARY KEY(PCD_NO);


-- 외래키 추가

ALTER TABLE EMPLOYEE
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPT_NO)
        REFERENCES DEPARTMENT(DEPT_NO);
        
ALTER TABLE PROCEEDING
    ADD CONSTRAINT FK_PROCEDDING_EMPLOYEE FOREIGN KEY(EMP_NO)
        REFERENCES EMPLOYEE(EMP_NO);
        
ALTER TABLE PROCEEDING
    ADD CONSTRAINT FK_PROCEEDING_PJT FOREIGN KEY(PJT_NO)
        REFERENCES COMP_PROJECT(PJT_NO);


-- 외래키 제거
-- DEPARTMENT 테이블의 DEPT_NO 칼럼을 참조하는 외래키 제약조건을 제거해야
-- DEPARTMENT 테이블의 DEPT_NO 칼럼에 추가된 기본키 제약조건을 제거할 수 있다.
ALTER TABLE EMPLOYEE
    DROP CONSTRAINT FK_EMPLOYEE_DEPARTMENT;
    
ALTER TABLE  PROCEEDING
    DROP CONSTRAINT FK_PROCEDDING_EMPLOYEE;
    
ALTER TABLE PROCEEDING
    DROP CONSTRAINT FK_PROCEEDING_PJT;

-- EMPLOYEE TABLE의 제약조건을 조회
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMPLOYEE';

        
-- 기본키 제거
-- FK에 의해서 참조되고 있는 PK는 제거할 수 없음
-- 먼저 FK를 제거해야 함 
ALTER TABLE DEPARTMENT
    DROP PRIMARY KEY;
    
ALTER TABLE PROCEEDING
    DROP CONSTRAINT PK_PROCEEDING;

ALTER TABLE EMPLOYEE
    DROP CONSTRAINT PK_EMPLOYEE;
  
ALTER TABLE COMP_PROJECT
    DROP CONSTRAINT PK_PROJECT;


