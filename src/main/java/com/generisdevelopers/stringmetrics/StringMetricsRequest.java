package com.generisdevelopers.stringmetrics;

import java.util.UUID;

public class StringMetricsRequest {

    String requestId;

    String sentenceA;

    String sentenceB;

    StringMetricAlgorithm algorithm;

    StringMetricsRequest(){}

    public String getRequestId(){
        return this.requestId;
    }

    public String getSentenceA(){
        return this.sentenceA;
    }

    public String getSentenceB(){
        return this.sentenceB;
    }

    public StringMetricAlgorithm getAlgorithm(){
        return this.algorithm;
    }

    @Override
    public String toString(){
        return "[ request id : "+ this.requestId +" , sentence A: "+ this.sentenceA + ", sentence B: "+ this.sentenceB +" ]";
    }

    StringMetricsRequest(String sentenceA, String sentenceB){
        this.sentenceA = sentenceA;
        this.sentenceB = sentenceB;
        this.requestId = UUID.randomUUID().toString();
    }

    StringMetricsRequest(String requestId, String sentenceA, String sentenceB){
        this.sentenceA = sentenceA;
        this.sentenceB = sentenceB;
        this.requestId = requestId;
    }

    public static class StringMetricsRequestBuilder{

        String requestId;

        String sentenceA;

        String sentenceB;

        StringMetricAlgorithm algorithm;

        public StringMetricsRequestBuilder(){}

        public StringMetricsRequestBuilder setRequestId(String requestId){
            this.requestId = requestId;
            return this;
        }

        public StringMetricsRequestBuilder setSentenceA(String sentenceA){
            this.sentenceA = sentenceA;
            return this;
        }

        public StringMetricsRequestBuilder setSentenceB(String sentenceB){
            this.sentenceB = sentenceB;
            return this;
        }

        public StringMetricsRequestBuilder setAlgorithm(StringMetricAlgorithm algorithm){
            this.algorithm = algorithm;
            return this;
        }

        public StringMetricsRequest build(){
            return new StringMetricsRequest(this.requestId, this.sentenceA, this.sentenceB);
        }
    }
}
