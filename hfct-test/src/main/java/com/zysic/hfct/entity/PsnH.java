package com.zysic.hfct.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zysic.hfct.datalog.aspect.IDataHis;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("psn_h")
public class PsnH extends Model<PsnH> implements IDataHis, Serializable {
    @TableId
    public String hisId;
    public String hisType;

    public int id;
    public String name;
    public int age;

}
