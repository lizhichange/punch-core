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

package com.fulihui.punch.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * @author  lizhi
 */
public class Collections {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static <T> List<T> emptyList() {
        return java.util.Collections.emptyList();
    }

    public static <K, V> Map<K, V> emptyMap() {
        return java.util.Collections.emptyMap();
    }

    public static <T> List<T> asList(T... elems) {
        return Arrays.asList(elems);
    }

    public static <T> List<T> singletonList(T elem) {
        return java.util.Collections.singletonList(elem);
    }

    public static <T> Set<T> singleton(T elem) {
        return java.util.Collections.singleton(elem);
    }

    public static <T, R> List<R> transform(Collection<T> src, Function<? super T, ? extends R> mapper) {
        if (isEmpty(src)) {
            return emptyList();
        }
        return src.stream().map(mapper).collect(Collectors.toList());
    }


}
