import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserLogInTest {
    private UserModel model;

    @Before
    public void setUp() {
        model = new UserModel();
    }

    @Test
    public void testAuthenticateSuccess() {
        // Given
        String username = "admin";
        String password = "12345";
        String role = "Customer";

        // When
        model.authenticate(username, password, role);

        // Then
        assertTrue("User should be authenticated with correct credentials", model.isAuthenticated());
    }

    @Test
    public void testAuthenticateFailure() {
        // Given
        String username = "wrongUser";
        String password = "wrongPass";
        String role = "Customer";

        // When
        model.authenticate(username, password, role);

        // Then
        assertFalse("User should not be authenticated with incorrect credentials", model.isAuthenticated());
    }
}


    // Add more tests for other scenarios like invalid roles, security questions, etc.


