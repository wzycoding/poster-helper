package com.wzy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.quaint.poster.core.impl.PosterDefaultImpl;
import com.wzy.holder.RequestHolder;
import com.wzy.mapper.PosterMapper;

import com.wzy.pojo.Poster;
import com.wzy.pojo.Users;
import com.wzy.pojo.bo.PosterBo;
import com.wzy.pojo.bo.SimplePoster;
import com.wzy.pojo.vo.PosterVo;
import com.wzy.service.PosterService;
import com.wzy.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 海报service
 */
@Service
public class PosterServiceImpl implements PosterService {

    @Resource
    private PosterMapper posterMapper;

    @Override
    public void create(PosterBo posterBo) throws IOException, IllegalAccessException {
        Users currentUser = RequestHolder.getCurrentUser();
        Poster newPoster = new Poster();
        BeanUtils.copyProperties(posterBo, newPoster);
        newPoster.setName(posterBo.getItemName());
        // 设置用户信息
        newPoster.setCreateUserId(currentUser.getId());

        posterMapper.insertSelective(newPoster);
        generatePoster(posterBo);
    }

    @Override
    public PagedGridResult list(int page, int pageSize) {
        Users currentUser = RequestHolder.getCurrentUser();
        Example posterExp = new Example(Poster.class);
        Example.Criteria criteria = posterExp.createCriteria();
        criteria.andEqualTo("createUserId", currentUser.getId());
        PageHelper.startPage(page, pageSize);
        List<Poster> posterList = posterMapper.selectByExample(posterExp);
        List<PosterVo> posterVoList = Lists.newArrayList();
        for (Poster poster : posterList) {
            PosterVo vo = new PosterVo();
            BeanUtils.copyProperties(poster, vo);
            posterVoList.add(vo);
        }
        return setterPagedGrid(posterVoList, page);
    }

    @Override
    public PosterVo detail(long posterId) {
        Poster poster = posterMapper.selectByPrimaryKey(posterId);
        PosterVo vo = new PosterVo();
        BeanUtils.copyProperties(poster, vo);
        return vo;
    }

    /**
     * 设置分页参数
     * @param list 源数据list
     * @param page 第几页
     * @return 分页结果
     */
    public PagedGridResult setterPagedGrid(List<?> list, int page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        // 总页数
        grid.setTotal(pageList.getPages());
        // 总记录数
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    public String generatePoster(PosterBo posterBo) throws IllegalAccessException, IOException {
        // 测试注解, 图片请自行添加到resources下 ClassPathResource 需要引入spring
        BufferedImage background = ImageIO.read(new ClassPathResource("image/background.jpg").getInputStream());
        BufferedImage head = ImageIO.read(new ClassPathResource("image/headimg.jpeg").getInputStream());
        SimplePoster poster = SimplePoster.builder()
                .backgroundImage(background)
                .head(head)
                .nickName("金凯达")
                .itemName("西红柿")
                .slogan("命运多舛，痴迷淡然。挥别了青春，数不尽的车站。甘于平凡，却不甘平凡地溃败。")
                .mainImage(head)
                .build();
        PosterDefaultImpl<SimplePoster> impl = new PosterDefaultImpl<>();
        BufferedImage test = impl.annotationDrawPoster(poster).draw(null);
        ImageIO.write(test,"png",new FileOutputStream("annTest.png"));
        return "";
    }

}
