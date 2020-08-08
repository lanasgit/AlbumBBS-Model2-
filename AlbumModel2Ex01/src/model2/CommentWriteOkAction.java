package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.CommentDAO;
import model1.CommentTO;

public class CommentWriteOkAction implements CommentAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("CommentWriteOkAction 호출");
		
		CommentTO cto = new CommentTO();
		cto.setSeq(request.getParameter("seq"));
		cto.setWriter(request.getParameter("cwriter"));
		cto.setPassword(request.getParameter("cpassword"));
		cto.setContent(request.getParameter("ccontent"));
		
		CommentDAO cdao = new CommentDAO();
		int flag = cdao.commentWriteOk(cto);
		
		// java에서 jsp로 데이터를 옮기는 방법
		request.setAttribute("flag", flag);
	}

}
