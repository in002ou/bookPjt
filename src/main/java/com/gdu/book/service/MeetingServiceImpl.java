package com.gdu.book.service;

import java.io.File;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gdu.book.domain.MeetingDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.util.MeetingFileUtil;
import com.gdu.book.util.MyFileUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private MeetingFileUtil meetingFileUtil;
	
	private String sep = Matcher.quoteReplacement(File.separator);
	
	@Override
	public int createMeeting(MeetingDTO meetingDTO, MultipartFile imageFile) {
		
		if(imageFile != null && imageFile.isEmpty() == false) {
			try {
				String path = meetingFileUtil.getPath();
				
				File dir = new File(path);
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				String originName = imageFile.getOriginalFilename();
				originName = originName.substring(originName.lastIndexOf(sep) + 1);
				
				String filesystemName = meetingFileUtil.getFilesystemName(originName);
				
				File file = new File(dir, filesystemName);
				imageFile.transferTo(file);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
}
