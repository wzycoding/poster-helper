package com.wzy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.quaint.poster.core.impl.PosterDefaultImpl;
import com.wzy.holder.RequestHolder;
import com.wzy.mapper.PosterMapper;

import com.wzy.mapper.PosterMapperCustom;
import com.wzy.pojo.Poster;
import com.wzy.pojo.Users;
import com.wzy.pojo.bo.PosterBo;
import com.wzy.pojo.bo.SimplePoster;
import com.wzy.pojo.vo.PosterVo;
import com.wzy.service.PosterService;
import com.wzy.utils.PagedGridResult;
import com.wzy.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 海报service
 */
@Service
@Slf4j
public class PosterServiceImpl implements PosterService {

    @Resource
    private PosterMapper posterMapper;

    @Resource
    private PosterMapperCustom posterMapperCustom;

    @Override
    public Poster create(PosterBo posterBo, String imageUserFaceLocation, String imageServerUrl) throws IOException, IllegalAccessException {
        Users currentUser = RequestHolder.getCurrentUser();
        Poster newPoster = new Poster();
        BeanUtils.copyProperties(posterBo, newPoster);
        newPoster.setName(posterBo.getItemName());
        // 设置用户信息
        newPoster.setCreateUserId(currentUser.getId());

        posterMapperCustom.insertSelective(newPoster);
        String posterUrl = generatePoster(newPoster, currentUser, imageUserFaceLocation, imageServerUrl);
        Poster posterUpdate = new Poster();
        posterUpdate.setId(newPoster.getId());
        posterUpdate.setPosterImgUrl(posterUrl);
        newPoster.setId(newPoster.getId());
        newPoster.setPosterImgUrl(posterUrl);
        posterMapper.updateByPrimaryKeySelective(posterUpdate);
        return newPoster;
    }

    @Override
    public PagedGridResult list(int page, int pageSize) {
        Users currentUser = RequestHolder.getCurrentUser();
        Example posterExp = new Example(Poster.class);
        Example.Criteria criteria = posterExp.createCriteria();
        criteria.andEqualTo("createUserId", currentUser.getId());
        // todo: 将来分页
//        PageHelper.startPage(page, pageSize);
        List<Poster> posterList = posterMapper.selectByExample(posterExp);
        List<PosterVo> posterVoList = Lists.newArrayList();
        for (Poster poster : posterList) {
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            PosterVo vo = new PosterVo();
            BeanUtils.copyProperties(poster, vo);
            vo.setCreateTime(sdfDateTime.format(poster.getCreateTime()));
            vo.setDiscountDate(sdfDate.format(poster.getDiscountDate()));
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
//        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        // 总页数
//        grid.setTotal(pageList.getPages());
        // 总记录数
//        grid.setRecords(pageList.getTotal());
        return grid;
    }

    public String generatePoster(Poster newPoster, Users currentUser,
                                 String imageUserFaceLocation, String imageServerUrl) throws IllegalAccessException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        // 测试注解, 图片请自行添加到resources下 ClassPathResource 需要引入spring
        BufferedImage background = ImageIO.read(new ClassPathResource("image/background.png").getInputStream());
        BufferedImage head = ImageIO.read(getInputStreamByUrl(newPoster.getImgUrl()));
        BufferedImage qrCode = ImageIO.read(new ClassPathResource("image/QR.jpg").getInputStream());
        SimplePoster poster = SimplePoster.builder()
                .backgroundImage(background)
                .description(newPoster.getName() + " " + newPoster.getDescription())
                .priceNormal("原价 ￥" + newPoster.getPriceNormal())
                .priceDiscount("会员福利 ￥" + newPoster.getPriceDiscount())
                .discountDate(sdf.format( newPoster.getDiscountDate()))
                .qrCode(qrCode)
                .mainImage(head)
                .slogan("每月6、16、26特价日")
                .build();
        PosterDefaultImpl<SimplePoster> impl = new PosterDefaultImpl<>();
        BufferedImage test = impl.annotationDrawPoster(poster).draw(null);
        String fileName = UUIDUtil.uuid();
        String finalPath = imageUserFaceLocation + File.separator + currentUser.getId() + File.separator + fileName + ".png";

        File outFile = new File(finalPath);
        if (outFile.getParentFile() != null) {
            // 创建文件夹
            boolean mkdirs = outFile.getParentFile().mkdirs();
            System.out.println(mkdirs);
        }
        ImageIO.write(test,"png",new FileOutputStream(finalPath));

        String postUrl = imageServerUrl + File.separator + currentUser.getId() + File.separator + fileName + ".png";
        return postUrl;
    }

    /**
     * 获取url的输入流
     * @param strUrl url
     * @return 输入流
     */
    public InputStream getInputStreamByUrl(String strUrl) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(), output);
            return new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            log.error("getInputStreamByUrl 异常,exception is {}", e);
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

}
