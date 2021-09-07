package com.telran.oscar.tests.products;

import com.telran.oscar.pages.home.BrowseStorePage;
import com.telran.oscar.pages.products.ProductItemPage;
import com.telran.oscar.pages.products.ProductListPage;
import com.telran.oscar.tests.TestBase;
import com.telran.oscar.utils.DataProviders;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsPageTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondition(){
        new BrowseStorePage(driver).clickOnAllProductsTab();
    }

    @Test
    public void compareTitleItemNameByProductNumberTest(){
        int productNum = 5;
        String productNameInList = new ProductListPage(driver).getItemNameFromList(productNum);
       // productNameInList = productNameInList.replace("...", "");
       // System.out.println("Books name is " + productNameInList);
        new ProductListPage(driver).clickOnChosenProduct(productNum);
        String productNameInItem = new ProductItemPage(driver).getBookName();
        Assert.assertTrue(productNameInItem.contains(productNameInList));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="testBooksNamesList")
    public void compareTitleItemNameByProductNameTest(String productNameInList){
       new ProductListPage(driver).clickOnProductItem(productNameInList);
       String productNameInItem = new ProductItemPage(driver).getBookName();
       Assert.assertTrue(productNameInItem.contains(productNameInList));
    }

    @Test
    public void paginationTest(){
        ProductListPage productList = new ProductListPage(driver);
        int totalPages = productList.getTotalPages();
        if(totalPages > 0 && productList.getCurrentPage() > 0) {
            while (productList.getCurrentPage() < totalPages) {
                Assert.assertTrue(productList.isNextBtnDisplayed());
                productList.clickOnNextBtn();
            }
            Assert.assertFalse(productList.isNextBtnDisplayed());
            Assert.assertTrue(productList.isPreviousBtnDisplayed());
            while (productList.getCurrentPage() > 1){
                Assert.assertTrue(productList.isPreviousBtnDisplayed());
                productList.clickOnPreviousBtn();
            }
            Assert.assertFalse(productList.isPreviousBtnDisplayed());
            Assert.assertTrue(productList.isNextBtnDisplayed());
        }else{
            System.out.println("Error : totalPage or currentPage not correct displayed");
        }
    }
}
