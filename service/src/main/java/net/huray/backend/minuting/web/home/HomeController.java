package net.huray.backend.minuting.web.home;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.common.dto.DataResponse;
import net.huray.backend.minuting.service.user.UserService;
import net.huray.backend.minuting.web.home.data.BaseInfoRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/me")
    public DataResponse<BaseInfoRes> getMe() {
        UUID uid = UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470");
        return DataResponse.from(userService.getBaseInfo(uid));
    }
}
