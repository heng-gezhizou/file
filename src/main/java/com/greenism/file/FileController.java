package com.greenism.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * ClassName:FileController
 * Package:com.greenism.file
 * Description
 *
 * @data:2019/12/30 21:50
 * @author:jiahongjie
 */

@RestController
public class FileController {

    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) throws Exception{
        String filePath = file.getOriginalFilename();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    @RequestMapping("/download")
    public ResponseEntity download() throws Exception{
        FileSystemResource file = new FileSystemResource("三体智子4k动漫壁纸_彼岸图网.jpg");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment; fileName=123.jpg");
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

}
