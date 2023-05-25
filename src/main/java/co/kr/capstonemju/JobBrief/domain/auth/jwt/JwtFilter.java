package co.kr.capstonemju.JobBrief.domain.auth.jwt;

import io.jsonwebtoken.IncorrectClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            // Access Token 추출
            String accessToken = resolveToken(request);

            try { // 정상 토큰인지 검사
                if (accessToken != null && jwtProvider.validateAccessToken(accessToken)) {
                    Authentication authentication = jwtProvider.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("Save authentication in SecurityContextHolder.");
                }
            } catch (IncorrectClaimException e) { // 잘못된 토큰일 경우
                SecurityContextHolder.clearContext();
                log.debug("잘못된 토근입니다. Invalid JWT token.");
                response.sendError(403);
            } catch (UsernameNotFoundException e) { // 회원을 찾을 수 없을 경우
                SecurityContextHolder.clearContext();
                log.debug("회원을 찾을 수 없습니다.");
                response.sendError(403);
            }

            filterChain.doFilter(request, response);
        }

        // HTTP Request 헤더로부터 토큰 추출
        public String resolveToken(HttpServletRequest httpServletRequest) {
            String bearerToken = httpServletRequest.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        }

//        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
//        log.info("authorization : {}",authorization);
//
//        //Authorization 정상 입력 여부
//        if (authorization == null || !authorization.startsWith(BEARER_TYPE)){
//            log.error("authorization 을 잘못 보냈습니다.");
//            filterChain.doFilter(request,response);
//            return;
//        }
//        //Token 꺼내기
//        String token = authorization.split(" ")[1];
//        //Token Expired 여부
//        if (jwtProvider.isExpired(token,secretKey)){
//            log.error("Token 이 만료되었습니다." );
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//
//        //username Token에서 꺼내기
//
//        String username = jwtProvider.getMemberName(token,secretKey);
//        log.info("userName: {}",username);
//
//        //권한 부여
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(username,null, List.of(new SimpleGrantedAuthority("USER")));
//        //Detail 넣어주기
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(request,response);
//    }
}
