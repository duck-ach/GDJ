package com.gdu.app15.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.domain.BlogDTO;
import com.gdu.app15.mapper.BlogMapper;
import com.gdu.app15.util.MyFileUtil;
import com.gdu.app15.util.PageUtil;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	private MyFileUtil myFileUtil;
	
	@Autowired
	public void set(BlogMapper blogMapper, PageUtil pageUtil, MyFileUtil myFileUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = pageUtil;
		this.myFileUtil = myFileUtil;
	}
	
	@Override
	public void getBlogList(Model model) {
		
		// Model에 저장된 request 꺼내기
		Map<String, Object> modelMap = model.asMap(); // model을 map으로 변신
		HttpServletRequest request = (HttpServletRequest) modelMap.get("request");
		
		// 파라미터(paging처리할 때 request에 현재페이지가 필요함)
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 블로그 게시물 개수
		int totalRecord = blogMapper.selectBlogListCount(); // DB에서 구해온다.
		
		// 페이징 처리에 필요한 변수 계산
		pageUtil.setPageUtil(page, totalRecord);
		
		// 조회 조건으로 사용할 Map 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin()); // 계산 하고 꺼내 올 것
		map.put("end", pageUtil.getEnd());
		
		// 뷰로 전달할 데이터를 model에 저장하기
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("blogList", blogMapper.selectBlogListByMap(map));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/blog/list")); // 상세보기를 하다가 페이지이동을 눌렀을 때 목록으로 이동
	}
	
	
	@Override
	public void saveBlog(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 title, content (request에서 꺼냄)
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 작성자의 ip (request에서 꺼냄)
		// 작성된 내용이 어딘가를 경유해서 도착하면 원래 IP가  X-Forwarded-For라는 요청헤더에 저장된다.
		// 출발			경유		도착 	
		// 1.1.1.1		2.2.2.2  	2.2.2.2 : request.getRemoteAddr()
		//							1.1.1.1 : request.getHeader("X-Forwarded-For")
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());
		
		// DB로 보낼 DTO
		BlogDTO blog = BlogDTO.builder()
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		
		// DB에 저장
		int result = blogMapper.insertBlog(blog);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				out.println("<script>");
				out.println("alert('삽입 성공!');");
				out.println("location.href='" + request.getContextPath() + "/blog/list';");
				out.println("</script>");
				
			} else {
				
				out.println("<script>");
				out.println("alert('삽입 실패!');");
				out.println("history.back();");
				out.println("</script>");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 파라미터 file 꺼내기 (파일을 꺼내는 건 getParameter 하면 안됨)
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 저장할 파일 이름
		String filesystem = myFileUtil.getFilename(multipartFile.getOriginalFilename());
		
		// 저장 경로 (separator : 경로 구분자) 윈도우만 쓰려고할 때는 C:\\upload
		String path = "C:\\upload";
		
		// 저장 경로가 없으면 만들기
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 저장할 File 객체
		File file = new File(dir, filesystem); // new File(dir, filesystem)도 가능
		
		// HDD에 File 객체 저장하기
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 저장된 파일을 확인할 수 있는 매핑을 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("src", multipartRequest.getContextPath() + "/load/image/" + filesystem);
		
		return map;
		
		// 저장된 파일이 aaa.jpg라고 가정하면
		// src=${contextPath}/load/image/aaa.jpg 이다.
	}
	
}
