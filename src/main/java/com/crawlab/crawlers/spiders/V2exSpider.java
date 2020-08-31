package com.crawlab.crawlers.spiders;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.CssSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;

public class V2exSpider implements PageProcessor {
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().css("title", "text"));
        if (page.getUrl().get().equals("https://www.v2ex.com")) {
            processList(page);
        } else if (page.getUrl().get().matches("https://www.v2ex.com/t/.*")) {
            processDetail(page);
        }
    }

    private void processList(Page page) {
        List<Selectable> listItems = page.getHtml().selectList(new CssSelector("#Main > .box > .cell.item")).nodes();
        for (Selectable el: listItems) {
            // item
            HashMap<String, String> item = new HashMap<>();
            item.put("title", el.$("a.topic-link", "text").get());
            item.put("url", "https://www.v2ex.com" + el.$("a.topic-link", "href").get());

            // print item
            System.out.println(item);

            // visit detail page
            Request request = new Request(item.get("url"));
            request.putExtra("item", item);
            page.addTargetRequest(request);
        }
    }

    private void processDetail(Page page) {
        HashMap<String, String> item = (HashMap<String, String>)page.getRequest().getExtra("item");
        item.put("content", page.getHtml().smartContent().get());
        System.out.println(item);
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36");
    }
}
