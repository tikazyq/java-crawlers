package com.crawlab.crawlers.service;

import com.crawlab.crawlers.spiders.BaiduSpider;
import com.crawlab.crawlers.spiders.BingSpider;
import com.crawlab.crawlers.spiders.V2exSpider;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class SpiderService {
    public void run() {
        String spiderName = "v2ex";
        Spider spider = createSpider(spiderName);
        spider.run();
    }

    public Spider createSpider(String spiderName) throws NotImplementedException {
        switch (spiderName) {
            case "baidu":
                return Spider.create(new BaiduSpider()).addUrl("https://www.baidu.com/s?wd=crawlab");
            case "bing":
                return Spider.create(new BingSpider()).addUrl("https://cn.bing.com/search?q=crawlab");
            case "v2ex":
                return Spider.create(new V2exSpider()).addUrl("https://www.v2ex.com");
        }
        throw new NotImplementedException(spiderName + " is not implemented");
    }
}
