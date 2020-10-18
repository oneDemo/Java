package com.wang.nodehandler;

import com.wang.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * 处理select标签的子标签
 */
public interface NodeHandler {

    void handleNode(Element nodeToHandle, List<SqlNode> contents);
}
