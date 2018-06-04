package com.fulihui.punch.dal.dataobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PunchJackpotAmountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public PunchJackpotAmountExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andCumulativeAmountIsNull() {
            addCriterion("cumulative_amount is null");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountIsNotNull() {
            addCriterion("cumulative_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountEqualTo(Integer value) {
            addCriterion("cumulative_amount =", value, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountNotEqualTo(Integer value) {
            addCriterion("cumulative_amount <>", value, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountGreaterThan(Integer value) {
            addCriterion("cumulative_amount >", value, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("cumulative_amount >=", value, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountLessThan(Integer value) {
            addCriterion("cumulative_amount <", value, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("cumulative_amount <=", value, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountIn(List<Integer> values) {
            addCriterion("cumulative_amount in", values, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountNotIn(List<Integer> values) {
            addCriterion("cumulative_amount not in", values, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountBetween(Integer value1, Integer value2) {
            addCriterion("cumulative_amount between", value1, value2, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("cumulative_amount not between", value1, value2, "cumulativeAmount");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberIsNull() {
            addCriterion("cumulative_number is null");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberIsNotNull() {
            addCriterion("cumulative_number is not null");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberEqualTo(Integer value) {
            addCriterion("cumulative_number =", value, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberNotEqualTo(Integer value) {
            addCriterion("cumulative_number <>", value, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberGreaterThan(Integer value) {
            addCriterion("cumulative_number >", value, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("cumulative_number >=", value, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberLessThan(Integer value) {
            addCriterion("cumulative_number <", value, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberLessThanOrEqualTo(Integer value) {
            addCriterion("cumulative_number <=", value, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberIn(List<Integer> values) {
            addCriterion("cumulative_number in", values, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberNotIn(List<Integer> values) {
            addCriterion("cumulative_number not in", values, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberBetween(Integer value1, Integer value2) {
            addCriterion("cumulative_number between", value1, value2, "cumulativeNumber");
            return (Criteria) this;
        }

        public Criteria andCumulativeNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("cumulative_number not between", value1, value2, "cumulativeNumber");
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

        public Criteria andSubsidyAmountIsNull() {
            addCriterion("subsidy_amount is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIsNotNull() {
            addCriterion("subsidy_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountEqualTo(Integer value) {
            addCriterion("subsidy_amount =", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotEqualTo(Integer value) {
            addCriterion("subsidy_amount <>", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountGreaterThan(Integer value) {
            addCriterion("subsidy_amount >", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("subsidy_amount >=", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountLessThan(Integer value) {
            addCriterion("subsidy_amount <", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("subsidy_amount <=", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIn(List<Integer> values) {
            addCriterion("subsidy_amount in", values, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotIn(List<Integer> values) {
            addCriterion("subsidy_amount not in", values, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountBetween(Integer value1, Integer value2) {
            addCriterion("subsidy_amount between", value1, value2, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("subsidy_amount not between", value1, value2, "subsidyAmount");
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