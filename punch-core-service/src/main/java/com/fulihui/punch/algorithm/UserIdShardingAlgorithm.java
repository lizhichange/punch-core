/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
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
 * </p>
 */

package com.fulihui.punch.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

/**
 * lz
 * @author lizhi
 */
@Component
public class UserIdShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserIdShardingAlgorithm.class);

    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames,
                                  final ShardingValue<String> shardingValue) {
        int i = new HashCodeBuilder().append(shardingValue.getValue()).hashCode();
        int abs = Math.abs(i);
        LOGGER.info("userId:{},表定位:{}", shardingValue.getValue(),
            abs % availableTargetNames.size());
        for (String each : availableTargetNames) {
            if (each.endsWith(abs % availableTargetNames.size() + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames,
                                           final ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<String> values = shardingValue.getValues();
        for (String value : values) {
            for (String tableNames : availableTargetNames) {
                int i = new HashCodeBuilder().append(value).hashCode();
                int abs = Math.abs(i);
                if (tableNames.endsWith(abs % availableTargetNames.size() + "")) {
                    result.add(tableNames);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames,
                                                final ShardingValue<String> shardingValue) {

        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        String userId = "22222222222";
        int i = new HashCodeBuilder().append(userId).hashCode();
        int abs = Math.abs(i);
        System.out.println("表定位:" + abs % 10);

    }
}
