package model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.BoardDAO;
import model1.BoardTO;

public class BoardWriteOkAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("BoardWriteOkAction 호출");
		
		String uploadPath = "C:/JSP/jsp-workspace/AlbumModel2Ex01/WebContent/upload";
		int maxFileSize = 1024 * 1024 * 5; //5MB
		String encType = "utf-8";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy());

			BoardTO to = new BoardTO();
			to.setSubject(multi.getParameter("subject"));
			to.setWriter(multi.getParameter("writer"));
			to.setMail("");
			if (!multi.getParameter("mail1").equals("") && !multi.getParameter("mail2").equals("")) {
				to.setMail(multi.getParameter("mail1") + "@" + multi.getParameter("mail2"));
			}
			to.setPassword(multi.getParameter("password"));
			to.setContent(multi.getParameter("content"));
			to.setFilename(multi.getFilesystemName("upload"));
			long filesize = 0;
			File file = multi.getFile("upload");
			if (file != null) {
				to.setFilesize(file.length());
			}
			to.setWip(request.getRemoteAddr());
			
			BoardDAO dao = new BoardDAO();
			int flag = dao.boardWriteOk(to);
			
			// java에서 jsp로 데이터를 옮기는 방법
			request.setAttribute("flag", flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
