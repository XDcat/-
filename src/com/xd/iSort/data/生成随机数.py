import random


def i_random(n, s=0):
    return random.randrange(s, n * 100)


for i in [20, 200, 2000]:
    data = [i_random(i) for j in range(i)]
    with open(r'J:\数据结构\数据结构实践\src\com\xd\iSort\data\{}个随机数据'.format(i), 'w', encoding='utf-8') as f:
        f.write('\n'.join(list(map(str, data))))
    with open(r'J:\数据结构\数据结构实践\src\com\xd\iSort\data\{}个正序数据'.format(i), 'w', encoding='utf-8') as f:
        f.write('\n'.join(list(map(str, sorted(data)))))
    with open(r'J:\数据结构\数据结构实践\src\com\xd\iSort\data\{}个逆序数据'.format(i), 'w', encoding='utf-8') as f:
        f.write('\n'.join(list(map(str, sorted(data, reverse=True)))))
