package org.xmlrpc.android;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * You can also pass as a parameter any object implementing XMLRPCSerializable interface. In this
 * case your object overrides getSerializable() telling how to serialize to XMLRPC protocol
 * </p>
 */

public class XMLRPCClient extends XMLRPCCommon {

    private static final String TAG = "WsAndroidXmlRPC";
    public static boolean isPrint = false;

    private HttpClient client;
    private HttpPost postMethod;
    private HttpParams httpParams;

    private static final int REQUEST_TIMEOUT = 10 * 1000;//设置请求超时10秒钟
    private static final int SO_TIMEOUT = 15 * 1000;  //设置等待数据超时时间10秒钟

    /**
     * XMLRPCClient constructor. Creates new instance based on server URI
     *
     * @param "XMLRPC" server URI
     */
    public XMLRPCClient(URI uri) {

        LogUtil.d(TAG, uri.toString());
        postMethod = new HttpPost(uri);
        postMethod.addHeader("Content-Type", "text/xml");

        // WARNING
        // I had to disable "Expect: 100-Continue" header since I had
        // two second delay between sending http POST request and POST body
        httpParams = postMethod.getParams();
        HttpProtocolParams.setUseExpectContinue(httpParams, false);

        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore
                    .getDefaultType());

            trustStore.load(null, null);
            SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);

            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  //允许所有主机的验证

            HttpParams params = new BasicHttpParams();

            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params,
                    HTTP.DEFAULT_CONTENT_CHARSET);
            HttpProtocolParams.setUseExpectContinue(params, true);

            // 设置连接管理器的超时
            ConnManagerParams.setTimeout(params, REQUEST_TIMEOUT);
            // 设置连接超时
            HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
            // 设置socket超时
            HttpConnectionParams.setSoTimeout(params, REQUEST_TIMEOUT);

            // 设置http https支持
            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 80));
            schReg.register(new Scheme("https", sf, 443));

            ClientConnectionManager conManager = new ThreadSafeClientConnManager(
                    params, schReg);

            client = new DefaultHttpClient(conManager, params);

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        //HTTPS的代码写着<------------------------------------------------------------------------------
        if(client == null){
            client = new DefaultHttpClient();
        }

        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, REQUEST_TIMEOUT);

    }

    /**
     * Convenience constructor. Creates new instance based on server String address
     *
     * @param "XMLRPC" server address
     */
    public XMLRPCClient(String url) {
        this(URI.create(url));
    }

    /**
     * Convenience XMLRPCClient constructor. Creates new instance based on server URL
     *
     * @param "XMLRPC" server URL
     */
    public XMLRPCClient(URL url) {
        this(URI.create(url.toExternalForm()));
    }

    /**
     * Convenience constructor. Creates new instance based on server String address
     *
     * @param "XMLRPC" server address
     * @param "HTTP    "  Server - Basic Authentication - Username
     * @param "HTTP"   Server - Basic Authentication - Password
     */
    public XMLRPCClient(URI uri, String username, String password) {
        this(uri);

        ((DefaultHttpClient) client).getCredentialsProvider().setCredentials(
                new AuthScope(uri.getHost(), uri.getPort(), AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(username, password));
    }

    /**
     * Convenience constructor. Creates new instance based on server String address
     *
     * @param "XMLRPC" server address
     * @param "HTTP    "  Server - Basic Authentication - Username
     * @param "HTTP"   Server - Basic Authentication - Password
     */
    public XMLRPCClient(String url, String username, String password) {
        this(URI.create(url), username, password);
    }

    /**
     * Convenience constructor. Creates new instance based on server String address
     *
     * @param "XMLRPC" server url
     * @param "HTTP"   Server - Basic Authentication - Username
     * @param "HTTP"   Server - Basic Authentication - Password
     */
    public XMLRPCClient(URL url, String username, String password) {
        this(URI.create(url.toExternalForm()), username, password);
    }

    /**
     * Sets basic authentication on web request using plain credentials
     *
     * @param username The plain text username
     * @param password The plain text password
     */
    public void setBasicAuthentication(String username, String password) {
        ((DefaultHttpClient) client).getCredentialsProvider().setCredentials(
                new AuthScope(postMethod.getURI().getHost(), postMethod.getURI().getPort(),
                        AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(username, password));
    }

    /**
     * Call method with optional parameters. This is general method.
     * If you want to call your method with 0-8 parameters, you can use more
     * convenience call() methods
     *
     * @param method name of method to call
     * @param params parameters to pass to method (may be null if method has no parameters)
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    @SuppressWarnings("unchecked")
    public Object callEx(String method, Object[] params) throws XMLRPCException {
        try {
            // prepare POST body
            String body = methodCall(method, params);

            LogUtil.d(TAG, "--->POST-XML:" + body);

            HttpEntity entity = new StringEntity(body);
            postMethod.setEntity(entity);

            postMethod.setHeader("Content-Type","text/xml");
            postMethod.setHeader("Accept","application/xml");
            if(!isPrint){
                postMethod.setHeader("Accept-Encoding", "gzip");
            }

            HttpResponse response = client.execute(postMethod);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new XMLRPCException("HTTP status code: " + statusCode + " != " + HttpStatus.SC_OK);
            }


            XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();

            Reader reader = null;

            if(isPrint){
                entity = response.getEntity();
                if (LogUtil.isDebug) {
                    String result = EntityUtils.toString(entity);
                    LogUtil.d(TAG, "--->RETURN-XML:" + result);
                    reader = new InputStreamReader(new BufferedInputStream(new ByteArrayInputStream(result.getBytes("ISO-8859-1"))));

                } else {
                    reader = new InputStreamReader(new BufferedInputStream(entity.getContent()));
                }
            }
            else {
                reader = getJsonStringFromGZIP(response);
            }


            pullParser.setInput(reader);

            // lets start pulling...
            pullParser.nextTag();
            pullParser.require(XmlPullParser.START_TAG, null, Tag.METHOD_RESPONSE);

            pullParser.nextTag(); // either Tag.PARAMS (<params>) or Tag.FAULT (<fault>)
            String tag = pullParser.getName();

            if (tag.equals(Tag.PARAMS)) {
                // normal response
                pullParser.nextTag(); // Tag.PARAM (<param>)
                pullParser.require(XmlPullParser.START_TAG, null, Tag.PARAM);
                pullParser.nextTag(); // Tag.VALUE (<value>)
                // no parser.require() here since its called in XMLRPCSerializer.deserialize() below

                // deserialize result
                Object obj = iXMLRPCSerializer.deserialize(pullParser);
                entity.consumeContent();
                return obj;
            } else if (tag.equals(Tag.FAULT)) {
                // fault response
                pullParser.nextTag(); // Tag.VALUE (<value>)
                // no parser.require() here since its called in XMLRPCSerializer.deserialize() below

                // deserialize fault result
                Map<String, Object> map = (Map<String, Object>) iXMLRPCSerializer.deserialize(pullParser);
                String faultString = (String) map.get(Tag.FAULT_CODE);
                int faultCode = 0;
                entity.consumeContent();
                throw new XMLRPCException(faultString);
            } else {
                entity.consumeContent();
                throw new XMLRPCException("Bad tag <" + tag + "> in XMLRPC response - neither <params> nor <fault>");
            }
        } catch (XMLRPCException e) {
            // catch & propagate XMLRPCException/XMLRPCFault
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            // wrap any other Exception(s) around XMLRPCException
            throw new XMLRPCException(e);
        }
    }

    private String methodCall(String method, Object[] params)
            throws IllegalArgumentException, IllegalStateException, IOException {
        StringWriter bodyWriter = new StringWriter();
        serializer.setOutput(bodyWriter);
        serializer.startDocument(null, null);
        serializer.startTag(null, Tag.METHOD_CALL);
        // set method name
        serializer.startTag(null, Tag.METHOD_NAME).text(method).endTag(null, Tag.METHOD_NAME);

        serializeParams(params);

        serializer.endTag(null, Tag.METHOD_CALL);
        serializer.endDocument();

        return bodyWriter.toString();
    }

    /**
     * Convenience method call with one parameter
     *
     * @param method name of method to call
     * @param p0     method's parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0) throws XMLRPCException {
        Object[] params = {
                p0,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with two parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1) throws XMLRPCException {
        Object[] params = {
                p0, p1,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with three parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @param p2     method's 3rd parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1, Object p2) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with four parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @param p2     method's 3rd parameter
     * @param p3     method's 4th parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1, Object p2, Object p3) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with five parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @param p2     method's 3rd parameter
     * @param p3     method's 4th parameter
     * @param p4     method's 5th parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with six parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @param p2     method's 3rd parameter
     * @param p3     method's 4th parameter
     * @param p4     method's 5th parameter
     * @param p5     method's 6th parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with seven parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @param p2     method's 3rd parameter
     * @param p3     method's 4th parameter
     * @param p4     method's 5th parameter
     * @param p5     method's 6th parameter
     * @param p6     method's 7th parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6,
        };
        return callEx(method, params);
    }

    /**
     * Convenience method call with eight parameters
     *
     * @param method name of method to call
     * @param p0     method's 1st parameter
     * @param p1     method's 2nd parameter
     * @param p2     method's 3rd parameter
     * @param p3     method's 4th parameter
     * @param p4     method's 5th parameter
     * @param p5     method's 6th parameter
     * @param p6     method's 7th parameter
     * @param p7     method's 8th parameter
     * @return deserialized method return value
     * @throws XMLRPCException
     */
    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6, p7,
        };
        return callEx(method, params);
    }

    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6, p7, p8
        };
        return callEx(method, params);
    }


    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9
        };
        return callEx(method, params);
    }


    public Object call11(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
                         Object p6, Object p7, Object p8, Object p9, Object p10) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10
        };
        return callEx(method, params);
    }

    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
                         Object p6, Object p7, Object p8, Object p9, Object p10) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10
        };
        return callEx(method, params);
    }

    public Object call(String method, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
                         Object p6, Object p7, Object p8, Object p9, Object p10, Object p11) throws XMLRPCException {
        Object[] params = {
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11
        };
        return callEx(method, params);
    }


    public InputStreamReader getJsonStringFromGZIP(HttpResponse response) {
        InputStreamReader reader = null;
        try {
            InputStream is = response.getEntity().getContent();
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            // 判断是否是GZIP格式
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b) { LogUtil.d("HttpTask", " use GZIPInputStream  ");
                is = new GZIPInputStream(bis);
            } else {
                LogUtil.d("HttpTask", " not use GZIPInputStream");
                is = bis;
            }
            reader = new InputStreamReader(is);
        } catch (Exception e) {
            LogUtil.e("HttpTask", e.toString(),e);
        }

        return reader;
    }

    private int getShort(byte[] data) {
        return (int)((data[0]<<8) | data[1]&0xFF);
    }

}
