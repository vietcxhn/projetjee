package mybootapp.web.security;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import mybootapp.repo.PersonRepository;
import mybootapp.model.Person;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    PersonRepository personRepo;

    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        Person p = new Person("c17027775", "Xuan Viet", "CONG", "vietcxhn1@gmail.com", "github.com/vietcxhn", "1999-08-27", encoder.encode("aaa"), null, Set.of("ADMIN", "USER"));
        personRepo.save(p);
        System.out.println("--- INIT SPRING SECURITY");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // -- ne pas activer la protection CSRF
                // .csrf().disable()
                // -- URL sans authentification
                .authorizeRequests()//    
                .antMatchers("/", "/group/**", "/person/**", "/webjars/**", //
                        "/home", "/login", "/logout", //
                        "/calculator/**")//
                .permitAll()//
                .antMatchers("/simple-user/**")//
                .hasAnyAuthority("ADMIN") /// ****** NOUVEAU
                // -- Les autres URL nécessitent une authentification
                .anyRequest().authenticated()
                // -- Nous autorisons un formulaire de login
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                // -- Nous autorisons un formulaire de logout
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
        
    }
    
    @Autowired
    UserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}