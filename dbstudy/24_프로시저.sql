/*
    프로시저 (PROCEDURE)
        1. 한 번에 수행할 쿼리문이 여러 개인 경우 쿼리문을 한 번에 실행할 수 있다.
        2. (이체 : UPDATE문 2개)
        3. 작성된 프로시저는 EXECUTE문으로 실행
        4. 형식
           CREATE [OR REPLACE] 프로시저_이름[(매개변수)]
            IS  -- AS 가능
                변수선언
            BEGIN
                프로시저본문
            [EXCEPTION
                예외처리]
            END [프로시저_이름];
        
           [OR REPLACE] : 언제든지 트리거를 재생성하겠다. (CREATE TABLE앞에 DROP TABLE을 먼저 넣는것과 같다.)
        
    
    함수
        : 사용자가 필요한 기능을 함수로 정의
    
    트리거
        : 행(ROW) 삽입/수정/삭제 시 자동으로 처리되는 기능 정의
    
*/
SET SERVEROUTPUT ON;
-- 프로시저 PROC1 정의(만들기)
CREATE OR REPLACE PROCEDURE PROC1
IS
    NAME VARCHAR2(10 BYTE);
BEGIN
    NAME := '엄희라';
    DBMS_OUTPUT.PUT_LINE(NAME);
END PROC1;
-- 프로시저 PROC1 호출(실행)
EXECUTE PROC1(); -- 명령어는 4글자까지 줄일 수 있다. EXEC