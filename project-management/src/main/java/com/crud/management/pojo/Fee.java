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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sys_fee")
@ApiModel(value = "账单实体类", description = "账单实体类")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "账单表主键")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Column(name = "fee_type")
    @ApiModelProperty(value = "费用类型")
    private String feeType;

    @Column(name = "create_time")
    @ApiModelProperty(value = "生成时间")
    private LocalDate createTime;

    @Column(name = "fee")
    @ApiModelProperty(value = "收入或费用")
    private Double fee;

}
