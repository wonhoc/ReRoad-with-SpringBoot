package com.example.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchVO {
    private String keyfield;
    private String keyword;
}
