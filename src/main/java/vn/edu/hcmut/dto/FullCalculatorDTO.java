package vn.edu.hcmut.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullCalculatorDTO {
    private int o1;
    private int o2;
    private String op;
}
