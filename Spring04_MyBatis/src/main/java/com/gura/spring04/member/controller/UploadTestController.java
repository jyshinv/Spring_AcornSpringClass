package com.gura.spring04.member.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring04.member.dto.FileDto;

@Controller
public class UploadTestController {
	/*
	<input type="file" name="myfile"/>
	
	을 통해서 전송되는 파일 정보는 MultipartFile 객체로 받으면 된다. 
	name속성의 value "myFile"과 
	MultipartFile 지역변수명 myFile과 같아야 한다.
	*/
	@RequestMapping("/upload.do") //.do는 생략가능
	public String upload(@RequestParam String title, MultipartFile myFile,
			HttpServletRequest request) {
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//파일을 저장할 실제 경로 "webapp/upload"
		String realPath=request.getServletContext().getRealPath("/upload");
		File upload=new File(realPath);
		if(!upload.exists()) { //만일 존재하지 않으면
			upload.mkdir();//폴더를 만든다.
		}
		//저장할 파일명을 구성한다. 1977.1.1 0시를 기준으로 경과시간이 1000분의1초 단위로 나온다.
		//똑같은 이름의 파일을 저장하더라도 파일이름에 중복이 생기지 않도록 currentTimeMillis를 쓴다. 
		String saveFileName=System.currentTimeMillis()+orgFileName;
		//저장할 파일의 전체 경로를 구성한다.
		//윈도우 :\, linux:/이기 때문에 separator을 이용해주어야한다.
		String path=realPath+File.separator+saveFileName;
		try {
			//임시폴더에 업로드 된 파일을 원하는 위치에 원하는 파일명으로 이동시킨다.
			myFile.transferTo(new File(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//전송된 정보를 view page에서 확인하기 위해 request scope에 담기
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("path", path);
		request.setAttribute("title", title);
		
		return "upload";
	}
	
	/*
	폼전송된 String title과 MultipartFile myFile을 FileDto 객체에 담아서 쓸 수 있게 해준다.
	위의 upload() 메소드와 차이점을 확인할 것!
	*/
	@RequestMapping("/upload2.do") //.do는 생략가능
	public String upload2(FileDto dto, HttpServletRequest request) {
		//원본 파일명
		String orgFileName=dto.getmyFile().getOriginalFilename();
		//파일의 크기
		long fileSize=dto.getmyFile().getSize();
		//파일을 저장할 실제 경로 "webapp/upload"
		String realPath=request.getServletContext().getRealPath("/upload");
		File upload=new File(realPath);
		if(!upload.exists()) { //만일 존재하지 않으면
			upload.mkdir();//폴더를 만든다.
		}
		//저장할 파일명을 구성한다. 1977.1.1 0시를 기준으로 경과시간이 1000분의1초 단위로 나온다.
		//똑같은 이름의 파일을 저장하더라도 파일이름에 중복이 생기지 않도록 currentTimeMillis를 쓴다. 
		String saveFileName=System.currentTimeMillis()+orgFileName;
		//저장할 파일의 전체 경로를 구성한다.
		//윈도우 :\, linux:/이기 때문에 separator을 이용해주어야한다.
		String path=realPath+File.separator+saveFileName;
		try {
			//임시폴더에 업로드 된 파일을 원하는 위치에 원하는 파일명으로 이동시킨다.
			dto.getmyFile().transferTo(new File(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//전송된 정보를 view page에서 확인하기 위해 request scope에 담기
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("path", path);
		request.setAttribute("title", dto.getTitle());
		
		return "upload";
	}
	
}
