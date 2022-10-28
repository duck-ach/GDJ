package com.gdu.app02.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
	@Controller
	
	�ȳ�, �� ��Ʈ�ѷ���
	@Conponent�� ���ؼ� �ڵ����� Bean���� ��������� �������� ���ؼ� ������.
	
 */

@Controller
public class MvcController {
	
	// �޼ҵ� 1�� : ��û 1���� ���� 1���� ó���ϴ� ����(����ġ�� �и��ϴ����� ����ġ���� �޼ҵ�� �и��ϸ� �ȴ�.)
	
	/*
		��û�� ó���ϱ����� Annotation �Ұ�
		
		@RequestMapping
		�ȳ�? �� ��û�� �ν��ϴ� Annotation�̾�.
		URLMapping�� ��û �޼ҵ�(GET, POST) �� �ν�����
		
		�Ӽ�
			1) value  : URL Mapping
			2) method : RequestMethod 
		
	 */
	
	// welcome ���� �۾��ϱ�
	// URLMapping���� "/"�� ��û�ϸ� "/WEB-INF/views/index.jsp"�� �����ش�.
	// @�ֳ����̼��� ���������� �ʰ� �޼ҵ���� ����� �ڵ��ϼ��� �� �ȴ�.
	
	@RequestMapping(value="/", method=RequestMethod.GET) //������ ������� �����Ƿ� method="GET" �̷��� �ؽ�Ʈ�� ����ϸ� �ȵȴ�.
	
	// �޼ҵ� �ۼ� ���
	// 1. ��ȯŸ�� : String (������ ��(JSP)�� �̸��� ��ȯ)
	// 2. �޼ҵ�� : �ƹ� �ϵ� ����. ����� �ۼ�.
	// 3. �Ű����� : ����(��û�� ������ request, ������ ����� response ��)
	// method�� ������ import�� ��
	public String welcome() {
		return "index"; // DispatcherServlet�� ViewResolver�� ���ؼ� �ؼ��ȴ�.
	} 					// prefix="/WEB-INF/views/"
						// suffix=".jsp"
						// prefix�� suffix�� ���ؼ� index��� �Է��ص� "/WEB-INF/views/index.jsp"�� ���� �ؼ��ǰ� ó���ȴ�.
						// servlet-context.xml�� <beans:bean>�±׿� �����Ǿ� �ִ�.
	// index.jsp�� forward ������? redirect ������?
	// ���� : forward �ߴ�.
	// redirect�Ҷ��� return "redirect:���"; ó�� ��ȯ�Ѵ�.
	
	
	// <a href="${contextPath}/animal">
	@RequestMapping(value="/animal", method=RequestMethod.GET)
	public String ������������() {
		//  /WEB-INF/views/ + gallery/animal + .jsp
		return "gallery/animal"; // ������/jsp��
	}
	
	// �Ʒ� �ټ����� ���� �ڵ��̴�.
	// @RequestMapping(value="/animal", method=RequestMethod.GET)
	// @RequestMapping(value="animal", method=RequestMethod.GET)  �����ð� ��� �˴ϴ�.
	// @RequestMapping(value="/animal")							  GET�� ��� �˴ϴ�. (POST�� �־�� ��)
	// @RequestMapping("/animal")								  value�� �ν��մϴ�. 
	// @RequestMapping("animal") 								  ��������
	
	// <a href="${contextPath}/flower">
	@RequestMapping("flower")
	public String �ɺ�������() {
		// return "/gallery/flower" ������(/)�� �־ �˴ϴ�.
		return "gallery/flower"; // ������(/)�� ��� �˴ϴ�.
	}
	
	// <a href="${contextPath}/animal/flower">
	@RequestMapping("animal/flower") // �����������ٰ� �ɺ�������
	public String ��������ɺ���() {
		// redirect�Ҷ��� return "redirect:���"; ó�� ��ȯ�Ѵ�.
		// redirect: �������� �׻� �ٸ� URL Mapping�� ���� �ش�.
		return "redirect:/flower"; 
		
	}
	
	// <a href="${contextPath}/want/animal?filename=animal5.jsp">
	@RequestMapping("want/animal")
	public String ����5����(HttpServletRequest request) {
		// ��û�Ķ���ʹ� request�� ���޵ȴ�.
		
		System.out.println(request.getParameter("filename"));
		return "gallery/animal5";
		
	}
	
	// <a href="${contextPath}/response">
	@RequestMapping("response")
	public void ���丸���(HttpServletRequest request ,HttpServletResponse response) {
		
		// ������ ���鶧�� return�� ���� �ʴ´�.
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���� ���� ����')");
			out.println("location.href='" + request.getContextPath() + "/animal';");
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
