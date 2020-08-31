package com.crawlab.crawlers.spiders;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.CssSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;

public class BaiduSpider implements PageProcessor {
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().css("title", "text"));
        List<Selectable> listItems = page.getHtml().selectList(new CssSelector(".result.c-container")).nodes();
        for (Selectable el: listItems) {
            // item
            HashMap<String, String> item = new HashMap<>();
            item.put("title", el.$("h3.t > a", "text").get());
            item.put("url", el.$("h3.t > a", "href").get());

            // print item
            System.out.println(item);
        }

        // pagination
        String nextUrlPath = page.getHtml().css("a.n", "href").get();
        if (nextUrlPath != null) {
            String nextUrl = "https://www.baidu.com" + nextUrlPath;
            page.addTargetRequest(nextUrl);
        }
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36");
    }
}
