#### 更新git上的数据,然后打包
```aidl
    @echo on
    cd /d C:\A-MyWork\GitR\git\kylo
    git fetch
    git rebase origin/dev
    mvn clean install -Dmaven.test.skip=true
```
#### 执行完若想保留黑窗口,在另一个bat文件上调用此文件
```aidl
    @call windows.bat
    pause
```