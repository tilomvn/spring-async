# Spring Async
* Async Task occurs in Controller with Non-Blocking I/O.
* From Filter to Servlet Request Serialization is still Blocking I/O.
* Use Apache Bench(ab) and JConsole for high load testing.
```
D:\Soft\httpd-2.4.48-win32-VS16\Apache24\bin>ab -n 100 -c 10 http://localhost:5050/api/test
Server Software:
Server Hostname:        localhost
Server Port:            5050

Document Path:          /api/test
Document Length:        5 bytes

Concurrency Level:      10
Time taken for tests:   0.802 seconds
Complete requests:      100
Failed requests:        0
Total transferred:      13700 bytes
HTML transferred:       500 bytes
Requests per second:    124.68 [#/sec] (mean)
Time per request:       80.205 [ms] (mean)
Time per request:       8.020 [ms] (mean, across all concurrent requests)
Transfer rate:          16.68 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.5      0       1
Processing:     6   35  49.4     30     498
Waiting:        6   30  48.6     21     489
Total:          6   35  49.5     30     499

Percentage of the requests served within a certain time (ms)
  50%     30
  66%     37
  75%     42
  80%     44
  90%     54
  95%     67
  98%     79
  99%    499
 100%    499 (longest request)

D:\Soft\httpd-2.4.48-win32-VS16\Apache24\bin>ab -n 100 -c 10 http://localhost:5050/api/test1
Server Software:
Server Hostname:        localhost
Server Port:            5050

Document Path:          /api/test1
Document Length:        31 bytes

Concurrency Level:      10
Time taken for tests:   0.450 seconds
Complete requests:      100
Failed requests:        90
   (Connect: 0, Receive: 0, Length: 90, Exceptions: 0)
Total transferred:      13690 bytes
HTML transferred:       3190 bytes
Requests per second:    222.21 [#/sec] (mean)
Time per request:       45.003 [ms] (mean)
Time per request:       4.500 [ms] (mean, across all concurrent requests)
Transfer rate:          29.71 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.5      0       3
Processing:     3   20  28.3     14     276
Waiting:        2   10  26.9      6     272
Total:          3   20  28.4     15     277

Percentage of the requests served within a certain time (ms)
  50%     15
  66%     23
  75%     27
  80%     29
  90%     35
  95%     40
  98%     53
  99%    277
 100%    277 (longest request)

```
