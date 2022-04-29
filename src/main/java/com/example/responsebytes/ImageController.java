package com.example.responsebytes;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/")
@RestController
public class ImageController {

    @GetMapping
    @RequestMapping("/test1")
    public ResponseEntity<String> test1() {
        String body = "test1";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/test2")
    public byte[] test2() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/Svg_example1.svg");
        return IOUtils.toByteArray(in);
    }

    @GetMapping
    @RequestMapping("/test3")
    public ResponseEntity<byte[]> test3() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/Svg_example1.svg");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "image/svg+xml");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(in.readAllBytes(), httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping
    @RequestMapping("/test4")
    public void test4(HttpServletResponse response) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/Svg_example1.svg");
        response.setContentType("image/svg+xml");
        IOUtils.copy(in, response.getOutputStream());
    }
}
