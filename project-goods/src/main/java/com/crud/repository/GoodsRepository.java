package com.crud.repository;

import com.crud.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findGoodsByCategoryId(Long categoryId);

    @Query(value = "select g from Goods g where g.goodName like %?1% or g.content like %?1%")
    List<Goods> findByGoodNameOrContentLike(String keyword);

}
