package net.huray.backend.minuting.web.home;

import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.contract.MinutesContract;
import net.huray.backend.minuting.dto.MinutesDto;
import net.huray.backend.minuting.service.MinutesReadService;
import net.huray.backend.minuting.service.MinutesWriteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MinutesController implements MinutesContract {

    private final MinutesWriteService minutesWriteService;
    private final MinutesReadService minutesReadService;

    @Override
    public MinutesDto.MinutesSimple create(MinutesDto.CreateReq req) {
        return minutesWriteService.create(req);
    }

    @Override
    public List<MinutesDto.MinutesSimple> listSimple() {
        return minutesReadService.list();
    }

    @Override
    public MinutesDto.MinutesDetail getDetail(Long id) {
        return minutesReadService.getDetailById(id);
    }

    @Override
    public void update(Long id, MinutesDto.UpdateReq req) {
        minutesWriteService.update(id, req);
    }

    @Override
    public void delete(Long id) {
        minutesWriteService.hardDelete(id);
    }
}
