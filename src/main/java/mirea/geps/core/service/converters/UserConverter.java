package mirea.geps.core.service.converters;

import mirea.geps.core.dto.User;
import mirea.geps.core.service.db.UserDb;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDb convertEntityToDb(User user) {
        UserDb result = new UserDb();
        copyProperties(user, result);
        return result;
    }

}
