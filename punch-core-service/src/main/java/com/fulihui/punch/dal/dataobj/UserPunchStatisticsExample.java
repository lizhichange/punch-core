package com.fulihui.punch.dal.dataobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserPunchStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    private Integer limit;

    private Integer offset;

    public UserPunchStatisticsExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(Long value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(Long value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(Long value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(Long value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(Long value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<Long> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<Long> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(Long value1, Long value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(Long value1, Long value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andSuccessNumIsNull() {
            addCriterion("success_num is null");
            return (Criteria) this;
        }

        public Criteria andSuccessNumIsNotNull() {
            addCriterion("success_num is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessNumEqualTo(Long value) {
            addCriterion("success_num =", value, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumNotEqualTo(Long value) {
            addCriterion("success_num <>", value, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumGreaterThan(Long value) {
            addCriterion("success_num >", value, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumGreaterThanOrEqualTo(Long value) {
            addCriterion("success_num >=", value, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumLessThan(Long value) {
            addCriterion("success_num <", value, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumLessThanOrEqualTo(Long value) {
            addCriterion("success_num <=", value, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumIn(List<Long> values) {
            addCriterion("success_num in", values, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumNotIn(List<Long> values) {
            addCriterion("success_num not in", values, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumBetween(Long value1, Long value2) {
            addCriterion("success_num between", value1, value2, "successNum");
            return (Criteria) this;
        }

        public Criteria andSuccessNumNotBetween(Long value1, Long value2) {
            addCriterion("success_num not between", value1, value2, "successNum");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andPeriodDateIsNull() {
            addCriterion("period_date is null");
            return (Criteria) this;
        }

        public Criteria andPeriodDateIsNotNull() {
            addCriterion("period_date is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodDateEqualTo(Date value) {
            addCriterionForJDBCDate("period_date =", value, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("period_date <>", value, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateGreaterThan(Date value) {
            addCriterionForJDBCDate("period_date >", value, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("period_date >=", value, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateLessThan(Date value) {
            addCriterionForJDBCDate("period_date <", value, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("period_date <=", value, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateIn(List<Date> values) {
            addCriterionForJDBCDate("period_date in", values, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("period_date not in", values, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("period_date between", value1, value2, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPeriodDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("period_date not between", value1, value2, "periodDate");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountIsNull() {
            addCriterion("pay_one_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountIsNotNull() {
            addCriterion("pay_one_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountEqualTo(Long value) {
            addCriterion("pay_one_amount =", value, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountNotEqualTo(Long value) {
            addCriterion("pay_one_amount <>", value, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountGreaterThan(Long value) {
            addCriterion("pay_one_amount >", value, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_one_amount >=", value, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountLessThan(Long value) {
            addCriterion("pay_one_amount <", value, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountLessThanOrEqualTo(Long value) {
            addCriterion("pay_one_amount <=", value, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountIn(List<Long> values) {
            addCriterion("pay_one_amount in", values, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountNotIn(List<Long> values) {
            addCriterion("pay_one_amount not in", values, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountBetween(Long value1, Long value2) {
            addCriterion("pay_one_amount between", value1, value2, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andPayOneAmountNotBetween(Long value1, Long value2) {
            addCriterion("pay_one_amount not between", value1, value2, "payOneAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIsNull() {
            addCriterion("subsidy_amount is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIsNotNull() {
            addCriterion("subsidy_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountEqualTo(Long value) {
            addCriterion("subsidy_amount =", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotEqualTo(Long value) {
            addCriterion("subsidy_amount <>", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountGreaterThan(Long value) {
            addCriterion("subsidy_amount >", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("subsidy_amount >=", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountLessThan(Long value) {
            addCriterion("subsidy_amount <", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountLessThanOrEqualTo(Long value) {
            addCriterion("subsidy_amount <=", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIn(List<Long> values) {
            addCriterion("subsidy_amount in", values, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotIn(List<Long> values) {
            addCriterion("subsidy_amount not in", values, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountBetween(Long value1, Long value2) {
            addCriterion("subsidy_amount between", value1, value2, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotBetween(Long value1, Long value2) {
            addCriterion("subsidy_amount not between", value1, value2, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(Long value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(Long value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(Long value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(Long value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(Long value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<Long> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<Long> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(Long value1, Long value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(Long value1, Long value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountIsNull() {
            addCriterion("subsidy_setup_amount is null");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountIsNotNull() {
            addCriterion("subsidy_setup_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountEqualTo(Long value) {
            addCriterion("subsidy_setup_amount =", value, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountNotEqualTo(Long value) {
            addCriterion("subsidy_setup_amount <>", value, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountGreaterThan(Long value) {
            addCriterion("subsidy_setup_amount >", value, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("subsidy_setup_amount >=", value, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountLessThan(Long value) {
            addCriterion("subsidy_setup_amount <", value, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountLessThanOrEqualTo(Long value) {
            addCriterion("subsidy_setup_amount <=", value, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountIn(List<Long> values) {
            addCriterion("subsidy_setup_amount in", values, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountNotIn(List<Long> values) {
            addCriterion("subsidy_setup_amount not in", values, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountBetween(Long value1, Long value2) {
            addCriterion("subsidy_setup_amount between", value1, value2, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidySetupAmountNotBetween(Long value1, Long value2) {
            addCriterion("subsidy_setup_amount not between", value1, value2, "subsidySetupAmount");
            return (Criteria) this;
        }

        public Criteria andFailNumIsNull() {
            addCriterion("fail_num is null");
            return (Criteria) this;
        }

        public Criteria andFailNumIsNotNull() {
            addCriterion("fail_num is not null");
            return (Criteria) this;
        }

        public Criteria andFailNumEqualTo(Long value) {
            addCriterion("fail_num =", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumNotEqualTo(Long value) {
            addCriterion("fail_num <>", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumGreaterThan(Long value) {
            addCriterion("fail_num >", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumGreaterThanOrEqualTo(Long value) {
            addCriterion("fail_num >=", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumLessThan(Long value) {
            addCriterion("fail_num <", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumLessThanOrEqualTo(Long value) {
            addCriterion("fail_num <=", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumIn(List<Long> values) {
            addCriterion("fail_num in", values, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumNotIn(List<Long> values) {
            addCriterion("fail_num not in", values, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumBetween(Long value1, Long value2) {
            addCriterion("fail_num between", value1, value2, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumNotBetween(Long value1, Long value2) {
            addCriterion("fail_num not between", value1, value2, "failNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumIsNull() {
            addCriterion("partake_num is null");
            return (Criteria) this;
        }

        public Criteria andPartakeNumIsNotNull() {
            addCriterion("partake_num is not null");
            return (Criteria) this;
        }

        public Criteria andPartakeNumEqualTo(Long value) {
            addCriterion("partake_num =", value, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumNotEqualTo(Long value) {
            addCriterion("partake_num <>", value, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumGreaterThan(Long value) {
            addCriterion("partake_num >", value, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumGreaterThanOrEqualTo(Long value) {
            addCriterion("partake_num >=", value, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumLessThan(Long value) {
            addCriterion("partake_num <", value, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumLessThanOrEqualTo(Long value) {
            addCriterion("partake_num <=", value, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumIn(List<Long> values) {
            addCriterion("partake_num in", values, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumNotIn(List<Long> values) {
            addCriterion("partake_num not in", values, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumBetween(Long value1, Long value2) {
            addCriterion("partake_num between", value1, value2, "partakeNum");
            return (Criteria) this;
        }

        public Criteria andPartakeNumNotBetween(Long value1, Long value2) {
            addCriterion("partake_num not between", value1, value2, "partakeNum");
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    
    
    
}