package dsg.unibamberg.assignment1.configurations;


import dsg.unibamberg.assignment1.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class AppSecurityConfigurations {

    private final UserService userService;


    @Autowired
    public AppSecurityConfigurations(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
        userService.createDefaultUser();
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable().authorizeHttpRequests()
                .requestMatchers("/users").hasAuthority("ADMIN")
                .requestMatchers("/orders").authenticated()
//                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/bottles/add").hasAuthority("ADMIN")
                .requestMatchers("/crates/add").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .and()
//                .logout().logoutSuccessUrl("/")
//                .and().csrf().ignoringRequestMatchers("/h2-console/**") // needed to access the h2-console after introducing security module;
//                .and().headers().frameOptions().sameOrigin() // needed to access the h2-console after introducing security module
                .and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/");


        return http.build();
    }


}
