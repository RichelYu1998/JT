package cn.tedu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    /**
     * url地址: http://localhost:8091/file
     * 参数:    File=fileImage
     * 返回值:   字符串
     *
     * 参数说明：MultipartFile 接口， 主要负责实现文件接收
     * 常识:
     * 		1.必须指定文件上传的路径信息   D:\JT-SOFT\images\文件名称.jpg
     * 		2.将字节信息利用outPutStream进行输出操作
     *
     * 说明:文件上传默认大小1M=1024*1024
     * 具体参见CommonsFileUploadSupport类
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IllegalStateException, IOException{
        //1.定义文件目录信息
        String dirPath ="H:/images";
        File fileDir  = new File(dirPath);
        //2.校验图片目录是否存在.
        if(!fileDir.exists()){//如果文件目录不存在,应该创建目录
            fileDir.mkdirs();//创建多级目录
        }
        //3.获取文件信息. 一般都在上传提交的参数中     a.jpg
        String fileName  = fileImage.getOriginalFilename();
        //4.实现文件上传. 指定文件真实路径
        File file = new File(dirPath + "/" + fileName);
        //5.利用api实现文件输出.
        fileImage.transferTo(file);
        return "恭喜你,文件上传成功!!!";
    }
}
