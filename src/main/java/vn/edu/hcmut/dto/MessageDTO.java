package vn.edu.hcmut.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;


@Data
@ToString
@Builder
public class MessageDTO {
    private String message;
    @Min(value = 0)
    private int status;
}
