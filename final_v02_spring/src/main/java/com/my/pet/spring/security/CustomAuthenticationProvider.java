package com.my.pet.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	SysUserDetailsServiceImpl sysUserDetailsServiceImpl;
//	= new SysUserDetailsServiceImpl();
		
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        if (username == null || username.isBlank()) {
            throw new BadCredentialsException("invalid login details");
        }
        // get user details using Spring security user details service
        UserDetails user = null;
        try {
            user = sysUserDetailsServiceImpl.loadUserByUsername(username);

        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("invalid login details");
        }
        
        if (user == null || !authentication.getCredentials().equals(user.getPassword())) {
        	return null;
        }
        
        return createSuccessfulAuthentication(authentication, user);
	}
	
	private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
		try {
	        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
	        token.setDetails(authentication.getDetails());
	        return token;
		} catch (Exception e) {
			return null;
		}
    }

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
