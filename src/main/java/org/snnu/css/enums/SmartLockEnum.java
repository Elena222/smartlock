package org.snnu.css.enums;

/**
 * Created by lhy on 2017/1/6.
 */
public enum SmartLockEnum {


    //     枚举列表
    LOAD_SUCCESS(1, "登录成功"),
    LOAD_ERROR(0, "登录失败"),
    CHECK_SUCCESS(-1, "查询成功"),
    CHECK_ERROR(-2, "查询失败"),
    INSERT_SUCCESS(2, "插入成功"),
    INSERT_ERROR(3, "插入失败"),
    DETELE_SUCCESS(4, "删除成功"),
    DETELE_ERROR(5, "删除失败"),
    UPDATE_SUCCESS(6, "更新成功"),
    UPDATE_ERROR(7, "更新失败"),
    OPEN_SUCCESS(8, "开门成功"),
    OPEN_ERROR(9, "开门失败"),
    CLOSE_SUCCESS(10,"关门成功"),
    CLOSE_ERROR(11,"关门失败");


        private  int state;
        private  String stateInfo;

         SmartLockEnum(int state, String stateInfo) {
            this.state = state;
            this.stateInfo = stateInfo;
        }

        public int getState() {
            return state;
        }

        public String getStateInfo() {
            return stateInfo;
        }

        public static SmartLockEnum stateOf(int index){
            for(SmartLockEnum state :values()){
                if(state.getState()==index){
                    return  state;
                }
            }
            return null;
        }



}
