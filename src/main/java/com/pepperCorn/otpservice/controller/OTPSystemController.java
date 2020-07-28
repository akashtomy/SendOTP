package com.pepperCorn.otpservice.controller;/**************************************************
 * Copyright (c) 2020 logictrix.
 * All rights reserved.
 * @Author Akash Thomas
 * Date: 7/28/2020
 * Time: 11:28 AM
 *
 **************************************************/

import com.pepperCorn.otpservice.dto.OTPSystemDto;
import com.pepperCorn.otpservice.service.OTPSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("otpSystem")
public class OTPSystemController {

    @Autowired
    OTPSystemService otpSystemService;

    @PostMapping("/mobileNumber/{mobileNumber}/otp")
    public ResponseEntity<Object> sendOtp(@PathVariable("mobileNumber") String mobileNumber) {
        return otpSystemService.sendOtp(mobileNumber);
    }

    @PutMapping("/mobileNumber/{mobileNumber}/otp")
    public ResponseEntity<Object> verifyOtp(@PathVariable("mobileNumber") String mobileNumber,
                                    @RequestBody OTPSystemDto requestDto) {
        return otpSystemService.verifyOtp(requestDto, mobileNumber);
    }
}
