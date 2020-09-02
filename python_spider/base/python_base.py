_author_="liguoliang";
#*********输入输出********
a=111;
b="lgl";
print("hello world");
print("hello","world");
print("hello %s"%a);
print("hello %s"%b);
print("hello %s---%s"%(b,a));

#*****数据类型*******
a=111;
print("a=%s, 数据类型：%s"%(a, type(a)));

a="asdfghjkhgfdsfghjkhghfgd";
print("a=%s, 数据类型：%s"%(a, type(a)));

a=("111",32,12,13);
print("a=%s, 数据类型：%s"%(a, type(a)));

a = ("aaa", "bbb");
print("a的数据类型%s, 值%s"%(type(a), a));

a = ["aaa", "bbb"];
print("a的数据类型%s, 值%s"%(type(a), a));

a = None;
print("a的数据类型%s, 值%s"%(type(a), a));

a = b'asd';
print("a的数据类型%s, 值%s"%(type(a), a));


#*******运算符********
print(1+4-1/2*7);
print(100**2);

a = True and False;
print("a的数据类型%s, 值%s"%(type(a), a));

a = True or False;
print("a的数据类型%s, 值%s"%(type(a), a));

a = not False;
print("a的数据类型%s, 值%s"%(type(a), a));

# ==== ASCII转换 ====

print("98-->%s;a-->%s"%(chr(98), ord('a')));


# ==== encode && decode ====
print("cdsa".encode("ascii"));  # 输出b'cdsa'，转化为bytes类型；
#print("学无止境".encode("ascii"));  # 报错，ascii只定义了127个字符，中文无法解析；
print("学无止境".encode("utf-8"));  # 输出b'\xe5\xad\xa6\xe6\x97\xa0\xe6\xad\xa2\xe5\xa2\x83'，转化为bytes类型，无法显示为ASCII字符的字节，用\x##显示；
print(b'cdsa'.decode("ascii"));
print(b'\xe5\xad\xa6\xe6\x97\xa0\xe6\xad\xa2\xe5\xa2\x83'.decode("utf-8"));

# ==== 前缀字符串 ====
print(u'学无止境'); # 后面字符串是以Unicode编码
print(r'dddd'); # 后面字符串是普通字符串
print(b'qwerr'); # 后面是bytes


# ==== len（计算字符串长度） ====
print(len("qqqqqq"));
print(len("学无止境"));
print(len("学无止境".encode("UTF-8")));
print(len("AAA".encode("UTF-8")));

# ==== replace（替换） ====
a = "zxcvbnmsdfghjklqwertyuiop";
print(a.replace("ghjkl", "-----"));
print(a);
a = "这是一首简单的小情歌，唱出我们心头的白鸽。";
print(a.replace("小情歌", "---"));
print(a);

# ==== find（查找） ====
print("这是一首简单的小情歌，唱出我们心头的白鸽。".find("歌")); # 字符串第一次出现下标，没有-1
print("这是一首简单的小情歌，唱出我们心头的白鸽歌歌哈哈。".rfind("歌")); # 字符串最后一次出现下标，没有-1

# ==== isspace ====
print("".isspace());#有空客为true，无为False

# ==== 字符串格式化 ====
print("%d----%2d----%02d"%(2, 3, 4)); # 2d（不足两位左边补空格）、02d（不足两位，左边补0）
print("%f----%.2f"%(3.141592687, 3.141592687));#保留小数位数
print("%x"%333); # 格式化为十六进制
print("%s%%%s"%("3", "2"));
print(list("%s" % x for x in range(2, 10))); # 将2-10生成器，转化为字符串list

print("Hi {0}, 成绩提高了{1:.1f}%".format("小明", 1.234));

print("Hi {0}, 成绩提高了{1}%".format("小明", "%.1f"%1.234));

print("-".join(["a", "b", "c"]));


''''
. ---- 匹配任意字符；\d ---- 匹配数字；\w ---- 匹配字母；\s ---- 匹配一个空格（包含tab）；
* ---- 任意个字符（包括0个）；+ ---- 至少一个字符；? ---- 表示0个或1个字符；{n} ---- 表示n个字符；{n,m} ---- 表示n-m个字符；
[] ---- 表示范围 ---- [0-9a-zA-Z\_]可以匹配一个数字、字母或者下划线；
() ---- 表示提取的分组，按照正则表达式拆分原始字符串，group(0)永远是原始字符串；
A|B ---- 匹配A或B；^ ----表示行的开头；^\d ---- 表示必须以数字开头；$ ---- 表示行的结束；\d$ ---- 表示必须以数字结束；
'''


import re;
# ==== 匹配字符串 ====
email_re = "^[\d-]+(\.[\d-]+)*@[\w-]+(\.[\w-]+)+$";
# 如果匹配成功，将返回一个Math对象，失败则返回None
if re.match(email_re, "2313458226@qq.com"):
    print("ok");
else:
    print("no");

# ==== 切分字符串 ====
print("a b c".split(" "));
print(re.split(r"\s+", "a b  c"));
print(re.split(r"[\s\,\;]+", "a,b;; c   d"));

# ==== 分组 ====
# 分组提取电话号码
math = re.match(r"^(\d{3})-(\d{3,8})$", "010-12345");
print(math.groups()); # ('010', '12345')
print(math.group(0)); # 010-12345
print(math.group(1)); # 010
print(math.group(2)); # 12345

# 分组提起时间
math = re.match(r"^(0[0-9]|1[0-9]|2[0-3]|[0-9])\:"
    r"(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])\:"
    r"(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])$", "19:05:30");
print(math.groups());

