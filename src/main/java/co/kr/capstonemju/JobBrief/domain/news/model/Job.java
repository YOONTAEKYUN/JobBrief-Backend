package co.kr.capstonemju.JobBrief.domain.news.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum Job {
    PRODUCTION_QUALITY("생산/품질",0),
    IT_DEVELOPMENT("IT개발",1),
    HUMAN_AFFAIRS("인사/총무",2),
    FINANCE_ACCOUNTING("재무/회계/금융",3),
    STRATEGY_PLANNING("전략/기획",4),
    SALES_MANAGEMENT("영업/영업관리",5),
    MARKETING_MERCHANDISER("마케팅/MD",6);

    private final String jobName;
    private final int jobNumber;

    Job(String jobName,int jobNumber){
        this.jobName = jobName;
        this.jobNumber = jobNumber;
    }

    @JsonCreator
    public static Job of(String jobName){
        return Arrays.stream(Job.values())
                .filter( job -> Objects.nonNull(jobName))
                .filter( job -> Objects.equals(job.getJobName(),jobName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
