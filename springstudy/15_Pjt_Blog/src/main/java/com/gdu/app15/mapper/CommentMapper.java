package com.gdu.app15.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app15.domain.CommentDTO;

@Mapper
public interface CommentMapper {
	public int selectCommentCount(int BlogNo);
	public int insertComment(CommentDTO comment);
	public List<CommentDTO> selectCommentList(Map<String, Object> map); // blogNo, begin, end 세개를 전달해야 하므로
}
