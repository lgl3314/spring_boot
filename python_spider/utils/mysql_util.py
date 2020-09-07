__author__ ="liguoliang";

import pymysql;

def get_connect_resource():
    conn = pymysql.connect(host='localhost', user='root', passwd='123456', db='test', charset='utf8');
    return conn,conn.cursor();

def execute_insert_update_delete(cursor,sql):
    result=cursor.execute(sql);
    return result;

def execute_query(cursor,sql):
    cursor.execute(sql);
    return cursor.fetchall();

def commit_(conn):
    conn.commit();

def close_connect_cursor(conn,cus):
    cus.close();
    conn.close();

def execute_(sql):
    connect, cursor = get_connect_resource();
    result = None;
    if sql.startswith("select"):
        result = execute_query(cursor, sql);
        close_connect_cursor(connect, cursor);
    else:
        result = execute_insert_update_delete(cursor, sql);
        commit_(connect);
        close_connect_cursor(connect, cursor);
    return result
