package vn.edu.iuh.fit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình người dùng trong bộ nhớ
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password")) // Mã hóa mật khẩu
                .roles("USER") // Vai trò USER
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("password")) // Mã hóa mật khẩu
                .roles("ADMIN", "USER") // Vai trò ADMIN
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Cấu hình bảo mật
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                // CSRF Protection
                .csrf(csrf -> csrf.disable())
                // Phân quyền cho các URL
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/donhang/**").hasAnyRole("ADMIN", "USER") // Chỉ ADMIN và USER mới truy cập được
                        .anyRequest().authenticated() // Các yêu cầu khác cần xác thực
                )
                // Cấu hình đăng nhập
                .formLogin(Customizer.withDefaults() )
                // Cấu hình đăng xuất
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để đăng xuất
                        .logoutSuccessUrl("/login?logout=true") // Chuyển hướng đến trang đăng nhập sau khi đăng xuất
                        .permitAll() // Cho phép truy cập URL đăng xuất mà không cần xác thực
                );

        return http.build();
    }
}
