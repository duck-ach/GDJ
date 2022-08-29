-- 함수 확인용 기초데이터
DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NAME VARCHAR2(20 BYTE),
    KOR NUMBER(3),
    ENG NUMBER(3),
    MATH NUMBER(3)
);

INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES(NULL, 100, 100, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('영숙', NULL, 100, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('정수', 100, NULL, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('지영', 100, 100, NULL);
COMMIT;

-- NULL값의 연산에서 사용되면 결과가 NULL이다.
SELECT 1 + NULL FROM DUAL;

-- NULL 처리 함수

-- 1. NVL 함수
--      NVL(칼럼, 칼럼값이 NULL일 때 대신 사용할 값)

-- NAME이 없으면 '아무개' , KOR, ENG, MATH가 없으면 0으로 조회
SELECT
    NVL(NAME, '아무개') AS STU_NAME
  , NVL(KOR, 0)
  , NVL(ENG, 0)
  , NVL(MATH, 0)
  FROM
    SAMPLE
 WHERE
    NAME != '아무개'
 ORDER BY
    STU_NAME ASC;
    
-- 이름과 총점을 조회하기
-- 이름이 없으면 '아무개', 점수가 없으면 0점 처리
-- 결과
-- 아무개 300
-- 영숙   200
-- 정수   200
-- 지영   200

SELECT 
    NVL(NAME, '아무개') AS 이름
  , NVL(KOR, 0) + NVL(ENG, 0) + NVL(MATH, 0) AS 총점
  , KOR + ENG + MATH
  FROM
    SAMPLE;
    
-- 2. NVL2 함수
--    NVL2(칼럼, NULL이 아닐 때 사용할 값, NULL일 때 사용할 값)
SELECT
    NVL2(NAME, NAME || '님', '아무개') AS 이름
  , NVL2(KOR, '응시', '결시') AS 국어
  , NVL2(ENG, '응시', '결시') AS 영어
  , NVL2(MATH, '응시', '결시') AS 수학
  FROM
    SAMPLE;
    
-- 집계함수 (그룹함수)
-- 1. 통계(합계, 평균, 최대, 최소, 개수 등)를 낼 때 사용
-- 2. NULL값을 연산에서 제외
-- 3. 종류
--  1) SUM(칼럼) : 칼럼 합계
--  2) AVG(칼럼) : 칼럼 평균
--  3) MAX(칼럼) : 칼럼 최대값
--  4) MIN(칼럼) : 칼럼 최소값
--  5) COUNT(칼럼) : 칼럼에 입력된 데이터의 개수

SELECT
    SUM(KOR) AS 국어
  , SUM(ENG) AS 영어
  , SUM(MATH) AS 수학
--  , SUM(KOR, ENG, MATH) // 인수(ARGUMENTS)가 3개이므로 불가능하다. (인수 1개여야함)
  , SUM(KOR + ENG + MATH) AS 총합1  -- KOR + ENG + MATH와 같은 연산(SUM함수를 잘못 사용한 예시)
  , SUM(KOR) + SUM(ENG) + SUM(MATH) AS 총합2 -- 국어합 + 영어합 + 수학합
FROM 
    SAMPLE;
    
-- 각 칼럼(KOR, ENG, MATH)의 평균
SELECT
    AVG(KOR)  -- NULL 제외한 KOR의 평균
  , AVG(ENG)  -- NULL 제외한 ENG의 평균
  , AVG(MATH) -- NULL 제외한 MATH의 평균
  FROM
    SAMPLE;
    
-- NULL값은 결시를 의미하므로 0점처리함
SELECT
    AVG(NVL(KOR, 0))
  , AVG(NVL(ENG, 0))
  , AVG(NVL(MATH, 0))
  FROM
   SAMPLE;
   
-- 각 칼럼(KOR, ENG, MATH) 의 최대값
SELECT
    MAX(KOR)
  , MAX(ENG)
  , MAX(MATH)
  FROM
    SAMPLE;
    
