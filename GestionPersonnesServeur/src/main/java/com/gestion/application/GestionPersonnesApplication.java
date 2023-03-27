package com.gestion.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.gestion.server.AppServer;
import com.gestion.server.DerbyServer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.InputStream;
import java.util.Properties;

@ComponentScan(basePackages = {"com.gestion"})
@EntityScan(basePackages = {"com.gestion.models"})
@EnableJpaRepositories(basePackages = {"com.gestion.repositories"})
@SpringBootApplication
public class GestionPersonnesApplication extends SpringBootServletInitializer{
    protected static Logger logger = LoggerFactory.getLogger(GestionPersonnesApplication.class);
    protected static String APPLICATION_PROPERTIES_FILE = "application.properties";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GestionPersonnesApplication.class);
        application.setBannerMode(Banner.Mode.OFF);

        application.addListeners(new ApplicationListener<ApplicationStartingEvent>() {
            @Override
            public void onApplicationEvent(ApplicationStartingEvent event) {
                startDbServer();
            }
        });
        application.run(args);
    }

    public static void startDbServer() {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = GestionPersonnesApplication.class.getResourceAsStream("/" + APPLICATION_PROPERTIES_FILE);
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        }

        for (Object keyo : properties.keySet()) {
            String key = (String) keyo;
            if (key.startsWith("gestion.") && System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }

            if (key.startsWith("logging.") && System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        String host = properties.getProperty("gestion.db.server.host");
        if (StringUtils.isBlank(host)) {
            host = "127.0.0.1";
        }

        String dbHome = properties.getProperty("gestion.db.server.home");
        if (StringUtils.isBlank(dbHome)) {
            dbHome = FilenameUtils.concat(System.getProperty("user.dir"), FilenameUtils.concat("database/gestion_db", ""));
        } else {
            if(dbHome.startsWith("~/")){
                dbHome = dbHome.replace("~", System.getProperty("user.dir"));
            }else
            if(dbHome.startsWith("user.dir/")){
                dbHome = dbHome.replace("user.dir", System.getProperty("user.dir"));
            }else
            if(dbHome.startsWith("${user.dir}/")){
                dbHome = dbHome.replace("${user.dir}", System.getProperty("user.dir"));
            }
        }

        String portString = properties.getProperty("gestion.db.server.port");
        int port = 60601;
        if (!StringUtils.isBlank(portString)) {
            try {
                port = Integer.valueOf(portString.trim());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        try {
            System.setProperty("gestion.db.server.port", port+"");
            System.setProperty("gestion.db.server.host", host+"");
            System.setProperty("gestion.db.server.home", dbHome);

            AppServer server = new DerbyServer();
            server.launch();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
