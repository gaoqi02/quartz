package com.gaoqi.rc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RcQuartzExecutionCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RcQuartzExecutionCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Integer value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Integer value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Integer value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Integer value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Integer value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Integer> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Integer> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Integer value1, Integer value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Integer value1, Integer value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIsNull() {
            addCriterion("schedule_id is null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIsNotNull() {
            addCriterion("schedule_id is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdEqualTo(Integer value) {
            addCriterion("schedule_id =", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotEqualTo(Integer value) {
            addCriterion("schedule_id <>", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThan(Integer value) {
            addCriterion("schedule_id >", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("schedule_id >=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThan(Integer value) {
            addCriterion("schedule_id <", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThanOrEqualTo(Integer value) {
            addCriterion("schedule_id <=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIn(List<Integer> values) {
            addCriterion("schedule_id in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotIn(List<Integer> values) {
            addCriterion("schedule_id not in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdBetween(Integer value1, Integer value2) {
            addCriterion("schedule_id between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("schedule_id not between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andFireTimeIsNull() {
            addCriterion("fire_time is null");
            return (Criteria) this;
        }

        public Criteria andFireTimeIsNotNull() {
            addCriterion("fire_time is not null");
            return (Criteria) this;
        }

        public Criteria andFireTimeEqualTo(Date value) {
            addCriterion("fire_time =", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeNotEqualTo(Date value) {
            addCriterion("fire_time <>", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeGreaterThan(Date value) {
            addCriterion("fire_time >", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fire_time >=", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeLessThan(Date value) {
            addCriterion("fire_time <", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeLessThanOrEqualTo(Date value) {
            addCriterion("fire_time <=", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeIn(List<Date> values) {
            addCriterion("fire_time in", values, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeNotIn(List<Date> values) {
            addCriterion("fire_time not in", values, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeBetween(Date value1, Date value2) {
            addCriterion("fire_time between", value1, value2, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeNotBetween(Date value1, Date value2) {
            addCriterion("fire_time not between", value1, value2, "fireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeIsNull() {
            addCriterion("pre_fire_time is null");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeIsNotNull() {
            addCriterion("pre_fire_time is not null");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeEqualTo(Date value) {
            addCriterion("pre_fire_time =", value, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeNotEqualTo(Date value) {
            addCriterion("pre_fire_time <>", value, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeGreaterThan(Date value) {
            addCriterion("pre_fire_time >", value, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pre_fire_time >=", value, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeLessThan(Date value) {
            addCriterion("pre_fire_time <", value, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeLessThanOrEqualTo(Date value) {
            addCriterion("pre_fire_time <=", value, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeIn(List<Date> values) {
            addCriterion("pre_fire_time in", values, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeNotIn(List<Date> values) {
            addCriterion("pre_fire_time not in", values, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeBetween(Date value1, Date value2) {
            addCriterion("pre_fire_time between", value1, value2, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andPreFireTimeNotBetween(Date value1, Date value2) {
            addCriterion("pre_fire_time not between", value1, value2, "preFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeIsNull() {
            addCriterion("next_fire_time is null");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeIsNotNull() {
            addCriterion("next_fire_time is not null");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeEqualTo(Date value) {
            addCriterion("next_fire_time =", value, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeNotEqualTo(Date value) {
            addCriterion("next_fire_time <>", value, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeGreaterThan(Date value) {
            addCriterion("next_fire_time >", value, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("next_fire_time >=", value, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeLessThan(Date value) {
            addCriterion("next_fire_time <", value, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeLessThanOrEqualTo(Date value) {
            addCriterion("next_fire_time <=", value, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeIn(List<Date> values) {
            addCriterion("next_fire_time in", values, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeNotIn(List<Date> values) {
            addCriterion("next_fire_time not in", values, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeBetween(Date value1, Date value2) {
            addCriterion("next_fire_time between", value1, value2, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andNextFireTimeNotBetween(Date value1, Date value2) {
            addCriterion("next_fire_time not between", value1, value2, "nextFireTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andExecResultIsNull() {
            addCriterion("exec_result is null");
            return (Criteria) this;
        }

        public Criteria andExecResultIsNotNull() {
            addCriterion("exec_result is not null");
            return (Criteria) this;
        }

        public Criteria andExecResultEqualTo(Byte value) {
            addCriterion("exec_result =", value, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultNotEqualTo(Byte value) {
            addCriterion("exec_result <>", value, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultGreaterThan(Byte value) {
            addCriterion("exec_result >", value, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultGreaterThanOrEqualTo(Byte value) {
            addCriterion("exec_result >=", value, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultLessThan(Byte value) {
            addCriterion("exec_result <", value, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultLessThanOrEqualTo(Byte value) {
            addCriterion("exec_result <=", value, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultIn(List<Byte> values) {
            addCriterion("exec_result in", values, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultNotIn(List<Byte> values) {
            addCriterion("exec_result not in", values, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultBetween(Byte value1, Byte value2) {
            addCriterion("exec_result between", value1, value2, "execResult");
            return (Criteria) this;
        }

        public Criteria andExecResultNotBetween(Byte value1, Byte value2) {
            addCriterion("exec_result not between", value1, value2, "execResult");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}