package com.generisdevelopers;

import java.util.UUID;

import com.generisdevelopers.stringmetrics.StringMetricsProcess;
import com.generisdevelopers.stringmetrics.StringMetricsRequest;
import com.generisdevelopers.stringmetrics.StringMetricsResponse;
import com.generisdevelopers.stringmetrics.StringMetricsRequest.StringMetricsRequestBuilder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StringMetricsTest extends TestCase {

    public static Test suite()
    {
        return new TestSuite( StringMetricsTest.class );
    }


    public void testApp()
    {
        assertTrue( true );
    }

    public void testStringMetricsBuilder(){
        String requestId = UUID.randomUUID().toString();
        String sentenceA = "Boy";
        String sentenceB = "boy";

        StringMetricsRequest stringMetricsRequest = new StringMetricsRequestBuilder()
        .setRequestId(requestId)
        .setSentenceA(sentenceA)
        .setSentenceB(sentenceB)
        .build();

        assertNotNull(stringMetricsRequest);

        System.out.println(stringMetricsRequest.toString());

        assertNotNull(stringMetricsRequest.getRequestId());
        assertNotNull(stringMetricsRequest.getSentenceA());
        assertNotNull(stringMetricsRequest.getSentenceB());
    }

    public void testStringMetricsScore(){
        String requestId = UUID.randomUUID().toString();
        String sentenceA = "Boy";
        String sentenceB = "Boy";

        StringMetricsRequest stringMetricsRequest = new StringMetricsRequestBuilder()
        .setRequestId(requestId)
        .setSentenceA(sentenceA)
        .setSentenceB(sentenceB)
        .build();

        StringMetricsResponse response = StringMetricsProcess.init(stringMetricsRequest).process();

        assertNotNull(response);

        System.out.println(" string metrics response : "+ response.toString());

        assertNotNull(response.getScore());
    }


    public void testStringMetricsScore_Not_Match(){
        String requestId = UUID.randomUUID().toString();
        String sentenceA = "Boy";
        String sentenceB = "Nana kwame Appiah";

        StringMetricsRequest stringMetricsRequest = new StringMetricsRequestBuilder()
        .setRequestId(requestId)
        .setSentenceA(sentenceA)
        .setSentenceB(sentenceB)
        .build();

        StringMetricsResponse response = StringMetricsProcess.init(stringMetricsRequest).process();

        assertNotNull(response);

        System.out.println(" string metrics response : "+ response.toString());

        assertTrue(response.getScore() > 0.5f);
    }

    public void testStringMetricsScore_Not_Match_WithSlightVariance(){
        String requestId = UUID.randomUUID().toString();
        String sentenceA = "nana Yaw Appiah";
        String sentenceB = "Nana kwame Appiah";

        StringMetricsRequest stringMetricsRequest = new StringMetricsRequestBuilder()
        .setRequestId(requestId)
        .setSentenceA(sentenceA)
        .setSentenceB(sentenceB)
        .build();

        StringMetricsResponse response = StringMetricsProcess.init(stringMetricsRequest).process();

        assertNotNull(response);

        System.out.println(" string metrics response : "+ response.toString());

        assertTrue(response.getScore() < 0.5f);
    }
}



