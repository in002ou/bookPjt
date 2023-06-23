package com.gdu.book.service;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.book.domain.MeetingDTO;
import com.gdu.book.domain.MeetingMemberDTO;
import com.gdu.book.domain.OpenTalkBoardDetailDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.mapper.MeetingMapper;
import com.gdu.book.util.MeetingFileUtil;
import com.gdu.book.util.MyFileUtil;
import com.gdu.book.util.PageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private final MeetingFileUtil meetingFileUtil;
	private final MeetingMapper meetingMapper;
	private final PageUtil pageUtil;
	
	private String sep = Matcher.quoteReplacement(File.separator);
	
	@Override
	@Transactional
	public int createMeeting(MultipartHttpServletRequest multipartRequest) {
		
		MeetingDTO meetingDTO = new MeetingDTO();
		Map<String, Object> map = new HashMap<>();
		
		MultipartFile imageFile = multipartRequest.getFile("image");
//		int userNo = Integer.parseInt(multipartRequest.getParameter("userNo"));
		int userNo = 1;
		UserDTO userDTO = new UserDTO();
		userDTO.setUserNo(userNo);
		meetingDTO.setUserDTO(userDTO);
		String meetingName = multipartRequest.getParameter("meetingName");
		int capacity = Integer.parseInt(multipartRequest.getParameter("capacity"));
		String category = multipartRequest.getParameter("category");
		String meetingContent = multipartRequest.getParameter("meetingContent");
		int addDate = Integer.parseInt(multipartRequest.getParameter("endDate"));
		LocalDate currentDate = LocalDate.now();
		LocalDate endDate = currentDate.plusDays(addDate);
		Date psEndDate = Date.valueOf(endDate);
		
		map.put("userNo", userNo);
		
		meetingDTO.setMeetingName(meetingName);
		meetingDTO.setCapacity(capacity); 
		meetingDTO.setCategory(category);
		meetingDTO.setMeetingEndAt(psEndDate);
		meetingDTO.setMeetingContent(meetingContent);
		
		int addResult = 0;
		
		if(imageFile != null && imageFile.isEmpty() == false) {
			
			try {
				String originFileName = imageFile.getOriginalFilename();
				String extension = originFileName.substring(originFileName.lastIndexOf(".")+1).toLowerCase();
				if(!Arrays.asList("jpg", "jpeg", "png", "gif").contains(extension)){
					return addResult;
				}
				String path = meetingFileUtil.getPath();
				
				File dir = new File(path);
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				String originName = imageFile.getOriginalFilename();
				originName = originName.substring(originName.lastIndexOf(sep) + 1);
				
				String filesystemName = meetingFileUtil.getFilesystemName(originName);
				meetingDTO.setImage(path + originName);
				File file = new File(dir, filesystemName);
				imageFile.transferTo(file);
				
				addResult = meetingMapper.createMeeting(meetingDTO);
				map.put("meetingNo", meetingDTO.getMeetingNo());
				meetingMapper.createApply(map);
				return addResult;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			
			Random random = new Random();
			int randomNum = random.nextInt(6) + 1;
			String defaultImagePath = "/img/book" + randomNum + ".jpg";
			meetingDTO.setImage(defaultImagePath);
			meetingMapper.createMeeting(meetingDTO);
			map.put("meetingNo", meetingDTO.getMeetingNo());
			meetingMapper.createApply(map);
			
		}
		
		return addResult;
		
	}
	
	@Override
	public Map<String, Object> loadMeetingList(HttpServletRequest request) {

		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		int totalRecord = meetingMapper.getMeetingCount();
		
		int recordPerPage = 8;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<MeetingDTO> meetingList = meetingMapper.loadMeetingList(map);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("meetingList", meetingList);
		resultMap.put("totalPage", pageUtil.getTotalPage());
		
		return resultMap;
		
	}
	
	@Transactional
	@Override
	public void getMeetingByNo(HttpServletRequest request, Model model) {
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		
		MeetingDTO meetingDTO = new MeetingDTO();
		meetingDTO = meetingMapper.getMeetingByNo(meetingNo);

		int count = meetingMapper.getMemberCount(meetingNo);
		
		model.addAttribute("meeting", meetingDTO);
		model.addAttribute("count", count);
	}
	
	@Override
	public Map<String, Object> apply(HttpServletRequest request) {

		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", userNo);
		map.put("meetingNo", meetingNo);
		
		int result = meetingMapper.apply(map);
		
		Map<String, Object> applyResult = new HashMap<>();
		applyResult.put("result", result);
		
		return applyResult;
	}
	
	@Override
	public Map<String, Object> comment(HttpServletRequest request) {

		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		
		List<OpenTalkBoardDetailDTO> commentList = meetingMapper.commentList(meetingNo);
		
		System.out.println(commentList);
		
		Map<String, Object> map = new HashMap<>();
		map.put("commentList", commentList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> commentWrite(HttpServletRequest request) {

		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		String boardDetailContent = request.getParameter("commentContent");
		
		OpenTalkBoardDetailDTO openTalkDTO = new OpenTalkBoardDetailDTO();
		openTalkDTO.setMeetingNo(meetingNo);
		openTalkDTO.setBoardDetailContent(boardDetailContent);
		UserDTO user = new UserDTO();
		user.setUserNo(userNo);
		openTalkDTO.setUserDTO(user);
		
		int result = meetingMapper.commentWrite(openTalkDTO);
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	@Override
	public Map<String, Object> participant(HttpServletRequest request) {
		
		int meetingNo = Integer.parseInt(request.getParameter("meetingNo"));
		
		List<MeetingMemberDTO> participantList = meetingMapper.participantList(meetingNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("participantList", participantList);
		
		return map;
	}
	
}
