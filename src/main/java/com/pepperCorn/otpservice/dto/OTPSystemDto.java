package com.pepperCorn.otpservice.dto;

import lombok.Data;

/**************************************************
 * Copyright (c) 2020 logictrix.
 * All rights reserved.
 * @Author Akash Thomas
 * Date: 7/28/2020
 * Time: 11:27 AM
 *
 **************************************************/
@Data
public class OTPSystemDto {

    private String mobileNumber;
    private Integer otp;
    private long expiryTime;
}
