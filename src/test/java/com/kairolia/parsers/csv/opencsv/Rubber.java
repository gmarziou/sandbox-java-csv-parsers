package com.kairolia.parsers.csv.opencsv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.util.Date;

public class Rubber {
    @CsvBindByName
    private String brand;

    @CsvBindByName
    private String model;

    @CsvBindByName
    private String color;

    @CsvBindByName(locale = "fr")
    private BigDecimal thickness;

    @CsvBindByName(locale = "fr")
    private BigDecimal price;

    @CsvBindByName
    @CsvDate("yyyy-MM-dd")
    private Date date;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rubber{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", thickness=" + thickness +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
