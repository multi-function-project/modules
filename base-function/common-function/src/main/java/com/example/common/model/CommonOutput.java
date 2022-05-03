package com.example.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonOutput<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorOutput error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

}
