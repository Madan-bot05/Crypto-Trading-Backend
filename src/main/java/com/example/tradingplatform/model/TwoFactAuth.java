package com.example.tradingplatform.model;


import com.example.tradingplatform.domain.verificationType;
import lombok.Data;

@Data
public class TwoFactAuth {
    private boolean isEnabled=false;
    private verificationType sendTo;
}
