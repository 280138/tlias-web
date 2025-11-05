# Git操作



知乎链接  https://zhuanlan.zhihu.com/p/135183491

## 基本操作

把readme.txt添加到暂存区		git add readme.txt
把本地修改全部添加到暂存区		git add .
把readme.txt提交到本地repo	git commit -m '第一次提交'

查看官方日记					git log
重置上个版本					git reset --hard HEAD^
查看私人日记					git reflog
重置到指定版本				git reset --hard[版本号]

## 关联远程库？

添加远程仓库关联	git remote add origin https://github.com/280138/testgit.git
查看对应远程仓库									git remote -v
第一次推送master分支  git push -u origin master:main  (用-u 进行分支关联)
把"本地"master分支推送到远程仓库origin中同名分支		git push origin master
将本地master推送到远程main						git push origin master:main

修改本地分支名为main												git branch -m main

## vim操作备忘

i	进入插入模式
Esc	退出插入模式，回到命令模式
:wq  保存并退出
:q!	强制退出不保存