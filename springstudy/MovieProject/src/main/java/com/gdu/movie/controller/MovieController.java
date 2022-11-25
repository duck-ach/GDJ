package com.gdu.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.service.MovieService;
import com.gdu.movie.util.SecurityUtil;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping(value="/searchAllMovies", produces="application/json; charset=UTF-8")
	public List<MovieDTO> list(HttpServletRequest request) {
		return movieService.getAllMovieList(request);
	}

	@ResponseBody
	@GetMapping(value="/searchMovie", produces="application/json; charset=UTF-8")
	public List<MovieDTO> getSearchMovie(HttpServletRequest request, Model model) {
		return movieService.findSearchMovieList(request, model);
	}

}
