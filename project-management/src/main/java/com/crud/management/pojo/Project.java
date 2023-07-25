package com.crud.management.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_project")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "项目实体类", description = "项目实体类")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "项目表主键")
    private Long id;

    @Column(name = "project_name")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @Column(name = "order_time")
    @ApiModelProperty(value = "下单时间, 得到当前时间")
    private LocalDate orderTime;

    @Column(name = "finished_time")
    @ApiModelProperty(value = "完成时间, 改变状态为'已完成'的时候修改")
    private LocalDate finishedTime;

    @Column(name = "frontend")
    @ApiModelProperty(value = "前端技术")
    private String frontend;

    @Column(name = "backend")
    @ApiModelProperty(value = "后端技术")
    private String backend;

    @JoinColumn(name = "dealer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "经销商")
    private Dealer dealer;

    @Column(name = "college")
    @ApiModelProperty(value = "大学")
    private String college;

    @Column(name = "status")
    @ApiModelProperty(value = "订单状态，默认为未完成")
    private String status;

    @Column(name = "front_developer")
    @ApiModelProperty(value = "前端开发者")
    private String frontDeveloper;

    @Column(name = "back_developer")
    @ApiModelProperty(value = "后端开发者")
    private String backDeveloper;

    private Double dealerFee;

}
