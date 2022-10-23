package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Post {
	private long post_no; 	// 게시글이 많을 경우를 대비해 long처리. 
	private String writer;
	private String title;
	private String content;
	private long views;  	// 조회수가 많을 경우를 대비해 long처리.
	private Date post_date;
	private Date modify_date;
	private String ip;
	
}
