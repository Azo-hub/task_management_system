package com.task_management_system.custom_login_handler;

import com.task_management_system.domain.User;
import com.task_management_system.security.UserPrincipal;
import com.task_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessHandler {
    @Autowired
    private UserService userService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {

        Object principal = event.getAuthentication().getPrincipal();

        if(principal instanceof UserPrincipal) {

            UserPrincipal userPrincipal = (UserPrincipal) event.getAuthentication().getPrincipal();
            User user = userService.findByUsername(userPrincipal.getUsername());
            if (user.getFailedAttempt() > 0) {
                userService.resetFailedAttempts(user.getUsername());
            }
        }
    }

}
