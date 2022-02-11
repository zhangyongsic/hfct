package com.zysic.hfct.datalog.aspect;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zysic.hfct.core.helper.BeanHelper;
import lombok.Data;

import java.util.UUID;


@Data
public abstract class DataLogBiz<T extends Model<T>,H extends Model<H>>{
    protected T root;
    protected H hist;
    protected String type;
    public void saveHist() {
        try {
            root = root.selectById();
            BeanHelper.copyProperties(root,hist);
            if (hist instanceof IDataHis){
                ((IDataHis) hist).setHisId(UUID.randomUUID().toString());
                ((IDataHis) hist).setHisType(getType());
                hist.insert();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
