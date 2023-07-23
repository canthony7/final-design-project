package com.crud;

import com.crud.pojo.Category;
import com.crud.pojo.Goods;
import com.crud.pojo.User;
import com.crud.repository.GoodsRepository;
import com.crud.service.CategoryService;
import com.crud.service.GoodsService;
import com.crud.service.UserService;
import com.crud.utils.UUIDUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SystemTest {

    @Resource
    CategoryService categoryService;

    @Resource
    GoodsService goodsService;

    @Test
    void test01(){
        List<Goods> goods = goodsService.findGoodsByCategoryId(1L);
        for (Goods good : goods) {
            System.out.println(good.getGoodName());
        }
    }

}
