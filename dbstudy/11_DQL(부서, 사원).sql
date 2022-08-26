/*
    DQL
    
    1. DATA QUERY LANGUAGE
    2. 데이터 질의어
    3. 테이블의 데이터를 조회/검색
    4. 데이터베이스에 변화가 없으므로 COMMIT 없음
        (트랜잭션의 대상이 아님)
    5. 형식
        SELECT 칼럼1, 칼럼2, ...
          FROM 조회할 테이블의 이름
        [WHERE 조건식]
        [GROUP BY 그룹화]
        [HAVING 그룹화_조건식]
        [ORDER BY 정렬 (오름차순, 내림차순)]
    6. 실행 순서
    ⑤  SELECT 칼럼
    ①   FORM 테이블
    ②  WHERE 조건식
    ③  GROUP BY 그룹화
    ④ HAVING 그룹화_조건식
    ⑥  ORDER BY 정렬기준
*/

-- 사원 테이블에서 사원명 조회하기
SELECT NAME
FROM EMPLOYEE
ORDER BY NAME;