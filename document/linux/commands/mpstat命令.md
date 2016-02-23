# mpstat命令

```linux
mpstat 2
Linux 2.6.32-431.20.3.el6.x86_64 (hostname)         02/22/2016      _x86_64_        (1 CPU)

02:52:56 PM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest   %idle
02:52:58 PM  all   97.50    0.00    1.50    0.00    0.50    0.50    0.00    0.00    0.00
02:53:00 PM  all   96.98    0.00    2.51    0.00    0.00    0.50    0.00    0.00    0.00
02:53:02 PM  all   96.02    0.00    2.99    0.00    0.50    0.50    0.00    0.00    0.00
02:53:04 PM  all   97.50    0.00    1.50    0.00    0.50    0.50    0.00    0.00    0.00
02:53:06 PM  all   97.00    0.00    2.50    0.00    0.00    0.50    0.00    0.00    0.00
```

### 参数说明：
usr     在internal时间段里，处理用户进程所使用CPU的百分比。用户进程是用于应用程序（如 Oracle 数据库）的非内核进程。
nice    在internal时间段里，表示使用nice命令对进程进行降级时CPU的百分比。简单来说，nice命令更改进程的优先级。 
sys     在internal时间段里，表示内核进程使用的CPU百分比。 
iowait  在internal时间段里，表示等待进行I/O所使用的CPU时间百分比。
irq     在internal时间段里，表示用于处理系统中断的CPU百分比。
soft    在internal时间段里，表示用于软件中断的CPU百分比。
idle    在internal时间段里，显示CPU的空闲时间。
intr/s  在internal时间段里，每秒CPU接收的中断的次数。