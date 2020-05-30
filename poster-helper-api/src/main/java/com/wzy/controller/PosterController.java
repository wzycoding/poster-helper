package com.wzy.controller;

import com.wzy.pojo.bo.PosterBo;
import com.wzy.utils.PagedGridResult;
import com.wzy.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 海报
 */
@RestController
@RequestMapping("/api/v1.0/poster")
public class PosterController {

    /**
     * 创建海报
     */
    @PostMapping("create")
    public Result create(@RequestBody PosterBo posterBo) {

        return Result.ok();
    }

    /**
     * 用户创建海报列表
     */
    @GetMapping("list")
    public PagedGridResult list() {

        return new PagedGridResult();
    }

    /**
     * 海报信息详情
     */
    @GetMapping("detail/{posterId}")
    public Result detail(@PathVariable long posterId) {

        return Result.ok();
    }


}
