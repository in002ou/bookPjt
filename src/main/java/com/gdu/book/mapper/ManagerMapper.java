package com.gdu.book.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.AnnouncementDTO;
import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.DeclarationDTO;
import com.gdu.book.domain.QnaDTO;
import com.gdu.book.domain.SummernoteImageDTO;

@Mapper
public interface ManagerMapper {
	
	// 책 등록
	public int addBook(BookDTO bookDTO);
	
	// Qna
	public int addQna(QnaDTO qnaDTO);
	public int addQnaGroupNo(QnaDTO qnaDTO);
	public int getQnaCount();
	public List<QnaDTO> selectQnaList(Map<String, Object> map);
	public int answerQna(QnaDTO qnaDTO);
	public int deleteQna(int qnaNo);
	
	// 공지사항
	public int addAnmt(AnnouncementDTO anoDTO);
	public int addSummernoteImage(SummernoteImageDTO summernoteImageDTO);
	public List<AnnouncementDTO> selectAnmt(Map<String, Object> map);
	public int getAnmtCount();
	public AnnouncementDTO anmtDetail(int anmNo);
	public int deleteAnmt(List<String> anmNo);
	public int updateAnmt(AnnouncementDTO anoDTO);
	public int updateSummernoteImage(SummernoteImageDTO summernoteImageDTO);
	
	// 신고
	public int addDec(DeclarationDTO decDTO);
}
