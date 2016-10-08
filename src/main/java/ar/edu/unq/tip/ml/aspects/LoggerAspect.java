package ar.edu.unq.tip.ml.aspects;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

//@Component
@Aspect
public class LoggerAspect {

    private Logger log = Logger.getLogger(getClass());

    //busca todos los metodos publicos del paquete XX y que tengan la annotacion Loggeable
    //  @After("execution(public * ar.edu.unq.tip.ml.*.*(..)) && @annotation(Loggable)")
    //@After("execution(* *(..)) && @annotation(pablin)")
    //@After("execution (@Loggeable public * ar.edu.unq.tip.ml.services.*.*(..))")
    @After("execution(public * ar.edu.unq.tip.ml.services.*.*(..))")
    public void logAfter(JoinPoint pjp) {
        //public void logAfter(JoinPoint pjp, Loggable loggable) {
        log.info(pjp.getSignature().getName() + " called...TESTING");
    }

}