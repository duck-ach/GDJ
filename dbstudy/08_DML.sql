-- DROP TABLE
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

-- CREATE TABLE
CREATE TABLE DEPARTMENT (
    DEPT_NO NUMBER              NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION VARCHAR2(15 BYTE)  NOT NULL
);

CREATE TABLE EMPLOYEE (
    EMP_NO NUMBER               NOT NULL,
    NAME VARCHAR2(20 BYTE)      NOT NULL,
    DEPART NUMBER,
    POSITION VARCHAR2(20 BYTE),
    GENDER VARCHAR2(20 BYTE),
    HIRE_DATE DATE,
    SALARY NUMBER
);

-- PK
ALTER TABLE DEPARTMENT 
    ADD CONSTRAINT PK_DEPT_NO PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE
    ADD CONSTRAINT PK_EMP_NO PRIMARY KEY(EMP_NO);

-- FK
ALTER TABLE EMPLOYEE
    ADD CONSTRAINT FK_DEPART FOREIGN KEY(DEPART)
    REFERENCES DEPARTMENT(DEPT_NO)
    ON DELETE SET NULL;
    
/**************************************************************************/

/*
    DML
    1. Data Manipulation Language
    2. 데이터 조작어
    3. 행(Row, Record, Tuple) 단위 삽입, 수정, 삭제
    4. 트랜잭션(작업) 완료를 위해 COMMIT이 필요
    5. 트랜잭션(작업) 취소를 위해 ROLLBACK 사용 가능
    6. 종류
        1) INSERT INTO VALUES
        2) UPDATE SET WHERE
        3) DELETE FROM WHERE
*/
/*
    행 삽입
    1. 지정한 칼럼에 데이터 삽입
        INSERT INTO 테이블(칼럼1, 칼럼2) VALUES(값1, 값2)
    2. 모든 칼럼에 데이터 삽입(칼럼 리스트 생략)
        INSERT INTO 테이블 VALUES(값1, 값2)

*/

/* 
    부서 테이블에 행(row) 삽입
    부모 테이블(관계에서 PK를 가진 테이블)에 먼저 삽입을 해야 함
*/




INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (1, '영업부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (2, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (3, '총무부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (4, '기획부', '서울');
    
COMMIT;

-- INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME) VALUES(5, '개발부'); 
-- 만약 값이 없으면 NULL값을 넣게되는데 LOCATION 칼럼이 NOT NULL이라서 실행이 되지않는다.

-- INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5, '개발부', '부에노스아이리스');
-- VARCHAR2(15 BYTE)라서 크기초과로 실패

-- INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(4, '개발부', '인천');
-- PK값이 중복이라서 실행이 되지않는다.
-- DELETE FROM DEPARTMENT WHERE DEPT_NO=5;

----------------------------------------------------------------------------------------------

-- 자식 테이블(관계에서 FK를 가진 테이블)은 참조 무결성에 위배되지 않는 데이터만 삽입 가능
-- 부서(부서번호) - 사원(소속부서)
-- PK            - FK
-- 1,2,3,4       - 1,2,3,4중 하나만 가능

-- 날짜 입력 규칙
-- 문자열처럼 ' ' 작은 따옴표를 이용하여 입력한다.
-- 하이픈(-)과 슬래쉬(/)를 이용하여 구분

INSERT INTO
    EMPLOYEE
VALUES
    (1001, '구창민', 1, '과장', 'M', '95/05/01', 5000000); -- 하나도 남김없이 다 집어넣는다.
INSERT INTO
    EMPLOYEE
VALUES
    (1002, '김민서', 1, '사원', 'F', '17/09/01', 2000000);
INSERT INTO
    EMPLOYEE
VALUES
    (1003, '이은영', 2, '부장', NULL, '90-09-01', 5500000);
INSERT INTO
    EMPLOYEE
VALUES
    (1004, '한성일', 2, '과장', 'M', '93-04-01', 5000000);
COMMIT;

/*
INSERT INTO EMPLOYEE
    (EMP_NO, NAME, DEPART)
VALUES
    (1005, '아무개', 5);
    
-- 부모테이블을 참조하는 EMP_NO에서 1005값이 없기때문에 에러가 뜬다.
*/ 
    
-- 외부 데이터 IMPORT
-- 엑셀 데이터(시트마다 테이블 1개)
-- 