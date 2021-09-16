package de.lostuxos.learn.archunit.avatar.domain;

import de.lostuxos.learn.archunit.avatar.ports.in.GetAvatarPort;
import de.lostuxos.learn.archunit.avatar.ports.out.FindAvatarPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AvatarService implements GetAvatarPort {
    private final FindAvatarPort findAvatarPort;

    @Override
    public byte[] getAvatar(String email) {
        return findAvatarPort.findAvatar(email);
    }
}
