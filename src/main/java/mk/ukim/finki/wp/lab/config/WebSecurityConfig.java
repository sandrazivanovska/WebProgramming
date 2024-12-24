package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // Оневозможено за тестирање
                .headers((headers) -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Дозволено за H2 конзолата
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/songs", "/artist", "/songDetails", "/songs/selected").permitAll() // Дозволено за сите
                        .requestMatchers("/songs/add", "/songs/edit/**", "/songs/delete/**").hasRole("ADMIN") // Само за ADMIN
                        .anyRequest().authenticated() // Сите други барања бараат најава
                )
                .formLogin((form) -> form
                        .permitAll() // Овозможете пристап до автоматската форма
                        .defaultSuccessUrl("/songs", true) // Пренасочување по успешна најава
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL за одјава
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID") // Избриши сесијата
                        .logoutSuccessUrl("/songs") // Пренасочување по одјава
                )
                .exceptionHandling((ex) -> ex
                        .accessDeniedPage("/access_denied") // Страница за забранет пристап
                );

        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("sandra.zhivanovska")
                .password(passwordEncoder.encode("sz"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("user")
                .password(passwordEncoder.encode("us"))
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(user1, user2);
    }


}
