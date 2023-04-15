# StarlinUtils
[![License](https://img.shields.io/badge/license-CC%20BY--NC--ND--4.0-green?style=flat-square)](http://creativecommons.org/licenses/by-nc-nd/4.0) [![Pull Requests](https://img.shields.io/github/issues-pr-closed/katorlys/StarlinUtils?style=flat-square)](https://github.com/katorlys/StarlinUtils/pulls) [![Issues](https://img.shields.io/github/issues-closed/katorlys/StarlinUtils?style=flat-square)](https://github.com/katorlys/StarlinUtils/issues) [![Lines](https://img.shields.io/tokei/lines/github/katorlys/StarlinUtils?style=flat-square)](https://github.com/katorlys/StarlinUtils) [![Build](https://img.shields.io/github/actions/workflow/status/katorlys/StarlinUtils/build.yml?style=flat-square)](https://github.com/katorlys/StarlinUtils/actions/workflows/build.yml) [![Codacy](https://img.shields.io/codacy/grade/b187c52b9b754ed8a670a3017201c05f?style=flat-square)](https://app.codacy.com/gh/katorlys/StarlinUtils)

## 简介
星林宇宙 (StarlinWorld) 服务器定制插件 StarlinUtils。  

主要功能:
- 防卡下界门：若玩家登录地点为下界门，则传送其至出生点，防止玩家无法使用登录功能。正版验证为否时打开。
  ![](https://cdn.jsdelivr.net/gh/katorly/Gallery001/plugins/2022-01-20_13.37.53.png)
- 防止耕地被踩踏。
  ![](https://cdn.jsdelivr.net/gh/katorly/Gallery001/plugins/2022-01-20_12.58.39.png)
- 飞行权限组快捷管理。
- 若玩家执行 `/help`, 推送帮助文档链接。
- Forces server to delay shutdown progress and inform players (including players who just joined) that the server is going to shutdown when `/stop`, `/restart` or `/reload` being excuted.
- 增加如下的**无序**彩色混凝土合成配方:
  ![](https://cdn.jsdelivr.net/gh/katorly/Gallery001/plugins/2022_03_11_11.28.30.PNG)

## 指令
- `/su reload` 重载插件配置文件.
- `/su gr(grm,grms)` 执行游戏规则自动设置.
- `/help` 若执行者为非OP，推送帮助文档链接.
- `/setupfly` 初始化飞行权限组 (当安装了 LuckPerms 时可用).
- `/givefly <player>` 给予一名玩家飞行权限.
- `/listfly` 列出所有拥有飞行权限的玩家.
- `/delfly <player>` 移除一名玩家的飞行权限.

## 配置
### config.yml

```yml
# 本插件一切消息的前缀
prefix: "&b&l星林宇宙 &r&7>> &7"

# 当玩家输入 /help 时, 推送该文档链接.
help-document: "https://star-lin.feishu.cn/docs/doccnow2uz3RoK28RKLJleq0Qug"
# 服务器内有玩家时, 执行关闭服务器前倒计时的秒数(仅支持整数).
close-countdown: 10

# [模块] 飞行权限组管理
# 可用参数: <player> = 玩家.
fly:
  # 给予一名玩家飞行权限的指令.
  give-fly: "lp user <player> group add fly"
  # 列出所有拥有飞行权限的玩家的指令.
  list-fly: "lp group fly listmembers"
  # 移除一名玩家的飞行权限的指令.
  del-fly: "lp user <player> group remove fly"

```

配置由插件自动生成，请勿在不清楚相关键值的作用的情况下修改配置文件。

### gamemode.yml

```yml
# 模块: 游戏规则自动设置
# 每次服务器启动完毕时将自动执行一次, 也可以使用 /su gr 触发.

# 请务必确保游戏规则存在且相应的游戏规则值存在. 否则您的设置无效.
# worlds键值填写 "*" 则表示全部世界. (请务必带上引号, 否则用 * 会报错)
# 若您填写了不存在的游戏规则、游戏规则值 和 世界, 插件将自动忽略.

# 示例:
#keepInventory:
#  value: true
#  worlds: "*"

keepInventory:
  value: true
  worlds: "*"
randomTickSpeed:
  value: 10
  worlds: world, world_nether, world_the_end
```
