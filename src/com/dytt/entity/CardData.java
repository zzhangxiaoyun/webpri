package com.dytt.entity;

/**
 * Created by zhangxiaoyun01 on 2017/5/21.
 */
public class CardData {
    private int cardId;
    private String jsonData;

    public CardData() {
    }

    public CardData(int cardId, String jsonData) {
        this.cardId = cardId;
        this.jsonData = jsonData;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
