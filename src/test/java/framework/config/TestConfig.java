package framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:testing.properties"})
public interface TestConfig extends Config {

    TestConfig cfg = ConfigFactory.create(TestConfig.class, System.getenv(), System.getProperties());

    @org.aeonbits.owner.Config.DefaultValue("chrome")
    String browser();

    String baseUrl();

    String env();

    boolean remote();

    String remoteUrl();
}