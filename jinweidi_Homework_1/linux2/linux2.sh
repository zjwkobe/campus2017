#!/bin/bash

# 题目分析：
#     1. 题目未说明当前工作目录与 dir1 的相对关系，于是默认 dir1 在当前工作目录下。
#     2. 题目指出“从本地目录 dir1 拷贝一个文件...”，即表明 dir1 目录下至少有一个普通文件。

scp dir1/filename l-test.dev.cn1:/tmp/
