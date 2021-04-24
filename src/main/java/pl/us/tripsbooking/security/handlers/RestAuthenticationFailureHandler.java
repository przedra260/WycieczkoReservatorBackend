package pl.us.tripsbooking.security.handlers;

import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.security.model.UnauthorizedTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        if (e instanceof LockedException)
            response.getWriter().write("\"message\": \"" + UnauthorizedTypes.ACCOUNT_BLOCKED + "\"");
        else if (e instanceof CredentialsExpiredException)
            response.getWriter().write("\"message\": \"" + UnauthorizedTypes.CREDENTIALS_EXPIRED + "\"");
        else
            response.getWriter().write("\"message\": \"" + UnauthorizedTypes.ACCESS_DENIED + "\"");

        return;
    }
}