package com.yeahmobi.yunit.sample.entity;

public class Shop extends ShopKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.value
     *
     * @mbggenerated
     */
    private String value;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.value
     *
     * @return the value of shop.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.value
     *
     * @param value the value for shop.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}