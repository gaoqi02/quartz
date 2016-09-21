package com.gaoqi.rc.dao;

import com.gaoqi.rc.model.RcQuartzJobCriteria;
import com.gaoqi.rc.model.RcQuartzJob;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RcQuartzJobMapper {
    int countByExample(RcQuartzJobCriteria example);

    int deleteByExample(RcQuartzJobCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RcQuartzJob record);

    int insertSelective(RcQuartzJob record);

    List<RcQuartzJob> selectByExampleWithBLOBsWithRowbounds(RcQuartzJobCriteria example, RowBounds rowBounds);

    List<RcQuartzJob> selectByExampleWithBLOBs(RcQuartzJobCriteria example);

    List<RcQuartzJob> selectByExampleWithRowbounds(RcQuartzJobCriteria example, RowBounds rowBounds);

    List<RcQuartzJob> selectByExample(RcQuartzJobCriteria example);

    RcQuartzJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RcQuartzJob record, @Param("example") RcQuartzJobCriteria example);

    int updateByExampleWithBLOBs(@Param("record") RcQuartzJob record, @Param("example") RcQuartzJobCriteria example);

    int updateByExample(@Param("record") RcQuartzJob record, @Param("example") RcQuartzJobCriteria example);

    int updateByPrimaryKeySelective(RcQuartzJob record);

    int updateByPrimaryKeyWithBLOBs(RcQuartzJob record);

    int updateByPrimaryKey(RcQuartzJob record);
}