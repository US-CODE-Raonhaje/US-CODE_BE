package com.raonhaje.memorymap.auth.application;

import com.raonhaje.memorymap.auth.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.*;

import jakarta.annotation.PostConstruct;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoAuthService {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret:}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        System.out.println("Kakao Client ID: " + clientId);
        System.out.println("Redirect URI: " + redirectUri);
    }

    public String getAccessToken(String code) {
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", "http://localhost:8080/api/v1/auth/kakao");
        params.add("code", code);

        if (clientSecret != null && !clientSecret.isBlank()) {
            params.add("client_secret", clientSecret);
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Map.class
            );
            return (String) response.getBody().get("access_token");
        } catch (HttpClientErrorException e) {
            System.out.println("카카오 토큰 요청 실패");
            System.out.println("상태 코드: " + e.getStatusCode());
            System.out.println("응답 본문: " + e.getResponseBodyAsString());
            throw e;
        }
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url, HttpMethod.GET, request, Map.class
            );

            Map<String, Object> body = response.getBody();
            Long id = ((Number) body.get("id")).longValue();

            return new KakaoUserInfo(id);
        } catch (HttpClientErrorException e) {
            System.out.println("사용자 정보 요청 실패");
            System.out.println("상태 코드: " + e.getStatusCode());
            System.out.println("응답 본문: " + e.getResponseBodyAsString());
            throw e;
        }
    }
}