package com.example.tradingplatform.model;


import com.example.tradingplatform.domain.VerificationType;
import lombok.Data;

@Data
public class TwoFactAuth {
    private boolean isEnabled=false;
    private VerificationType sendTo;
}
