package mvc_hiber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service("securityService")
public class SecurityServiceImpl implements task5.spring.service.SecurityService {

 //   private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

  // @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @Override
    public String findLoggedInUsername() {
        //объект содержит данные по юзеру
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            //получаю имя, залогиненного пользователя
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
       //экземпляр юзерДетэйлс
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//ауторизация
        authenticationManager.authenticate(authenticationToken);
//если токен ауторизован, то залогирование
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

 //           logger.debug(String.format("Successfully %s auto logged in", username));
        }
    }
}
