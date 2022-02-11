package com.zysic.hfct.optlog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志记录表
 * @author ZHANGYONG797
 * @date 2021-12-16
 */
@Data
@TableName("um_business_log")
public class OptLogDO implements Serializable {
    @TableId
    private String operationLogId;
    private String userId;
    private String userType;
    private String ip;
    private String requestUrl;
    private String requestUrlExplain;
    private Date startDate;
    private String inParam;
    private Date endDate;
    private String outParam;
    private String resultCode;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
