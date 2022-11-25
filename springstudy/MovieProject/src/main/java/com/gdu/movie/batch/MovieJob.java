package com.gdu.movie.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;

@EnableScheduling
@Component
public class MovieJob {

	@Autowired
	private MovieMapper movieMapper;
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void execute() throws Exception {
		if(movieMapper.selectComedyMovie() != null) {
			
			File file = new File("코미디.txt");
			List<MovieDTO> movies = movieMapper.selectComedyMovie();
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				for(int i = 0; i < movies.size(); i++) {
					bw.write("제목 : " + movies.get(i).getTitle() + "\n");
					bw.write("장르 : " + movies.get(i).getGenre() + "\n");
					bw.write("개요 : " + movies.get(i).getDescription() + "\n");
					bw.write("평점 : " + movies.get(i).getStar() + "\n");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else {
			File file = new File("error.txt");
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

				bw.write("코미디 검색 결과가 없습니다.");

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
