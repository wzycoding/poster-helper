package com.wzy.controller;

import com.wzy.pojo.Poster;
import com.wzy.pojo.bo.PosterBo;
import com.wzy.pojo.vo.PosterVo;
import com.wzy.service.PosterService;
import com.wzy.utils.PagedGridResult;
import com.wzy.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 海报
 */
@RestController
@RequestMapping("/api/v1.0/poster")
public class PosterController {

    @Resource
    private PosterService posterService;

    /**
     * 创建海报
     */
    @PostMapping("create")
    public Result create(@RequestBody @Valid PosterBo posterBo) throws IOException, IllegalAccessException {
        posterService.create(posterBo);
        return Result.success();
    }

    /**
     * 用户创建海报列表
     */
    @GetMapping("list")
    public Result list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        PagedGridResult gridResult = posterService.list(page, pageSize);
        return Result.success(gridResult);
    }

    /**
     * 海报信息详情
     */
    @GetMapping("detail/{posterId}")
    public Result detail(@PathVariable long posterId) {
        PosterVo posterVo = posterService.detail(posterId);
        return Result.success(posterVo);
    }


}
