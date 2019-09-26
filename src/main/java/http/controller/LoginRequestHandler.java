package http.controller;

import db.DataBase;
import http.model.*;
import http.supoort.NonexistentUserIdException;
import http.supoort.NotMatchPasswordException;
import http.view.Model;
import http.view.ModelAndView;
import model.User;

public class LoginRequestHandler implements HttpRequestHandler {
    private static final String LOCATION = "Location";
    private static final String ROOT_URI = "Location: http://localhost:8080";
    private static final String CONTENT_TYPE = "Content-Type";

    @Override
    public ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        HttpParameters httpParameters = httpRequest.getParameters();

        String userId = httpParameters.getParameter("userId");
        String password = httpParameters.getParameter("password");

        User findUser = DataBase.findUserById(userId);
        if (userId == null) {
            throw new NonexistentUserIdException();
        }
        if (!findUser.getPassword().equals(password)) {
            throw new NotMatchPasswordException();
        }
        Model model = new Model();
        model.addAttributes("user", findUser);
        ModelAndView modelAndView = new ModelAndView(model, "redirect:/index.html");

        createHttpResponse(httpResponse);
        return modelAndView;
    }

    private void createHttpResponse(HttpResponse httpResponse) {
        StatusLine statusLine = new StatusLine(HttpProtocols.HTTP1_1, HttpStatus.FOUND);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.addHeader(LOCATION, ROOT_URI + "/index.html");
        httpHeaders.addHeader(CONTENT_TYPE, ContentType.HTML.getType());
        httpResponse = new HttpResponse(statusLine, httpHeaders);
    }
}
