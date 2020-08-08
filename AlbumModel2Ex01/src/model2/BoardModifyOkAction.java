package model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.BoardDAO;
import model1.BoardTO;

public class BoardModifyOkAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("BoardModifyOkAction 호출");
		
		String uploadPath = "C:/JSP/jsp-workspace/AlbumModel2Ex01/WebContent/upload";
		int maxFileSize = 1024 * 1024 * 5; //5MB
		String encType = "utf-8";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy());

			BoardTO to = new BoardTO();	
			to.setCpage(multi.getParameter("cpage"));
			to.setSeq(multi.getParameter("seq"));
			to.setSubject(multi.getParameter("subject"));
			to.setPassword(multi.getParameter("password"));
			to.setContent(multi.getParameter("content"));
			to.setMail("");
			if (!multi.getParameter("mail1").equals("") && !multi.getParameter("mail2").equals("")) {
				to.setMail(multi.getParameter("mail1") + "@" + multi.getParameter("mail2"));
			}
			to.setNewFilename(multi.getFilesystemName("upload"));
			long newFilesize = 0;
			File newFile = multi.getFile("upload");
			if (newFile != null) {
				to.setNewFilesize(newFile.length());
			}

			BoardDAO dao = new BoardDAO();
			int flag = dao.boardModifyOk(to);
			
			request.setAttribute("flag", flag);
			request.setAttribute("cpage", to.getCpage());
			request.setAttribute("seq", to.getSeq());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
