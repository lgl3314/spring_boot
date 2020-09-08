from django.core import serializers
from django.shortcuts import render

# Create your views here.

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "HymanHu";

from django.http import HttpResponse, JsonResponse
from my_app.models import *;
import random, datetime;

def hello_world(request):
    user_model = User(user_name="liguoliang" + str(random.randint(1, 10)), password="111111",
                      create_date = datetime.datetime.now())
    user_model.save()

    users = User.objects.all()
    print("==== 查询所有 ====", users)
    users = User.objects.filter(id=1)
    print("==== 条件查询 ====", users)
    user = User.objects.get(id=1)
    print("==== 获取单个对象 ====", user)
    # 更新数据
    user.user_name = "liguoliang" + str(random.randint(1, 10))
    # user.save()
    users = serializers.serialize("json", User.objects.order_by("user_name")[0:2])
    print("==== 排序&limit ====" + users)

    # return HttpResponse("Hello World!");
    return JsonResponse(users, safe=False)

def hello_world_1(request):
    context = {};
    context["name"] = "lgl";
    return render(request," hello_world.html",context);












