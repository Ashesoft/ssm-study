package com.longrise.myssm.service;

import java.util.List;

import com.longrise.myssm.vo.DataMsg;

public interface DataMsgService {
    public List<DataMsg> queryAll();

    public DataMsg queryOne(int id);

    public DataMsg addOne(DataMsg dataMsg);

    public DataMsg postOne(DataMsg dataMsg);

    public boolean delOne(int id);
}