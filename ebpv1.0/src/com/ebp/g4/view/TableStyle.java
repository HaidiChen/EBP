package com.ebp.g4.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TableStyle
{
    public static void Tablestyle(JTable table)
    {
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("宋体", Font.PLAIN, 18));// 设置表格字体
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.setRowHeight(30);
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        DefaultTableCellRenderer ter = new DefaultTableCellRenderer()// 设置表格间隔色
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object

            value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                if (row % 2 == 0)
                    setBackground(new Color(244, 247, 252));
                else if (row % 2 == 1)
                    setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value,

                        isSelected, hasFocus, row, column);
            }
        };
        ter.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, ter);
    }
}
