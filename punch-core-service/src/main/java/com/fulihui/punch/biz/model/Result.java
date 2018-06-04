package com.fulihui.punch.biz.model;

import java.util.List;

import org.near.toolkit.model.ToString;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-3-12
 * Time: 16:14
 */
public class Result extends ToString {
    private static final long serialVersionUID = 458488177114207111L;


    /**
     * tbk_dg_newuser_order_get_response : {"results":{"data":{"has_next":true,"page_no":1,"page_size":20,"results":{"map_data":[{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:51:10","member_id":118203612,"mobile":"139****7391","order_tk_type":-1,"register_time":"2018-03-11 23:51:10","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm","buy_time":"2018-03-11 22:52:55","tb_trade_parent_id":124211643674220941},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:24:01","member_id":118203612,"mobile":"130****3026","order_tk_type":-1,"register_time":"2018-03-11 23:23:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:04:24","member_id":118203612,"mobile":"151****7748","order_tk_type":-1,"register_time":"2018-03-11 23:04:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:00:31","member_id":118203612,"mobile":"133****1875","order_tk_type":-1,"register_time":"2018-03-11 23:00:30","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:49:38","member_id":118203612,"mobile":"138****5682","order_tk_type":-1,"register_time":"2018-03-11 22:49:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:21","buy_time":"2018-03-11 22:52:55","member_id":118203612,"mobile":"138****5341","order_tk_type":1,"register_time":"2018-03-11 22:38:21","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124211643674220941,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:12","buy_time":"2018-03-11 23:04:45","member_id":118203612,"mobile":"151****6813","order_tk_type":1,"register_time":"2018-03-11 22:38:11","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124154970376590449,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:12:12","member_id":118203612,"mobile":"159****6658","order_tk_type":-1,"register_time":"2018-03-11 22:12:11","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:11:58","member_id":118203612,"mobile":"176****4419","order_tk_type":-1,"register_time":"2018-03-11 22:11:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:58:15","member_id":118203612,"mobile":"150****9097","order_tk_type":-1,"register_time":"2018-03-11 21:58:14","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:54:53","buy_time":"2018-03-11 21:57:15","member_id":118203612,"mobile":"150****6379","order_tk_type":1,"register_time":"2018-03-11 21:54:53","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124135118344453544,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:43:55","buy_time":"2018-03-11 21:53:55","member_id":118203612,"mobile":"156****1041","order_tk_type":1,"register_time":"2018-03-11 21:43:54","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124086845915297837,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:41:20","buy_time":"2018-03-11 21:42:37","member_id":118203612,"mobile":"135****2737","order_tk_type":1,"register_time":"2018-03-11 21:41:19","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":136879091908427019,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:37:39","member_id":118203612,"mobile":"189****6558","order_tk_type":-1,"register_time":"2018-03-11 21:37:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:35:24","member_id":118203612,"mobile":"176****0626","order_tk_type":-1,"register_time":"2018-03-11 21:35:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:30:42","member_id":118203612,"mobile":"131****7160","order_tk_type":-1,"register_time":"2018-03-11 21:30:42","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","member_id":118203612,"mobile":"184****6554","order_tk_type":-1,"register_time":"2018-03-11 21:30:19","site_id":21544321,"site_name":"互力微信淘客系统","status":1,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:13:44","member_id":118203612,"mobile":"139****6066","order_tk_type":-1,"register_time":"2018-03-11 21:13:44","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:03:47","member_id":118203612,"mobile":"137****5351","order_tk_type":-1,"register_time":"2018-03-11 21:03:47","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:00:15","member_id":118203612,"mobile":"139****6032","order_tk_type":-1,"register_time":"2018-03-11 21:00:15","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"}]}}},"request_id":"86nfoz7wfnd"}
     */

    private TbkDgNewuserOrderGetResponseBean tbk_dg_newuser_order_get_response;

    public TbkDgNewuserOrderGetResponseBean getTbk_dg_newuser_order_get_response() {
        return tbk_dg_newuser_order_get_response;
    }

    public void setTbk_dg_newuser_order_get_response(TbkDgNewuserOrderGetResponseBean tbk_dg_newuser_order_get_response) {
        this.tbk_dg_newuser_order_get_response = tbk_dg_newuser_order_get_response;
    }

