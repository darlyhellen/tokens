/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
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
 */
package com.darly.token.ui.chart;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.RangeCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * @author zhangyh2 TemperatureChart $ 上午10:39:59 TODO 温度显示情况对照图。
 */
public class TemperatureChart extends AbstractDemoChart {

	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName() {
		return "Temperature range chart";
	}

	/**
	 * Returns the chart description.
	 * 
	 * @return the chart description
	 */
	public String getDesc() {
		return "The monthly temperature (vertical range chart)";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.ui.chart.IDemoChart#execute(android.content.Context)
	 * 启动后直接执行此方法，进行绘图。
	 */
	public Intent execute(Context context) {
		double[] minValues = new double[] { -24, -19, -10, -1, 7, 12, 15, 14,
				9, 1, -11, -16, 1, -11, -16 };
		double[] maxValues = new double[] { 7, 12, 24, 28, 33, 35, 37, 36, 28,
				19, 11, 4, 21, 11, -1 };

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		RangeCategorySeries series = new RangeCategorySeries("温度颜色");
		int length = minValues.length;
		for (int k = 0; k < length; k++) {
			series.add(minValues[k], maxValues[k]);
		}
		dataset.addSeries(series.toXYSeries());
		int[] colors = new int[] { Color.CYAN };
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		setChartSettings(renderer, "每月温度", "月份", "温度", 0, 20, -30, 45,
				Color.GRAY, Color.LTGRAY);
		renderer.setBarSpacing(1.0);
		renderer.setXLabels(10);
		renderer.setYLabels(10);
		// renderer.addXTextLabel(1, "Jan");
		// renderer.addXTextLabel(3, "Mar");
		// renderer.addXTextLabel(5, "May");
		// renderer.addXTextLabel(7, "Jul");
		// renderer.addXTextLabel(10, "Oct");
		// renderer.addXTextLabel(12, "Dec");
		renderer.setMargins(new int[] { 88, 44, 10, 0 });
		renderer.setYLabelsAlign(Align.RIGHT);
		XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(0);
		r.setDisplayChartValues(true);
		r.setChartValuesTextSize(30);
		r.setChartValuesSpacing(3);
		r.setGradientEnabled(true);
		r.setGradientStart(-20, Color.BLUE);
		r.setGradientStop(20, Color.GREEN);
		return ChartFactory.getRangeBarChartIntent(context, dataset, renderer,
				Type.DEFAULT, "Temperature range");
	}
}
