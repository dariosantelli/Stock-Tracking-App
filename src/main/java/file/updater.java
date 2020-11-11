package file;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class updater {
	
	static int val = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                //System.out.println(new Date());
                mainone f = new mainone();
                val = f.printer(val);
                
                //launcher gg = new launcher();
                
            }
        }, 0, 3, TimeUnit.SECONDS);
	}

}
