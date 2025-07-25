# Kyle-TldScanner
快捷扫描指定目录下所有jar快速列出包含或不含tld的jar文件，并将不包含tld的jar文件名以符合服务器过滤设置的格式输出，直接拷贝进配置文件即可。
J2EE服务器启动时，会扫描每个jar文件，检查其是否包含标签定义文件，及.tld后缀的文件，随着项目规模扩大，用到的jar包文件越来越多，检查每个jar文件会延缓服务器启动时间，比如Tomcat启动时如果检测到扫描了不含.tld文件的jar包，会输出提示信息“org.apache.jasper.servlet.TldScanner.scanJars 至少有一个JAR被扫描用于TLD但尚未包含TLD。 ”
当然解决办法很简单，就是找到 Tomcat 服务器安装目录下的 conf 文件夹下的**catalina.properties文件**

如D:\code\apache-tomcat-9.0.24\conf\catalina.properties
找到下面这一行

tomcat.util.scan.StandardJarScanFilter.jarsToSkip=/
在后面添加需要跳过的jar文件就可以。
但在遇到jar文件很多的情况，又要避免将包含tld的jar跳过，要精准找到每个需要跳过的jar文件还是比较麻烦。

本软件就是提供给大家一个快捷扫描指定目录下所有jar快速列出包含或不含tld的jar文件，并将不包含tld的jar文件名以符合服务器过滤设置的格式输出，直接拷贝进配置文件即可。

Usage: java -jar build/libs/Kyle-TldScanner-1.0.jar [jars' folder path]
