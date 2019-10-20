package Tests;

import com.faceless.Application;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



public class HttpServerTest {
    private static final String URL = "http://localhost:8080";
    private static final HttpClientBuilder BUILDER = HttpClientBuilder.create();

    @BeforeClass
    public static void setUpClass() throws Throwable{
        ApplicationRunner.run(new Application());
    }
    @Test
    public void requestOfLogInShouldBeOK() throws Exception {
        // Don't know how to include name in correct way
        HttpUriRequest request = new HttpPost(URL + "/login?name=vasya&password=123");
        HttpResponse response = BUILDER.build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }
    @Test
    public void requestOfLogInWhenAlreadyLoggedInShouldBeOK() throws Exception {
        HttpResponse response = BUILDER.build()
                .execute( new HttpPost(URL + "/login?name=vasya&password=123"));
        HttpResponse responseSecond = BUILDER.build()
                .execute(new HttpPost(URL + "/login?name=vova&password=123"));

        Assert.assertEquals(response.getStatusLine().getStatusCode(),
                responseSecond.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
    }
    @Test
    public void requestOfLogInShouldBeNotOK() throws Exception {
        HttpUriRequest request = new HttpPost(URL + "/login");
        HttpResponse response = BUILDER.build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }


    @Test
    public void requestOfLogOutShouldBeOK() throws Exception {
        HttpUriRequest request = new HttpPost(URL + "/logout");
        HttpResponse response = BUILDER.build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void requestOfLogOutWhenAlreadyLoggedOutShouldBeConflict() throws Exception {
        HttpResponse response = BUILDER.build()
                .execute( new HttpPost(URL + "/login?name=vasya"));

        Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);

        HttpResponse responseSecond = BUILDER.build()
                .execute(new HttpPost(URL + "/login?name=vova"));

        Assert.assertEquals(responseSecond.getStatusLine().getStatusCode(),
                HttpStatus.SC_CONFLICT);
    }

    @Test
    public void requestOfMainGet() throws Exception {
        HttpUriRequest request = new HttpGet(URL + "/");
        HttpResponse response = BUILDER.build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void postRequestOfOrderVmPageShouldBeOk() throws Exception {
        HttpUriRequest request = new HttpGet(URL + "/vmorderpage");
        HttpResponse response = BUILDER.build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }
    @Test
    public void postRequestOfLookPageShouldBeOk() throws Exception {
        HttpUriRequest request = new HttpGet(URL + "/vmlookpage");
        HttpResponse response = BUILDER.build().execute(request);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void pullRequestOfCreateVMShouldBeOk() throws Exception {
        HttpResponse response = BUILDER.build()
                .execute(new HttpPost(URL + "/login?name=vasya&password=123"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);


        String order = "/ordervm?owner=Nonidentified%" +
                "20user&vm_name=fff" +
                "&cpu_vendor=amd&" +
                "cpu_frequency=2mhz&" +
                "cpu_core_count=2" +
                "&ram_volume=35" +
                "&hdd_volume=644&" +
                "monitor_enabled=1" +
                "&os=linux-debian";
        HttpUriRequest request = new HttpPut(URL + order);
        HttpResponse responseRemove = BUILDER.build().execute(request);
        Assert.assertEquals(responseRemove.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }
    @Test
    public void pullRequestOfCreateVMShouldBeNotOkCauseOfWrongVM() throws Exception {
        HttpResponse response = BUILDER.build()
                .execute(new HttpPost(URL + "/login?name=vasya&password=123"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        HttpUriRequest request = new HttpPut(
                URL +
                "/ordervm?owner=Nonidentified%" +
                "20user&vm_name=fff" +
                "&cpu_vendor=amude&" + // here is mistake
                "cpu_frequency=2mhz&" +
                "cpu_core_count=2" +
                "&ram_volume=35" +
                "&hdd_volume=644&" +
                "monitor_enabled=1" +
                "&os=linux-debian");

        HttpResponse responseRemove = BUILDER.build().execute(request);
        Assert.assertEquals(responseRemove.getStatusLine().getStatusCode(), HttpStatus.SC_CONFLICT);
    }

    @Test
    public void getRequestOfVMlListShouldBeOk() throws Exception {
        HttpResponse response = BUILDER.build()
                .execute(new HttpPost(URL + "/login?name=vasya&password=123"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        HttpResponse responseShow = BUILDER.build()
                .execute(new HttpGet(URL + "/myvms"));
        Assert.assertEquals(responseShow.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }
}