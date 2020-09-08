def trim(s):
    # print("in：", s)
    if len(s) < 0:
        return ''

    # 去掉前面空格
    index = 0
    while index < len(s):
        if s[index] == ' ':
            if index + 1 == len(s):
                return ''
            else:
                index += 1
        else:
            s = s[index:]
            break

    # print("-：", s, "-")

    # 去掉后面空格
    index = -1
    while index >= -len(s):
        if s[index] == ' ':
            index -= 1
        else:
            if index == -1:
                break
            else:
                s = s[:index + 1]
                break
    # print("out：", s)
    return s