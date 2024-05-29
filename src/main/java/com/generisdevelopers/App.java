package com.generisdevelopers;

import java.util.UUID;

import com.generisdevelopers.stringmetrics.StringMetricsProcess;
import com.generisdevelopers.stringmetrics.StringMetricsRequest;
import com.generisdevelopers.stringmetrics.StringMetricsResponse;
import com.generisdevelopers.stringmetrics.StringMetricsRequest.StringMetricsRequestBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String requestId = UUID.randomUUID().toString();

        String sentenceA = "Nana Kwame";

        String sentenceB = "Nana Kwame";

        StringMetricsRequest stringMetricsRequest = new StringMetricsRequestBuilder()
        .setRequestId(requestId)
        .setSentenceB(sentenceA)
        .setSentenceB(sentenceB)
        .build();

        StringMetricsResponse response = StringMetricsProcess.init(stringMetricsRequest).process();

        float score = response.getScore();

        System.out.println("score computed for sentence A : \""+ sentenceA +"\" and sentence B : \""+ sentenceB + "\" : "+ score);
    }
}
