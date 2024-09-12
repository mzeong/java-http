package org.apache.coyote;

import com.techcourse.service.UserService;
import org.apache.coyote.http.request.RequestBody;
import org.apache.coyote.http11.HttpRequest;
import org.apache.coyote.http11.HttpResponse;
import org.apache.coyote.http11.HttpStatus;

public class RegisterServlet extends HttpServlet {

    private final UserService userService;

    public RegisterServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {
        try {
            RequestBody body = request.getBody();
            String account = body.getValue("account");
            String email = body.getValue("email");
            String password = body.getValue("password");
            userService.save(account, email, password);

            response.sendRedirect("/index.html");
        } catch (IllegalArgumentException exception) {
            response.sendRedirect("/500.html");
        }
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) throws Exception {
        response.setStatus(HttpStatus.OK);
        response.setBody("/register.html");
    }
}