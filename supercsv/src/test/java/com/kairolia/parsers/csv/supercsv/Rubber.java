package com.kairolia.parsers.csv.supercsv;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rubber {
    enum Color {RED, BLACK}

    private String brand;
    private String model;
    private Color color;
    private BigDecimal thickness;
    private BigDecimal price;
    private LocalDate date;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
