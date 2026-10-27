package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SelenideProductsPage {

    private final SelenideElement title = $("[data-test='title']");

    public SelenideElement title() {
        return title;
    }
}