/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.github.datapipeline.core.spark.config;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Abstract Config
 */
public abstract class AbstractConfig implements Serializable {

    protected Map<String, Object> internalMap;

    public AbstractConfig(Map<String, Object> map) {
        if (map != null) {
            internalMap = map;
        } else {
            internalMap = new HashMap<>();
        }
    }

    public Map<String, Object> getAll() {
        return internalMap;
    }

    public void setVal(String key, Object value) {
        internalMap.put(key, value);
    }

    public void setStringVal(String key, String value) {
        setVal(key, value);
    }

    public void setBooleanVal(String key, boolean value) {
        setVal(key, value);
    }

    public void setIntVal(String key, int value) {
        setVal(key, value);
    }

    public void setLongVal(String key, long value) {
        setVal(key, value);
    }

    public void setDoubleVal(String key, double value) {
        setVal(key, value);
    }

    public String getStringVal(String key) {
        return (String) internalMap.get(key);
    }

    public String getStringVal(String key, String defaultValue) {
        String ret = getStringVal(key);
        if (ret == null || ret.trim().length() == 0) {
            return defaultValue;
        }
        return ret;
    }

    public int getIntVal(String key, int defaultValue) {
        Object ret = internalMap.get(key);
        if (ret == null) {
            return defaultValue;
        }
        if (ret instanceof Integer) {
            return ((Integer) ret).intValue();
        }
        if (ret instanceof String) {
            return Integer.valueOf((String) ret).intValue();
        }
        if (ret instanceof Long) {
            return ((Long) ret).intValue();
        }
        if (ret instanceof Float) {
            return ((Float) ret).intValue();
        }
        if (ret instanceof Double) {
            return ((Double) ret).intValue();
        }
        if (ret instanceof BigInteger) {
            return ((BigInteger) ret).intValue();
        }
        if (ret instanceof BigDecimal) {
            return ((BigDecimal) ret).intValue();
        }
        throw new RuntimeException("can't cast " + key + " from " + ret.getClass().getName() + " to Integer");
    }

    public long getLongVal(String key, long defaultValue) {
        Object ret = internalMap.get(key);
        if (ret == null) {
            return defaultValue;
        }
        if (ret instanceof Long) {
            return ((Long) ret);
        }
        if (ret instanceof Integer) {
            return ((Integer) ret).longValue();
        }
        if (ret instanceof String) {
            return Long.valueOf((String) ret);
        }
        if (ret instanceof Float) {
            return ((Float) ret).longValue();
        }
        if (ret instanceof Double) {
            return ((Double) ret).longValue();
        }
        if (ret instanceof BigInteger) {
            return ((BigInteger) ret).longValue();
        }
        if (ret instanceof BigDecimal) {
            return ((BigDecimal) ret).longValue();
        }
        throw new RuntimeException("can't cast " + key + " from " + ret.getClass().getName() + " to Long");
    }

    public double getDoubleVal(String key, double defaultValue) {
        Object ret = internalMap.get(key);
        if (ret == null) {
            return defaultValue;
        }
        if (ret instanceof Double) {
            return ((Double) ret);
        }
        if (ret instanceof Long) {
            return ((Long) ret).doubleValue();
        }
        if (ret instanceof Integer) {
            return ((Integer) ret).doubleValue();
        }
        if (ret instanceof String) {
            return Double.valueOf((String) ret);
        }
        if (ret instanceof Float) {
            return ((Float) ret).doubleValue();
        }
        if (ret instanceof BigInteger) {
            return ((BigInteger) ret).doubleValue();
        }
        if (ret instanceof BigDecimal) {
            return ((BigDecimal) ret).doubleValue();
        }
        throw new RuntimeException("can't cast " + key + " from " + ret.getClass().getName() + " to Long");
    }

    public boolean getBooleanVal(String key, boolean defaultValue) {
        Object ret = internalMap.get(key);
        if (ret == null) {
            return defaultValue;
        }
        if (ret instanceof Boolean) {
            return (Boolean) ret;
        }
        throw new RuntimeException("can't cast " + key + " from " + ret.getClass().getName() + " to Long");
    }

    public List<String> getStringList(String key) {
        Object ret = internalMap.get(key);
        if (ret == null) {
            return null;
        }
        if (ret instanceof List) {
            List<String> list = (List<String>) ret;
            return list.stream().map(v -> String.valueOf(v)).collect(Collectors.<String>toList());
        }
        throw new RuntimeException("can't cast " + key + " from " + ret.getClass().getName() + " to List<String>");
    }

    public List<String> getStringList(String key, List<String> defaultValue) {
        List<String> ret = getStringList(key);
        return ret != null ? ret : defaultValue;
    }
}
