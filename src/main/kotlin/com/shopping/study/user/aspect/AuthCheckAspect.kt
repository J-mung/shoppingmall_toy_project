import jakarta.servlet.http.HttpSession
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthCheckAspect(private val session: HttpSession) {

    @Pointcut("execution(* com.shopping.study.*.controller.*.*(..)) " +
            "&& !execution(* com.shopping.study.user.controller.UserController.*(..)) " +
            "&& !execution(* com.shopping.study.product.controller.ProductController.*(..))")  // 🔥 상품 컨트롤러 제외
    fun requireLogin() {}

    // api 요청 실행전 로그인 여부 자동 검사
    @Before("requireLogin()")
    fun checkLogin() {
        val loggedInUser = session.getAttribute("userId")
        if (loggedInUser == null) {
            throw IllegalStateException("로그인이 필요합니다.")
        }
    }
}
