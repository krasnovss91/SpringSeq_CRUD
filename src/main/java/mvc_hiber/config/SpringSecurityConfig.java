package mvc_hiber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.converter.RsaKeyConverters;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    private SuccessUserHandler successUserHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();//java.lang.NoClassDefFoundError: org/springframework/security/converter/RsaKeyConverters
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SpringSecurityConfig --- userDetailsService ------ " + userDetailsService);
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SpringSecurityConfig - configure - ROLE_USER + ROLE_ADMIN");
        System.out.println("или USER + ADMIN");
        http
                .authorizeRequests()
                .antMatchers("/read").access("hasAuthority('user') or hasAuthority('admin')")
                .antMatchers("/user/**").access("hasAuthority('user')")
                .antMatchers("/admin/**").access("hasAuthority('admin')")

                .anyRequest().anonymous()
                .and()

                .formLogin()
                .loginPage("/login")
                .successHandler(successUserHandler)//здесь вызывается страница входа, затем летит в проблемный метод
                .failureUrl("/login?error=true") // при неудачной попытке входа бросает сюда
                .and()

                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()



                .csrf()
                .disable()
        ;

    }
}

