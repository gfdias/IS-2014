package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
public class StartupSingleton {

    public StartupSingleton() {
    }
    @PostConstruct
    public void initialize() {
    	System.out.println("\033[1;32m Service Started");
        System.out.print("\033[0m");
    }
    
    @PreDestroy
    public void terminate() {
        System.out.println("Shut down in progress");
    }
 }
