import jakarta.servlet.http.HttpSession
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component //빈객체는 스프링이 생성해서 주입해주는것 (스프링이생성한거니 딱 한번 생성)
class AuthCheckAspect(private val session: HttpSession) {
    private val logger = LoggerFactory.getLogger(AuthCheckAspect::class.java)

    // 의미 없는 그런
    companion object {
        private const val SESSION_USER_ID = "userId"
    }

    // @LoginRequired 붙은 메서드만 aop 적용
    @Before("@annotation(com.shopping.study.user.aspect.LoginRequired)")
    fun checkLogin(joinPoint: JoinPoint) {
        val loggedInUser = session.getAttribute("SESSION_USER_ID")
        val methodName = joinPoint.signature.name
        val className = joinPoint.target::class.simpleName

        if (loggedInUser == null) {
            logger.warn("로그인 필요 [클래스: $className, 메서드: $methodName]")
            throw UnauthorizedException("로그인이 필요합니다.")
        } else {
            logger.info("로그인 확인 [클래스: $className, 메서드: $methodName]")
        }
    }
}

// 예외 클래스 정의
class UnauthorizedException(message: String) : RuntimeException(message)