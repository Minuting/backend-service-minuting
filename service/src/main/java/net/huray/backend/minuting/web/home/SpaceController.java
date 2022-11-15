package net.huray.backend.minuting.web.home;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.common.dto.DataResponse;
import net.huray.backend.minuting.service.space.SpaceService;
import net.huray.backend.minuting.web.home.data.PublicSpaceRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/space")
@RestController
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/public")
    public DataResponse<List<PublicSpaceRes>> listPublicSpace() {
        UUID uid = UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470");
        return DataResponse.from(spaceService.listPublicSpace(uid));
    }
}
