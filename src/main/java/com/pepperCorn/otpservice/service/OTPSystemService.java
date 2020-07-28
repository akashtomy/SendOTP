package com.pepperCorn.otpservice.service;

import com.pepperCorn.otpservice.dto.OTPSystemDto;
import org.springframework.http.ResponseEntity;

/**************************************************
 * Copyright (c) 2020 logictrix.
 * All rights reserved.
 * @Author Akash Thomas
 * Date: 7/28/2020
 * Time: 11:30 AM
 *
 **************************************************/
public interface OTPSystemService  {

    ResponseEntity<Object> verifyOtp(OTPSystemDto requestDto, String mobileNumber);

    ResponseEntity<Object> sendOtp(String mobileNumber);
}