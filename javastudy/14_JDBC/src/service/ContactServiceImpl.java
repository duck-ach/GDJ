package service;

import domain.ContactDTO;
import repository.ContactDAO;

public class ContactServiceImpl implements ContactService {

	/********************* Field **********************/
	// DAO에 데이터를 전달하고, DAO로부터 결과를 반환받기 위해서 DAO를 선언
	private ContactDAO dao = ContactDAO.getInstance();
	
	/********************* Method **********************/
	@Override
	public void addContact(ContactDTO contact) {
		int result = dao.insertContact(contact);
		
		if (result > 0) {
			System.out.println("연락처가 등록되었습니다.");
		} else {
			System.out.println("연락처 등록이 실패했습니다.");
		}
	}

	@Override
	public void modifyContact(ContactDTO contact) {
		int result = dao.updateContact(contact);
		
		if (result > 0) {
			System.out.println("연락처가 수정되었습니다.");
		} else {
			System.out.println("연락처 수정이 실패했습니다.");
		}

	}

	@Override
	public void deleteContact(int contact_no) {
		int result = dao.deleteContact(contact_no);
		
		if (result > 0) {
			System.out.println("연락처가 삭제되었습니다.");
		} else {
			System.out.println("연락처 삭제가 실패했습니다.");
		}

	} 

	@Override
	public void findContact(int contact_no) {
		

	}

	@Override
	public void findALLContacts() {
		

	}

}
