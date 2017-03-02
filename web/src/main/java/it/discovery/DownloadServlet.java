package it.discovery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**1. Создайте базовую папку на вашем компьютере, например c:\base.
2. Создайте новый сервлет в вашем веб-приложении. Этот сервлет должен обрабатывать GET запрос и
 возвращать файл в папке c:\base.
3. Например, если у вас есть файл c:\base\text\data.txt, context path вашего приложения web,
 URL сервлета - download, то после открытия адреса http://localhost:8080/web/download/text/data.txt
он вернуть файл data.txt(открыть для скачивания либо открыть в браузере).
*/
/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download/*")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String BASE_PATH = System.getProperty("user.home") + "/base";
	private static final String SERVLET_URL_PATTERN = "/download";

	public DownloadServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String filePath = BASE_PATH + requestURI
				.substring(requestURI.indexOf(SERVLET_URL_PATTERN) + SERVLET_URL_PATTERN.length());
		PrintWriter out = response.getWriter();

		try (FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr);) {

			String line = null;
			while ((line = br.readLine()) != null) {
				out.append(line);
			}
		} catch (IOException ex) {

			response.sendError(404, "file not found");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
