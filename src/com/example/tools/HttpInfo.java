package com.example.tools;

import org.apache.http.HttpStatus;

public class HttpInfo {
  //Http״̬��
  	public static String httpStatus(int statusCode){
  		String rst = "" ;
  		/**1xx��Ϣ**/
  		if(statusCode == HttpStatus.SC_CONTINUE)//100 Continue�ͻ���Ӧ��������������
  			rst = "100 ���������ڽ�������!" ;
  		else if(statusCode == HttpStatus.SC_SWITCHING_PROTOCOLS)//101 Switching Protocols
  			rst = "101 Switching Protocols!" ;
  		else if(statusCode == HttpStatus.SC_PROCESSING)//102 Processing����������ִ��
  			rst = "102  ����������ִ��!" ;
  		/**2xx�ɹ�**/
  		else if(statusCode == HttpStatus.SC_OK)//200 OK�����ѳɹ�
  			rst = "200 ����ɹ�!" ;
  		else if(statusCode == HttpStatus.SC_CREATED)//201 Created�����Ѿ���ʵ��
  			rst = "201 �����Ѿ�ʵ��!" ;
  		else if(statusCode == HttpStatus.SC_ACCEPTED)//202 Accepted�������ѽ������󣬵���δ����
  			rst = "202 �������ѽ������󣬵���δ����!" ;
  		else if(statusCode == HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION)//203 Non-Authoritative Information�������ѳɹ�����������
  			rst = "203 �������ѳɹ�����������!" ;
  		else if(statusCode == HttpStatus.SC_NO_CONTENT)//204 No Content�������ɹ����������󣬵�����Ҫ�����κ�ʵ�����ݣ�����ϣ�����ظ����˵�Ԫ��Ϣ��
  			rst = "204 �������ɹ����������󣬵�����Ҫ�����κ�ʵ������!" ;
  		else if(statusCode == HttpStatus.SC_RESET_CONTENT)//205 Reset Content�������ɹ�������������û�з����κ�����
  			rst = "205 �������ɹ�������������û�з����κ�����!" ;
  		else if(statusCode == HttpStatus.SC_PARTIAL_CONTENT)//206 Partial Content�������Ѿ��ɹ������˲���GET����
  			rst = "206 �������Ѿ��ɹ������˲���GET����!" ;
  		else if(statusCode == HttpStatus.SC_MULTI_STATUS)//207 Multi-Status֮�����Ϣ�彫��һ��XML��Ϣ
  			rst = "207 ֮�����Ϣ�彫��һ��XML��Ϣ!" ;
  		/** 3xx�ض��� **/
  		else if(statusCode == HttpStatus.SC_MULTIPLE_CHOICES)//300 Multiple Choices���������Դ��һϵ�пɹ�ѡ��Ļ�����Ϣ��ÿ�������Լ��ض��ĵ�ַ�������������������Ϣ
  			rst = "300 Multiple Choices!" ;
  		else if(statusCode == HttpStatus.SC_MOVED_PERMANENTLY)//301 Moved Permanently���������Դ�������ƶ�����λ�ã����ҽ����κζԴ���Դ�����ö�Ӧ��ʹ�ñ���Ӧ���ص����ɸ�URI֮һ
  			rst = "301 Moved Permanently!" ;
  		else if(statusCode == HttpStatus.SC_SEE_OTHER)//303 See Other��Ӧ��ǰ�������Ӧ��������һ��URI�ϱ��ҵ������ҿͻ���Ӧ������GET�ķ�ʽ�����Ǹ���Դ
  			rst = "303 See Other!" ;
  		else if(statusCode == HttpStatus.SC_NOT_MODIFIED)//304 Not Modified����ͻ��˷�����һ����������GET�����Ҹ������ѱ��������ĵ������ݣ����ϴη����������߸����������������û�иı䣬�������Ӧ���������״̬��
  			rst = "304 Not Modified!" ;
  		else if(statusCode == HttpStatus.SC_USE_PROXY)//305 Use Proxy���������Դ����ͨ��ָ���Ĵ�����ܱ�����
  			rst = "305 Use Proxy!" ;
  		else if(statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)//307 Temporary Redirect�������Դ������ʱ�Ӳ�ͬ��URI��Ӧ����
  			rst = "307 Temporary Redirect!" ;
  		/** 4xx������� **/
  		else if(statusCode == HttpStatus.SC_BAD_REQUEST)//400 Bad Request���ڰ����﷨���󣬵�ǰ�����޷������������
  			rst = "400 Bad Request�Ƿ�����!" ;
  		else if(statusCode == HttpStatus.SC_UNAUTHORIZED)//401 Unauthorized��ǰ������Ҫ�û���֤
  			rst = "401 Unauthorizedδ��Ȩ!" ;
  		else if(statusCode == HttpStatus.SC_FORBIDDEN)//403 Forbidden�������ܾ���Ӧ
  			rst = "403 �������ܾ���Ӧ!" ;
  		else if(statusCode == HttpStatus.SC_NOT_FOUND)//404 Not Found������δ�ҵ�
  			rst = "404 ������δ�ҵ�!" ;
  		else if(statusCode == HttpStatus.SC_METHOD_NOT_ALLOWED)//405 Method Not Allowed���󷽷����ܱ�����������Ӧ����Դ
  			rst = "405 ���󷽷����ܱ�����������Ӧ����Դ!" ;
  		else if(statusCode == HttpStatus.SC_NOT_ACCEPTABLE)//406 Not Acceptable�޷�������Ӧʵ��
  			rst = "406 �޷�������Ӧʵ��!" ;
  		else if(statusCode == HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED)//407 Proxy Authentication Required��401��Ӧ���ƣ�ֻ�����ͻ��˱����ڴ���������Ͻ��������֤
  			rst = "407 �ͻ��˱����ڴ���������Ͻ��������֤!" ;
  		else if(statusCode == HttpStatus.SC_REQUEST_TIMEOUT)//408 Request Timeout����ʱ
  			rst = "408 ����ʱ!" ;
  		else if(statusCode == HttpStatus.SC_CONFLICT)//409 Conflict���������Դ�ĵ�ǰ״̬֮����ڳ�ͻ�������޷����
  			rst = "409 ��Դ��ͻ�������޷����!" ;
  		else if(statusCode == HttpStatus.SC_GONE)//410 Gone���������Դ�ڷ��������Ѿ����ٿ��ã�����û���κ���֪��ת����ַ
  			rst = "410 ��Դ������!" ;
  		else if(statusCode == HttpStatus.SC_LENGTH_REQUIRED)//411 Length Required�������ܾ���û�ж���Content-Lengthͷ������½�������
  			rst = "411 Length Required!" ;
  		else if(statusCode == HttpStatus.SC_PRECONDITION_FAILED)//412 Precondition Failed����������֤�������ͷ�ֶ��и����Ⱦ�����ʱ��û���������е�һ������
  			rst = "412 Precondition Failed!" ;
  		else if(statusCode == HttpStatus.SC_REQUEST_TOO_LONG)//413 Request Entity Too Large�������ܾ�����ǰ������Ϊ�������ύ��ʵ�����ݴ�С�����˷�����Ը������ܹ�����ķ�Χ
  			rst = "413 Request Entity Too Large!" ;
  		else if(statusCode == HttpStatus.SC_REQUEST_URI_TOO_LONG)//414 Request-URI Too Long�����URI���ȳ����˷������ܹ����͵ĳ��ȣ���˷������ܾ��Ը������ṩ����
  			rst = "414 Request-URI Too Long!" ;
  		else if(statusCode == HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)//415 Unsupported Media Type���ڵ�ǰ����ķ��������������Դ���������ύ��ʵ�岢���Ƿ���������֧�ֵĸ�ʽ��������󱻾ܾ���
  			rst = "415 Unsupported Media Type!" ;
  		else if(statusCode == HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE)//416 Requested Range Not Satisfiable��������а�����Range����ͷ������Range��ָ�����κ����ݷ�Χ���뵱ǰ��Դ�Ŀ��÷�Χ���غϣ�ͬʱ��������û�ж���If-Range����ͷ����ô��������Ӧ������416״̬��
  			rst = "416 Requested Range Not Satisfiable!" ;
  		else if(statusCode == HttpStatus.SC_EXPECTATION_FAILED)//417 Expectation Failed������ͷExpect��ָ����Ԥ�������޷������������㣬���������������һ��������������������Ե�֤��֤���ڵ�ǰ·�ɵ���һ���ڵ��ϣ�Expect�������޷�������
  			rst = "417 Expectation Failed!" ;
  		else if(statusCode == HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE)//419 
  			rst = "419 !" ;
  		else if(statusCode == HttpStatus.SC_UNPROCESSABLE_ENTITY)//422 Unprocessable Entity�����ʽ��ȷ���������ں�����������޷���Ӧ
  			rst = "422 Unprocessable Entity!" ;
  		else if(statusCode == HttpStatus.SC_LOCKED)//423 Locked��ǰ��Դ������
  			rst = "423 Locked!" ;
  		else if(statusCode == HttpStatus.SC_FAILED_DEPENDENCY)//424 Failed Dependency����֮ǰ��ĳ���������Ĵ��󣬵��µ�ǰ����ʧ��
  			rst = "424 Failed Dependency!" ;
  		/** 5xx���������� **/
  		else if(statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR)//500 Internal Server Error�������ڲ�����
  			rst = "500 �������ڲ�����!" ;
  		else if(statusCode == HttpStatus.SC_NOT_IMPLEMENTED)//501 Not Implemented��������֧�ֵ�ǰ��������Ҫ��ĳ������
  			rst = "501 Not Implemented!" ;
  		else if(statusCode == HttpStatus.SC_BAD_GATEWAY)//502 Bad Gateway��Ϊ���ػ��ߴ������ķ���������ִ������ʱ�������η��������յ���Ч����Ӧ
  			rst = "502 Bad Gateway!" ;
  		else if(statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE)//503 Service Unavailable���񲻿���
  			rst = "503 ���񲻿���!" ;
  		else if(statusCode == HttpStatus.SC_GATEWAY_TIMEOUT)//504 Gateway Timeout��Ϊ���ػ��ߴ������ķ���������ִ������ʱ��δ�ܼ�ʱ�����η�������URI��ʶ���ķ�����������HTTP��FTP��LDAP�����߸���������������DNS���յ���Ӧ
  			rst = "504 Gateway Timeout!" ;
  		else if(statusCode == HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED)//505 HTTP Version Not Supported��������֧�֣����߾ܾ�֧����������ʹ�õ�HTTP�汾
  			rst = "505 HTTP Version Not Supported!" ;
  		else if(statusCode == HttpStatus.SC_INSUFFICIENT_STORAGE)//507 Insufficient Storage�������޷��洢������������������
  			rst = "507 Insufficient Storage!" ;
  		else //��������
  			rst = "��������!" ;
  		
  		return rst ;
  	}
}