-- NULL값은 결시를 의미하므로 0점 처리함
-- 국어 시험을 응시한 학생이 몇 명인가?
SELECT
    COUNT(KOR)
  FROM
    SAMPLE;
    
-- 전체 학생은 몇 명인가?
-- 특정 컬럼을 지정하지 않고 전체 칼럼(*)을 이용해서 전체 ROW개수를 구함
SELECT
    COUNT (*)
  FROM
    SAMPLE;
    
-- 성명   국어 영어 수학 합계 평균
-- 아무개 100  100  100  300  100
-- 영숙   0    100  100  200  66.66

SELECT 
    NVL(KOR, 0) AS 국어
  , NVL(ENG, 0) AS 영어
  , NVL(MATH, 0) AS 수학
  , (NVL(KOR,0) + NVL(ENG,0) + NVL(MATH,0) ) AS 총합
  , (NVL(KOR, 0) + NVL(ENG, 0) + NVL(MATH,0)) / 3 AS 평균
  FROM
  SAMPLE;

-- 1. 제곱
--  POWER(A, B) : A의 B제곱
SELECT POWER(2, 10) FROM DUAL;

-- 2. 제곱근(루트)
--      SQRT(A) : 루트 A
SELECT SQRT(25) FROM DUAL;

-- 3. 절대값
--      ABS(A) : A의 절댓값
SELECT ABS(5), ABS(-5) FROM DUAL;

-- 4. 나머지
SELECT MOD(7, 2) FROM DUAL;

-- 5. 부호판별
-- SIGN(A) : A의 부호가 양수면 1, 음수면 -1, 0이면 0을 반환
SELECT SIGN(5), SIGN(-5), SIGN(0) FROM DUAL;

-- 6. 정수로 올림
-- CEIL(A) : 실수 A를 정수로 올림
SELECT CEIL(1.1), CEIL(-1.1) FROM DUAL;

-- 7. 정수로 내림
-- FLOOR(A) : 실수 A를 정수로 내림
SELECT FLOOR(1.1), FLOOR(-1.1) FROM DUAL;

-- 8. 원하는 자릿수로 절사
--      TRUNCATE(A, [DIGIT]) : 실수 A를 DIGIT 자릿수로 절사 (DEFAULT : 정수로 절사)
SELECT
    TRUNC(1.9999)    -- 1
  , TRUNC(1.9999, 1) -- 1.9
  , TRUNC(1.9999, 2) -- 1.99
    FROM
    DUAL;

SELECT
    TRUNC(9999, -1)  -- 9990(원 단위 절사)
  , TRUNC(9999, -2)  -- 9900
    FROM
        DUAL;
        
-- 9. 반올림
-- ROUND(A, [DIGIT]) : 실수 A를 DIGIT 자릿수로 반올림. 생략하면 정수로 반올림
SELECT
    ROUND(145.45)     -- 145
  , ROUND(145.45, 1)  -- 145.5
  , ROUND(145.45, -1) -- 150
  FROM
    DUAL;

-- 문제발생
-- 1. 원하는 자릿수로 올림                       DIGIT
--    1) 소수 1자리 : CEIL(값 * 10) / 10         1
--    2) 소수 2자리 : CEIL(값 * 100) / 100        2
--    3) 소수 3자리 : CEIL(값 * 1000) / 1000      3
--    4) 정수       : CEIL(값 * 1) / 1           0
--    5) 일의자리   : CEIL(값 * 0.1) / 0.1        -1
--    6) 십의자리   : CEIL(값 * 0.01) / 0.01      -2
--    7) 백의자리   : CEIL(값 * 0.001) / 0.001    -3
--
--    일반화        : CEIL(값 * POWER(10, DIGIT)) / POWER(10, DIGIT)
SELECT
    CEIL(1.111 * POWER(10, 1)) / POWER(10, 1)       -- 1.2
  , CEIL(1.111 * POWER(10, 2)) / POWER(10, 2)       -- 1.12
  , CEIL(11111 * POWER(10, -1)) / POWER(10, -1)     -- 11120
  , CEIL(11111 * POWER(10, -2)) / POWER(10, -2)     -- 11200
  FROM DUAL; 

