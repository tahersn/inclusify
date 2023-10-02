package tn.esprit.feedservice.Configuration;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;


/**
 * @author Jozef
 */
@Slf4j
@Component
@Aspect
public class LoggingAspect {
//    @Before(" execution(* tn.esprit.feedservice.services.ContratImpl.*(..)) ")
//    public void logMethodEntry(JoinPoint joinPoint) {
//        String name = joinPoint.getSignature().getName();
//        log.info("In method " + name + " : ");
//    }
//    @After(" execution(* tn.esprit.feedservice.services.ContratImpl.*(..)) ")
//    public void logMethodExit(JoinPoint joinPoint) {
//        String name = joinPoint.getSignature().getName();
//        log.info(" Out of method " + name + " : ");
//    }
}
