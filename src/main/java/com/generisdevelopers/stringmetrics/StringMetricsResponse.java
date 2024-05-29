package com.generisdevelopers.stringmetrics;

import java.util.List;
import java.util.Objects;

public class StringMetricsResponse {
    StringMetricsRequest request;
    Float score;
    StringMetricAlgorithm algorithmApplied;
    List<RequestError> requestErrors;

    public StringMetricsResponse(StringMetricsRequest request){
        Objects.requireNonNull(request, "string metrics request is required.");
        this.request = request;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public StringMetricAlgorithm getAlgorithmApplied() {
        return algorithmApplied;
    }

    public void setAlgorithmApplied(StringMetricAlgorithm algorithmApplied) {
        this.algorithmApplied = algorithmApplied;
    }

    public List<RequestError> getRequestErrors() {
        return requestErrors;
    }

    public void setRequestErrors(List<RequestError> requestErrors) {
        this.requestErrors = requestErrors;
    }

    @Override
    public String toString(){
        return "[ requestId : "+ this.request.getRequestId() +", sentence A: "+ this.request.getSentenceA()+", sentence B: "+ this.request.getSentenceB() +", score : "+ this.getScore() +" ]";
    }
}
