package com.gdu.app14.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.UploadDTO;

public interface UploadService {
	public List<UploadDTO> getUploadList();
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void getUploadByNo(int uploadNo, Model model); // Model에 uploadDTO, AttachList를 둘다 담아야함
										// 둘다 받아오려고하면 힘드니까 모델에 담아서 온다.
	public ResponseEntity<Resource> download(String userAgent, int attachNo);
	public ResponseEntity<Resource> downloadAll(String userAgent, int uploadNo);
	public void modifyUpload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void removeAttachByAttachNo(int attachNo); // 삭제하려면 attachNo필요
	public void removeUpload(HttpServletRequest multipartRequest, HttpServletResponse response);
}
