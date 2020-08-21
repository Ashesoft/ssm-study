package com.longrise.myssm.service.impl;

import java.util.List;

import com.longrise.myssm.dao.DataMsgMapper;
import com.longrise.myssm.service.DataMsgService;
import com.longrise.myssm.vo.DataMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataMsgService")
public class DataMsgServiceImpl implements DataMsgService {
    @Autowired
    private DataMsgMapper dataMsgMapper;

    /**
     * @param dataMsgMapper
     */
    // public DataMsgServiceImpl(DataMsgMapper dataMsgMapper) {
    //     this.dataMsgMapper = dataMsgMapper;
    // }

    @Override
    public List<DataMsg> queryAll() {
        return dataMsgMapper.queryAll();
    }

    @Override
    public DataMsg queryOne(int id) {
        return dataMsgMapper.queryOne(id);
    }

    @Override
    public DataMsg addOne(DataMsg dataMsg) {
        int num = dataMsgMapper.addOne(dataMsg);
        if(num == 1){
            return dataMsgMapper.queryOne(dataMsg.getId());
        }
        return null;
    }

    @Override
    public DataMsg postOne(DataMsg dataMsg) {
        int num = dataMsgMapper.postOne(dataMsg);
        if(num == 1){
            return dataMsgMapper.queryOne(dataMsg.getId());
        }
        return null;
    }

    @Override
    public boolean delOne(int id) {
        return dataMsgMapper.delOne(id);
    }

}