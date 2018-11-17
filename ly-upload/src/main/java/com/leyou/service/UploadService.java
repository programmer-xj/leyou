package com.leyou.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}22:33
 */
@Service
public class UploadService {
    @Autowired
    FastFileStorageClient storageClient;
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
    private static final List<String> strings = Arrays.asList("image/png", "image/jpeg");

    public String uploadService(MultipartFile file) {
        /**
         * 判断文件的大小  配置文件已校验
         * 判断图片的类型
         * 判断文件的内容
         */

        //文件类型
        try {
            String contentType = file.getContentType();
            if (!strings.contains(contentType)) {
                logger.info("文件类型不匹配:{}", contentType);
                return null;
            }
            //文件内容

            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                logger.info("文件内容出错");
                return null;
            }
            //保存图片

//            file.transferTo(new File("E:/test", file.getOriginalFilename()));
            //获取文件后拽
            String filename = file.getOriginalFilename();
            String s = StringUtils.substringAfterLast(filename, ".");
            //上传文件
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), s, null);
            String url = "http://image.leyou.com/" + storePath.getFullPath();
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
