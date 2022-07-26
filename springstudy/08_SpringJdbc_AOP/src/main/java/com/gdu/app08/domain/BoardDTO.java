package com.gdu.app08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // getter setter 한번에 넣어주는 애
public class BoardDTO {

	private int board_no;
	private String title, content, writer, create_date, modify_date;

}
