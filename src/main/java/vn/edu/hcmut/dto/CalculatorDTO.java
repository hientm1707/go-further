package vn.edu.hcmut.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CalculatorDTO {
    private int o1;
    private int o2;
}
