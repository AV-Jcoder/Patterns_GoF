package com.afoninav.patterns.behavoiral.TemplateMethod;

/**
 * Пример паттерна шаблонный метод.
 */
public class Main {
    public static void main(String[] args) {

        WebSiteTemplate emptyPage = new WebSiteTemplate();
        emptyPage.showPage();

        WelcomePage page1 = new WelcomePage();
        page1.showPage();

        System.out.println(" ");

        NewsPage page2 = new NewsPage();
        page2.showPage();

    }
}

/**
 * Класс определяет шаблонный метод showPage().
 */
class WebSiteTemplate {

    public final void showPage() {
        showHeader();
        showPageContent();
        showFooter();
    }

    protected void showHeader() {
        System.out.println(     "#########################################\n"+
                                "#########   This is header      #########\n" +
                                "#########################################"
        );
    }

    protected void showFooter() {
        System.out.println(     "#########################################\n"+
                                "#########   This is footer      #########\n" +
                                "#########################################\n"
        );
    }

    protected void showPageContent() {
        System.out.println(     "#########      Empty Page       #########");
    };
}

/**
 * Классы наследники, которые могут переодпределять
 * поведение по умолчанию родительского класса.
 */
class WelcomePage extends WebSiteTemplate {
    @Override
    protected void showPageContent() {
        System.out.println("######### WelcomePage's content #########");
    }
}

class NewsPage extends WebSiteTemplate {
    @Override
    protected void showPageContent() {
        System.out.println("#########   NewsPage's content  #########");
    }
}

