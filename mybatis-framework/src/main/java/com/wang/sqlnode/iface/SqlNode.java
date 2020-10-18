package com.wang.sqlnode.iface;

import com.wang.sqlnode.DynamicContext;

/**
 * 封装不同的sql脚本，提供sql脚本处理功能
 */
public interface SqlNode {

    void apply(DynamicContext context);
}
