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
    서브쿼리
    1. SUB QUERY
    2. 메인쿼리(MAIN QUERY)에 포함하는 하위 쿼리(SUB QUERY)
    3. 서브쿼리는 메인쿼리에 괄호()를 이용해서 포함시킴
    4. 항상 서브쿼리를 먼저 실행하고, 서브쿼리 실행 결과를 메인 커리에서 사용함
    5. 사용되는 절에 따른 구분
        1) SELECT절 : 스칼라 서브쿼리
        2) FROM절   : 인라인뷰
        3) WHERE절  : 서브쿼리
    6. 서브쿼리 결과에 따른 구분
        1) 단일 행 서브쿼리
            (1) 서브쿼리 결과가 1개
            (2) PK나 UNIQUE 칼럼의 동등 비교(=)결과, 함수의 결과
            (3) 단일 행 연산자를 사용
        2) 다중 행 서브쿼리
            (1) 서브쿼리 결과가 2개 이상
            (2) FROM절, WHERE절에서 사용
            (3) 다중 행 연산자를 사용(IN, ANY, ALL 등)
*/

-- 1. 사원번호가 1001인 사원과 같은 직급(POSITION)을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION = (SELECT POSITION        -- 단일 행 서브쿼리이므로 연산자 =를 사용
                     FROM EMPLOYEE        -- 하위쿼리는 메인쿼리와 동등비교되므로, 반드시 POSITION을 반환시켜줘야한다.
                    WHERE EMP_NO = 1001); -- EMP_NO는 PK이므로 단일 행 서브쿼리(중복이없기때문)
