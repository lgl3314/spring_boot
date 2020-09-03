__author__ ="liguoliang";

'''
requests and bs4
Requests 主要方法：
requests.request()：构造一个 Request 请求，作为以下方法的基础调用方法；
requests.get()：获取网页的主要方法，对应于 HTTP 的 GET；
requests.head()：获取网页的请求头，对应于 HTTP 的 HEAD；
requests.post()：向 HTML 页面提交 POST 请求，对应于 HTTP 的 POST；
requests.put()：向 HTML 页面提交 PUT 请求，对应于 HTTP 的 PUT；
requests.delete()：向 HTML 页面提交 DELETE 请求，对应于 HTTP 的 DELETE；
requests.patch()：向 HTML 页面提交局部修改请求，对应于 HTTP 的 PATCH；
Response 主要方法：
r.status_code：HTTP 请求的返回状态，200 表示成果，404 表示失败；
r.text：HTTP 响应内容的字符串形式，即 url 对应的页面内容；
r.encoding：从 HTTP header 中猜测的响应内容编码方式，如果 header 中不存在 charset，默认为 iso-8859-1；
r.apparent_encoding：从内容中分析出响应编码方式；
r.content：HTTP 响应内容的二进制格式；
'''

import requests;
from bs4 import

def requests_test(url):
    r=requests.get(url);
    r.encoding=r.apparent_encoding;
    print(r.text);
    print(r.status_code);
    print(r.url);
    print(r.headers);

if __name__=="__main__":
    url="http://wsjkw.sc.gov.cn/scwsjkw/gzbd01/2020/9/3/fe0eb6e3101d4709a9bbd27f5a12ae78.shtml";
    requests_test(url);

















