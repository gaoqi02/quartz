package com.gaoqi.rc.dao;

import com.gaoqi.rc.model.RcQuartzExecutionCriteria;
import com.gaoqi.rc.model.RcQuartzExecution;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RcQuartzExecutionMapper {
    int countByExample(RcQuartzExecutionCriteria example);

    int deleteByExample(RcQuartzExecutionCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RcQuartzExecution record);

    int insertSelective(RcQuartzExecution record);

    List<RcQuartzExecution> selectByExampleWithRowbounds(RcQuartzExecutionCriteria example, RowBounds rowBounds);

    List<RcQuartzExecution> selectByExample(RcQuartzExecutionCriteria example);

    RcQuartzExecution selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RcQuartzExecution record, @Param("example") RcQuartzExecutionCriteria example);

    int updateByExample(@Param("record") RcQuartzExecution record, @Param("example") RcQuartzExecutionCriteria example);

    int updateByPrimaryKeySelective(RcQuartzExecution record);

    int updateByPrimaryKey(RcQuartzExecution record);
}