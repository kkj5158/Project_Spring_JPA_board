package kafka.boardproject.sec.springsecurity;

import kafka.boardproject.sec.jwt.JwtAuthFilter;
import kafka.boardproject.sec.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;


//    @Bean // 비밀번호 암호화 기능 등록
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    // h2 데이터 베이스 접근 허용
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf().disable();

        // 접근 경로 제한하기 -> 해당 패턴의 경로들만으로 제한한다.
        // 로그인 , 회원가입 페이지만 제한하기



        http.authorizeHttpRequests().
                requestMatchers("/user/**").permitAll().
                requestMatchers("/boards/**").permitAll().
                requestMatchers("/board/**").hasAnyRole("USER", "ADMIN").
                requestMatchers("/comment/**").hasAnyRole("USER", "ADMIN").
                anyRequest().authenticated().and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 경로가 제한된 페이지 접근시에 접근금지 페이지를 띄운다.
        http.exceptionHandling().accessDeniedPage("/user/forbidden");

        // 401 Error 처리, Authorization 즉, 인증과정에서 실패할 시 처리
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        // 403 Error 처리, 인증과는 별개로 추가적인 권한이 충족되지 않는 경우
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        // 로그인 사용
        http.formLogin().loginPage("/user/login-page").loginPage("/user/signup-page").permitAll();
        return http.build();
    }

}
