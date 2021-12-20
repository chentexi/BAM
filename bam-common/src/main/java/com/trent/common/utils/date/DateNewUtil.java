/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trent.common.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static java.time.temporal.ChronoField.*;
import static java.time.temporal.ChronoUnit.*;

/**
 * JDK 8  新日期类 格式化与字符串转换 工具类
 */
public class DateNewUtil{
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * 获取当前日期时间
     */
    public static LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }
    /**
     * 获取当前日期字符串
     * @return
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
    
    /**
     * 获取当前日期时间字符串
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
    
    /**
     * Date 转 LocalDate
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    /**
     * Date 转 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    /**
     * Date 转 日期字符串
     * @param date
     * @return
     */
    public static String dateToDateStr(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DATE_FORMATTER);
    }
    
    /**
     * Date 转 日期时间字符串
     * @param date
     * @return
     */
    public static String dateToDateTimeStr(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DATE_TIME_FORMATTER);
    }
    
    
    /**
     * LocalDate 转 Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * LocalDateTime 转 Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * LocalDate 转 日期字符串
     * @param localDate
     * @return
     */
    public static String localDateToDateStr(LocalDate localDate) {
        return localDate.format(DATE_FORMATTER);
    }
    
    /**
     * LocalDateTime 转 日期时间字符串
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToDateTimeStr(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }
    
    /**
     * 日期时间字符串 转 LocalDateTime
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime dateTimeStrToLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
    }
    
    /**
     * 日期字符串 转 LocalDate
     * @param dateStr
     * @return
     */
    public static LocalDate dateStrToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }
    
    /**
     * 日期字符串 转 Date
     * @param dateStr
     * @return
     */
    public static Date dateStrToDate(String dateStr) {
        return Date.from(LocalDate.parse(dateStr, DATE_FORMATTER).atStartOfDay(ZoneId.systemDefault()).toInstant());
        
    }
    
    /**
     * 日期时间字符串 转 Date
     * @param dateStr
     * @return
     */
    public static Date dateTimeStrToDate(String dateStr) {
        return Date.from(LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER).atZone(ZoneId.systemDefault()).toInstant());
        
    }
    
    /**
     * 获取上个月的第一天
     * @return
     */
    public static LocalDate getLastMonthFirstDay() {
        return LocalDate.now()
                .with((temporal) -> temporal.with(DAY_OF_MONTH, 1).plus(-1, MONTHS));
    }
    
    /**
     * 获取上个月的最后一天
     * @return
     */
    public static LocalDate getLastMonthLastDay() {
        return LocalDate.now().plus(-1, MONTHS)
                .with(temporal -> temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum()));
    }
    
    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static LocalDate getMonthFirstDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }
    
    /**
     * 获取当月最后一天
     * @return
     */
    public static LocalDate getMonthLastDay() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }
    
    /**
     * 获取下个月的第一天
     *
     * @return
     */
    public static LocalDate getNextMonthFirstDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }
    
    /**
     * 获取下个月的最后一天
     *
     * @return
     */
    public static LocalDate getNextMonthLastDay() {
        return LocalDate.now()
                .plus(1, MONTHS)
                .with(temporal -> temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum()));
    }
    
    
    /**
     * 获取去年第一天
     * @return
     */
    public static LocalDate getLastYearFirstDay() {
        return LocalDate.now()
                .with((temporal) -> temporal.with(DAY_OF_YEAR, 1).plus(-1, YEARS));
    }
    
    /**
     * 获取去年最后一天
     * @return
     */
    public static LocalDate getLastYearLastDay() {
        return LocalDate.now()
                .plus(-1, YEARS)
                .with(temporal -> temporal.with(DAY_OF_YEAR, temporal.range(DAY_OF_YEAR).getMaximum()));
    }
    
    /**
     * 获取今年第一天
     * @return
     */
    public static LocalDate getYearFirstDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
    }
    
    /**
     * 获取今年最后一天
     * @return
     */
    public static LocalDate getYearLastDay() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
    }
    
    /**
     * 获取明年第一天
     * @return
     */
    public static LocalDate getNextYearFirstDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear());
    }
    
    /**
     * 获取明年最后一天
     * @return
     */
    public static LocalDate getNextYearLastDay() {
        return LocalDate.now()
                .plus(1, YEARS)
                .with(temporal -> temporal.with(DAY_OF_YEAR, temporal.range(DAY_OF_YEAR).getMaximum()));
    }
    
    /**
     * 获取上个星期第一天
     * @return
     */
    public static LocalDate getLastWeekFirstDay() {
        return LocalDate.now()
                .plus(-1, WEEKS)
                .with(temporal -> temporal.with(DAY_OF_WEEK, temporal.range(DAY_OF_WEEK).getMinimum()));
    }
    
    /**
     * 获取上个星期最后一天
     * @return
     */
    public static LocalDate getLastWeekLastDay() {
        return LocalDate.now()
                .plus(-1, WEEKS)
                .with(temporal -> temporal.with(DAY_OF_WEEK, temporal.range(DAY_OF_WEEK).getMaximum()));
    }
    
    /**
     * 获取当前星期第一天
     * @return
     */
    public static LocalDate getWeekFirstDay() {
        return LocalDate.now().with(temporal -> temporal.with(DAY_OF_WEEK, temporal.range(DAY_OF_WEEK).getMinimum()));
    }
    
    /**
     * 获取当前星期最后一天
     * @return
     */
    public static LocalDate getWeekLastDay() {
        return LocalDate.now().with(temporal -> temporal.with(DAY_OF_WEEK, temporal.range(DAY_OF_WEEK).getMaximum()));
    }
    
    /**
     * 获取下个星期第一天
     * @return
     */
    public static LocalDate getNextWeekFirstDay() {
        return LocalDate.now()
                .plus(1, WEEKS)
                .with(temporal -> temporal.with(DAY_OF_WEEK, temporal.range(DAY_OF_WEEK).getMinimum()));
    }
    
    /**
     * 获取下个星期最后一天
     * @return LocalDate
     */
    public static LocalDate getNextWeekLastDay() {
        return LocalDate.now()
                .plus(1, WEEKS)
                .with(temporal -> temporal.with(DAY_OF_WEEK, temporal.range(DAY_OF_WEEK).getMaximum()));
    }
    
    /**
     * 获取某年某月的第一天
     * @param year
     * @param month
     * @return
     */
    public static LocalDate getSomeYearMonthFirstDay(int year, int month) {
        return LocalDate.of(year, month, 1);
    }
    
    /**
     * 获取某年某月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static LocalDate getSomeYearMonthLastDay(int year, int month) {
        return LocalDate.of(year, month, 1)
                .with(temporal -> temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum()));
    }
    
    /**
     * 获取时间戳(秒)
     */
    public static Long getTimestampSeconds(){
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
    
    /**
     * 获取时间戳(毫秒)
     */
    public static Long getTimestampMilliScond(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
