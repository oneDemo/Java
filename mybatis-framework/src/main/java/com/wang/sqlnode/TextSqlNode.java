package com.wang.sqlnode;

import com.wang.sqlnode.iface.SqlNode;
import com.wang.util.GenericTokenParser;
import com.wang.util.OgnlUtils;
import com.wang.util.SimpleTypeRegistry;
import com.wang.util.TokenHandler;

/**
 * 封装sql文本
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        super();
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        //先处理${}，将处理之后的SQL语句，追加到context中
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", new BindingTokenParser(context));
        String sql = tokenParser.parse(sqlText);
        context.appendSql(sql);
    }

    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }

        return false;
    }

    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;
        }

        /**
         * expression：比如说${username}，那么expression就是username username也就是Ognl表达式
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBindings().get("_parameter");
            if (paramObject == null) {
                // context.getBindings().put("value", null);
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            }

            // 使用Ognl api去获取相应的值
            Object value = OgnlUtils.getValue(expression, context.getBindings());
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }

    }

}
