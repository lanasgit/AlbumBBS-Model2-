package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.CommentDAO;
import model1.CommentTO;

public class CommentDeleteOkAction implements CommentAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("CommentDeleteOkAction 호출");
		
		String cpage = request.getParameter("cpage");
		String seq = request.getParameter("seq");
		String cseq = request.getParameter("cseq");
		String password = request.getParameter("password");
		
		CommentTO to = new CommentTO();
		to.setSeq(seq);
		to.setCseq(cseq);
		to.setPassword(password);
		
		CommentDAO dao = new CommentDAO();
		int flag = dao.commentDeleteOk(to);	
		
		request.setAttribute("flag", flag);
	}

}
