/*
 * Copyright (c) 2017. Dawn Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fulihui.punch.enums;

import org.apache.commons.lang3.EnumUtils;

/**
 * 枚举工具类，继承与 commons-lang 的 EnumUtils，并作扩展
 * @author Willard.Hu on 2017/9/29.
 */
public class Enums extends EnumUtils {

    /**
     * 通过枚举代码获取枚举值，适用于实现 IEnum 的枚举类
     * @param code 枚举代码
     * @param enumClz 枚举类
     * @return 匹配到的枚举值
     */
    public static <T extends IEnum> T ofCode(Object code, Class<T> enumClz) {
        T[] arr = enumClz.getEnumConstants();
        if (arr == null || arr.length == 0) {
            return null;
        }
        for (T i : arr) {
            if (i.code().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
