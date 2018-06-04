package com.fulihui.punch.dal.dataobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserPunchRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UserPunchRecordExample() {
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

        public Criteria andPunchOrderIdIsNull() {
            addCriterion("punch_order_id is null");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdIsNotNull() {
            addCriterion("punch_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdEqualTo(String value) {
            addCriterion("punch_order_id =", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdNotEqualTo(String value) {
            addCriterion("punch_order_id <>", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdGreaterThan(String value) {
            addCriterion("punch_order_id >", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("punch_order_id >=", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdLessThan(String value) {
            addCriterion("punch_order_id <", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdLessThanOrEqualTo(String value) {
            addCriterion("punch_order_id <=", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdLike(String value) {
            addCriterion("punch_order_id like", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdNotLike(String value) {
            addCriterion("punch_order_id not like", value, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdIn(List<String> values) {
            addCriterion("punch_order_id in", values, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdNotIn(List<String> values) {
            addCriterion("punch_order_id not in", values, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdBetween(String value1, String value2) {
            addCriterion("punch_order_id between", value1, value2, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andPunchOrderIdNotBetween(String value1, String value2) {
            addCriterion("punch_order_id not between", value1, value2, "punchOrderId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIsNull() {
            addCriterion("out_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIsNotNull() {
            addCriterion("out_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoEqualTo(String value) {
            addCriterion("out_trade_no =", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotEqualTo(String value) {
            addCriterion("out_trade_no <>", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoGreaterThan(String value) {
            addCriterion("out_trade_no >", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_trade_no >=", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLessThan(String value) {
            addCriterion("out_trade_no <", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLessThanOrEqualTo(String value) {
            addCriterion("out_trade_no <=", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLike(String value) {
            addCriterion("out_trade_no like", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotLike(String value) {
            addCriterion("out_trade_no not like", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIn(List<String> values) {
            addCriterion("out_trade_no in", values, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotIn(List<String> values) {
            addCriterion("out_trade_no not in", values, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoBetween(String value1, String value2) {
            addCriterion("out_trade_no between", value1, value2, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotBetween(String value1, String value2) {
            addCriterion("out_trade_no not between", value1, value2, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andPunchDateIsNull() {
            addCriterion("punch_date is null");
            return (Criteria) this;
        }

        public Criteria andPunchDateIsNotNull() {
            addCriterion("punch_date is not null");
            return (Criteria) this;
        }

        public Criteria andPunchDateEqualTo(Date value) {
            addCriterion("punch_date =", value, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateNotEqualTo(Date value) {
            addCriterion("punch_date <>", value, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateGreaterThan(Date value) {
            addCriterion("punch_date >", value, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateGreaterThanOrEqualTo(Date value) {
            addCriterion("punch_date >=", value, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateLessThan(Date value) {
            addCriterion("punch_date <", value, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateLessThanOrEqualTo(Date value) {
            addCriterion("punch_date <=", value, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateIn(List<Date> values) {
            addCriterion("punch_date in", values, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateNotIn(List<Date> values) {
            addCriterion("punch_date not in", values, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateBetween(Date value1, Date value2) {
            addCriterion("punch_date between", value1, value2, "punchDate");
            return (Criteria) this;
        }

        public Criteria andPunchDateNotBetween(Date value1, Date value2) {
            addCriterion("punch_date not between", value1, value2, "punchDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNull() {
            addCriterion("pay_date is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("pay_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Date value) {
            addCriterion("pay_date =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Date value) {
            addCriterion("pay_date <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Date value) {
            addCriterion("pay_date >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Date value) {
            addCriterion("pay_date <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Date value) {
            addCriterion("pay_date <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Date> values) {
            addCriterion("pay_date in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Date> values) {
            addCriterion("pay_date not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Date value1, Date value2) {
            addCriterion("pay_date between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Date value1, Date value2) {
            addCriterion("pay_date not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelIsNull() {
            addCriterion("open_id_channel is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelIsNotNull() {
            addCriterion("open_id_channel is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelEqualTo(String value) {
            addCriterion("open_id_channel =", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelNotEqualTo(String value) {
            addCriterion("open_id_channel <>", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelGreaterThan(String value) {
            addCriterion("open_id_channel >", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelGreaterThanOrEqualTo(String value) {
            addCriterion("open_id_channel >=", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelLessThan(String value) {
            addCriterion("open_id_channel <", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelLessThanOrEqualTo(String value) {
            addCriterion("open_id_channel <=", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelLike(String value) {
            addCriterion("open_id_channel like", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelNotLike(String value) {
            addCriterion("open_id_channel not like", value, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelIn(List<String> values) {
            addCriterion("open_id_channel in", values, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelNotIn(List<String> values) {
            addCriterion("open_id_channel not in", values, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelBetween(String value1, String value2) {
            addCriterion("open_id_channel between", value1, value2, "openIdChannel");
            return (Criteria) this;
        }

        public Criteria andOpenIdChannelNotBetween(String value1, String value2) {
            addCriterion("open_id_channel not between", value1, value2, "openIdChannel");
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

        public Criteria andPayDecsIsNull() {
            addCriterion("pay_decs is null");
            return (Criteria) this;
        }

        public Criteria andPayDecsIsNotNull() {
            addCriterion("pay_decs is not null");
            return (Criteria) this;
        }

        public Criteria andPayDecsEqualTo(String value) {
            addCriterion("pay_decs =", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsNotEqualTo(String value) {
            addCriterion("pay_decs <>", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsGreaterThan(String value) {
            addCriterion("pay_decs >", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsGreaterThanOrEqualTo(String value) {
            addCriterion("pay_decs >=", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsLessThan(String value) {
            addCriterion("pay_decs <", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsLessThanOrEqualTo(String value) {
            addCriterion("pay_decs <=", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsLike(String value) {
            addCriterion("pay_decs like", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsNotLike(String value) {
            addCriterion("pay_decs not like", value, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsIn(List<String> values) {
            addCriterion("pay_decs in", values, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsNotIn(List<String> values) {
            addCriterion("pay_decs not in", values, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsBetween(String value1, String value2) {
            addCriterion("pay_decs between", value1, value2, "payDecs");
            return (Criteria) this;
        }

        public Criteria andPayDecsNotBetween(String value1, String value2) {
            addCriterion("pay_decs not between", value1, value2, "payDecs");
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

        public Criteria andPayAmountEqualTo(Integer value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(Integer value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(Integer value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(Integer value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(Integer value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<Integer> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<Integer> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(Integer value1, Integer value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPushStartDateIsNull() {
            addCriterion("push_start_date is null");
            return (Criteria) this;
        }

        public Criteria andPushStartDateIsNotNull() {
            addCriterion("push_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andPushStartDateEqualTo(Date value) {
            addCriterion("push_start_date =", value, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateNotEqualTo(Date value) {
            addCriterion("push_start_date <>", value, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateGreaterThan(Date value) {
            addCriterion("push_start_date >", value, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("push_start_date >=", value, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateLessThan(Date value) {
            addCriterion("push_start_date <", value, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateLessThanOrEqualTo(Date value) {
            addCriterion("push_start_date <=", value, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateIn(List<Date> values) {
            addCriterion("push_start_date in", values, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateNotIn(List<Date> values) {
            addCriterion("push_start_date not in", values, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateBetween(Date value1, Date value2) {
            addCriterion("push_start_date between", value1, value2, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushStartDateNotBetween(Date value1, Date value2) {
            addCriterion("push_start_date not between", value1, value2, "pushStartDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateIsNull() {
            addCriterion("push_end_date is null");
            return (Criteria) this;
        }

        public Criteria andPushEndDateIsNotNull() {
            addCriterion("push_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andPushEndDateEqualTo(Date value) {
            addCriterion("push_end_date =", value, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateNotEqualTo(Date value) {
            addCriterion("push_end_date <>", value, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateGreaterThan(Date value) {
            addCriterion("push_end_date >", value, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("push_end_date >=", value, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateLessThan(Date value) {
            addCriterion("push_end_date <", value, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateLessThanOrEqualTo(Date value) {
            addCriterion("push_end_date <=", value, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateIn(List<Date> values) {
            addCriterion("push_end_date in", values, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateNotIn(List<Date> values) {
            addCriterion("push_end_date not in", values, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateBetween(Date value1, Date value2) {
            addCriterion("push_end_date between", value1, value2, "pushEndDate");
            return (Criteria) this;
        }

        public Criteria andPushEndDateNotBetween(Date value1, Date value2) {
            addCriterion("push_end_date not between", value1, value2, "pushEndDate");
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

        public Criteria andPayExtDateIsNull() {
            addCriterion("pay_ext_date is null");
            return (Criteria) this;
        }

        public Criteria andPayExtDateIsNotNull() {
            addCriterion("pay_ext_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayExtDateEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ext_date =", value, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ext_date <>", value, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateGreaterThan(Date value) {
            addCriterionForJDBCDate("pay_ext_date >", value, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ext_date >=", value, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateLessThan(Date value) {
            addCriterionForJDBCDate("pay_ext_date <", value, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ext_date <=", value, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateIn(List<Date> values) {
            addCriterionForJDBCDate("pay_ext_date in", values, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pay_ext_date not in", values, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pay_ext_date between", value1, value2, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andPayExtDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pay_ext_date not between", value1, value2, "payExtDate");
            return (Criteria) this;
        }

        public Criteria andRebateAmountIsNull() {
            addCriterion("rebate_amount is null");
            return (Criteria) this;
        }

        public Criteria andRebateAmountIsNotNull() {
            addCriterion("rebate_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRebateAmountEqualTo(Integer value) {
            addCriterion("rebate_amount =", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountNotEqualTo(Integer value) {
            addCriterion("rebate_amount <>", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountGreaterThan(Integer value) {
            addCriterion("rebate_amount >", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("rebate_amount >=", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountLessThan(Integer value) {
            addCriterion("rebate_amount <", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountLessThanOrEqualTo(Integer value) {
            addCriterion("rebate_amount <=", value, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountIn(List<Integer> values) {
            addCriterion("rebate_amount in", values, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountNotIn(List<Integer> values) {
            addCriterion("rebate_amount not in", values, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountBetween(Integer value1, Integer value2) {
            addCriterion("rebate_amount between", value1, value2, "rebateAmount");
            return (Criteria) this;
        }

        public Criteria andRebateAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("rebate_amount not between", value1, value2, "rebateAmount");
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