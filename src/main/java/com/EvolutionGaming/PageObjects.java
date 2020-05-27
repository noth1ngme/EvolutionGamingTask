package com.EvolutionGaming;

import org.openqa.selenium.By;

import java.io.IOException;

import static com.EvolutionGaming.Helper.*;

public class PageObjects {
    public static String url;
    public static final By mainCategory = By.cssSelector(".main_category>a");
    public static final By subCategory = By.cssSelector(".category>a");
    public static final By memoCheckbox = By.cssSelector("input[type=checkbox]");
    public static final By addToMemo = By.id("a_fav_sel");
    public static final By showSelected = By.id("sel_cnt_obj");
    public static final By memoCounter = By.id("mnu_fav_id");
    public static final By alertMessage = By.id("alert_msg");

    public static void getUrl() throws IOException {
        url = data("url");
    }

    public static String getSelectedQuantity(By by) {
        String quantity = findElement(by).getText();
        quantity = quantity.replace("(", "");
        quantity = quantity.replace(")", "");
        quantity = quantity.replace(" ", "");
        return quantity;
    }
}
