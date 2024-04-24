package com.hoangkimgia.cellphones.dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)/*giá trị message nào bằng null thì không hiện*/
public class ApiResponse <T>{
    private  int code=1000;
    private String message;
    private T result;
}
