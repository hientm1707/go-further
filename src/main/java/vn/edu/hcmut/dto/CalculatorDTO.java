package vn.edu.hcmut.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CalculatorDTO {
    private int a;
    private int b;
    private String op;
}
