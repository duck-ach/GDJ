package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.MovieService;

@Controller
public class MovieController {

	@GetMapping("movie")
	public String welcome() {
		return "movie";
	}
	
	@Autowired
	private MovieService movieService;
	
	// 요청처리 메소드만들기
	@ResponseBody
	@GetMapping("movie/boxOfficeList")
	public String movieDetail(String targetDt) {
		return movieService.getBoxOffice(targetDt);
	}
	
}
