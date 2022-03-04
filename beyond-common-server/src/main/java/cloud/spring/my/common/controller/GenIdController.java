package cloud.spring.my.common.controller;

import cloud.spring.my.common.service.IGenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: GenIdController
 * @Author: uray
 * @Date: 2022-02-28 21:34
 **/
@RestController("/genId")
public class GenIdController {

    @Autowired
    IGenIdService genIdService;

    @GetMapping("/sfid")
    public Long genSFId() {
        return genIdService.genSFId();
    }

    public static void main(String[] args) {
        int i = 15;
        int j = 8;
        System.out.println(i/2);
        System.out.println((i+j)/2);
    }
}
