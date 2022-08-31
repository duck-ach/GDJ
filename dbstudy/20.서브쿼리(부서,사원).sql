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


-- 2. 급여(SALARY)가 가장 높은 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY) 
                    FROM EMPLOYEE);
                    
-- 3. 부서번호가 1인 부서와 같은 지역에 있는 부서 정보를 조회하기
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE LOCATION = (SELECT LOCATION
                     FROM DEPARTMENT
                    WHERE DEPT_NO = 1);


-- 4. 평균급여 이상을 갖는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT AVG(SALARY)
                    FROM EMPLOYEE);

-- 5. 평균근속기간 이상을 근무한 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE (SYSDATE - HIRE_DATE)  >= (SELECT AVG(SYSDATE - HIRE_DATE)
                        FROM EMPLOYEE);


SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= (SELECT AVG(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
                                                    FROM EMPLOYEE);

-- 6. 부서번호가 2인 부서에 근무하는 사원들의 직급과 일치하는 직급을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION IN (SELECT POSITION         -- 다중행 서브쿼리의 동등 비교는 IN 연산으로 수행해야 함
                     FROM EMPLOYEE
                    WHERE DEPART = 2); -- DEPART가 PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리

-- TIP) 단일 행 / 다중 행 상관 없이 동등 비교는 IN 연산으로 수행 가능

/********************다중행**********************/
-- 7. 부서명이 '영업부'인 부서에 근무하는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE DEPART IN (SELECT DEPT_NO 
                    FROM DEPARTMENT
                   WHERE DEPT_NAME = '영업부');
                   
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE DEPT_NAME = '영업부';
 
 
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM DEPARTMENT D, EMPLOYEE E
 WHERE D.DEPT_NO = E.DEPART
   AND DEPT_NAME = '영업부'; 

-- 8. 직급이 '과장'인 사원들이 근무하는 부서 조회하기
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE DEPT_NO IN (SELECT DEPART 
                     FROM EMPLOYEE 
                    WHERE POSITION = '과장');
                    
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE POSITION = '과장';


SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT D , EMPLOYEE E
 WHERE D.DEPT_NO = E.DEPART
   AND POSITION = '과장';
   
-- 9. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > ANY (SELECT SALARY 
                     FROM EMPLOYEE 
                    WHERE DEPART = 2);
                    
                    
-- WHERE SALARY > ANY(2000000, 5000000)
-- SALARY가 2000000보다 크거나, 5000000보다 크면 됨(OR의 개념)

-- SALARY > ANY(2000000, 5000000)
-- -> SALARY > (SELECT MIN(2000000, 5000000) ...)
-- SALARY > ALL(2000000, 5000000)
-- -> SALARY > (SELECT MAX(2000000, 5000000) ...)

-- 10. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- 모든 급여(2000000, 5000000)와 비교해서 많이 받으면 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > ALL(SELECT SALARY
                      FROM EMPLOYEE
                     WHERE DEPART = 1); -- DEPART가  PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리

-- WHERE SALARY > ALL(2000000, 5000000)
-- SALARY가 2000000보다 크고, 5000000보다 크면 됨(AND 개념)
-- 따라서 최대급여 5000000보다 크면 만족하는 상황임
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MAX(SALARY)
                    FROM EMPLOYEE
                  WHERE DEPART = 1);
                  
/* WHERE절의 서브쿼리 */

-- 1. 전체 사원의 인원수, 급여합계/평균/최대/최소 조회하기
SELECT
        (SELECT COUNT(*) FROM EMPLOYEE) AS 인원수
      , (SELECT SUM(SALARY) FROM EMPLOYEE) AS 급여합계
      , (SELECT AVG(SALARY) FROM EMPLOYEE) AS 평균
      , (SELECT MAX(SALARY) FROM EMPLOYEE) AS 최대
      , (SELECT MIN(SALARY) FROM EMPLOYEE) AS 최소
    FROM
        DUAL;
        
-- 2. 부서번호가 1인 부서와 같은 지역에서 근무하는 사원 조회하기
--    사원번호(EMP_NO), 사원명(NAME), 부서번호(DEPART), 부서명(DEPT_NAME) 조회
SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.LOCATION = (SELECT LOCATION 
                       FROM DEPARTMENT 
                      WHERE DEPT_NO = 1); -- PK의 동등비교는 답이 하나만 나오니까.
                   
-- 스칼라 서브쿼리 접근
-- 스칼라 서브쿼리는 일치하지 않는 정보를 NULL로 처리함
-- 따라서 스칼라 서브쿼리와 동일한 방식의 조인은 '외부조인'이다.
SELECT
    E.EMP_NO
  , E.NAME
  , E.DEPART
  , (SELECT D.DEPT_NAME 
       FROM DEPARTMENT D 
      WHERE D.DEPT_NO = E.DEPART
        AND D.DEPT_NO = 1)
 FROM
    EMPLOYEE E;

SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.LOCATION = (SELECT LOCATION
                       FROM DEPARTMENT
                      WHERE DEPT_NO = 1);
    

/* FROM절의 서브쿼리 */
/*
    인라인뷰
    1. Inline-View
    2. FROM절에서 사용하는 서브쿼리를 의미함
    3. 인라인뷰는 주로 테이블 형식임
    4. 인라인뷰에 별명을 주고 사용함
    5. 인라인뷰에서 조회한 칼럼만 메인쿼리에서 조회할 수 있음
    6. SELECT문의 실행순서를 바꿀 때 사용
    
    * 모든 목록보기는 정렬을 먼저 수행한다음에 어디서부터 어디까지 가져온다.
*/

SELECT A.EMP_NO, A.NAME, A.POSITION -- 인라인뷰가 조회한 칼럼만 작성할 수 있다.
  FROM (SELECT EMP_NO, NAME, POSITION
          FROM EMPLOYEE
         WHERE DEPART = 1) A; -- 인라인뷰의 별명은 A임