package com.example.tradingplatform.service;

import com.example.tradingplatform.domain.VerificationType;
import com.example.tradingplatform.model.User;
import com.example.tradingplatform.model.VerificationCode;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(User user, VerificationType verificationType);
    VerificationCode getVerificationCodeById(Long id) throws Exception;
    VerificationCode getVerificationCodeByUser(Long userId);
    void deleteVerificationCode(VerificationCode verificationCode);
}