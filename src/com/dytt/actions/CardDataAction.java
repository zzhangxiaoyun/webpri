package com.dytt.actions;

import com.dytt.BaseActionSupport;
import com.dytt.MyDB;

public class CardDataAction extends BaseActionSupport {
	private int cardId;
	private String jsonData;
    public String addCardData(){
        if (jsonData == null || "".equals(jsonData)) {
            resError(-1, "json data is null");
        }

    	return resSucceed(MyDB.addCardData(cardId, jsonData), null);
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
