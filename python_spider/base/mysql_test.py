__author__ = "liguoliang";

#import pymysql;
import mysql_util;

def inset_resource():
    conn,cus = mysql_util.get_connect_resource();
    sql="insert into resource (resource_uri,resource_name,permission) value ('aa','BB','cc')";
    mysql_util.execute_insert_update_delete(cus, sql);
    mysql_util.commit_(conn);
    mysql_util.close_connect_cursor(conn, cus);
    # 建立链接
    #conn = pymysql.connect(host='localhost', user='root', passwd='123456', db='test', charset='utf8');
    # 获取游标
   # cus = conn.cursor()
    #cus.execute("insert into resource (resource_uri,resource_name,permission) value ('aa','BB','cc')");
   # conn.commit()
   # cus.close();
  #  conn.close();


def query_resource():
    conn,cus=mysql_util.get_connect_resource();
    sql="select * from resource";
    result=mysql_util.execute_query(cus, sql);
    print(result);
    mysql_util.close_connect_cursor(conn, cus);
    # 建立链接
    #conn = pymysql.connect(host='localhost', user='root', passwd='123456', db='test', charset='utf8');
    # 获取游标
    #cus = conn.cursor()
    #result = cus.execute("select * from resource");
   # print(cus.fetchall());
   # conn.commit()
    #cus.close();
    #conn.close();

if __name__ == "__main__":
    query_resource()
