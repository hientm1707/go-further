//package vn.edu.hcmut.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import vn.edu.hcmut.dto.MessageDTO;
//import vn.edu.hcmut.service.GatewayService;
//
//import javax.validation.Valid;
//import java.util.concurrent.ExecutionException;
//
//@RestController
//@RequestMapping("/messages")
//public class MessageController {
//
//
//    @Autowired
//    private GatewayService gatewayService;
//
//    @PostMapping("/send")
//    public String sendMessage(@RequestBody @Valid MessageDTO dto) throws ExecutionException, InterruptedException {
//        var future = gatewayService.getResponseBack(dto);
//        return future.get();
//    }
//
//}
