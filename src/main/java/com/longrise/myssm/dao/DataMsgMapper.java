package com.longrise.myssm.dao;

import java.util.List;

import com.longrise.myssm.vo.DataMsg;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DataMsgMapper {
    @Select("select * from datamsg")
    public List<DataMsg> queryAll();

    @Select("select * from datamsg where id=#{id}")
    public DataMsg queryOne(int id);

    @Insert("insert into datamsg (msg) values (#{msg})")
    @SelectKey(statement="select @@identity", keyProperty="id", before=false, resultType=int.class) // 获取新增后自增列的值并赋值给指定的参数属性
    // @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=int.class) // 获取新增后自增列的值
    public int addOne(DataMsg dataMsg);

    @Update("update datamsg set msg=#{msg} where id=#{id}")
    public int postOne(DataMsg dataMsg);

    @Delete("delete from datamsg where id=#{id}")
    public boolean delOne(int id);
}