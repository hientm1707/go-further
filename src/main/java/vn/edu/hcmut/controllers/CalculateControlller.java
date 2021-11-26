package vn.edu.hcmut.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmut.dto.CalculatorDTO;
import vn.edu.hcmut.service.GatewayService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/calculate")
public class CalculateControlller {


    @Autowired
    private GatewayService gatewayService;

    @PostMapping("/{op}")
    public String calculate(@RequestBody CalculatorDTO dto, @PathVariable String op) throws ExecutionException, InterruptedException {

        var future = gatewayService.getCalResult(dto, op);
        return future.get();
    }

}
