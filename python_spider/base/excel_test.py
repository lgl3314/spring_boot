
__author__ ="liguoliang";

'''
excel test

导入 Openpyxl 模块 ---- import openpyxl
取得 Workbook 对象：openpyxl.load_workbook() 或者 openpyxl.Workbook()；
取得 Worksheet 对象：get_active_sheet() 或者 get_sheet_by_name() ；
数据操作；
保存 Workbook；
'''

import excel_util;
import random;

def write_excel():
    header = ["第一季度", "第二季度", "第三季度", "第四季度"]
    file_path = "/aaaa/1111.xlsx";
    body = list();
    for item in range(1, 10):
        line = list();
        for i in range(1, len(header) + 1):
            line.append(i * random.randint(1, 10));
        body.append(line);
    excel_util.create_excel(header, body, file_path);

    # # 创建一个Workbook对象
    # wb=openpyxl.Workbook();
    # # 获取活动的sheet
    # active_sheet = wb.get_active_sheet();
    # #数据操作
    # active_sheet.append(["第一季度", "第二季度", "第三季度", "第四季度"]);
    # for i in range(1, 10):
    #     active_sheet.append([i * random.randint(1, 10), i * random.randint(1, 10),
    #                          i * random.randint(1, 10), i * random.randint(1, 10)]);
    # wb.save(filename="/aaaa/1111.xlsx");

if __name__=="__main__":
    write_excel()