package ir.kit.github.phonebook.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to Kit Github Integration.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */

@ConfigurationProperties(
        prefix = "application",
        ignoreUnknownFields = false
)
public class ApplicationProperties {
    private final ApplicationProperties.Async async = new ApplicationProperties.Async();
    private final ApplicationProperties.Http http = new ApplicationProperties.Http();
    private final ApplicationProperties.Cache cache = new ApplicationProperties.Cache();
    private final ApplicationProperties.Swagger swagger = new ApplicationProperties.Swagger();
    private final ApplicationProperties.Metrics metrics = new ApplicationProperties.Metrics();
    private final ApplicationProperties.GitHub github = new ApplicationProperties.GitHub();
    private final ApplicationProperties.Mongodb mongodb = new ApplicationProperties.Mongodb();
    private final CorsConfiguration cors = new CorsConfiguration();

    public ApplicationProperties() {
    }

    public ApplicationProperties.Async getAsync() {
        return this.async;
    }

    public ApplicationProperties.Http getHttp() {
        return this.http;
    }

    public ApplicationProperties.Cache getCache() {
        return this.cache;
    }
    public ApplicationProperties.Swagger getSwagger() {
        return this.swagger;
    }

    public ApplicationProperties.GitHub getGithub() {
        return this.github;
    }

    public ApplicationProperties.Metrics getMetrics() {
        return this.metrics;
    }

    public ApplicationProperties.Mongodb getMongodb() {
        return this.mongodb;
    }

    public CorsConfiguration getCors() {
        return this.cors;
    }

    public static class Mongodb {

        private String mongoUri = "mongodb://localhost:27017";

        public Mongodb(){

        }

        public String getMongoUri() {
            return mongoUri;
        }

        public void setMongoUri(String mongoUri) {
            this.mongoUri = mongoUri;
        }
    }

    public static class GitHub {

        private String githubUrl = "https://api.github.com";

        public GitHub() {
        }

        public String getGithubUrl() {
            return githubUrl;
        }

        public void setGithubUrl(String githubUrl) {
            this.githubUrl = githubUrl;
        }
    }

    public static class Metrics {
        private final ApplicationProperties.Metrics.Logs logs = new ApplicationProperties.Metrics.Logs();

        public Metrics() {
        }

        public ApplicationProperties.Metrics.Logs getLogs() {
            return this.logs;
        }

        public static class Logs {
            private boolean enabled = false;
            private long reportFrequency = 60L;

            public Logs() {
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getReportFrequency() {
                return this.reportFrequency;
            }

            public void setReportFrequency(long reportFrequency) {
                this.reportFrequency = reportFrequency;
            }
        }
    }

    public static class Swagger {
        private String title = "Application API";
        private String description = "API documentation";
        private String version = "0.0.1";
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
        private String defaultIncludePattern;
        private String host;
        private String[] protocols;
        private boolean useDefaultResponseMessages;

        public Swagger() {
            this.termsOfServiceUrl = ApplicationDefaultProperties.Swagger.termsOfServiceUrl;
            this.contactName = ApplicationDefaultProperties.Swagger.contactName;
            this.contactUrl = ApplicationDefaultProperties.Swagger.contactUrl;
            this.contactEmail = ApplicationDefaultProperties.Swagger.contactEmail;
            this.license = ApplicationDefaultProperties.Swagger.license;
            this.licenseUrl = ApplicationDefaultProperties.Swagger.licenseUrl;
            this.defaultIncludePattern = "/api/.*";
            this.host = ApplicationDefaultProperties.Swagger.host;
            this.protocols = ApplicationDefaultProperties.Swagger.protocols;
            this.useDefaultResponseMessages = true;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return this.termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return this.contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return this.contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return this.contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return this.license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return this.licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getDefaultIncludePattern() {
            return this.defaultIncludePattern;
        }

        public void setDefaultIncludePattern(String defaultIncludePattern) {
            this.defaultIncludePattern = defaultIncludePattern;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String[] getProtocols() {
            return this.protocols;
        }

        public void setProtocols(String[] protocols) {
            this.protocols = protocols;
        }

        public boolean isUseDefaultResponseMessages() {
            return this.useDefaultResponseMessages;
        }

        public void setUseDefaultResponseMessages(boolean useDefaultResponseMessages) {
            this.useDefaultResponseMessages = useDefaultResponseMessages;
        }
    }

    public static class Cache {
        private final ApplicationProperties.Cache.Ehcache ehcache = new ApplicationProperties.Cache.Ehcache();

        public Cache() {
        }

        public ApplicationProperties.Cache.Ehcache getEhcache() {
            return this.ehcache;
        }

        public static class Ehcache {
            private int timeToLiveSeconds = 3600;
            private long maxEntries = 100L;

            public Ehcache() {
            }

            public int getTimeToLiveSeconds() {
                return this.timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public long getMaxEntries() {
                return this.maxEntries;
            }

            public void setMaxEntries(long maxEntries) {
                this.maxEntries = maxEntries;
            }
        }


    }

    public static class Http {
        private final ApplicationProperties.Http.Cache cache = new ApplicationProperties.Http.Cache();

        public Http() {
        }

        public ApplicationProperties.Http.Cache getCache() {
            return this.cache;
        }

        public static class Cache {
            private int timeToLiveInDays = 1461;

            public Cache() {
            }

            public int getTimeToLiveInDays() {
                return this.timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Async {
        private int corePoolSize = 2;
        private int maxPoolSize = 50;
        private int queueCapacity = 10000;

        public Async() {
        }

        public int getCorePoolSize() {
            return this.corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return this.maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return this.queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }
}

