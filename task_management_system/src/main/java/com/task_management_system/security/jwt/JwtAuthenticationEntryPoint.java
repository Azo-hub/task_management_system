package com.task_management_system.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task_management_system.utilities.HttpCustomResponse;
import com.task_management_system.utilities.SecurityConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException arg2) throws IOException {

        HttpCustomResponse httpCustomResponse =
                new HttpCustomResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN,
                        HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
                        SecurityConstant.FORBIDDEN_MESSAGE);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpCustomResponse);
        outputStream.flush();

    }

}
