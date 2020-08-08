package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.CommentDAO;
import model1.CommentTO;

public class CommentModifyOkAction implements CommentAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("CommentModifyOkAction 호출");
		
		String cpage = request.getParameter("cpage");
		String seq = request.getParameter("seq");
		String cseq = request.getParameter("cseq");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
				
		CommentTO to = new CommentTO();
		to.setCseq(cseq);
		to.setPassword(password);
		to.setContent(content);
		
		CommentDAO dao = new CommentDAO();
		int flag = dao.commentModifyOk(to);
		
		request.setAttribute("flag", flag);
	}

}
