#!/usr/bin/env python3
# -*- codin
# g: utf-8 -*-
__author__ = "lgl";

'''
公共部分
'''

class Result(object):
    def __init__(self, status, message):
        self.status = status;
        self.message = message;

    def result(self):
        return {"status": self.status, "message": self.message};

