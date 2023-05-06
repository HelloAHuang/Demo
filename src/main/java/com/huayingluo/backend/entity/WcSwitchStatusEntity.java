package com.huayingluo.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("wc_switch_status")
public class WcSwitchStatusEntity extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer isClosed;
    private BigDecimal electricity;
}