    public static class TbkDgNewuserOrderGetResponseBean  extends ToString{
        private static final long serialVersionUID = -8931311716393171572L;
        /**
         * results : {"data":{"has_next":true,"page_no":1,"page_size":20,"results":{"map_data":[{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:51:10","member_id":118203612,"mobile":"139****7391","order_tk_type":-1,"register_time":"2018-03-11 23:51:10","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm","buy_time":"2018-03-11 22:52:55","tb_trade_parent_id":124211643674220941},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:24:01","member_id":118203612,"mobile":"130****3026","order_tk_type":-1,"register_time":"2018-03-11 23:23:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:04:24","member_id":118203612,"mobile":"151****7748","order_tk_type":-1,"register_time":"2018-03-11 23:04:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:00:31","member_id":118203612,"mobile":"133****1875","order_tk_type":-1,"register_time":"2018-03-11 23:00:30","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:49:38","member_id":118203612,"mobile":"138****5682","order_tk_type":-1,"register_time":"2018-03-11 22:49:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:21","buy_time":"2018-03-11 22:52:55","member_id":118203612,"mobile":"138****5341","order_tk_type":1,"register_time":"2018-03-11 22:38:21","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124211643674220941,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:12","buy_time":"2018-03-11 23:04:45","member_id":118203612,"mobile":"151****6813","order_tk_type":1,"register_time":"2018-03-11 22:38:11","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124154970376590449,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:12:12","member_id":118203612,"mobile":"159****6658","order_tk_type":-1,"register_time":"2018-03-11 22:12:11","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:11:58","member_id":118203612,"mobile":"176****4419","order_tk_type":-1,"register_time":"2018-03-11 22:11:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:58:15","member_id":118203612,"mobile":"150****9097","order_tk_type":-1,"register_time":"2018-03-11 21:58:14","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:54:53","buy_time":"2018-03-11 21:57:15","member_id":118203612,"mobile":"150****6379","order_tk_type":1,"register_time":"2018-03-11 21:54:53","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124135118344453544,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:43:55","buy_time":"2018-03-11 21:53:55","member_id":118203612,"mobile":"156****1041","order_tk_type":1,"register_time":"2018-03-11 21:43:54","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124086845915297837,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:41:20","buy_time":"2018-03-11 21:42:37","member_id":118203612,"mobile":"135****2737","order_tk_type":1,"register_time":"2018-03-11 21:41:19","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":136879091908427019,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:37:39","member_id":118203612,"mobile":"189****6558","order_tk_type":-1,"register_time":"2018-03-11 21:37:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:35:24","member_id":118203612,"mobile":"176****0626","order_tk_type":-1,"register_time":"2018-03-11 21:35:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:30:42","member_id":118203612,"mobile":"131****7160","order_tk_type":-1,"register_time":"2018-03-11 21:30:42","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","member_id":118203612,"mobile":"184****6554","order_tk_type":-1,"register_time":"2018-03-11 21:30:19","site_id":21544321,"site_name":"互力微信淘客系统","status":1,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:13:44","member_id":118203612,"mobile":"139****6066","order_tk_type":-1,"register_time":"2018-03-11 21:13:44","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:03:47","member_id":118203612,"mobile":"137****5351","order_tk_type":-1,"register_time":"2018-03-11 21:03:47","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:00:15","member_id":118203612,"mobile":"139****6032","order_tk_type":-1,"register_time":"2018-03-11 21:00:15","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"}]}}}
         * request_id : 86nfoz7wfnd
         */

        private ResultsBeanX results;
        private String request_id;

        public ResultsBeanX getResults() {
            return results;
        }