-- 2. 원하는 자릿수로 내림
--      CEIL 대신 FLOOR 함수를 사용하면 됨
SELECT
       FLOOR(1.111 * POWER(10, 1)) / POWER(10, 1)    -- 1.1
     , FLOOR(1.111 * POWER(10, 2)) / POWER(10, 2)    -- 1.11
     , FLOOR(11111 * POWER(10, -1)) / POWER(10, -1)  -- 11110
     , FLOOR(11111 * POWER(10, -2)) / POWER(10, -2)  -- 11100
  FROM
       DUAL;
       
-- 날짜함수

-- 1. 현재 날짜와 시간
SELECT SYSDATE, SYSTIMESTAMP FROM DUAL;

-- 2. 원하는 형식으로 날짜와 시간 조회
--    TO_CHAR 함수 : 문자로 변환해서 조회
SELECT
    TO_CHAR(SYSDATE, 'YYYY-MM-DD HH:MI:SS'),   --12시간
    TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') --24시간
    FROM DUAL;
    
-- 3. 단위(년, 월, 일, 시, 분, 초) 추출 함수
--  EXTRACT(단위 FROM 날짜)
SELECT
        EXTRACT(YEAR FROM SYSDATE) AS 년도
      , EXTRACT(MONTH FROM SYSDATE) AS 월
      , EXTRACT(DAY FROM SYSDATE) AS 일
      , EXTRACT(HOUR FROM SYSTIMESTAMP) AS 시  -- UTC 기준
      , EXTRACT(MINUTE FROM SYSTIMESTAMP) AS 분
      , EXTRACT(SECOND FROM SYSTIMESTAMP) AS 초
      , EXTRACT(TIMEZONE_HOUR FROM SYSTIMESTAMP) AS 시 -- 우선 TIMEZONE 설정이 필요
      , FLOOR(EXTRACT(SECOND FROM SYSTIMESTAMP)) AS 초
      FROM
      DUAL;
      
-- 단위 (년,월,일,시,분,초) 추출은 TO_CHAR 함수로도 가능함
SELECT
    TO_CHAR(SYSDATE, 'YYYY')
  , TO_CHAR(SYSDATE, 'MM')
  , TO_CHAR(SYSDATE, 'DD')
  , TO_CHAR(SYSDATE, 'HH24')
  , TO_CHAR(SYSDATE, 'MI')
  , TO_CHAR(SYSDATE, 'SS')
  FROM
    DUAL;
    
-- 날짜 연산
--    1) 하루(1일)를 숫자 1로 처리
--      (12시간을 숫자 0.5로 처리)
--    2) 특정 단위 후 날짜
--      (1) 1년 후   : + 365, 함수 없음
--      (2) 1개월 후 : ADD_MONTHS 함수사용
--      (3) 1일 후   : +1, 함수 없음

SELECT
    SYSDATE - 1 AS 어제
  , SYSDATE AS 오늘
  , SYSDATE + 1 AS 내일
  , TO_CHAR(SYSDATE - 0.5, 'MM/DD AM HH:MI:SS') AS "12시간전"
  ,TO_CHAR(SYSDATE + 0.5, 'MM/DD AM HH:MI:SS') AS "12시간후"
  , TO_CHAR(SYSDATE + 1 / 24, 'MM/DD AM HH:MI:SS') AS "1시간후"
  FROM
    DUAL;
    
-- 5. N개월 전후 날짜
-- ADD_MONTHS(날짜, N)
SELECT
    ADD_MONTHS(SYSDATE, -1) AS "1개월전"
  , ADD_MONTHS(SYSDATE, 1) AS "1개월후"
  FROM
    DUAL;
    
-- 6. 경과한 개월 수
-- MONTHS_BETWEEN(최근날짜, 이전날짜) : 두 날짜 사이의 경과한 개월 수
SELECT
    TRUNC(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) AS 경과한개월
    FROM
    EMPLOYEE;