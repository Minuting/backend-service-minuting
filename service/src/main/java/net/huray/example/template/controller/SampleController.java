package net.huray.example.template.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.huray.example.template.contract.SampleContract;
import net.huray.example.template.domain.SampleCreateReq;
import net.huray.example.template.domain.SampleListReq;
import net.huray.example.template.domain.SampleSimple;
import net.huray.example.template.domain.SampleUpdateReq;
import net.huray.example.template.service.SampleService;
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
