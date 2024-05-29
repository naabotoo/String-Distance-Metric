package com.generisdevelopers.stringmetrics;

import static org.simmetrics.builders.StringDistanceBuilder.with;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.simmetrics.StringDistance;
import org.simmetrics.metrics.CosineSimilarity;
import org.simmetrics.metrics.Jaccard;
import org.simmetrics.metrics.Levenshtein;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizers;

public class StringMetricsProcess {

    StringMetricsRequest request;

    public static StringMetricsProcess init(StringMetricsRequest request){
        Objects.requireNonNull(request, "string metrics request is required.");
        return new StringMetricsProcess(request);
    }

    StringMetricsProcess(StringMetricsRequest request){
        this.request = request;
    }

    public StringMetricsResponse process(){
        StringMetricsResponse response = new StringMetricsResponse(request);
        float score = 0f;

        List<RequestError> errors = new ArrayList<>();

        boolean hasSentenceA = this.hasSentenceA();
        if(!hasSentenceA){
            errors.add(new RequestError("sentenceA", "sentence A is required."));
        }

        boolean hasSentenceB = this.hasSentenceB();
        if(!hasSentenceB){
            errors.add(new RequestError("sentenceB", "sentence B is required."));
        }

        boolean hasErrors = errors.isEmpty();

        if(hasErrors){
            StringMetricAlgorithm algorithm = this.request.getAlgorithm();

            if(algorithm == null){
                algorithm = StringMetricAlgorithm.COSINE_SIMILARITY;
            }

            if(algorithm != null){
            
                switch (algorithm) {
                    case COSINE_SIMILARITY:
                        score = this.consineSimilarity();
                        break;
                    case LAVENSHTIEN_DISTANCE:
                        score = this.lavenshtienDistance();
                    break;
                    case JACCARD:
                        score = this.jaccard();
                    break;
                    default:
                        score = this.consineSimilarity();
                    break;
                }
                
            }
        } else {
            response.setRequestErrors(errors);
        }

        response.setScore(score);

        return response;
    }

    float consineSimilarity(){
        String sentenceA = this.request.getSentenceA();
        String sentenceB = this.request.getSentenceB();

        StringDistance stringMetric = with(new CosineSimilarity<>())
            .simplify(Simplifiers.toLowerCase())
            .tokenize(Tokenizers.whitespace())
            .build();
        
        return stringMetric.distance(sentenceA, sentenceB);
    }

    float lavenshtienDistance(){
        String sentenceA = this.request.getSentenceA();
        String sentenceB = this.request.getSentenceB();

        StringDistance stringMetric = with(new Levenshtein())
        .simplify(Simplifiers.toLowerCase())
        .build();

        return stringMetric.distance(sentenceA, sentenceB);
    }

    float jaccard(){
        String sentenceA = this.request.getSentenceA();
        String sentenceB = this.request.getSentenceB();

        StringDistance stringMetric = with(new Jaccard<String>())
            .simplify(Simplifiers.toLowerCase())
            .tokenize(Tokenizers.whitespace())
            .build();
        
        return stringMetric.distance(sentenceA, sentenceB);
    }

    boolean hasSentenceA(){
        return (this.request.getSentenceA() != null && !this.request.getSentenceA().isEmpty());
    }

    boolean hasSentenceB(){
        return (this.request.getSentenceB() != null && !this.request.getSentenceB().isEmpty());
    }
}
