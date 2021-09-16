package de.lostuxos.learn.archunit.avatar.adapter.in;


import de.lostuxos.learn.archunit.avatar.ports.in.GetAvatarPort;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class AvatarController {

    private  final GetAvatarPort getAvatarPort;
    @GetMapping(path = "/api/v1/avatar/{email}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable("email") String email) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(getAvatarPort.getAvatar(email));
    }

}
