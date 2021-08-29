package com.telran.oscar.pages.user;

import com.telran.oscar.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHistoryPage extends PageBase {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "id_order_number")
    WebElement orderNumberInput;

    @FindBy(xpath = "//button[.='Filter results']")
    WebElement filterResultsBtn;

    @FindBy(tagName = "h1")
    WebElement titleStr;

    @FindBy(xpath = "//th[.='Order total']/../td")
    WebElement totalOrderTd;

    public String getOrderNumber() {
        return titleStr.getText();
    }

    public String getOrderTotal() {
        return totalOrderTd.getText();
    }

    public OrderHistoryPage filterOrder(String orderNumber){
        type(orderNumberInput, orderNumber);
        filterResultsBtn.click();
        return this;
    }
}
