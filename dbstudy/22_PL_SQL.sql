/*
    PL/SQL
    
    1. 오라클의 프로그래밍 처리가 가능한 SQL
    2. 프로그래밍 형식
        [DELARE]
            [변수선언]
        BEGIN
            수행할 PL/SQL
        END;
    3. PL/SQL의 데이터(변수, 상수)는 서버메시지로 출력
    4. 서버메시지 출력을 위해서 최초 1회 설정이 필요
       SET SERVEROUTPUT ON; -- (DEFAULT가 SET SERVEROUTPUT OFF 라서)
*/

-- 기초 데이터 준비
-- HR 계정의 EMPLOYEES 테이블을 SCOTT 계정으로 복사해 오기

CREATE TABLE EMPLOYEES
    AS (SELECT *
          FROM HR.EMPLOYEES);
              
-- PK
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT PK_EMPLOYEES PRIMARY KEY(EMPLOYEE_ID); 
    
-- 서버메시지 출력 가능 상태로 변경
-- 한 번만 실행하면 됨
SET SERVEROUTPUT ON;