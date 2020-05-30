package com.wzy.service;

import com.wzy.pojo.bo.PosterBo;
import com.wzy.pojo.vo.PosterVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PosterService {
    void create(PosterBo posterBo);

    List<PosterVo> list();

    int countList();
}
