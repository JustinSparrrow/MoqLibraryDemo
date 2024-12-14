package com.moqi.library.mapper.po;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserPoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table User
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table User
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table User
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public UserPoExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table User
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table User
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("`openid` is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("`openid` is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("`openid` =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("`openid` <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("`openid` >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("`openid` >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("`openid` <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("`openid` <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("`openid` like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("`openid` not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("`openid` in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("`openid` not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("`openid` between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("`openid` not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("`phone` is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("`phone` is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("`phone` =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("`phone` <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("`phone` >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("`phone` >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("`phone` <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("`phone` <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("`phone` like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("`phone` not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("`phone` in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("`phone` not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("`phone` between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("`phone` not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("`role` is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("`role` is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("`role` =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("`role` <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("`role` >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("`role` >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("`role` <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("`role` <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("`role` like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("`role` not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("`role` in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("`role` not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("`role` between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("`role` not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("`created_at` is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("`created_at` is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(LocalDateTime value) {
            addCriterion("`created_at` =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(LocalDateTime value) {
            addCriterion("`created_at` <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(LocalDateTime value) {
            addCriterion("`created_at` >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`created_at` >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(LocalDateTime value) {
            addCriterion("`created_at` <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`created_at` <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<LocalDateTime> values) {
            addCriterion("`created_at` in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<LocalDateTime> values) {
            addCriterion("`created_at` not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`created_at` between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`created_at` not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("`updated_at` is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("`updated_at` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(LocalDateTime value) {
            addCriterion("`updated_at` =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(LocalDateTime value) {
            addCriterion("`updated_at` <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(LocalDateTime value) {
            addCriterion("`updated_at` >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`updated_at` >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(LocalDateTime value) {
            addCriterion("`updated_at` <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`updated_at` <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<LocalDateTime> values) {
            addCriterion("`updated_at` in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<LocalDateTime> values) {
            addCriterion("`updated_at` not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`updated_at` between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`updated_at` not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("`nickname` is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("`nickname` is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("`nickname` =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("`nickname` <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("`nickname` >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("`nickname` >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("`nickname` <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("`nickname` <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("`nickname` like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("`nickname` not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("`nickname` in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("`nickname` not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("`nickname` between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("`nickname` not between", value1, value2, "nickname");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table User
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table User
     *
     * @mbg.generated
     */
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