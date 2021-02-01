package com.gura.spring05.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dao.CafeCommentDao;
import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.exception.DBFailException;

@Service
public class CafeServiceImpl implements CafeService{
	
	//서비스가 의존하는 핵심 의존 객체  DI
	//CafeDao 객체를 주입해서 쓴다.
	@Autowired
	private CafeDao cafeDao;
	
	//의존 객체 DI	
	@Autowired
	private CafeCommentDao cafeCommentDao;
	

	@Override
	public void saveContent(CafeDto dto) {
		// TODO Auto-generated method stub
		cafeDao.insert(dto);
	}


	@Override
	public void getList(ModelAndView mView, HttpServletRequest request) {
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum=1;
		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum=request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어 온다면
		if(strPageNum != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		/*
			[ 검색 키워드에 관련된 처리 ]
			-검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.		
		*/
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		//만일 키워드가 넘어오지 않는다면 
		if(keyword==null){
			//키워드와 검색 조건에 빈 문자열을 넣어준다. 
			//클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
			keyword="";
			condition=""; 
		}
		
		//특수기호를 인코딩한 키워드를 미리 준비한다. 
		String encodedK=URLEncoder.encode(keyword);
		
		//startRowNum 과 endRowNum  을 CafeDto 객체에 담고
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//ArrayList 객체의 참조값을 담을 지역변수를 미리 만든다.
		List<CafeDto> list=null;
		//전체 row 의 갯수를 담을 지역변수를 미리 만든다.
		int totalRow=0;
		//만일 검색 키워드가 넘어온다면 
		if(!keyword.equals("")){
			//검색 조건이 무엇이냐에 따라 분기 하기
			if(condition.equals("title_content")){//제목 + 내용 검색인 경우
				//검색 키워드를 CafeDto 에 담아서 전달한다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){ //제목 검색인 경우
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){ //작성자 검색인 경우
				dto.setWriter(keyword);
			} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
		}
		
		//아래 getList와 getCount에는 dynamic query로 구성되어있다.
		//위의 분기에 따라 dto에 담기는 내용이 다르다.
		//이렇게 조건이 다를때마다 조건에 맞는 쿼리문이 되도록 만드는 것을 dynamic query라고 한다.
		//글목록 얻어오기
		list=cafeDao.getList(dto);
		//글의 개수
		totalRow=cafeDao.getCount(dto);
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		//view page에서 필요한 내용을 ModelAndView 객체에 담아준다.
		mView.addObject("list",list);
		mView.addObject("pageNum",pageNum);
		mView.addObject("startPageNum",startPageNum);
		mView.addObject("endPageNum",endPageNum);
		mView.addObject("totalPageCount",totalPageCount);
		mView.addObject("condition",condition);
		mView.addObject("encodedK",encodedK);
		mView.addObject("totalRow",totalRow);
		
	}


	@Override
	public void getDatail(int num, ModelAndView mView) {
		//글번호를 이용해서 글 정보를 얻어오고
		CafeDto dto=cafeDao.getData(num);
		//글 정보를 ModelAndView 객체에 담고
		mView.addObject("dto", dto);
		//글 조회수를 증가시킨다.
		cafeDao.addViewCount(num);
		//원글의 글 번호를 이용해서 댓글 목록을 얻어온다.
		List<CafeCommentDto> commentList=cafeCommentDao.getList(num);
		//ModelAndView 객체 댓글 목록도 담아온다.
		mView.addObject("commentList", commentList);
		
	}


	@Override
	public void update(CafeDto dto) {
		// TODO Auto-generated method stub
		cafeDao.update(dto);
	}


	@Override
	public void deleteContent(int num) {
		// TODO Auto-generated method stub
		cafeDao.delete(num);
	}


	@Override
	public void saveComment(HttpServletRequest request) {
		//댓글 작성자(로그인된 아이디)
		String writer=(String)request.getSession().getAttribute("id");
		//폼 전송되는 댓글의 정보 얻어내기
		int ref_group=Integer.parseInt(request.getParameter("ref_group"));
		String target_id=request.getParameter("target_id");
		String content=request.getParameter("content");
		/*
		 * 원글의 댓글은 comment_group 번호가 전송이 안되고
		 * 댓글의 댓글은 comment_group 번호가 전송이 된다.
		 * 따라서 null 여부를 조사하면 원글의 댓글인지 댓글의 댓글인지 판별할수 있다.
		 */
		String comment_group=request.getParameter("comment_group");
		//새 댓글의 글번호는 미리 얻어낸다.
		int seq=cafeCommentDao.getSequence();
		//저장할 새 댓글 정보를 dto 에 담기
		CafeCommentDto dto=new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		if(comment_group == null) {//원글의 댓글인 경우 
			//댓글의 글번호와 comment_group 번호를 같게 한다.
			dto.setComment_group(seq);
		}else {//댓글의 댓글인 경우 
			//폼 전송된 comment_group 번호를 숫자로 바꿔서 dto 에 넣어준다.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		//댓글 정보를 DB 에 저장한다.
			cafeCommentDao.insert(dto);
	}
	
	@Override
	public void deleteComment(HttpServletRequest request) {
		//GET 방식 파라미터로 전달되는 삭제할 댓글 번호 
		int num=Integer.parseInt(request.getParameter("num"));
		//세션에 저장된 로그인된 아이디
		String id=(String)request.getSession().getAttribute("id");
		//댓글의 정보를 얻어와서 댓글의 작성자와 같은지 비교 한다.
		String writer=cafeCommentDao.getData(num).getWriter();
		if(!writer.equals(id)) {
			throw new DBFailException("남의 댓글을 삭제할수 없습니다.");
		}
		cafeCommentDao.delete(num);
	}
	
	@Override
	public void updateComment(CafeCommentDto dto) {
		cafeCommentDao.update(dto);
	}
}
