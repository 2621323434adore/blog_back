package org.xirang.Service;

import org.springframework.stereotype.Service;

@Service
public interface UserTokenService {
    public boolean isAuthenticated(String Token);
}
