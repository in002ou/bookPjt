package com.gdu.book.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.book.domain.AnnouncementDTO;
import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.QnaDTO;
import com.gdu.book.domain.SummernoteImageDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.mapper.ManagerMapper;
import com.gdu.book.util.MyFileUtil;
import com.gdu.book.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {
	
	private final ManagerMapper managerMapper;
	private final PageUtil pageUtil;
	private final MyFileUtil myFileUtil;
	
	@Override
	public String queryBook(HttpServletRequest request) {
    
		String query = request.getParameter("query");
		int display = 100;
		String sort = "sim";
	
		String clientId = "RoXpq8aHMxKbbLSBeIrg";
		String clientSecret = "V5clNGosjT";
		
		try {
		  
		  String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + URLEncoder.encode(query, "UTF-8") + "&display=" + display + "&sort=" + URLEncoder.encode(sort, "UTF-8"); 
		  URL url = new URL(apiURL);
		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	  
		  conn.setRequestMethod("GET");
		  conn.setRequestProperty("X-Naver-Client-Id", clientId);
		  conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	  
		  BufferedReader br = null;
		  if(conn.getResponseCode() == 200) {
		    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
		  } else {
		    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		  }
	  
		  StringBuilder sb = new StringBuilder();
		  String line = null;
		  
		  while((line = br.readLine()) != null) {
		    sb.append(line);
		  }
		  br.close();
		  
		  conn.disconnect();
		  
		  return sb.toString();
		  /*
		  JSONObject obj = new JSONObject(sb.toString());
		  JSONArray bookList = obj.getJSONArray("items");
		  
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("books", bookList);
		  return map;
		  */
	      
    		}catch (Exception e) {
			e.printStackTrace();
    	}
    	return null;
	}
  
	@Override
	public Map<String, Object> addBook(HttpServletRequest request) {
		
		try {
			String bookNo = request.getParameter("bookNo");
			String bookTitle = request.getParameter("bookTitle");
			int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
			String bookAtt =  request.getParameter("bookAt");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date bookAt = null;
			bookAt = dateFormat.parse(bookAtt);
			String bookAuthor = request.getParameter("bookAuthor");
			String bookImage = request.getParameter("bookImage");
			String bookDescription = request.getParameter("bookDescription");
			
			
			BookDTO bookDTO	= new BookDTO();
			bookDTO.setBookNo(bookNo);
			bookDTO.setBookTitle(bookTitle);
			bookDTO.setBookPrice(bookPrice);
			bookDTO.setBookAt(bookAt);
			bookDTO.setBookAuthor(bookAuthor);
			bookDTO.setBookImage(bookImage);
			bookDTO.setBookDescription(bookDescription);
			
			int addResult = managerMapper.addBook(bookDTO);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("addResult", addResult);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void QnaList(HttpServletRequest request, Model model) {
		
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		int totalRecord = managerMapper.getQnaCount();
		
		int recordPerPage = 10;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<QnaDTO> qnaList = managerMapper.selectQnaList(map);
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/manager/QnA.do"));
		
	}
	
	@Transactional
	@Override
	public int addQna(HttpServletRequest request) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserNo(1);
		String title = request.getParameter("title");
		int qnaState = 1;
		QnaDTO qnaDTO = new QnaDTO();
		qnaDTO.setUserDTO(userDTO);
		qnaDTO.setTitle(title);
		qnaDTO.setQnaState(qnaState);
		
		int addResult = managerMapper.addQna(qnaDTO);
		
		addResult += managerMapper.addQnaGroupNo(qnaDTO);
		
		return addResult;
	}
	
	@Override
	public int answerQna(HttpServletRequest request) {
		String answer = request.getParameter("answer");
		int depth = Integer.parseInt(request.getParameter("depth"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		
		QnaDTO qnaDTO = new QnaDTO();
		
		qnaDTO.setAnswer(answer);
		qnaDTO.setGroupNo(groupNo);
		qnaDTO.setDepth(depth);
		qnaDTO.setQnaNo(qnaNo);
		
		int updateResult = managerMapper.answerQna(qnaDTO);
		
		return updateResult;
	}
	
	@Override
	public int deleteQna(HttpServletRequest request) {
		int qnoNo = Integer.parseInt(request.getParameter("qnaNo"));
		
		int deleteResult = managerMapper.deleteQna(qnoNo);
		return deleteResult;
	}
	
	@Transactional
	@Override
	public void addAnmt(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String anmContent = request.getParameter("anmContent");
		int anmCount = 0;
		
		AnnouncementDTO anmDTO = new AnnouncementDTO();
		anmDTO.setTitle(title);
		anmDTO.setAnmContent(anmContent);
		anmDTO.setAnmCount(anmCount);
		
		
		
		Document document = Jsoup.parse(anmContent);
	    Elements elements = document.getElementsByTag("img");
		
	    if(elements != null) {
	        for(Element element : elements) {
	          String src = element.attr("src");
	          String filesystemName = src.substring(src.lastIndexOf("/") + 1);
	          SummernoteImageDTO summernoteImageDTO = new SummernoteImageDTO();
	          summernoteImageDTO.setFilesystemName(filesystemName);
	          summernoteImageDTO.setBlogNo(anmDTO.getAnmNo());
	          anmDTO.setImage(filesystemName);
	          
	          managerMapper.addSummernoteImage(summernoteImageDTO);
	        }
	    }
	    
	    int addResult = managerMapper.addAnmt(anmDTO);
	    
	    // 응답
	    try {
	      
	      response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      out.println("<script>");
	      if(addResult == 1) {
	          out.println("alert('공지사항이 등록되었습니다.');");
	          out.println("location.href='" + "/manager/anmt.do';");
	      } else {
	          out.println("alert('공지사항 등록이 실패했습니다.');");
	          out.println("history.back();");
	      	}
	      out.println("</script>");
	      out.flush();
	      out.close();
	      
	      
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
		
	}
	
	
	
	@Override
	public void selectAnmt(HttpServletRequest request, Model model) {
		
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		int totalRecord = managerMapper.getAnmtCount();
		
		int recordPerPage = 10;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<AnnouncementDTO> list = managerMapper.selectAnmt(map);
		
		model.addAttribute("anmtList", list);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/manager/anmt.do"));
		
	}
	
	@Override
	public AnnouncementDTO anmtDetail(int anmNo) {
		
		AnnouncementDTO anmDTO = managerMapper.anmtDetail(anmNo);
		
		return anmDTO;
	}
	
	@Override
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) {
    
		 MultipartFile multipartFile = multipartRequest.getFile("file");
		
		 String summernoteImagePath = myFileUtil.getSummernoteImagePath();
		
		 File dir = new File(summernoteImagePath);
		 if(dir.exists() == false) {
			 dir.mkdirs();
		 }
		
		 String filesystemName = myFileUtil.getFilesystemName(multipartFile.getOriginalFilename());
		
		 try {
			 File file = new File(dir, filesystemName);
			 multipartFile.transferTo(file);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("src", "/imageLoad/" + filesystemName);
		 
		 return map;
			 
	}
	
	@Override
	public void removeAnmt(HttpServletRequest request, HttpServletResponse response) {
		
		String[] anmNo = request.getParameterValues("anmNo");
		
		int removeResult = managerMapper.deleteAnmt(Arrays.asList(anmNo));
		
		try {	
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(removeResult == anmNo.length) {
				out.println("alert('선택된 모든 상품이 삭제되었습니다.')");
				out.println("location.href='" + "/manager/anmt.do'");
			} else {
				out.println("alert('선택된 상품이 삭제되지 않았습니다.')");
				out.println("location.href='" + "/manager/anmt.do'");
			}
			out.println("</script>");
			out.flush();
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void modifyAnmt(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String anmContent = request.getParameter("anmContent");
		int anmNo = Integer.parseInt(request.getParameter("anmNo"));
		
		AnnouncementDTO anmDTO = new AnnouncementDTO();
		anmDTO.setTitle(title);
		anmDTO.setAnmContent(anmContent);
		anmDTO.setAnmNo(anmNo);
		
		Document document = Jsoup.parse(anmContent);
	    Elements elements = document.getElementsByTag("img");
		
	    if(elements != null) {
	        for(Element element : elements) {
	          String src = element.attr("src");
	          String filesystemName = src.substring(src.lastIndexOf("/") + 1);
	          SummernoteImageDTO summernoteImageDTO = new SummernoteImageDTO();
	          summernoteImageDTO.setFilesystemName(filesystemName);
	          summernoteImageDTO.setBlogNo(anmDTO.getAnmNo());
	          anmDTO.setImage(filesystemName);
	          
	          managerMapper.updateSummernoteImage(summernoteImageDTO);
	        }
	    }
	    
	    int modifyResult = managerMapper.updateAnmt(anmDTO);
	    
	    // 응답
	    try {
	      
	      response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      out.println("<script>");
	      if(modifyResult == 1) {
	          out.println("alert('공지사항이 수정되었습니다.');");
	          out.println("location.href='" + "/manager/anmt.do';");
	      } else {
	          out.println("alert('공지사항 수정이 실패했습니다.');");
	          out.println("history.back();");
	      	}
	      out.println("</script>");
	      out.flush();
	      out.close();
	      
	      
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
		
	}
}
