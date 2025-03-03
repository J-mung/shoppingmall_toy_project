import jakarta.servlet.http.HttpSession
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthCheckAspect(private val session: HttpSession) {
    private val logger = LoggerFactory.getLogger(AuthCheckAspect::class.java)

    // @LoginRequired 붙은 메서드만 aop 적용
    @Before("@annotation(com.shopping.study.user.aspect.LoginRequired)")
    fun checkLogin(joinPoint: org.aspectj.lang.JoinPoint) {
        val loggedInUser = session.getAttribute("userId")
        val methodName = joinPoint.signature.name
        val className = joinPoint.target::class.simpleName

        if (loggedInUser == null) {
            logger.warn("로그인 필요 [클래스: $className, 메서드: $methodName]")
            throw IllegalStateException("로그인이 필요합니다.")
        } else {
            logger.info("로그인 확인 [클래스: $className, 메서드: $methodName]")
        }
    }
}
