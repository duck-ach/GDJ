-- 테이블 삭제
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

-- DEPARTMENT 테이블 생성
CREATE TABLE DEPARTMENT(
    DEPT_NO   NUMBER            NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION  VARCHAR2(15 BYTE) NOT NULL
);

-- EMPLOYEE 테이블 생성
CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER            NOT NULL,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER            NULL,
    POSITION  VARCHAR2(20 BYTE) NULL,
    GENDER    CHAR(2)           NULL,
    HIRE_DATE DATE              NULL, 
    SALARY    NUMBER            NULL
);

-- 기본키
ALTER TABLE DEPARTMENT 
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

-- 외래키
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) 
        REFERENCES DEPARTMENT(DEPT_NO)
            ON DELETE SET NULL;

/*************************************************************/

/*
    시퀀스
    1. SEQUENCE
    2. 일련번호를 생성하는 데이터베이스 객체
    3. 자동으로 증가하는 번호를 생성
    4. 기본키(PK) 에서 주로 사용(인공키)
    5. NEXTVAL를 이용하면 새로운 번호가 생성
    6. CURRVAL를 이용하면 현재 번호를 확인
*/

/*
    시퀀스 생성 형식
    CREATE SEQUENCE 시퀀스_이름
        START WITH 시작값                      -- 시작하면 1, 생성 이후 수정 불가
        INCREMENT BY 증가값
        MINVALUE 최소값
        MAXVALUE 최대값
        CACHE사용유무                          -- NOCACHE 권장
        CYCLE사용유무                          
    * 참고로 순서 상관없다.
*/

-- 부서 테이블에서 사용할 부서_시퀀스
DROP SEQUENCE DEPARTMENT_SEQ;
CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 100     -- NOMAXVALUE : 최대값없음
    NOCACHE
    NOCYCLE;         -- 테이블 하나당 시퀀스 하나씩 쓴다.

-- 부서 테이블에 행(Row) 삽입
-- 부모 테이블(관계에서 PK를 가진 테이블)에 먼저 삽입을 해야 함
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES (DEPARTMENT_SEQ.NEXTVAL , '영업부', '대구');
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES (DEPARTMENT_SEQ.NEXTVAL, '인사부', '서울');
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES (DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구');
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES (DEPARTMENT_SEQ.NEXTVAL, '기획부', '서울');

-- 작업의 완료
COMMIT;

-- 사원 테이블에서 사용할 사원_시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
    NOCACHE;
    
-- 사원 테이블에 행(Row) 삽입
-- 자식 테이블(관계에서 FK를 가진 테이블)은 참조 무결성에 위배되지 않는 데이터만 삽입 가능
-- 부서(부서번호) - 사원(소속부서)
-- PK             - FK
-- 1,2,3,4        - 1,2,3,4중 하나만 가능
INSERT INTO EMPLOYEE VALUES (EMPLOYEE_SEQ.NEXTVAL, '구창민', 1, '과장', 'M', '95/05/01', 5000000);
INSERT INTO EMPLOYEE VALUES (EMPLOYEE_SEQ.NEXTVAL, '김민서', 1, '사원', 'F', '17/09/01', 2000000);
INSERT INTO EMPLOYEE VALUES (EMPLOYEE_SEQ.NEXTVAL, '이은영', 2, '부장', NULL, '90-09-01', 5500000);
INSERT INTO EMPLOYEE VALUES (EMPLOYEE_SEQ.NEXTVAL, '한성일', 2, '과장', 'M', '93-04-01', 5000000);
COMMIT;

-- 참조무결성 위배 데이터 삽입을 위해서 외래키 일시중지
ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;

-- 참조무결성 위배 데이터 삽입
INSERT INTO EMPLOYEE VALUES (EMPLOYEE_SEQ.NEXTVAL, '신현준', 5, '대리', 'M', '98-12-01', 3500000);
COMMIT;

/*
    조인
    1. JOIN
    2. 2개 이상의 테이블을 조회하는 방법
    3. 조회할 테이블들은 관계를 줄 수 있어야 함
    4. 종류
        1) 크로스 조인 : 카테젼 곱, 각 테이블에 모든 행을 조인
           - CROSS JOIN
           - 조인 조건이 없으면 됨
           - 많은 행을 순식간에 만들 수 있음(기초데이터 작성용)
           - 조인 조건을 잘못 지정한 경우
        2) 내부 조인
           - INNER JOIN
           - 각 테이블에서 일치하는 모든 행을 조인
        3) 외부 조인
           - OUTER JOIN
           - 한 테이블은 일치하는 행을 조인, 한 테이블은 일치하지 않아도 조인
           - 왼쪽 외부 조인(LEFT OUTER JOIN), 오른쪽 외부 조인(RIGHT OUTER JOIN)
        4) 셀프 조인
           - SELF JOIN 
           - 한 테이블에 참조 관계가 있는 경우
           - 한 테이블의 특정 칼럼과 다른 특정 칼럼을 조인
    5. 형식
        1) JOIN 문법
            SELECT 칼럼
              FROM 테이블1 JOIN 테이블2
              ON 조인조건
        2) 콤마(,) 문법
            SELECT 칼럼
              FROM 테이블1, 테이블2
             WHERE 조인조건
            
*/


-- 1. 크로스 조인 확인
SELECT E.EMP_NO , E.NAME, E.SALARY, D.DEPT_NO, D.DEPT_NAME
  FROM EMPLOYEE E CROSS JOIN DEPARTMENT D;


-- 2. 내부 조인 확인
-- 사원번호, 사원명, 부서명을 조회하기
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART;

-- 3. 외부 조인 확인
-- 사원번호, 사원명, 부서명을 조회하기
-- 모든 사원을 반드시 조회하기
-- 사원     - 부서
-- 모두포함 - 일치하는 부서만 포함
-- 모두 포함시킬 사원테이블을 OUTER JOIN의 왼쪽/오른쪽에 두느냐에 따라
-- 왼쪽 외부 조인/오른쪽 외부조인으로 구분함
 
-- A (DRIVEN/DRIVEN 테이블이 잘못 지정된 조인)
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM EMPLOYEE E LEFT OUTER JOIN DEPARTMENT D -- 성능적으로 느림
    ON E.DEPART = D.DEPT_NO;
-- B (DRIVEN/DRIVEN 테이블이 잘 지정된 조인)
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E -- 성능적으로 빠름     부모 OUTER JOIN 자식
    ON D.DEPT_NO = E.DEPART;
-- C
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D LEFT OUTER JOIN E MPLOYEE E -- 성능적으로 빠름
    ON E.DEPART = D.DEPT_NO;
-- D
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM EMPLOYEE E RIGHT OUTER JOIN DEPARTMENT D -- 성능적으로 느림
    ON E.DEPART = D.DEPT_NO;

-- A. B / C. D가 결과가 같다. 하지만 A는 성능적으로 느리고, B는 성능적으로 빠르다.

/*
    드라이브(DRIVE) 테이블과 드리븐(DRIVEN) 테이블
    
    1. 드라이브 테이블
        1) 조인에서 검색할 때 사용하는 테이블
        2) 관계에서 PK를 가진 테이블
        3) 대부분 행(ROW)개수가 적은 테이블
    2. 드리븐 테이블
        1) 관계에서 FK를 가진 테이블
        2) 대부분 행(ROW)개수가 많은 테이블
    3. 조인할 때 드라이브 테이블을 드리븐 테이블보다 먼저 작성
*/











SELECT EMP_NO, NAME, DEPT_NAME
  FROM EMPLOYEE INNER JOIN DEPARTMENT
    ON DEPART = DEPT_NO;




/***********************************************************/

DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NO1 NUMBER,
    NO2 NUMBER
);