# 分组提取数字
new_line = r'截至9月2日0时，全省累计报告新型冠状病毒肺炎确诊病例653例(其中境外输入112例），' \
    r'累计治愈出院626例，死亡3例，目前在院隔离治疗24例，964人尚在接受医学观察';
new_line_re = r'^截至9月2日0时，全省累计报告新型冠状病毒肺炎确诊病例(\d+)例\(其中境外输入(\d+)例\），' \
    r'累计治愈出院(\d+)例，死亡(\d+)例，目前在院隔离治疗(\d+)例，(\d+)人尚在接受医学观察$';
new_line_math = re.match(new_line_re, new_line);
print(new_line_math.group(0));
print(new_line_math.group(1));
print(new_line_math.group(2));
print(new_line_math.group(3));
print(new_line_math.group(4));
print(new_line_math.group(5));
print(new_line_math.group(6));
# 贪婪匹配
print(re.match(r"^(\d+)(0*)$", "102300").groups()); # ('102300', '')
print(re.match(r"^(\d+?)(0*)$", "102300").groups()); # ('1023', '00')


# ==== list ====
l = list(range(10));
l = ["csa", 123, True, "cdsacda"];
# 从前取值，index从0开始往后；从后取值，index从-1开始往前
print(l[0] + "----" + l[-1]);
# 集合尾部添加元素
l.append("hymanhu");
# 将后面集合的元素添加到前面集合，注意和append的区别，append是将append的整体作为一个元素纳入前面集合；
l += list(range(3));
# 集合指定位置插入元素
l.insert(1, "hujiang");
# 删除集合最后一个元素
l.pop();
# 删除集合中指定位置元素
l.pop(0);
# 直接对集合元素进行赋值
l[0] = "hymanHu111";
print("list: %s, length: %s" % (l, len(l)));

# ==== tuple ====
t = tuple(range(10));
t = ("aads", 123, True, None, 12.3);
# 定义只有一个元素的元祖，元素后追加“,”，以免误解成数学计算意义上的括号
t = ("cdsa",);
# 集合作为元祖的元素，我们可以修改集合的元素
t = ("vsv", ["aaa", "sss"]);
t[1][1] = "bbbb";
print("tuple: %s, length: %s"%(t, len(t)));


# ==== dict ====
d = {"name":"hyman","age":22,"money":22.3};
print("dict: %s, length: %s"%(d, len(d)));
# get 取值，没有返回 None，也可给定默认值
print(d.get("name"), d.get("name1"), d.get("name1", "liguoliang"));
# 赋值取值
d["aaaa"] = "aaaa";
d["name"] = "hymanhu1";
print(d["name"], d["age"]);
# 删除
d.pop("money");
print("dict: %s, length: %s"%(d, len(d)));

# ==== set ====
s = set(["aaa",123, 123, True]);
s.add("fdsaa");
# 移除下标从1开始
s.remove(1);
print("set: %s, length: %s"%(s, len(s)));
# 交集、合集
s2 = set([123, "fdcasc"]);
print(s & s2);
print(s | s2);


# ==== 条件判断 ====
a = 20;
if a < 10:
    print("aaaa");
elif 10 <= a < 20:
    print("bbb");
else:
    print("ccc");
a = " ";
if a and a.strip():
    print(a);
else:
    print("Null string");
# 三目运算符
a, b, c = 1, 2, 3;
print(a if (c > a) else b);

# ==== 循环 ====
a = list(range(10));
print(a);
sum = 0;
for x in a:
    print(x);
    sum += x;
print("sum: %s"%sum);
sum = 0;
i = 0;
while i < 10:
    sum += i;
    i += 1;
print("sum: %s"%sum);
i = 0;
while i < 10:
    if i > 5:
        break;
    i += 1;
print(i);

# ==== 函数 ====
def test(a):
    a += 3;
    return a;

print(test(8));
f = test(8);
print(f);

def test_2(x, y="hujiang"):
    print(x, y);

def test_3(*num):
    count = 0;
    for i in num:
        count += i;
    return count;

def test_4(name, **kv):
    if "city" in kv:
        print("name:%s, city:%s"%(name,kv.get("city")));
    else:
        print("name:%s, city:%s" % (name, "sichuan"));

def test_5(name, *, city):
    if not isinstance(name, (str,)):
        raise TypeError("Type error");
    print("name:%s, city:%s"%(name, city));


if __name__ == "__main__":
    test_2("hello", "hyman");
    test_2("hello");
    print(test_3());
    print(test_3(*list(range(1, 9))));
    print(test_3(1,2,3,4,5));
    test_4("hujiang", **{"age":33});
    test_4("hujiang", **{"age":33,"city":"cd"});
    test_5("hujiang", city="cd");
    # test_5(111, city="cd");

# ==== 内置函数 ====
print(int("22")); # 数据类型转换函数，注意，如果定义变量名和函数名一样，则不会调用该函数，会报错
print(float("22.2"));
print(str(22));
print(abs(-111)); # abs函数，求绝对值
print(max(12, 34, 123.4)); # max函数，求最大值
print(min(-21, -11, 0, 22.3)); # min函数，求最小值
print(" aa bb  cc  ".strip()); # 字符串去前后空格
print("['6K-8K']".strip('[\'\']')); # 移除字符串头尾指定的字符
print(hex(12)); # hex函数，将十进制数转十六进制
print(math.sqrt(3)); # 求平方根
print(sum(range(1, 101))); # 求和
print(sum(list(range(101))));
print("cdaDcdsa".capitalize()); # 将字符串第一个字符变成大写，其他小写