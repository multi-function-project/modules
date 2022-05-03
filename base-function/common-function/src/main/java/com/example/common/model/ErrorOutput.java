package com.example.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorOutput  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
}
