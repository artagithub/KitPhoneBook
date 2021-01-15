package ir.kit.github.phonebook.config;

public interface ApplicationDefaultProperties {

    public interface Metrics {
        public interface Prometheus {
            boolean enabled = false;
            String endpoint = "/maintenance";
        }

        public interface Logs {
            boolean enabled = false;
            long reportFrequency = 60L;
        }

        public interface Jmx {
            boolean enabled = false;
        }
    }

    public interface Swagger {
        String title = "Application API";
        String description = "API documentation";
        String version = "0.0.1";
        String termsOfServiceUrl = null;
        String contactName = null;
        String contactUrl = null;
        String contactEmail = null;
        String license = null;
        String licenseUrl = null;
        String defaultIncludePattern = "/api/.*";
        String host = null;
        String[] protocols = new String[0];
        boolean useDefaultResponseMessages = true;
    }

    public interface Http {
        public interface Cache {
            int timeToLiveInDays = 1461;
        }
    }

    public interface Async {
        int corePoolSize = 2;
        int maxPoolSize = 50;
        int queueCapacity = 10000;
    }
}
