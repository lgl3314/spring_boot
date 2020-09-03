
__author__ ="liguoliang";

'''
excel test
'''

import openpyxl;

def create_excel(header, body, file_path):
    # 获取 workbook 对象
    wb = openpyxl.Workbook();
    # 获取 sheet 对象
    active_sheet = wb.get_active_sheet();
    # 数据操作
    active_sheet.append(header);
    for item in body:
        active_sheet.append(item);
    # 文件保存
    wb.save(filename=file_path);

if __name__ == "__main__":
    l=list(range(1,10));
    print(l[0]);