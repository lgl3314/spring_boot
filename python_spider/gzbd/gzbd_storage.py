__author__ ="liguoliang";

'''
冠状病毒数据存储
'''

import excel_util,mysql_util,gzbd.requests_bs4;

def storage_excel():
    # 获取所有数据
    gzbd_data = gzbd.requests_bs4.gzbd_data();

    header = ["地区", "日期", "确诊人数", "境外输入数", "治愈人数", "死亡人数", "隔离人数", "观察人数"];
    body = list();
    for item in gzbd_data:
        line = [];
        line.append(item["地区"]);
        line.append(item["日期"]);
        line.append(item.get("确诊人数", None));
        line.append(item.get("境外输入数", None));
        line.append(item.get("治愈人数", None));
        line.append(item.get("死亡人数", None));
        line.append(item.get("隔离人数", None));
        line.append(item.get("观察人数", None));
        body.append(line)
    file_path = "/aaaa/gzbd_data.xlsx";
    excel_util.create_excel(header, body, file_path);


def storage_mysql():
    # 获取所有数据
    gzbd_data = gzbd.requests_bs4.gzbd_data();

    for item in gzbd_data:
        # build sql 语句：根据时间查询、插入语句
        epidemic_date =item["日期"];
        query_sql = "select * from gzbd_epidemic where date = %s"%(epidemic_date,);
        insert_sql = "insert into gzbd_epidemic (region, date, diagnosis, overseas_import, cure, " \
                     "death, therapy, observation) values ('%s', '%s', %s, %s, %s, %s, %s, %s)"%\
                     (item["地区"],item["日期"],item.get("确诊人数", None),item.get("境外输入人数", None),item.get("治愈人数", None),
                      item.get("死亡人数", None),item.get("隔离人数", None),item.get("观察人数", None));

        insert_sql = insert_sql.replace("None", "null");
        print(insert_sql);

        # 查询数据，以此判断是否需要插入
        result = mysql_util.execute_(query_sql);
        if len(result) ==0:
            # 插入数据
            result = mysql_util.execute_(insert_sql);
            if result > 0:
                print("ok");
            else:
                print("no");
def get_gzbd_data():
    sql="select * from gzbd_epidemic order by date desc";
    return mysql_util.execute_(sql);

if __name__=="__main__":

    # storage_mysql();
    storage_excel();