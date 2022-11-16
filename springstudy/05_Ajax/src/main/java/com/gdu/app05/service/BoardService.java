package com.gdu.app05.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

//ResponseEntity : Ajax 응답 전용 객체가 만들어져 있음.
public interface BoardService {
	public ResponseEntity<Board> execute1(HttpServletRequest request);// ResponseEntity �ȿ� Board�� ����ִ´�.
	public ResponseEntity<Board> execute2(String title, String content);// ResponseEntity �ȿ� Board�� ����ִ´�.
	public ResponseEntity<Board> execute3(Board board);// ResponseEntity �ȿ� Board�� ����ִ´�.
}

