package com.zysic.hfct.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("psn")
public class Psn extends Model<Psn> implements Serializable {
    @TableId
    public int id;
    public String name;
    public int age;
}