DROP SEQUENCE SAMPLE_SEQ;
CREATE SEQUENCE SAMPLE_SEQ NOCACHE;

-- 최초 1번은 NEXTVAL를 사용해야 CURRVAL도 사용 가능하다.
INSERT INTO SAMPLE(NO1) VALUES(SAMPLE_SEQ.CURRVAL);

-- NEXTVAL, CURRVAL 함께 사용
INSERT INTO SAMPLE(NO1, NO2) VALUES(SAMPLE_SEQ.NEXTVAL, SAMPLE_SEQ.CURRVAL);
COMMIT;


-- 테이블 수정

-- 1. 부서번호가 1인 부서의 지역을 '인천'으로 수정
UPDATE DEPARTMENT
   SET LOCATION = '인천'
 WHERE DEPT_NO = 1;

-- 2. 부서번호가 3인 부서명을 '전략부', 지역을 '부산'으로 수정
UPDATE DEPARTMENT
   SET DEPT_NAME = '전략부'
     , LOCATION = '부산'
 WHERE DEPT_NO = 3;

/*
    부서  -  사원
    1     -  1
    2     -  1
    3     -  2
    4     -  2
    5     -  3
*/
-- 3. 부서번호가 3인 부서의 부서번호를 6으로 수정
-- DEPARTMENT의 부서번호를 EMPLOYEE가 참조중이므로 수정이 안 됨
-- 해결책
-- 1. 외래키 일시중지
-- 2. 수정
-- 3. 외래키 재시작

ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 중지

UPDATE EMPLOYEE
   SET DEPART = 6
 WHERE DEPART = 3;

UPDATE DEPARTMENT
   SET DEPT_NO = 6
 WHERE DEPT_NO = 3;

ALTER TABLE EMPLOYEE
    ENABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 시작

-- 4. 부서번호 1인 사원들의 월급을 100000 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY + 100000
 WHERE DEPART = 1;

-- 5. 직급이 '과장'인 사원들의 월급을 10% 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY * 1.1
 WHERE POSITION = '과장';


SELECT * FROM EMPLOYEE;
