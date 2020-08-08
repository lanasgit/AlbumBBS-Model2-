package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;
import model1.CommentDAO;
import model1.CommentTO;

public class BoardViewAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("BoardViewAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardView(to);
		
		CommentDAO cdao = new CommentDAO();
		ArrayList<CommentTO> cLists = cdao.commentList(to);
		
		request.setAttribute("cpage", request.getParameter("cpage"));
		request.setAttribute("to", to);
		request.setAttribute("cLists", cLists );
	}

}
