package org.example.util;

import org.junit.Assert;
import org.junit.Test;

public class JdbcUtilTest {
    @Test
    public void testConnection(){
        Assert.assertNotNull(JdbcUtil.createCon());
    }
}
