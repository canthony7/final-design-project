package com.crud.pojo;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "goods")
public class Goods {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goods_name")
    private String goodName;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "summary")
    private String summary;

    @Column(name = "price")
    private double price;

    @Column(name = "content")
    private String content;

    @Column(name = "stock")
    private int stock;

    @Column(name = "is_sale")
    private int isSale;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "image")
    private String image;

}
