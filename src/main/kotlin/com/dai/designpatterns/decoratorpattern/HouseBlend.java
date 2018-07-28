package com.dai.designpatterns.decoratorpattern;

/**
 * Created by Administrator on 2018/7/28 0028.
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
