package com.cloudcomputing.oauth;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  @GetMapping("/loginSuccess")
  public String loginSuccess(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                             OidcUser oidcUser) {
    System.out.println("User Info: " + oidcUser.getAttributes());
    System.out.println("Access Token: " + authorizedClient.getAccessToken().getTokenValue());
    return "redirect:/welcome";
  }

  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome to the OAuth demo!";
  }
}