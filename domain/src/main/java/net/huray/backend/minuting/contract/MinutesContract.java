package net.huray.backend.minuting.contract;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.huray.backend.minuting.dto.MinutesDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"회의록 API"})
@RequestMapping(value = "/minutes")
public interface MinutesContract {

    @ApiOperation("회의록 생성")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    MinutesDto.MinutesSimple create(MinutesDto.CreateReq req);

    @ApiOperation("회의록 리스트")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    List<MinutesDto.MinutesSimple> listSimple();

    @ApiOperation("회의록 상세 조회")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    MinutesDto.MinutesDetail getDetail(@PathVariable Long id);

    @ApiOperation("회의록 갱신(업데이트)")
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    void update(@PathVariable Long id, MinutesDto.UpdateReq req);

    @ApiOperation("회의록 삭제")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

}
