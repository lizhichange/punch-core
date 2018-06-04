package com.fulihui.punch.dal.dataobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PunchTemplateMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public PunchTemplateMsgExample() {
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(String value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(String value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(String value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(String value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLike(String value) {
            addCriterion("template_id like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotLike(String value) {
            addCriterion("template_id not like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<String> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<String> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(String value1, String value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(String value1, String value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andMessageLinkIsNull() {
            addCriterion("message_link is null");
            return (Criteria) this;
        }

        public Criteria andMessageLinkIsNotNull() {
            addCriterion("message_link is not null");
            return (Criteria) this;
        }

        public Criteria andMessageLinkEqualTo(String value) {
            addCriterion("message_link =", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkNotEqualTo(String value) {
            addCriterion("message_link <>", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkGreaterThan(String value) {
            addCriterion("message_link >", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkGreaterThanOrEqualTo(String value) {
            addCriterion("message_link >=", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkLessThan(String value) {
            addCriterion("message_link <", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkLessThanOrEqualTo(String value) {
            addCriterion("message_link <=", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkLike(String value) {
            addCriterion("message_link like", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkNotLike(String value) {
            addCriterion("message_link not like", value, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkIn(List<String> values) {
            addCriterion("message_link in", values, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkNotIn(List<String> values) {
            addCriterion("message_link not in", values, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkBetween(String value1, String value2) {
            addCriterion("message_link between", value1, value2, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageLinkNotBetween(String value1, String value2) {
            addCriterion("message_link not between", value1, value2, "messageLink");
            return (Criteria) this;
        }

        public Criteria andMessageBodyIsNull() {
            addCriterion("message_body is null");
            return (Criteria) this;
        }

        public Criteria andMessageBodyIsNotNull() {
            addCriterion("message_body is not null");
            return (Criteria) this;
        }

        public Criteria andMessageBodyEqualTo(String value) {
            addCriterion("message_body =", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyNotEqualTo(String value) {
            addCriterion("message_body <>", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyGreaterThan(String value) {
            addCriterion("message_body >", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyGreaterThanOrEqualTo(String value) {
            addCriterion("message_body >=", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyLessThan(String value) {
            addCriterion("message_body <", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyLessThanOrEqualTo(String value) {
            addCriterion("message_body <=", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyLike(String value) {
            addCriterion("message_body like", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyNotLike(String value) {
            addCriterion("message_body not like", value, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyIn(List<String> values) {
            addCriterion("message_body in", values, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyNotIn(List<String> values) {
            addCriterion("message_body not in", values, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyBetween(String value1, String value2) {
            addCriterion("message_body between", value1, value2, "messageBody");
            return (Criteria) this;
        }

        public Criteria andMessageBodyNotBetween(String value1, String value2) {
            addCriterion("message_body not between", value1, value2, "messageBody");
            return (Criteria) this;
        }

        public Criteria andChannleIsNull() {
            addCriterion("channle is null");
            return (Criteria) this;
        }

        public Criteria andChannleIsNotNull() {
            addCriterion("channle is not null");
            return (Criteria) this;
        }

        public Criteria andChannleEqualTo(String value) {
            addCriterion("channle =", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleNotEqualTo(String value) {
            addCriterion("channle <>", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleGreaterThan(String value) {
            addCriterion("channle >", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleGreaterThanOrEqualTo(String value) {
            addCriterion("channle >=", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleLessThan(String value) {
            addCriterion("channle <", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleLessThanOrEqualTo(String value) {
            addCriterion("channle <=", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleLike(String value) {
            addCriterion("channle like", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleNotLike(String value) {
            addCriterion("channle not like", value, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleIn(List<String> values) {
            addCriterion("channle in", values, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleNotIn(List<String> values) {
            addCriterion("channle not in", values, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleBetween(String value1, String value2) {
            addCriterion("channle between", value1, value2, "channle");
            return (Criteria) this;
        }

        public Criteria andChannleNotBetween(String value1, String value2) {
            addCriterion("channle not between", value1, value2, "channle");
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