package com.ys.keepaccount.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 姚森
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TblIncome implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 收入额
     */
    private BigDecimal income;

    /**
     * 收入类型
     */
    private String incomeType;

    private String month;

    private String year;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

    @TableLogic
    private Boolean isDelete;


}
