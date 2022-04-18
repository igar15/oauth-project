package ru.javaprojects.photoappwebclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.javaprojects.photoappwebclient.model.Album;

import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {
        System.out.println("Principal: " + principal);
        OidcIdToken idToken = principal.getIdToken();
        System.out.println(idToken.getTokenValue());

        String albumsUrl = "http://localhost:8082/albums";

        //!!!!!!!!! This if for Rest template using:!!!!!!!!!!!
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//        OAuth2AuthorizedClient oAuth2AuthorizedClient =
//                oAuth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
//        String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
//        System.out.println("Jwt Access Token: " + jwtAccessToken);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtAccessToken);
//        HttpEntity<List<Album>> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<List<Album>> responseEntity =
//                restTemplate.exchange(albumsUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Album>>() {});
//        List<Album> albums = responseEntity.getBody();

        List<Album> albums = webClient
                .get()
                .uri(albumsUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Album>>() {})
                .block();

        model.addAttribute("albums", albums);
        return "albums";
    }
}
