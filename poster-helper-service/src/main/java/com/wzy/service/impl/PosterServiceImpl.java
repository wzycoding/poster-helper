package com.wzy.service.impl;

import com.wzy.pojo.bo.PosterBo;
import com.wzy.pojo.vo.PosterVo;
import com.wzy.service.PosterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 海报service
 */
@Service
public class PosterServiceImpl implements PosterService {
    @Override
    public void create(PosterBo posterBo) {

    }

    @Override
    public List<PosterVo> list() {
        return null;
    }

    @Override
    public int countList() {
        return 0;
    }
}
