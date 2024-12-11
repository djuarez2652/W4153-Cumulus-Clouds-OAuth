package com.cloudcomputing.oauth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Authentication API", description = "Endpoints for OAuth2 authentication")
@Controller
@RequestMapping("/")
public class AuthController {

  @ApiOperation(
          value = "Handle login success",
          notes = "This endpoint processes successful login events, extracts user details and access tokens, and redirects to the welcome page."
  )
  @ApiResponses({
          @ApiResponse(code = 302, message = "Redirects to the /welcome page after successful login"),
          @ApiResponse(code = 401, message = "Unauthorized access")
  })
  @GetMapping("/loginSuccess")
  public String loginSuccess(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                             OidcUser oidcUser) {
    System.out.println("User Info: " + oidcUser.getAttributes());
    System.out.println("Access Token: " + authorizedClient.getAccessToken().getTokenValue());
    return "redirect:/welcome";
  }

  @ApiOperation(
          value = "Welcome page",
          notes = "Displays a welcome message for authenticated users."
  )
  @ApiResponses({
          @ApiResponse(code = 200, message = "Successfully returns the welcome message"),
          @ApiResponse(code = 500, message = "Internal server error")
  })
  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome to the OAuth demo!";
  }
}
