# Java

## Phần chung

Lệnh kiểm tra ip trên máy mình mở `cmd` gõ lệnh sau

```
ipconfig
```

## Phần socket TCP

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

### DataInputStream

Tạo luồng stream nhận dữ liệu socket

```java
DataInputStream dataIS = new DataInputStream(socket.getInputStream());

//  Một số kiểu đọc dữ liệu
dataIS.readUTF();
dataIS.readInt();
dataIS.readFloat();
dataIS.readChar();
```

### DataOutputStream

Tạo luồng stream để gửi dữ liệu qua socket

```java
DataOutputStream dataOS = new DataOutputStream(socket.getOutputStream());
```

### ObjectInputStream

Tạo luồng stream `nhận Object` qua socket

```java
ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());

// Nhận object và ép kiểu sang lớp cần nhận
Answer ans = (Answer) objectIS.readObject();

```

### ObjectOutputStream

Tạo luồng dữ liệu để `gửi Object` qua socket

```java
ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
// Gửi đối tượng lên server
objectOS.writeObject(s);
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

### Interface remote

Luu ý:

- Cần đúng package với server vd: `package control;`
- Cần extends `Remote`

```java
package control;
import java.rmi.*;
public interface IRemoteClient extends Remote {
    public String getMa() throws RemoteException;
}
```
### Class implement interface

Luu ý:
    - Cần đúng package với server vd: `package control;`
    - Cần extends `UnicastRemoteObject`
    


## Các lớp model

Các lớp khi gửi qua mạng cần implement `Serializable` và có `SerialVersionUID`

Luu ý:

- Cần đúng package với server vd `package model;`
- Cần cùng `SerialVersionUID` với server

```java
package model;
import java.io.Serializable;
public class Student implements Serializable {
    static final long serialVersionUID = 11L;
    private String maSv;
    public Student() {}
    public String getMaSv() {
        return maSv;
    }
    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }
}
```
