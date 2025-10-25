package com.cb.common.encipher;
import com.cb.common.encipher.service.IEncipherService;
import com.cb.common.encipher.service.impl.CloudEncipherServiceImpl;
import com.cb.common.encipher.service.impl.LocalEncipherServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EncipherConfig {

    @Value("${encipher.usage-type}")
    private String usageType ;
    /**
     * 加密算法配置
     * @return
     */
    @Bean
    @Primary
    public IEncipherService initCryptoConfig() {
        if("local".equals(usageType)) {
            return new LocalEncipherServiceImpl();
        }
        return new CloudEncipherServiceImpl();
    }
}
