


import com.faceless.Application;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.http.HttpResponse;


public class HttpServerTest {
//    private static final String URL = "http://localhost:8080";
//    private static final HttpClientBuilder BUILDER = HttpClientBuilder.create();
//
//    @BeforeClass
//    public static void setUpClass() throws Throwable{
//        ApplicationRunner.run(new Application());
//    }
//    @Test
//    public void requestOfLogInShouldBeOK() throws Exception {
//        // Don't know how to include name in correct way
//        HttpUriRequest request = new HttpPost(URL + "/login?login=vasya&password=123");
//        CloseableHttpResponse response = BUILDER.build().execute(request);
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
//    @Test
//    public void requestOfLogInWhenAlreadyLoggedInShouldBeOK() throws Exception {
//        CloseableHttpResponse response = BUILDER.build()
//                .execute( new HttpPost(URL + "/login?login=vasya&password=123"));
//        CloseableHttpResponse responseSecond = BUILDER.build()
//                .execute(new HttpPost(URL + "/login?login=vova&password=123"));
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(),
//                responseSecond.getStatusLine().getStatusCode(),
//                HttpStatus.SC_OK);
//    }
//    @Test
//    public void requestOfLogInShouldBeNotOK() throws Exception {
//        HttpUriRequest request = new HttpPost(URL + "/login");
//        CloseableHttpResponse response = BUILDER.build().execute(request);
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
//    }
//
//
//    @Test
//    public void requestOfLogOutShouldBeOK() throws Exception {
//        HttpUriRequest request = new HttpPost(URL + "/logout");
//        CloseableHttpResponse response = BUILDER.build().execute(request);
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
//
//    @Test
//    @Ignore
//    public void requestOfLogOutWhenAlreadyLoggedOutShouldBeConflict() throws Exception {
//        CloseableHttpResponse response = BUILDER.build()
//                .execute( new HttpPost(URL + "/login?login=vasya&password=123"));
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(),
//                HttpStatus.SC_OK);
//
//        CloseableHttpResponse responseSecond = BUILDER.build()
//                .execute(new HttpPost(URL + "/login?login=vovaa&password=123"));
//
//        Assert.assertEquals(responseSecond.getStatusLine().getStatusCode(),
//                HttpStatus.SC_CONFLICT);
//    }
//
//    @Test
//    public void requestOfMainGet() throws Exception {
//        HttpUriRequest request = new HttpGet(URL + "/");
//        CloseableHttpResponse response = BUILDER.build().execute(request);
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
//
//    @Test
//    public void postRequestOfOrderVmPageShouldBeOk() throws Exception {
//        HttpUriRequest request = new HttpGet(URL + "/vmorderpage");
//        CloseableHttpResponse response = BUILDER.build().execute(request);
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
//    @Test
//    @Ignore
//    public void postRequestOfLookPageShouldBeOk() throws Exception {
//        HttpUriRequest request = new HttpGet(URL + "/vmlookpage");
//        CloseableHttpResponse response = BUILDER.build().execute(request);
//
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
//
//    @Test
//    @Ignore
//    public void pullRequestOfCreateVMShouldBeOk() throws Exception {
//        CloseableHttpResponse response = BUILDER.build()
//                .execute(new HttpPost(URL + "/login?login=vasya&password=123"));
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//
//
//        String order = "/ordervm?owner=Nonidentified%" +
//                "20user&vm_name=fff" +
//                "&cpu_vendor=amd&" +
//                "cpu_frequency=2mhz&" +
//                "cpu_core_count=2" +
//                "&ram_volume=35" +
//                "&hdd_volume=644&" +
//                "monitor_enabled=1" +
//                "&os=linux-debian";
//        HttpUriRequest request = new HttpPut(URL + order);
//        CloseableHttpResponse responseRemove = BUILDER.build().execute(request);
//        Assert.assertEquals(responseRemove.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
//    @Test
//    @Ignore
//    public void pullRequestOfCreateVMShouldBeNotOkCauseOfWrongVM() throws Exception {
//        CloseableHttpResponse response = BUILDER.build()
//                .execute(new HttpPost(URL + "/login?login=vasya&password=123"));
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//
//        HttpUriRequest request = new HttpPut(
//                URL +
//                "/ordervm?owner=Nonidentified%" +
//                "20user&vm_name=fff" +
//                "&cpu_vendor=amude&" + // here is mistake
//                "cpu_frequency=2mhz&" +
//                "cpu_core_count=2" +
//                "&ram_volume=35" +
//                "&hdd_volume=644&" +
//                "monitor_enabled=1" +
//                "&os=linux-debian");
//
//        CloseableHttpResponse responseRemove = BUILDER.build().execute(request);
//        Assert.assertEquals(responseRemove.getStatusLine().getStatusCode(), HttpStatus.SC_CONFLICT);
//    }
//
//    @Test
//    public void getRequestOfVMlListShouldBeOk() throws Exception {
//        CloseableHttpResponse response = BUILDER.build()
//                .execute(new HttpPost(URL + "/login?login=vasya&password=123"));
//        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//
//        CloseableHttpResponse responseShow = BUILDER.build()
//                .execute(new HttpGet(URL + "/myvms?login=vasya"));
//        Assert.assertEquals(responseShow.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
//    }
}