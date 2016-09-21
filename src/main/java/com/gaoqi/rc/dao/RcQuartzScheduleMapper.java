package com.gaoqi.rc.dao;

import com.gaoqi.rc.model.RcQuartzSchedule;
import com.gaoqi.rc.model.RcQuartzScheduleCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RcQuartzScheduleMapper {
    int countByExample(RcQuartzScheduleCriteria example);

    int deleteByExample(RcQuartzScheduleCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RcQuartzSchedule record);

    int insertSelective(RcQuartzSchedule record);

    List<RcQuartzSchedule> selectByExampleWithRowbounds(RcQuartzScheduleCriteria example, RowBounds rowBounds);

    List<RcQuartzSchedule> selectByExample(RcQuartzScheduleCriteria example);

    RcQuartzSchedule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RcQuartzSchedule record, @Param("example") RcQuartzScheduleCriteria example);

    int updateByExample(@Param("record") RcQuartzSchedule record, @Param("example") RcQuartzScheduleCriteria example);

    int updateByPrimaryKeySelective(RcQuartzSchedule record);

    int updateByPrimaryKey(RcQuartzSchedule record);
}