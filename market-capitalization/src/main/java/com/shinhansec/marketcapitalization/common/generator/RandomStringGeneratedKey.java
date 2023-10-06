package com.shinhansec.marketcapitalization.common.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;


public class RandomStringGeneratedKey implements IdentifierGenerator, Configurable {

    private final String format = "%s-%s-%s";

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String pre = RandomStringUtils.randomAlphanumeric(3);
        String mid = RandomStringUtils.randomAlphanumeric(3);
        String post = RandomStringUtils.randomAlphanumeric(3);

        return String.format(this.format, pre, mid, post);
    }

}
