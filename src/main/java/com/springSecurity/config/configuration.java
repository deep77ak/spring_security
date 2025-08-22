package com.springSecurity.config;


import com.springSecurity.service.customUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class configuration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(e -> e
                        .requestMatchers("/home", "/signup").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//       UserDetails user1= User.withUsername("deepak")
//                .password("{noop}deepak123")
//                .roles("Admin")
//                .build();
//
//       UserDetails user2=User.withUsername("sunny")
//               .password("{noop}sunny123")
//               .roles("user")
//               .build();
//
//        UserDetails user3=User.withUsername("sunny")
//                .password("{noop}sunny1234")
//                .roles("user")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new customUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(userDetailsService());
        authprovider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return authprovider;
    }
}
