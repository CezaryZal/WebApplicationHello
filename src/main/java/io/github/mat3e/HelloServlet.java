package io.github.mat3e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "hello", urlPatterns = "/api/*") //gwiazdka umożliwia odpalenie `hello` nie zależnie co jest dalej
public class HelloServlet extends HttpServlet {
    private static final String NAME_PARAM = "name";
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    /**
     *  Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public HelloServlet (){
        this(new HelloService());
    }

    HelloServlet (HelloService service){
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request with parameters " + req.getParameterMap());

        var name = req.getParameter(NAME_PARAM);
        var greeting = service.prepareGreeting(name);
        resp.getWriter().write(greeting);

        //resp.getWriter().write(service.prepareGreeting(req.getParameter(NAME_PARAM)));
    }
}


