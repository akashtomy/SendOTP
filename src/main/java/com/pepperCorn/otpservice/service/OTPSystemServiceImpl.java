package com.pepperCorn.otpservice.service;/**************************************************
 * Copyright (c) 2020 logictrix.
 * All rights reserved.
 * @Author Akash Thomas
 * Date: 7/28/2020
 * Time: 2:20 PM
 *
 **************************************************/

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pepperCorn.otpservice.TwilioConfiguration;
import com.pepperCorn.otpservice.dto.OTPSystemDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPSystemServiceImpl implements OTPSystemService {

    private static final Integer EXPIRE_MINS = 2;
    @Autowired
    private TwilioConfiguration twilioConfiguration;
    private LoadingCache<String, Integer> otpCache;


    public OTPSystemServiceImpl() {
        super();
        otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    @Override
    public ResponseEntity verifyOtp(OTPSystemDto requestDto, String mobileNumber) {
        Integer otpSystemDto = otpCache.getIfPresent(mobileNumber);
        if (requestDto.getOtp().equals(otpSystemDto)) {
            otpCache.invalidate(mobileNumber);
            return new ResponseEntity<>("OTP verified successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Otp", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> sendOtp(String mobileNumber) {
        OTPSystemDto otpSystemDto = new OTPSystemDto();
        otpSystemDto.setMobileNumber(mobileNumber);
        Random random = new Random();
        otpSystemDto.setOtp(100000 + random.nextInt(900000));
        otpSystemDto.setExpiryTime(System.currentTimeMillis() + 60000);
        otpCache.put(mobileNumber, otpSystemDto.getOtp());
        Message.creator(new PhoneNumber(mobileNumber), new PhoneNumber(twilioConfiguration.getTrialNumber()), otpSystemDto.getOtp().toString()).create();

        return new ResponseEntity<>("OTP sent successfully", HttpStatus.OK);
    }
}