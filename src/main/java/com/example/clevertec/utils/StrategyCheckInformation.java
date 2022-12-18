package com.example.clevertec.utils;

import com.example.clevertec.data.Check;

public class StrategyCheckInformation {
    SendCheckInformation sendCheckInformation;
    Check check;

    public StrategyCheckInformation(Check check) {
        this.check = check;
    }

    public void setSendCheckInformation(SendCheckInformation sendCheckInformation) {
        this.sendCheckInformation = sendCheckInformation;
    }
    public void executeSendCheckInformation(){
        sendCheckInformation.writeInformation(check);
    }
}
