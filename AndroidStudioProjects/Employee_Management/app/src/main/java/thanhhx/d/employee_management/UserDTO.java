package thanhhx.d.employee_management;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
    private String userId;
    private String username;
    private String password;
    private String fullname;
    private String role;
    // 0: user // 1: Manager // 2:admin
}
