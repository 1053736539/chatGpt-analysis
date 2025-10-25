package com.cb.common.utils.poi;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;

/**
 * 图表绘制工具
 */
public class JFreeChartToFileUtil {

    /**
     * 数据集三维/3D饼图
     * @param pds
     * @param file
     * @param title
     */
    public static void createPieChart3D(DefaultPieDataset pds, File file, String title) {
        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createPieChart3D(title, pds, false, false, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            // 得到图块,准备设置标签的字体
            PiePlot plot = (PiePlot) chart.getPlot();
            // 设置标签字体
            plot.setLabelFont(font);
            plot.setStartAngle(3.14f / 2f);
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            plot.setCircular(true);// 是否是正圆
            // 设置外层图片 无边框 无背景色 背景图片透明
            chart.setBorderVisible(false);
            //chart.setBackgroundPaint(null);
            //chart.setBackgroundImageAlpha(0.0f);

            // 设置标签生成器(默认{0})
            // {0}:key {1}:value {2}:百分比 {3}:sum
            //plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}占{2}"));
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}占{2})"));
            // 将内存中的图片写到本地硬盘
            //ChartUtilities.saveChartAsJPEG(file, chart, 600, 300);
            ChartUtilities.saveChartAsPNG(file, chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认设置创建一个饼图
     * @param pds
     * @param file
     * @param title
     */
    public static void createPieChart(DefaultPieDataset pds, File file, String title) {
        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createPieChart(title, pds, false, false, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            // 得到图块,准备设置标签的字体
            PiePlot plot = (PiePlot) chart.getPlot();
            // 设置标签字体
            plot.setLabelFont(font);
            plot.setStartAngle(3.14f / 2f);
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            // 设置外层图片 无边框 无背景色 背景图片透明
            chart.setBorderVisible(false);
            //chart.setBackgroundPaint(null);
            //chart.setBackgroundImageAlpha(0.0f);

            // 设置标签生成器(默认{0})
            // {0}:key {1}:value {2}:百分比 {3}:sum
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}占{2}"));
            // 将内存中的图片写到本地硬盘
            ChartUtilities.saveChartAsJPEG(file, chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 柱形图具有3D效果
     * @param pds
     * @param file
     * @param title
     */
    public static void createBarChart3D(CategoryDataset pds, File file, String title) {
        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createBarChart3D(title, null,
                    null, pds, PlotOrientation.VERTICAL,
                    true, true, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            chart.getLegend().setItemFont(font);
            // 得到图块,准备设置标签的字体
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            // 设置标签生成器(默认{0})

            ValueAxis rangeAxis = plot.getRangeAxis();
            CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表

            rangeAxis.setLabelFont(font);
            rangeAxis.setTickLabelFont(font);
            domainAxis.setLabelFont(font);
            domainAxis.setTickLabelFont(font);
            domainAxis.setMaximumCategoryLabelLines(10);
            domainAxis.setMaximumCategoryLabelWidthRatio(0.5f);

            // 将内存中的图片写到本地硬盘
            ChartUtilities.saveChartAsJPEG(file, chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 条形图
     * @param pds
     * @param file
     * @param title
     */
    public static void createBarChart(CategoryDataset pds, File file, String title, String categoryAxisLabel, String valueAxisLabel) {
        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel,
                    valueAxisLabel, pds, PlotOrientation.VERTICAL,
                    true, true, true);

            //createBarChart(java.lang.String title, java.lang.String categoryAxisLabel, java.lang.String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
            //参数java.lang.String categoryAxisLabel标签放置在X轴的值。该参数的java.lang.String valueAxisLabel标签放置在Y轴的数值。此方法创建一个条形图。
            // 设置抗锯齿，防止字体显示不清楚
            chart.setTextAntiAlias(false);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            chart.getLegend().setItemFont(font);

            CategoryPlot plot = (CategoryPlot) chart.getPlot();// 获得图表显示对象
            plot.setOutlineVisible(false);// 是否显示外边框
            plot.setOutlinePaint(Color.WHITE);// 外边框颜色
            // plot.setOutlineStroke(new BasicStroke(2.f));// 外边框框线粗细
            //plot.setBackgroundPaint(Color.WHITE);// 白色背景
            //设置图表背景颜色(透明)
            plot.setBackgroundPaint(null);
            plot.setNoDataMessage("无图表数据");// 无数据提示
            plot.setNoDataMessageFont(font);// 提示字体
            plot.setNoDataMessagePaint(Color.RED);// 提示字体颜色

            // 网格线
            plot.setDomainGridlinePaint(Color.BLUE);
            plot.setDomainGridlinesVisible(true);// 竖线
            plot.setRangeGridlinePaint(Color.BLACK);
            plot.setRangeGridlinesVisible(true);// 横线
            // 横坐标
            CategoryAxis xAxis = plot.getDomainAxis();
            xAxis.setTickLabelFont(new java.awt.Font("宋体", Font.TRUETYPE_FONT, 12));// 设置X轴值字体
            xAxis.setLabelFont(new java.awt.Font("宋体", java.awt.Font.TRUETYPE_FONT, 12));// 设置X轴标签字体
            xAxis.setAxisLineStroke(new BasicStroke(1f)); // 设置X轴线粗细
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// 倾斜
            xAxis.setAxisLinePaint(Color.BLACK);// 轴线颜色
            xAxis.setUpperMargin(0.05D);// 右边距
            xAxis.setLowerMargin(0.05D);// 左边距

            // 纵坐标
            ValueAxis yAxis = plot.getRangeAxis();
            yAxis.setTickLabelFont(new java.awt.Font("宋体", java.awt.Font.TRUETYPE_FONT, 12));// 设置y轴字体
            yAxis.setLabelFont(new java.awt.Font("宋体", java.awt.Font.TRUETYPE_FONT, 12));// 设置y轴标签字体
            yAxis.setAxisLineStroke(new BasicStroke(1f)); // 设置y轴线粗细
            yAxis.setAxisLinePaint(Color.BLACK);// 轴线颜色
            yAxis.setUpperMargin(0.18D);// 上边距
            yAxis.setLowerMargin(0.1D);// 下边距

            // 标签数据
            BarRenderer renderer = new BarRenderer();
            renderer.setBarPainter(new StandardBarPainter());// 取消渐变效果
            renderer.setShadowVisible(false);// 关闭倒影
            renderer.setDrawBarOutline(false); // 设置柱子边框可见
            renderer.setItemMargin(0.2); // 设置每个平行柱的之间距离
            renderer.setMaximumBarWidth(0.05);// 设置条形柱最大宽度
            renderer.setMinimumBarLength(0.05);// 设置条形柱最小宽度
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 数据标签格式
            renderer.setBaseItemLabelsVisible(true);// 是否显示数据标签
            renderer.setItemLabelFont(font);// 数据标签字体
            renderer.setSeriesPaint(0, Color.BLUE);
            plot.setRenderer(renderer);

            // 将内存中的图片写到本地硬盘
            //ChartUtilities.saveChartAsJPEG(file, chart, 600, 300);
            ChartUtilities.saveChartAsPNG(file, chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
