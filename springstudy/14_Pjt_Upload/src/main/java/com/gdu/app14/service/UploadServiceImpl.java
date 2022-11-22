package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.AttachDTO;
import com.gdu.app14.domain.UploadDTO;
import com.gdu.app14.mapper.UploadMapper;
import com.gdu.app14.util.MyFileUtil;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadMapper uploadMapper;
	
	@Autowired
	private MyFileUtil myFileUtil;
	
	@Override
	public List<UploadDTO> getUploadList() {
		// 페이징 처리도 해야하는데 생략함 (11장참고)
		return uploadMapper.selectUploadList();
	}
	
	@Transactional // 2개의 테이블에 동시에 insert가 일어나므로
	@Override
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		/* Upload 테이블에 저장하기 */
		// 파라미터
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// DB로 보낼 UploadDTO
		UploadDTO upload = UploadDTO.builder()
				.title(title)
				.content(content)
				.build();
		// System.out.println(upload); // uploadNo 없음
		
		// DB에 UploadDTO 저장
		int uploadResult = uploadMapper.insertUpload(upload); // <selectKey>에 의해서 인수 upload에 uploadNo값이 저장된다.
		
		// System.out.println(upload); // uploadNo 있음
		/* 
			uploadNo UPLOAD_SEQ의 정체
		
			1/제목/내용/1121/1121
			
			1/파일경로(C:STORAGE)/파일원본이름(APPLE)/저장할때이름(APPLE)/다운로드개수0/게시글1번
			2/파일경로(C:STORAGE)/파일원본이름(BANANA)/저장할때이름(BANANA)/다운로드개수0/게시글1번
			3/파일경로(C:STORAGE)/파일원본이름(PEACH)/저장할때이름(PEACH)/다운로드개수0/게시글1번
			
			ATTACH 테이블에서 게시글 1번이라는 정보를 알 기 위해서는 SERVICE에서는 UPLOAD_NO의 현재 SEQ값을 모르고,
			DB에서 알 수 있기 때문에 DB에서 2번 작업 해주어야 한다.
			
			DB에서 selectKey를 이용하여 (UPLOAD_NO)를 UploadDTO에 넣어주었으므로 Service입장에서는 UploadDTO에서 가져오면 된다.
		*/
		
		/* ATTACH 테이블에 저장하기 */
		/* C:\GDJ\installer\sts-bundle\sts-3.9.18.RELEASE\storage\2022\11\21 에서 확인 가능 배포하면 배포된 곳에 따로 저장소가 열릴것임 */
		
		
		// 첨부된 파일 목록 가져오기
		List<MultipartFile> files = multipartRequest.getFiles("files"); // <input type="file" id="files" name="files" multiple="multiple"> 의 name값을 가져온 것
		
		
		// 첨부 결과(첨부 파일이 0개인 경우 게시글은 작성되오나, 오류메세지와 페이지 넘김이 잘 안되는것을 방지)
		int attachResult = 0;
		if(files.get(0).getSize() == 0) {  // 첨부가 없는 경우 (files 리스트에 [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 이렇게 저장되어 있어서 files.size()가 1이다.
			attachResult = 1;
		} else {
			attachResult = 0;
		}
		// 첨부된 파일 목록 순회(하나씩 저장
		for(MultipartFile multipartFile : files)  { // files 리스트 안에 MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0] 이렇게 저장되어있어서 files.size()가 0이아니라 1이다.
			try {
				// 첨부가 있는지 점검
				if(multipartFile != null && multipartFile.isEmpty() == false) { // 둘 다 필요함
					
					// 원래 이름
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1); // 역슬래쉬(\) 하나. IE(인터넷 익스플로러)는 origin에 전체 경로가 붙어서 파일명만 사용해야 함
					
					// 저장할 이름
					String filesystem = myFileUtil.getFilename(origin);
					
					// 저장할 경로
					String path = myFileUtil.getTodayPath();
					
					// 저장할 경로 만들기
					File dir = new File(path);
					if(dir.exists() == false) { // s안쓰면 오류남
						dir.mkdirs();
					}
					
					// 첨부할 File 객체
					File file = new File(dir, filesystem); // 경로, 파일의 이름
					
					// 첨부파일 서버에 저장(업로드 진행)
					multipartFile.transferTo(file); // 필요한 복사작업을 해주는 것
					
					// AttachDTO 생성
					AttachDTO attach = AttachDTO.builder()
							.path(path)						// 경로
							.origin(origin) 				// 원래이름
							.filesystem(filesystem)			// 바뀐 이름
							.uploadNo(upload.getUploadNo()) // 업로드 번호(DB에서 가져옴)
							.build();
					
					// DB에 AttachDTO 저장
					attachResult += uploadMapper.insertAttach(attach); // 누적(첨부된 개수와 attachResult와 같은지 비교하면 몇개의 첨부가 성공했는지 알 수 있다.)
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // for문 순회
		//응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(uploadResult > 0 && attachResult == files.size()) { // files는 첨부된 모든 애들 (list는 size가 개수)
				out.println("<script>");
				out.println("alert('업로드가 성공적으로 수행되었습니다.');");
				out.println("location.href='" + multipartRequest.getContextPath() + "/upload/list';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('업로드가 실패했습니다.');");
				out.println("'history.back();'");
				out.println("</script>");
			}
			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
			files 리스트 = [
				MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]
			]
			
			List에서 요소를 가져올 때는
			files[0] 불가능. 
			files.get(0).getSize() == 0 으로 가능
			files.getOriginName
			
		*/
	}
	
	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.selectUploadByNo(uploadNo));
		model.addAttribute("attachList", uploadMapper.selectAttachList(uploadNo));
		// 조회수증가 같은거 할거면 여기다가 model에 담아주면 됨
	}
	
	@Override // ResponseEntity는 페이지변화 X, 값 반환 (보통 ajax에서 많이 사용함)
	public ResponseEntity<Resource> download(String userAgent, int attachNo) {
		
		// 다운로드 할 첨부 파일의 정보(경로, 이름)
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		File file = new File(attach.getPath(), attach.getFilesystem()); // (경로, 파일명) 원래이름은 DB에만있음
		
		// 반환할 Resource
		Resource resource = new FileSystemResource(file);
		
		// Resource가 없으면 종료 (다운로드 할 파일이 없음)
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // '못찾겠다~' 만 간단히 반환한것
		}
		
		// 다운로드 횟수 증가
		uploadMapper.updateDownloadCnt(attachNo);
		
		// 다운로드 되는 파일명(브라우저 마다 다르게 세팅)
		String origin = attach.getOrigin();
		try {
			// UserAgent 값을 꺼내면 어떤 브라우저에서 접속했는 지 알 수 있다.
			// IE (userAgent에 "Trident"가 포함되어 있음)
			if(userAgent.contains("Trident")) { // replaceAll 뒤의 +는 정규식이기 때문에 인식시키기 위해 \\를 붙여주어야한다.
				origin = URLEncoder.encode(origin, "UTF-8").replaceAll("\\+", " ");
			}
			// Edge (userAgent에 "Edg"가 포함되어 있음)
			else if (userAgent.contains("Edg")) {
				origin = URLEncoder.encode(origin, "UTF-8");
			}
			// 나머지
			else {
				origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 헤더 만들기(spring framework)
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Disposition", "attachment; filename=" + origin); // attach만 하고 끝내도 됨(하지만 다운로드 이름 정해주어야 함)
		header.add("Content-Length", file.length() + "");
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@Override
	public void removeAttachByAttachNo(int attachNo) {
		
		// 삭제할 Attach 정보 받아오기
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo); // 삭제 전에 정보를 가지고 와야함
		
		// DB에서 Attach 정보 삭제
		int result = uploadMapper.deleteAttachByAttachNo(attachNo);
		
		// 첨부파일 삭제
		if(result > 0) {
			// 첨부파일을 File 객체로 만듬
			File file = new File(attach.getPath(), attach.getFilesystem());
			
			// 삭제
			if(file.exists()) {
				file.delete(); // 파일 삭제
			}
		}
		
	}
	
}
