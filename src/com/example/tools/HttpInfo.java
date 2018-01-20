package com.example.tools;

import org.apache.http.HttpStatus;

public class HttpInfo {
  //Http状态码
  	public static String httpStatus(int statusCode){
  		String rst = "" ;
  		/**1xx消息**/
  		if(statusCode == HttpStatus.SC_CONTINUE)//100 Continue客户端应当继续发送请求
  			rst = "100 服务器正在接受请求!" ;
  		else if(statusCode == HttpStatus.SC_SWITCHING_PROTOCOLS)//101 Switching Protocols
  			rst = "101 Switching Protocols!" ;
  		else if(statusCode == HttpStatus.SC_PROCESSING)//102 Processing处理将被继续执行
  			rst = "102  处理将被继续执行!" ;
  		/**2xx成功**/
  		else if(statusCode == HttpStatus.SC_OK)//200 OK请求已成功
  			rst = "200 请求成功!" ;
  		else if(statusCode == HttpStatus.SC_CREATED)//201 Created请求已经被实现
  			rst = "201 请求已经实现!" ;
  		else if(statusCode == HttpStatus.SC_ACCEPTED)//202 Accepted服务器已接受请求，但尚未处理
  			rst = "202 服务器已接受请求，但尚未处理!" ;
  		else if(statusCode == HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION)//203 Non-Authoritative Information服务器已成功处理了请求
  			rst = "203 服务器已成功处理了请求!" ;
  		else if(statusCode == HttpStatus.SC_NO_CONTENT)//204 No Content服务器成功处理了请求，但不需要返回任何实体内容，并且希望返回更新了的元信息。
  			rst = "204 服务器成功处理了请求，但不需要返回任何实体内容!" ;
  		else if(statusCode == HttpStatus.SC_RESET_CONTENT)//205 Reset Content服务器成功处理了请求，且没有返回任何内容
  			rst = "205 服务器成功处理了请求，且没有返回任何内容!" ;
  		else if(statusCode == HttpStatus.SC_PARTIAL_CONTENT)//206 Partial Content服务器已经成功处理了部分GET请求
  			rst = "206 服务器已经成功处理了部分GET请求!" ;
  		else if(statusCode == HttpStatus.SC_MULTI_STATUS)//207 Multi-Status之后的消息体将是一个XML消息
  			rst = "207 之后的消息体将是一个XML消息!" ;
  		/** 3xx重定向 **/
  		else if(statusCode == HttpStatus.SC_MULTIPLE_CHOICES)//300 Multiple Choices被请求的资源有一系列可供选择的回馈信息，每个都有自己特定的地址和浏览器驱动的商议信息
  			rst = "300 Multiple Choices!" ;
  		else if(statusCode == HttpStatus.SC_MOVED_PERMANENTLY)//301 Moved Permanently被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个URI之一
  			rst = "301 Moved Permanently!" ;
  		else if(statusCode == HttpStatus.SC_SEE_OTHER)//303 See Other对应当前请求的响应可以在另一个URI上被找到，而且客户端应当采用GET的方式访问那个资源
  			rst = "303 See Other!" ;
  		else if(statusCode == HttpStatus.SC_NOT_MODIFIED)//304 Not Modified如果客户端发送了一个带条件的GET请求且该请求已被允许，而文档的内容（自上次访问以来或者根据请求的条件）并没有改变，则服务器应当返回这个状态码
  			rst = "304 Not Modified!" ;
  		else if(statusCode == HttpStatus.SC_USE_PROXY)//305 Use Proxy被请求的资源必须通过指定的代理才能被访问
  			rst = "305 Use Proxy!" ;
  		else if(statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)//307 Temporary Redirect请求的资源现在临时从不同的URI响应请求
  			rst = "307 Temporary Redirect!" ;
  		/** 4xx请求错误 **/
  		else if(statusCode == HttpStatus.SC_BAD_REQUEST)//400 Bad Request由于包含语法错误，当前请求无法被服务器理解
  			rst = "400 Bad Request非法请求!" ;
  		else if(statusCode == HttpStatus.SC_UNAUTHORIZED)//401 Unauthorized当前请求需要用户验证
  			rst = "401 Unauthorized未授权!" ;
  		else if(statusCode == HttpStatus.SC_FORBIDDEN)//403 Forbidden服务器拒绝响应
  			rst = "403 服务器拒绝响应!" ;
  		else if(statusCode == HttpStatus.SC_NOT_FOUND)//404 Not Found服务器未找到
  			rst = "404 服务器未找到!" ;
  		else if(statusCode == HttpStatus.SC_METHOD_NOT_ALLOWED)//405 Method Not Allowed请求方法不能被用于请求相应的资源
  			rst = "405 请求方法不能被用于请求相应的资源!" ;
  		else if(statusCode == HttpStatus.SC_NOT_ACCEPTABLE)//406 Not Acceptable无法生成响应实体
  			rst = "406 无法生成响应实体!" ;
  		else if(statusCode == HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED)//407 Proxy Authentication Required与401响应类似，只不过客户端必须在代理服务器上进行身份验证
  			rst = "407 客户端必须在代理服务器上进行身份验证!" ;
  		else if(statusCode == HttpStatus.SC_REQUEST_TIMEOUT)//408 Request Timeout请求超时
  			rst = "408 请求超时!" ;
  		else if(statusCode == HttpStatus.SC_CONFLICT)//409 Conflict被请求的资源的当前状态之间存在冲突，请求无法完成
  			rst = "409 资源冲突，请求无法完成!" ;
  		else if(statusCode == HttpStatus.SC_GONE)//410 Gone被请求的资源在服务器上已经不再可用，而且没有任何已知的转发地址
  			rst = "410 资源不可用!" ;
  		else if(statusCode == HttpStatus.SC_LENGTH_REQUIRED)//411 Length Required服务器拒绝在没有定义Content-Length头的情况下接受请求
  			rst = "411 Length Required!" ;
  		else if(statusCode == HttpStatus.SC_PRECONDITION_FAILED)//412 Precondition Failed服务器在验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个
  			rst = "412 Precondition Failed!" ;
  		else if(statusCode == HttpStatus.SC_REQUEST_TOO_LONG)//413 Request Entity Too Large服务器拒绝处理当前请求，因为该请求提交的实体数据大小超过了服务器愿意或者能够处理的范围
  			rst = "413 Request Entity Too Large!" ;
  		else if(statusCode == HttpStatus.SC_REQUEST_URI_TOO_LONG)//414 Request-URI Too Long请求的URI长度超过了服务器能够解释的长度，因此服务器拒绝对该请求提供服务
  			rst = "414 Request-URI Too Long!" ;
  		else if(statusCode == HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)//415 Unsupported Media Type对于当前请求的方法和所请求的资源，请求中提交的实体并不是服务器中所支持的格式，因此请求被拒绝。
  			rst = "415 Unsupported Media Type!" ;
  		else if(statusCode == HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE)//416 Requested Range Not Satisfiable如果请求中包含了Range请求头，并且Range中指定的任何数据范围都与当前资源的可用范围不重合，同时请求中又没有定义If-Range请求头，那么服务器就应当返回416状态码
  			rst = "416 Requested Range Not Satisfiable!" ;
  		else if(statusCode == HttpStatus.SC_EXPECTATION_FAILED)//417 Expectation Failed在请求头Expect中指定的预期内容无法被服务器满足，或者这个服务器是一个代理服务器，它有明显的证据证明在当前路由的下一个节点上，Expect的内容无法被满足
  			rst = "417 Expectation Failed!" ;
  		else if(statusCode == HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE)//419 
  			rst = "419 !" ;
  		else if(statusCode == HttpStatus.SC_UNPROCESSABLE_ENTITY)//422 Unprocessable Entity请求格式正确，但是由于含有语义错误，无法响应
  			rst = "422 Unprocessable Entity!" ;
  		else if(statusCode == HttpStatus.SC_LOCKED)//423 Locked当前资源被锁定
  			rst = "423 Locked!" ;
  		else if(statusCode == HttpStatus.SC_FAILED_DEPENDENCY)//424 Failed Dependency由于之前的某个请求发生的错误，导致当前请求失败
  			rst = "424 Failed Dependency!" ;
  		/** 5xx服务器错误 **/
  		else if(statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR)//500 Internal Server Error服务器内部错误
  			rst = "500 服务器内部错误!" ;
  		else if(statusCode == HttpStatus.SC_NOT_IMPLEMENTED)//501 Not Implemented服务器不支持当前请求所需要的某个功能
  			rst = "501 Not Implemented!" ;
  		else if(statusCode == HttpStatus.SC_BAD_GATEWAY)//502 Bad Gateway作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应
  			rst = "502 Bad Gateway!" ;
  		else if(statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE)//503 Service Unavailable服务不可用
  			rst = "503 服务不可用!" ;
  		else if(statusCode == HttpStatus.SC_GATEWAY_TIMEOUT)//504 Gateway Timeout作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应
  			rst = "504 Gateway Timeout!" ;
  		else if(statusCode == HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED)//505 HTTP Version Not Supported服务器不支持，或者拒绝支持在请求中使用的HTTP版本
  			rst = "505 HTTP Version Not Supported!" ;
  		else if(statusCode == HttpStatus.SC_INSUFFICIENT_STORAGE)//507 Insufficient Storage服务器无法存储完成请求所必须的内容
  			rst = "507 Insufficient Storage!" ;
  		else //其他错误
  			rst = "其他错误!" ;
  		
  		return rst ;
  	}
}
