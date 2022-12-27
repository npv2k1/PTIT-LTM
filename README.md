# Java

## Phần chung

Lệnh kiểm tra ip trên máy mình mở `cmd` gõ lệnh sau

```
ipconfig
```

## Phần socket

Lưu ý:

- Đúng ip port với server
- Thú tự khai báo luồng stream phải đúng thứ tự
- Các lệnh gửi nhận phải đúng thứ tự
- Các lớp phải `implements Serializable` và có `SerialVersionUID` theo đề

Phần import thư viện

```java
import java.net.*;
import java.io.*;
```

## Phần rmi

Lưu ý:

- Đúng ip port với server
- Khai báo interface phải extends `Remote`
- implement interface phải extends `UnicastRemoteObject`

Phần import thư viện

```java
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
```
