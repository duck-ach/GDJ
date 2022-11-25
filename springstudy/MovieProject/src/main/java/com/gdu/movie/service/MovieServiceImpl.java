package com.gdu.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;
import com.gdu.movie.util.SecurityUtil;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	// All Movie List
	@Override
	public List<MovieDTO> getAllMovieList(HttpServletRequest request) {
		request.setAttribute("movieCnt", movieMapper.selectAllMoviesCount());
		return movieMapper.selectAllMovies();
	}
	
	// search movie
	@Override
	public List<MovieDTO> findSearchMovieList(HttpServletRequest request, Model model) {
		
		// 파라미터
		String column = request.getParameter("column");
		String searchText = securityUtil.preventXSS(request.getParameter("searchText"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("searchText", searchText);
		
		return movieMapper.selectMoviesByQuery(map);
		
	}
	
}
