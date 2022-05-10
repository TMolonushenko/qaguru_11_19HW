package config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:properties/auth.properties"
})
public interface APIConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demowebshop.tricentis.com/")
    String getBaseUrl();

    @Key("email")
    String getEmail();

    @Key("password")
    String getPassword();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("remoteDriverUrl")
    String getRemoteDriverUrl();

}