        public void setResults(ResultsBeanX results) {
            this.results = results;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public static class ResultsBeanX extends ToString{
            /**
             * data : {"has_next":true,"page_no":1,"page_size":20,"results":{"map_data":[{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:51:10","member_id":118203612,"mobile":"139****7391","order_tk_type":-1,"register_time":"2018-03-11 23:51:10","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm","buy_time":"2018-03-11 22:52:55","tb_trade_parent_id":124211643674220941},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:24:01","member_id":118203612,"mobile":"130****3026","order_tk_type":-1,"register_time":"2018-03-11 23:23:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:04:24","member_id":118203612,"mobile":"151****7748","order_tk_type":-1,"register_time":"2018-03-11 23:04:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:00:31","member_id":118203612,"mobile":"133****1875","order_tk_type":-1,"register_time":"2018-03-11 23:00:30","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:49:38","member_id":118203612,"mobile":"138****5682","order_tk_type":-1,"register_time":"2018-03-11 22:49:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:21","buy_time":"2018-03-11 22:52:55","member_id":118203612,"mobile":"138****5341","order_tk_type":1,"register_time":"2018-03-11 22:38:21","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124211643674220941,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:12","buy_time":"2018-03-11 23:04:45","member_id":118203612,"mobile":"151****6813","order_tk_type":1,"register_time":"2018-03-11 22:38:11","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124154970376590449,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:12:12","member_id":118203612,"mobile":"159****6658","order_tk_type":-1,"register_time":"2018-03-11 22:12:11","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:11:58","member_id":118203612,"mobile":"176****4419","order_tk_type":-1,"register_time":"2018-03-11 22:11:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:58:15","member_id":118203612,"mobile":"150****9097","order_tk_type":-1,"register_time":"2018-03-11 21:58:14","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:54:53","buy_time":"2018-03-11 21:57:15","member_id":118203612,"mobile":"150****6379","order_tk_type":1,"register_time":"2018-03-11 21:54:53","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124135118344453544,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:43:55","buy_time":"2018-03-11 21:53:55","member_id":118203612,"mobile":"156****1041","order_tk_type":1,"register_time":"2018-03-11 21:43:54","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124086845915297837,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:41:20","buy_time":"2018-03-11 21:42:37","member_id":118203612,"mobile":"135****2737","order_tk_type":1,"register_time":"2018-03-11 21:41:19","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":136879091908427019,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:37:39","member_id":118203612,"mobile":"189****6558","order_tk_type":-1,"register_time":"2018-03-11 21:37:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:35:24","member_id":118203612,"mobile":"176****0626","order_tk_type":-1,"register_time":"2018-03-11 21:35:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:30:42","member_id":118203612,"mobile":"131****7160","order_tk_type":-1,"register_time":"2018-03-11 21:30:42","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","member_id":118203612,"mobile":"184****6554","order_tk_type":-1,"register_time":"2018-03-11 21:30:19","site_id":21544321,"site_name":"互力微信淘客系统","status":1,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:13:44","member_id":118203612,"mobile":"139****6066","order_tk_type":-1,"register_time":"2018-03-11 21:13:44","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:03:47","member_id":118203612,"mobile":"137****5351","order_tk_type":-1,"register_time":"2018-03-11 21:03:47","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:00:15","member_id":118203612,"mobile":"139****6032","order_tk_type":-1,"register_time":"2018-03-11 21:00:15","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"}]}}
             */

            private DataBean data;

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean extends ToString {
                /**
                 * has_next : true
                 * page_no : 1
                 * page_size : 20
                 * results : {"map_data":[{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:51:10","member_id":118203612,"mobile":"139****7391","order_tk_type":-1,"register_time":"2018-03-11 23:51:10","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:24:01","member_id":118203612,"mobile":"130****3026","order_tk_type":-1,"register_time":"2018-03-11 23:23:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:04:24","member_id":118203612,"mobile":"151****7748","order_tk_type":-1,"register_time":"2018-03-11 23:04:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 23:00:31","member_id":118203612,"mobile":"133****1875","order_tk_type":-1,"register_time":"2018-03-11 23:00:30","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:49:38","member_id":118203612,"mobile":"138****5682","order_tk_type":-1,"register_time":"2018-03-11 22:49:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:21","buy_time":"2018-03-11 22:52:55","member_id":118203612,"mobile":"138****5341","order_tk_type":1,"register_time":"2018-03-11 22:38:21","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124211643674220941,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:38:12","buy_time":"2018-03-11 23:04:45","member_id":118203612,"mobile":"151****6813","order_tk_type":1,"register_time":"2018-03-11 22:38:11","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124154970376590449,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:12:12","member_id":118203612,"mobile":"159****6658","order_tk_type":-1,"register_time":"2018-03-11 22:12:11","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 22:11:58","member_id":118203612,"mobile":"176****4419","order_tk_type":-1,"register_time":"2018-03-11 22:11:57","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:58:15","member_id":118203612,"mobile":"150****9097","order_tk_type":-1,"register_time":"2018-03-11 21:58:14","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:54:53","buy_time":"2018-03-11 21:57:15","member_id":118203612,"mobile":"150****6379","order_tk_type":1,"register_time":"2018-03-11 21:54:53","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124135118344453544,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:43:55","buy_time":"2018-03-11 21:53:55","member_id":118203612,"mobile":"156****1041","order_tk_type":1,"register_time":"2018-03-11 21:43:54","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":124086845915297837,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:41:20","buy_time":"2018-03-11 21:42:37","member_id":118203612,"mobile":"135****2737","order_tk_type":1,"register_time":"2018-03-11 21:41:19","site_id":21544321,"site_name":"互力微信淘客系统","status":3,"tb_trade_parent_id":136879091908427019,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:37:39","member_id":118203612,"mobile":"189****6558","order_tk_type":-1,"register_time":"2018-03-11 21:37:38","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:35:24","member_id":118203612,"mobile":"176****0626","order_tk_type":-1,"register_time":"2018-03-11 21:35:24","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:30:42","member_id":118203612,"mobile":"131****7160","order_tk_type":-1,"register_time":"2018-03-11 21:30:42","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","member_id":118203612,"mobile":"184****6554","order_tk_type":-1,"register_time":"2018-03-11 21:30:19","site_id":21544321,"site_name":"互力微信淘客系统","status":1,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:13:44","member_id":118203612,"mobile":"139****6066","order_tk_type":-1,"register_time":"2018-03-11 21:13:44","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:03:47","member_id":118203612,"mobile":"137****5351","order_tk_type":-1,"register_time":"2018-03-11 21:03:47","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"},{"adzone_id":72194855,"adzone_name":"微管家","bind_time":"2018-03-11 21:00:15","member_id":118203612,"mobile":"139****6032","order_tk_type":-1,"register_time":"2018-03-11 21:00:15","site_id":21544321,"site_name":"互力微信淘客系统","status":2,"union_id":"fklm"}]}
                 */

                private boolean has_next;
                private int page_no;
                private int page_size;
                private ResultsBean results;

                public boolean isHas_next() {
                    return has_next;
                }

                public void setHas_next(boolean has_next) {
                    this.has_next = has_next;
                }

                public int getPage_no() {
                    return page_no;
                }

                public void setPage_no(int page_no) {
                    this.page_no = page_no;
                }

                public int getPage_size() {
                    return page_size;
                }

                public void setPage_size(int page_size) {
                    this.page_size = page_size;
                }

                public ResultsBean getResults() {
                    return results;
                }

                public void setResults(ResultsBean results) {
                    this.results = results;
                }

                public static class ResultsBean extends ToString {
                    private List<MapDataBean> map_data;

                    public List<MapDataBean> getMap_data() {
                        return map_data;
                    }

                    public void setMap_data(List<MapDataBean> map_data) {
                        this.map_data = map_data;
                    }

                    public static class MapDataBean extends ToString{
                        /**
                         * adzone_id : 72194855
                         * adzone_name : 微管家
                         * bind_time : 2018-03-11 23:51:10
                         * member_id : 118203612
                         * mobile : 139****7391
                         * order_tk_type : -1
                         * register_time : 2018-03-11 23:51:10
                         * site_id : 21544321
                         * site_name : 互力微信淘客系统
                         * status : 2
                         * union_id : fklm
                         * buy_time : 2018-03-11 22:52:55
                         * tb_trade_parent_id : 124211643674220941
                         */

                        private int adzone_id;
                        private String adzone_name;
                        private String bind_time;
                        private int member_id;
                        private String mobile;
                        private int order_tk_type;
                        private String register_time;
                        private int site_id;
                        private String site_name;
                        private int status;
                        private String union_id;
                        private String buy_time;
                        private long tb_trade_parent_id;
                        private String member_nick;

                        public String getMember_nick() {
                            return member_nick;
                        }

                        public void setMember_nick(String member_nick) {
                            this.member_nick = member_nick;
                        }

                        public int getAdzone_id() {
                            return adzone_id;
                        }

                        public void setAdzone_id(int adzone_id) {
                            this.adzone_id = adzone_id;
                        }

                        public String getAdzone_name() {
                            return adzone_name;
                        }

                        public void setAdzone_name(String adzone_name) {
                            this.adzone_name = adzone_name;
                        }

                        public String getBind_time() {
                            return bind_time;
                        }

                        public void setBind_time(String bind_time) {
                            this.bind_time = bind_time;
                        }

                        public int getMember_id() {
                            return member_id;
                        }

                        public void setMember_id(int member_id) {
                            this.member_id = member_id;
                        }

                        public String getMobile() {
                            return mobile;
                        }

                        public void setMobile(String mobile) {
                            this.mobile = mobile;
                        }

                        public int getOrder_tk_type() {
                            return order_tk_type;
                        }

                        public void setOrder_tk_type(int order_tk_type) {
                            this.order_tk_type = order_tk_type;
                        }

                        public String getRegister_time() {
                            return register_time;
                        }

                        public void setRegister_time(String register_time) {
                            this.register_time = register_time;
                        }

                        public int getSite_id() {
                            return site_id;
                        }

                        public void setSite_id(int site_id) {
                            this.site_id = site_id;
                        }

                        public String getSite_name() {
                            return site_name;
                        }

                        public void setSite_name(String site_name) {
                            this.site_name = site_name;
                        }

                        public int getStatus() {
                            return status;
                        }

                        public void setStatus(int status) {
                            this.status = status;
                        }

                        public String getUnion_id() {
                            return union_id;
                        }

                        public void setUnion_id(String union_id) {
                            this.union_id = union_id;
                        }

                        public String getBuy_time() {
                            return buy_time;
                        }

                        public void setBuy_time(String buy_time) {
                            this.buy_time = buy_time;
                        }

                        public long getTb_trade_parent_id() {
                            return tb_trade_parent_id;
                        }

                        public void setTb_trade_parent_id(long tb_trade_parent_id) {
                            this.tb_trade_parent_id = tb_trade_parent_id;
                        }
                    }
                }
            }
        }
    }
}
