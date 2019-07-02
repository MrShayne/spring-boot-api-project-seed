package com.company.project.exception;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(value = "handle filter throws exception", description = "处理filter抛出的异常")
public class MyErrorController extends BasicErrorController {

    public MyErrorController(ServerProperties serverProperties){
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
    * override json error
    * */
    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        // customize json error
        Map<String, Object> map = new HashMap<>();
        map.put("code", status.value());
        map.put("message", body.toString());
        return new ResponseEntity<>(map, status);
    }
}
