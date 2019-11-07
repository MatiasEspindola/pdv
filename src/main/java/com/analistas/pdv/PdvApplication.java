package com.analistas.pdv;

import com.analistas.pdv.model.service.IUploadFile_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Autowired
//IUploadFileService uploadFileService;
@SpringBootApplication
public class PdvApplication {

    @Autowired
    private IUploadFile_Service uploadFileService;

    public static void main(String[] args) {
        SpringApplication.run(PdvApplication.class, args);
    }

    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
       // uploadFileService.deleteAll();
        uploadFileService.init();
    }
    
}
