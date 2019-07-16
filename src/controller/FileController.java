package controller;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {
    @RequestMapping("download")
    public void download(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        设置响应头  下载："attachment;filename="+fileName  直接打开：inline
        resp.setHeader("Content-Disposition","attachment;filename="+fileName);
//        输出一句字符串
//        resp.getWriter().write();
//        输出二进制文件
        ServletOutputStream os = resp.getOutputStream();
        //获取根目录路径
        String filePath = req.getServletContext().getRealPath("files");
        File file = new File(filePath,fileName);
        //将文件转为二进制数组
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }
    @RequestMapping("upload")
    public String upload(String uname,MultipartFile ufile) throws IOException {
        //获取文件名
        System.out.println(uname);
        String filename = ufile.getOriginalFilename();
        System.out.println(filename);
    //        获取文件名后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        //将 文件流 存到指定路径
        FileUtils.copyInputStreamToFile(ufile.getInputStream(),new File("F:/"+uuid+suffix));
        return "index";
    }
}
