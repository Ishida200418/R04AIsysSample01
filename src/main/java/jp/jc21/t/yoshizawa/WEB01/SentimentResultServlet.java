package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SentimentResultServlet")
public class SentimentResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SentimentResultServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String string = "むっちゃ楽しい！";
		try {
			Sentimental result = Sentiment.getLanguage(string);
			float message01 =  result.documents[0].confidenceScores.negative;
			request.setAttribute("message01", message01);
			
			float message02 =  result.documents[0].confidenceScores.neutral;
			request.setAttribute("message02", message02);
			
			float message03 =  result.documents[0].confidenceScores.positive;
			request.setAttribute("message03", message03);
			
			request.getRequestDispatcher("/WEB-INF/SentimentResult.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String string = request.getParameter("string");
		request.setAttribute("string", string);

		try {
			Sentimental result = Sentiment.getLanguage(string);
			float message01 =  result.documents[0].confidenceScores.negative;
			request.setAttribute("message01", message01);
			
			float message02 =  result.documents[0].confidenceScores.neutral;
			request.setAttribute("message02", message02);
			
			float message03 =  result.documents[0].confidenceScores.positive;
			request.setAttribute("message03", message03);
			
			request.getRequestDispatcher("/WEB-INF/jsp/SentimentResult.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

