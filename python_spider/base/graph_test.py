__author__ ="liguoliang";

'''
graph test
绘图流程
添加图轨数据，使用 go.Scatter、go.Bar 等函数；
设置画图布局，使用 go.Layout(title,xaxis,yaxis) 函数；
集成图轨，布局数据，使用 go.Figure 函数；
绘制输出，使用 offline.plot 和 offline.iplot 函数；
'''

import plotly;

def draw_line_graph():
    trace_1 = plotly.graph_objs.Scatter(
    x = [1, 2, 3, 4, 5, 6, 7],
    y = [32, 23, 10, 34,10, 23, 32],
    name = "散点",
    mode = 'markers'
    );
    trace_2 = plotly.graph_objs.Scatter(
    x = [1, 2, 3, 4, 5, 6, 7],
    y = [33, 22, 11, 33, 11, 22, 33],
    name = "折线"
    );
    line_data = [trace_1, trace_2];
    #设置布局
    layout = plotly.graph_objs.Layout(title="折线图", xaxis={'title': 'x'}, yaxis={'title': 'y'});
    #融合图轨数据与布局
    fig = plotly.graph_objs.Figure(data=line_data, layout=layout);
    #输出
    plotly.offline.plot(fig, filename="/aaaa/line-graph.html", image="png");

def draw_zf_graph():
    trace_1 = plotly.graph_objs.Histogram(
        x=[1, 2, 3, 4],
        y=[10, 15, 13, 17],
        name="第一产业"
    );
    trace_2 = plotly.graph_objs.Bar(
        x=[1, 2, 3, 4],
        y=[9, 12, 10, 14],
        name="第二产业"
    );
    trace_3 = plotly.graph_objs.Bar(
        x=[1, 2, 3, 4],
        y=[11, 15, 14, 12],
        name="第三产业"
    );
    bar_data = [trace_1, trace_2, trace_3];
    #设置布局
    layout = plotly.graph_objs.Layout(title="产业柱状图", xaxis={'title': '季度'}, yaxis={'title': '总值'});
    # 融合图轨数据与布局
    fig = plotly.graph_objs.Figure(data=bar_data, layout=layout);
    #输出
    plotly.offline.plot(fig, filename="/aaaa/bar-graph.html", image="png");

    def draw_bar_graph():
        trace_1 = plotly.graph_objs.Bar(
            x=[1, 2, 3, 4],
            y=[10, 15, 13, 17],
            name="第一产业"
        );
        trace_2 = plotly.graph_objs.Bar(
            x=[1, 2, 3, 4],
            y=[9, 12, 10, 14],
            name="第二产业"
        );
        trace_3 = plotly.graph_objs.Bar(
            x=[1, 2, 3, 4],
            y=[11, 15, 14, 12],
            name="第三产业"
        );
        bar_data = [trace_1, trace_2, trace_3];
        # 设置布局
        layout = plotly.graph_objs.Layout(title="产业柱状图", xaxis={'title': '季度'}, yaxis={'title': '总值'});
        # 融合图轨数据与布局
        fig = plotly.graph_objs.Figure(data=bar_data, layout=layout);
        # 输出
        plotly.offline.plot(fig, filename="/aaaa/bar-graph.html", image="png");


def draw_pie_graph():
    trace_1 = plotly.graph_objs.Pie(
        labels=['产品1', '产品2', '产品3', '产品4', '产品5'],
        values=[38.7, 15.33, 19.9, 8.6, 17.47]
    );
    pie_data = [trace_1];
    layout = plotly.graph_objs.Layout(title="Pie");
    fig = plotly.graph_objs.Figure(data=pie_data, layout=layout);
    plotly.offline.plot(fig, filename="/aaaa/pie-graph.html", image="png");

def draw_graphs():
    trace_1 = plotly.graph_objs.Bar(
        x=[1, 2, 3, 4],
        y=[10, 15, 13, 17],
        name="第一产业"
    );
    trace_2 = plotly.graph_objs.Scatter(
        x=[1, 2, 3, 4],
        y=[16, 5, 11, 9],
        name="折线"
    );
    fig = plotly.tools.make_subplots(rows=2, cols=1);
    fig.append_trace(trace_1, 1, 1);
    fig.append_trace(trace_2, 2, 1);
    fig['layout'].update(height=600, width=600, title="总标题");
    plotly.offline.plot(fig, filename="/aaaa/graphs.html", image="png");


if __name__=="__main__":
   # draw_line_graph();
    # draw_bar_graph();
    # draw_pie_graph();
    # draw_graphs();
    draw_zf_graph()