**设计目标**

该文档旨在设计并实现一个基于Java的简易文件传输系统，满足以下目标：
1.实现功能：支持多用户连接、文件传输、文件解析展示、命令操作等功能。
2.提高安全性：在文件传输过程中通过base64编码加密传输，保障数据安全。
3.保证代码质量：通过合理的架构设计、设计模式的应用、多线程处理、异常处理和代码规范，确保代码稳定性和可维护性。

主要解决的问题：
1.多用户连接和文件传输：实现一个支持多个用户连接到服务器，并能够发送文件和展示文件的系统。
2.数据安全性：确保文件在传输过程中被加密，接收后自动解密还原初始文件。
3.文件解析和展示：支持用户在服务器端和客户端查看接收到的XML和JSON文件内容。
4.系统稳定性和可扩展性：通过使用设计模式、多线程处理、异常处理等手段，保证系统的稳定性和可扩展性。

**系统框架**

![image](https://github.com/liuyang123654/bosssoft-programDemo/assets/95218907/daea2950-e13a-46d2-9ee4-96d94cefa167)


**系统类图**

![image](https://github.com/liuyang123654/bosssoft-programDemo/assets/95218907/35b83add-ec43-475d-b418-733f92b60923)


**画出系统时序图**

![image](https://github.com/liuyang123654/bosssoft-programDemo/assets/95218907/41362246-4fa2-4db3-b761-81a21cf26f98)


**定义系统的数据结构**

message:
1.命令关键字
2.被发送人的ID
3.文件路径filePath

异常处理类：ExceptionHandler类：
logger:静态私有Logger对象，用于记录日志。
方法：
handleException(Throwablee):处理通用异常的方法，根据不同类型的异常分别调用对应的处理方法。
handleServiceException(ServiceExceptione):处理业务异常的方法，记录错误码和错误消息。
handleNullPointerException(NullPointerExceptione):处理空指针异常的方法，记录错误消息和异常信息。
handleIOException(IOExceptione):处理IO异常的方法，记录错误消息和异常信息。
handleException(Exceptione):处理其他类型异常的方法，记录错误消息和异常信息。
异常处理类ExceptionHandler中没有定义任何数据结构，它主要是一组静态方法，用于处理不同类型的异常。

ServiceException类：
code:整型，表示自定义错误码。
ServiceException(intcode,Stringmsg):构造函数，初始化自定义异常对象的错误码和错误消息。
getCode():获取错误码的方法。

这两个类的组合提供了一套异常处理机制，可以根据不同类型的异常进行相应的处理，并且记录相应的错误消息和异常信息，以便于排查和解决问题。

**对主要的类的方法做设计**

![image](https://github.com/liuyang123654/bosssoft-programDemo/assets/95218907/6d8538b9-bb05-4403-a16f-1c2a9bfd19b1)
![image](https://github.com/liuyang123654/bosssoft-programDemo/assets/95218907/356cd4ec-b851-431d-b9e3-8c7eb80a8965)


             
