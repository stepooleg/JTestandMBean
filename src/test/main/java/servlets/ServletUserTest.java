package main.java.servlets;

import main.java.server.ServerUserRegistr;
import main.java.server.ServerUserRegistrable;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;


public class ServletUserTest {
    private ServerUserRegistrable serverUserRegistrable = mock(ServerUserRegistr.class);

    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {

        HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        return response;
    }

    private HttpServletRequest getMockedRequest(String url) {
        HttpSession httpSession = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(httpSession);
        when(request.getPathInfo()).thenReturn(url);
        return request;
    }

    @Test
    public void testRemove() throws ServletException, IOException {
        final StringWriter stringWriter = new StringWriter();
        HttpServletRequest request = getMockedRequest(ServletUser.PAGE_URL);
        HttpServletResponse response = getMockedResponse(stringWriter);
        when(request.getParameter("remove")).thenReturn("");

        ServletUser servletUser = new ServletUser(serverUserRegistrable);

        servletUser.doGet(request, response);

        assertEquals("Goodbay!!", stringWriter.toString().trim());
        verify(serverUserRegistrable, times(1)).removeUser();


    }
}