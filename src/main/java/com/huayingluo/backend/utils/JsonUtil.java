package com.huayingluo.backend.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    /**
     * 初始化ObjectMapper
     *
     * @return
     */
    private static ObjectMapper createObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

        // 允许序列化空的POJO类(否则会抛出异常)

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 对象的所有字段全部列入。NON_NULL：不返回 null 值字段

        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 取消java.util.Date, Calendar默认转换timestamps形式

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.registerModule(new JavaTimeModule());

        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        return objectMapper;

    }


    public static String object2Json(Object o) {

        StringWriter sw = new StringWriter();

        JsonGenerator gen = null;

        try {

            gen = new JsonFactory().createGenerator(sw);

            OBJECT_MAPPER.writeValue(gen, o);

        } catch (IOException e) {

            throw new RuntimeException("不能序列化对象为Json", e);

        } finally {

            if (null != gen) {

                try {

                    gen.close();

                } catch (IOException e) {

                    throw new RuntimeException("不能序列化对象为Json", e);

                }

            }

        }

        return sw.toString();

    }


    /**
     * 对象转map
     *
     * @param o 转行对象
     * @return
     */

    public static Map<String, Object> object2Map(Object o) {

        return OBJECT_MAPPER.convertValue(o, Map.class);

    }


    /**
     * map转java对象
     *
     * @param map   参数map
     * @param clazz T字节对象
     * @param <T>   返回对象类型
     * @return
     */

    public static <T> T map2Object(Map map, Class<T> clazz) {

        return OBJECT_MAPPER.convertValue(map, clazz);

    }


    /**
     * 将 json 字段串转换为 对象.
     *
     * @param json  字符串
     * @param clazz 需要转换为的类
     * @return
     */

    public static <T> T json2Object(String json, Class<T> clazz) {

        try {

            return OBJECT_MAPPER.readValue(json, clazz);

        } catch (IOException e) {

            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);

        }

    }


    /**
     * 将 json 字段串转换为 List.
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */

    public static <T> List<T> json2List(String json, Class<T> clazz) throws IOException {

        JavaType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);

        return OBJECT_MAPPER.readValue(json, type);

    }


    /**
     * 将 json 字段串转换为 数据.
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */

    public static <T> T[] json2Array(String json, Class<T[]> clazz) throws IOException {

        return OBJECT_MAPPER.readValue(json, clazz);


    }


    public static <T> T node2Object(JsonNode jsonNode, Class<T> clazz) {

        try {

            T t = OBJECT_MAPPER.treeToValue(jsonNode, clazz);

            return t;

        } catch (JsonProcessingException e) {

            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + jsonNode.toString(), e);

        }

    }


    public static JsonNode object2Node(Object o) {

        try {

            if (o == null) {

                return OBJECT_MAPPER.createObjectNode();

            } else {

                return OBJECT_MAPPER.convertValue(o, JsonNode.class);

            }

        } catch (Exception e) {

            throw new RuntimeException("不能序列化对象为Json", e);

        }

    }


    /**
     * JsonNode转换为Java泛型对象，可以是各种类型。
     *
     * @param <T>
     * @param json String
     * @param tr   TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     */

    public static <T> T json2GenericObject(String json, TypeReference<T> tr) {

        if (json == null || "".equals(json)) {

            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json);

        } else {

            try {

                return (T) OBJECT_MAPPER.readValue(json, tr);

            } catch (Exception e) {

                throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);

            }

        }

    }
}