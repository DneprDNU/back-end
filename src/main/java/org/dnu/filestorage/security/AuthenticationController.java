package org.dnu.filestorage.security;

import org.dnu.filestorage.data.model.User;
import org.dnu.filestorage.data.service.UserService;
import org.dnu.filestorage.security.transfer.TokenTransfer;
import org.dnu.filestorage.security.transfer.UserTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author demyura
 * @since 19.11.14
 */
@Controller
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    /**
     * Retrieves the currently logged in user.
     *
     * @return A transfer containing the username and the roles.
     */
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Object getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
            return new JSONWrapper(new RESTError(190, "Invalid access token"));
        }
        UserDetails userDetails = (UserDetails) principal;

        User user = userService.findByUserName(userDetails.getUsername());

        return new UserTransfer(user.getId(), userDetails.getUsername(), this.createRoleMap(userDetails));
    }

    /**
     * Authenticates a user and creates an authentication token.
     *
     * @param username The name of the user.
     * @param password The password of the user.
     * @return A transfer containing the authentication token.
     */
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Object authenticate(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = this.authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            return new JSONWrapper(new RESTError(401, "Invalid username or password"));
        }

		/*
         * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }


    private List<String> createRoleMap(UserDetails userDetails) {
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.add(authority.toString());
        }

        return roles;
    }

}
