package com.example.exchange;



public class Currencies {

    private String currencies;
    private String price;
    private String changes;
    private String highest;
    private String lowest;
    private String amount;
    private String asset;

    public Currencies(String currencies, String price, String changes, String highest, String lowest) {
        this.currencies = currencies;
        this.price = price;
        this.changes = changes;
        this.highest = highest;
        this.lowest = lowest;
    }
    public Currencies(String currencies, String amount, String price, String asset){
        this.currencies = currencies;
        this.amount = amount;
        this.price = price;
        this. asset = asset;
    }

    public String getAmount() {
        return amount;
    }

    public String getAsset() {
        return asset;
    }

    public String getCurrencies() {
        return currencies;
    }

    public String getPrice() {
        return price;
    }

    public String getChanges() {
        return changes;
    }

    public String getHighest() {
        return highest;
    }

    public String getLowest() {
        return lowest;
    }
}
