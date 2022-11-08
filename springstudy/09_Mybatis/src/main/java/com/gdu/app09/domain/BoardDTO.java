package com.gdu.app09.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // getter setter 한번에 넣어주는 애
public class BoardDTO {

	private int boardNo;
	private String title, content, writer, createDate, modifyDate;

}
