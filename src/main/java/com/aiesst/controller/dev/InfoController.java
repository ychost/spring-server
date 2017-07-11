package com.aiesst.controller.dev;

import com.aiesst.config.server.RouteConfig;
import com.aiesst.controller.RestRequestMapping;
import com.aiesst.model.request.ReqInfo;
import com.aiesst.model.response.RestModel;
import com.aiesst.service.dev.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ychost<ychost@outlook.com>
 * @version v1.0
 * @date 17-7-11
 */
@RestController
@Api(value = "InfoController", description = "测试接口")
public class InfoController {
    private final InfoService infoService;

    InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @ApiOperation(value = "测试保存信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "info", value = "待保存的信息", required = true, dataType = "ReqInfo")
    @RestRequestMapping(value = RouteConfig.SaveInfo, method = RequestMethod.POST)
    public RestModel saveInfo(@RequestBody @Valid ReqInfo info) {
        return this.infoService.saveInfo(info);
    }

    @ApiOperation(value = "测试模糊查询信息，Sql的like", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "infoLike", value = "查询的like匹配", required = true, dataType = "String")
    @RestRequestMapping(value = RouteConfig.QueryInfo, method = RequestMethod.POST)
    public RestModel queryInfo(@RequestBody String infoLike) {
        return this.infoService.queryInfo(infoLike);

    }
}
