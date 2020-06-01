package com.wzy.service;


import com.wzy.pojo.bo.PosterBo;
import com.wzy.pojo.vo.PosterVo;
import com.wzy.utils.PagedGridResult;

import java.io.IOException;

public interface PosterService {
    void create(PosterBo posterBo, String imageUserFaceLocation, String imageServerUrl) throws IOException, IllegalAccessException;

    PagedGridResult list(int page, int pageSize);

    PosterVo detail(long posterId);
}
