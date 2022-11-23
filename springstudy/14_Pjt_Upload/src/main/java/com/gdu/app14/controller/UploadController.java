package com.gdu.app14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList()); // list.jsp에서 uploadList라는 이름으로 가져가므로
		return "upload/list";
	}
	
	@GetMapping("/upload/write")
	public String write() {
		return "upload/write";
	}
	
	// 첨부할 때는 multipartRequest 사용 (일반 Request 사용 불가능)
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		uploadService.save(multipartRequest, response);
	}
	
	// 상세보기
	@GetMapping("/upload/detail") // service에서 필요한 model과 uploadNo전달
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	}
	
	// [첨부파일] 다운로드
	@ResponseBody // 요청헤더를 뒤지는 애 @RequestHeader              // attachNo는 @RequestParam을 생략한 것 1. @RequestParam적어도되고, 2.뒤에 "attachNo"적고싶으면 적어도 되고, 3 생략하고 int attachNo만해도 되고
	@GetMapping("/upload/download")
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent, @RequestParam("attachNo") int attachNo) {
		return uploadService.download(userAgent, attachNo);
	}
	
	@ResponseBody
	@GetMapping("/upload/downloadAll")
	public ResponseEntity<Resource> downloadAll(@RequestHeader("User-Agent") String userAgent, @RequestParam("uploadNo") int uploadNo) {
		return uploadService.downloadAll(userAgent, uploadNo);
	}
	
	@PostMapping("/upload/edit")
	public String edit(@RequestParam("uploadNo") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/edit";
	}
	
	@PostMapping("/upload/modify")
	public void modify(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		uploadService.modifyUpload(multipartRequest, response);
	}
	
	// [첨부파일] 삭제
	@GetMapping("/upload/attach/remove")
	public String attachRemove(@RequestParam("uploadNo") int uploadNo, @RequestParam("attachNo") int attachNo) {
		uploadService.removeAttachByAttachNo(attachNo);
		return "redirect:/upload/detail?uploadNo=" + uploadNo; // uploadNo를 가지고 가야함
	}
	
	@PostMapping("/upload/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		uploadService.removeUpload(request, response);
	}
	
}
