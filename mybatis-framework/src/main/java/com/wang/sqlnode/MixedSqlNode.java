package com.wang.sqlnode;

import com.wang.sqlnode.iface.SqlNode;

import java.util.List;

/**
 * 封装所有sqlnode，方便统一处理
 */
public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    public void apply(DynamicContext context) {

        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
    }
}
