package com.crud.management.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Data
@Entity
@Table(name = "sys_dealer")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "代理商实体类", description = "代理商实体类")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "代理主键")
    private Long id;

    @Column(name = "dealer_name")
    @ApiModelProperty(value = "代理商姓名", required = true)
    private String dealerName;

    @Column(name = "fee_rate")
    @ApiModelProperty(value = "代理费比率")
    private Double feeRate;

    @Column(name = "enable")
    @ApiModelProperty(value = "是否可用")
    private Integer enable;

    @Column(name = "dealer_number")
    @ApiModelProperty(value = "代理编号")
    private String dealerNumber;

}
