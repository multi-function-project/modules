package com.example.goodmorning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodMorningInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
}
