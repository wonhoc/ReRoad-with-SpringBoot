package com.example.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidErrVO {
    //유효성 검사에 위반된 항목 이름
    private String field;
    //@valid로 설정한 메시지
    private String Message;
    //유효성 검사에 위반된 사용자의 입력 내용
    private String RejectedValue;
}
