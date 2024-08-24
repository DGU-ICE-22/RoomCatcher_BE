package com.roomcatcher.RoomCatcher.global.exception.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roomcatcher.RoomCatcher.global.exception.dto.ErrorResponse;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 필터에서 인증이나 인가를 통과하지 못했을 때, 이를 Handle할 Handler클래스
// 인증에 실패한 경우 Unauthorized 상태를 반환해줄 EntryPoint
@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        setResponse(response);
    }

    private void setResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter()
            .write(objectMapper.writeValueAsString(
                ErrorResponse.of(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION.getCode(),
                    ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION.getMessage())));
    }
    // Filter에서 예외가 발생했을 경우 Response를 커스텀해서 리턴
}