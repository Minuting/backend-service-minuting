package net.huray.backend.minuting.sample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.huray.backend.minuting.contract.SampleContract;
import net.huray.backend.minuting.dto.SampleCreateReq;
import net.huray.backend.minuting.dto.SampleListReq;
import net.huray.backend.minuting.dto.SampleSimple;
import net.huray.backend.minuting.dto.SampleUpdateReq;
import net.huray.backend.minuting.sample.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/samples")
public class SampleController implements SampleContract {

    private final SampleService sampleService;

    @GetMapping("/test")
    public void testAdder() {
        SampleCreateReq req = new SampleCreateReq(1, "test");
        sampleService.add(req);
    }

    @Override
    public SampleSimple createSample(SampleCreateReq req) {
        return sampleService.add(req);
    }

    @Override
    public List<SampleSimple> listSimple(SampleListReq req) {
        return sampleService.listSimple(req);
    }

    @Override
    public SampleSimple getDetail(Long id) {
        return sampleService.getOne(id);
    }

    @Override
    public SampleSimple updateSample(Long id, SampleUpdateReq sampleUpdateReq) {
        return sampleService.update(id, sampleUpdateReq);
    }

    @Override
    public Long deleteSample(Long id) {
        return sampleService.hardDelete(id);
    }

}
