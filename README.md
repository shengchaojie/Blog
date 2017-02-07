#自己写的博客，基本功能完成，慢慢完善。  
框架spring+jpa+springMVC
1.HTTP 缓存实践 关于etag和修改事件的缓存实践 HelloController  的
2.自己写的接口调用频率拦截器 和filter,自己设计了xsd来规范xml元数据,FrequencyFilter 和FrequencyInterceptor 2个类，1个耦合spring框架，1个只要是web项目都能使用  
3.react实践，bootstrap作为前端，做了一个分页器，公用性不是很强，但是比官方demo还是很有料的  
4.用户登录控制，uid=登录名|有效时间Expires|hash值,hash值可以由"登录名+有效时间Expires+用户密码（加密后的）的前几位 +salt,uid返回给前台cookie，后台根据传过来的uid的前2个值加服务器得到密码和salt值，来进行权限控制和过期控制。  
