package kr.or.ddit.conf;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
@Component
public class TimingAspect {

    @Around("@annotation(TrackTime)")
    public Object time(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        try {
            return pjp.proceed();
        } finally {
            long elapsedNs = System.nanoTime() - start;
            double ms = elapsedNs / 1_000_000.0;
            String sig = pjp.getSignature().toShortString();
            // 원하는 로거로 기록
            log.info("[Timing] {} took {} ms", sig, String.format("%.3f", ms));
        }
    }
}
