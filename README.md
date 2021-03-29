### 基于([VLibrary](https://github.com/oooo7777777/VLibrary))框架,编写的代码模板插件
### 开发效率提升80%

#### 介绍
- 每次写代码都要做很多得重复动作
> 以一个MVVM架构为例,新建Activity-&gt;继承Base类-&gt;写ViewModel-&gt;写bean--&gt;修改xml--&gt;然后各种关系得绑定,引用等等..
- 这些无用且繁琐得操作,完全可以避免,此插件就是为了解决这些,让你快速高效得撸代码...
- 你也可以下载下来,依葫芦画瓢改成自己框架所需要得代码


#### 功能
- 1.可一键约束资源命名(组件化开发下使用)
> 以你包名得最后一段得第一个字母大写为约束,比如包名为com.v.home,资源约束就是h
> 开启以后res文件里面生成得资源都会以h_为开头,比如h_activity,h_string
- 2.可一键约束是否生成ModelView代码
- 3.一键生成VLibraryActivity(bean,ViewModel,activity,xml,注册AndroidManifest)
- 4.一键生成VLibraryFragment(bean,ViewModel,activity,xml)
- 5.一键生成VLibraryDialog(dialog,xml)
- 6.一键生成VLibraryViewModel(bean,modelView)
- 7.一键生成VLibraryAdapter(bean,xml,adapter)
- 8.一键生成VLibraryNet自定义网络架构




#### 1.安装方法
- ([前往下载](https://pan.baidu.com/s/1ujkoM_eX0u2BtRujVQ9z0g)) 提取码: 46pr
- Android Studio File-&gt; Settings -&gt; Plugins -&gt; install plugin from disk..导入下载的 VLibraryPlugin.jar
- 重启 Android Studio .

#### 2.安装方法
- 源代码下载下来,编译
- 运行 Run Plugin
- 打开\intellij-platform-plugin-template-main\build\libs\找到里面的VLibraryPlugin.jar
- Android Studio File-&gt; Settings -&gt; Plugins -&gt; install plugin from disk..导入生成的 VLibraryPlugin.jar
- 重启 Android Studio .




#### 使用

- 方法1
![方法1](https://images.gitee.com/uploads/images/2021/0329/140602_e76a4a49_699199.jpeg "Dingtalk_20210326175103.jpg")

- 方法2
![方法1](https://images.gitee.com/uploads/images/2021/0329/145118_fd8da60c_699199.jpeg "Dingtalk_20210329145047.jpg")

- 输入框解释(鼠标移动到输入框上面有相对于得中文提示)
![鼠标移动到输入框上面有相对于得中文提示](https://images.gitee.com/uploads/images/2021/0329/145519_47c73f7c_699199.jpeg "Dingtalk_20210329145503.jpg")