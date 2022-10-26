package net.huray.backend.minuting.contract;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.huray.backend.minuting.dto.SampleListReq;
import net.huray.backend.minuting.dto.SampleUpdateReq;
import net.huray.backend.minuting.dto.SampleCreateReq;
import net.huray.backend.minuting.dto.SampleSimple;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"샘플"})
public interface SampleContract {

    @ApiOperation("샘플 생성")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    SampleSimple createSample(@RequestBody SampleCreateReq req);

    @ApiOperation("샘플 리스트")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    List<SampleSimple> listSimple(SampleListReq req);

    @ApiOperation("상세 조회")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    SampleSimple getDetail(@PathVariable Long id);

    @ApiOperation("샘플 갱신(업데이트)")
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    SampleSimple updateSample(@PathVariable Long id, @RequestBody SampleUpdateReq sampleUpdateReq);

    @ApiOperation("샘플 삭제")
    @DeleteMapping("/{id}")
    Long deleteSample(@PathVariable Long id);

}
