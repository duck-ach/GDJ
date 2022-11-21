package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
		
		// 첨부 결과
		int attachResult = 0;
		
		// 첨부된 파일 목록 가져오기
		List<MultipartFile> files = multipartRequest.getFiles("files"); // <input type="file" id="files" name="files" multiple="multiple"> 의 name값을 가져온 것
		
		
		// 첨부된 파일 목록 순회(하나씩 저장
		for(MultipartFile multipartFile : files) {
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
		
	}
	
	
}
