package com.ap_express_server.controllers.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ap_express_server.common_utitlity.CommonNumber;
import com.ap_express_server.common_utitlity.GeneralConstants;
import com.ap_express_server.constant.Notification.ClientNotification;
import com.ap_express_server.constant.Notification.HttpUrlConstants;
import com.ap_express_server.exception.token.TokenRefreshException;
import com.ap_express_server.models.token.RefreshToken;
import com.ap_express_server.payload.request.LoginRequest;
import com.ap_express_server.payload.request.SignupRequest;
import com.ap_express_server.payload.response.MessageResponse;
import com.ap_express_server.payload.response.UserInfoResponse;
import com.ap_express_server.repository.role.RoleRepository;
import com.ap_express_server.repository.user.UserRepository;
import com.ap_express_server.security.jwt.JwtUtils;
import com.ap_express_server.services_impl.user.UserDetailsImpl;
import com.ap_express_server.service.jwt.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap_express_server.models.role.ERole;
import com.ap_express_server.models.role.Role;
import com.ap_express_server.models.user.User;

@CrossOrigin(origins = HttpUrlConstants.FRONTEND_BASE_URL, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping(HttpUrlConstants.API_AUTH_BASE_URL)
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping(HttpUrlConstants.SIGN_IN_URL)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
            .body(new UserInfoResponse(userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
  }

  /**
   * Method use for signup new user
   * @param signUpRequest to SignupRequest
   * @return ResponseEntity
   */
  @PostMapping(HttpUrlConstants.SIGN_UP_URL)
  public ResponseEntity<Object> registerUser(@RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>(new MessageResponse(ClientNotification.USERNAME_ALREADY_TAKEN), HttpStatus.CONFLICT);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>(new MessageResponse(ClientNotification.EMAIL_ALREADY_TAKEN), HttpStatus.CONFLICT);
    }

    User user = new User(signUpRequest.getUsername(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      roles.add(new Role(CommonNumber.ROLE_USER, ERole.ROLE_USER));
      user.setRoles(roles);
      }
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse(ClientNotification.USER_REGISTER_SUCCESSFULLY));
  }

  @PostMapping(HttpUrlConstants.SIGN_OUT_URL)
  public ResponseEntity<?> logoutUser() {
    Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!Objects.equals(principle.toString(), GeneralConstants.ANONYMOUS_USER_ROLE)) {
      Integer userId = ((UserDetailsImpl) principle).getId();
      refreshTokenService.deleteByUserId(userId);
    }

    ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
    ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

    return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
            .body(new MessageResponse(ClientNotification.SIGNED_OUT_MESSAGE));
  }

  @PostMapping(HttpUrlConstants.REFRESH_TOKEN_URL)
  public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
    String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

    if ((refreshToken != null) && (!refreshToken.isEmpty())) {
      return refreshTokenService.findByToken(refreshToken)
              .map(refreshTokenService::verifyExpiration)
              .map(RefreshToken::getUser)
              .map(user -> {
                ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(UserDetailsImpl.build(user));

                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                        .body(new MessageResponse(ClientNotification.TOKEN_REFRESH_SUCCESS));
              })
              .orElseThrow(() -> new TokenRefreshException(refreshToken,
                      ClientNotification.REFRESH_TOKEN_NOT_IN_DATABASE));
    }

    return ResponseEntity.badRequest().body(new MessageResponse(ClientNotification.EMPTY_REFRESH_TOKEN));
  }
}
