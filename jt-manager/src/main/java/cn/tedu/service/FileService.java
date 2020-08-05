package cn.tedu.service;

import cn.tedu.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ImageVO uploadFile(MultipartFile uploadFile);
}
