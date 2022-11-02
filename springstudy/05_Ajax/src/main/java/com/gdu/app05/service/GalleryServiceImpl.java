package com.gdu.app05.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class GalleryServiceImpl implements GalleryService {

	@Override
	public ResponseEntity<byte[]> imageDisplay(String path, String filename) {
		
		File file = new File(path, filename);
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			// System.out.println(contentType); // (image/jpeg)
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", contentType); // Setting Content Type MIME-TYPE (image/jpeg)
			entity = new ResponseEntity<byte[]>( FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK ); // file을 byte 배열로 바꿔주는 것
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
