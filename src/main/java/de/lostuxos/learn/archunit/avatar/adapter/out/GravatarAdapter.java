package de.lostuxos.learn.archunit.avatar.adapter.out;

import de.lostuxos.learn.archunit.avatar.ports.out.FindAvatarPort;
import de.lostuxos.learn.archunit.avatar.utils.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class GravatarAdapter implements FindAvatarPort {
    private static String GRAVATAR_URL = "https://www.gravatar.com/avatar/";

    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public byte[] findAvatar(String email) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String hash = MD5Util.md5Hex(email);
        String url = GRAVATAR_URL + hash + "?f=y";

        return restTemplate.getForObject(url, byte[].class);
    }
}
